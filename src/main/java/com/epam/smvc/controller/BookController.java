package com.epam.smvc.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.smvc.model.Book;
import com.epam.smvc.service.BookService;

@Controller
public class BookController {
	
	private static final String ACTUAL_DATE_FORMAT = "EEE MMM d, yyyy";
	private static final String END_TAG = ">";
	private static final String START_TAG = "<";
	private static final int MAX_LENGTH = 35;
	
	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String listBooks(final Locale locale, final Model model) {
		setActualDate(locale, model);
		
		model.addAttribute("books", bookService.getBooks());
		
		return "books";
	}
	
	@RequestMapping(value = "/maintainbook", method = RequestMethod.GET)
	public String removeBook(final Locale locale, final Model model) {
		setActualDate(locale, model);
		
		List<Book> books = bookService.getBooks();
		
		setShortNamesFor(books);
		
		escapeWhitespaceFromBookDescription(books);
		
		model.addAttribute("books", books);
		
		return "maintainbook";
	}

	@RequestMapping(value = "/maintainbook/{id}", method = RequestMethod.GET)
	public String removeBookById(@PathVariable Long id, final Locale locale, final Model model) {
		bookService.removeBookById(id);
		
		return "redirect:/maintainbook";
	}
	
	@RequestMapping(value = "/updatebook", method = RequestMethod.GET)
	public String preInitUpdate(@RequestParam Long id, final Locale locale, final Model model) {
		setActualDate(locale, model);
		Book book = bookService.find(id);
		model.addAttribute("book", book);
		
		return "updatebook";
	}
	
	@RequestMapping(value = "/updatebook", method = RequestMethod.POST)
	public String updateBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, final Model model, final Locale locale) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("status", bindingResult);
			setActualDate(locale, model);
			
			return "updatebook";
		}
		
		bookService.updateBook(escapeInputIn(book));
		
		return "redirect:/maintainbook";
	}
	
	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String preInitAdd(final Locale locale, final Model model) {
		setActualDate(locale, model);
		
		model.addAttribute("book", new Book());
		
		return "addbook";
	}
	
	@RequestMapping(value = "/addbook", method = RequestMethod.POST)
	public String addNewBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, final Model model, final Locale locale) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("status", bindingResult);
			setActualDate(locale, model);
			
			return "addbook";
		}
		
		bookService.save(escapeInputIn(book));
		
		return "redirect:/maintainbook";
	}

	@RequestMapping(value = "/bookAJAX", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String loadBooks() {
		List<Book> books = bookService.getBooks();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String result = "";
		try {
			result = mapper.writeValueAsString(books);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private void setShortNamesFor(List<Book> books) {
		for (Book book : books) {
			book.setShortTitle(getShortName(book.getTitle()));
			book.setShortAuthor(getShortName(book.getAuthor()));
		}
	}
	
	private String getShortName(String input) {
		  String shortName = input;
		  
		  if(input.length() > MAX_LENGTH){
		   shortName = input.substring(0, MAX_LENGTH - 4) + "...";
		  }
		  
		  return shortName;
		 }
	
	private void escapeWhitespaceFromBookDescription(List<Book> books) {
		for (Book book : books) {
			book.setDescription(book.getDescription().replaceAll("\n", " "));
			book.setDescription(book.getDescription().replaceAll("\r", ""));
			book.setDescription(book.getDescription().replaceAll("'", "\\'"));
			book.setDescription(book.getDescription().replaceAll("/", "\\/"));
		}
	}
	
	private Book escapeInputIn(Book book) {
		book.setAuthor(escapeInvalidCharacters(book.getAuthor()));
		book.setDescription(escapeInvalidCharacters(book.getDescription()));
		book.setIsbn(escapeInvalidCharacters(book.getIsbn()));
		book.setTitle(escapeInvalidCharacters(book.getTitle()));
		
		return book;
	}
	
	private String escapeInvalidCharacters(String input) {
		return input.replaceAll(START_TAG, "&lt;").replaceAll(END_TAG, "&gt;");
	}

	private String getDate(final Locale locale) {
		String actualDate = new SimpleDateFormat(ACTUAL_DATE_FORMAT, Locale.ENGLISH).format(new Date());
		
		return actualDate;
	}
	
	private void setActualDate(final Locale locale, final Model model) {
		String today = getDate(locale);
		model.addAttribute("today", today);
	}
}