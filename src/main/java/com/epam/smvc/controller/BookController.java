package com.epam.smvc.controller;

import java.io.IOException;
import java.text.DateFormat;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.smvc.form.AddBookForm;
import com.epam.smvc.model.Book;
import com.epam.smvc.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String listBooks(final Locale locale, final Model model) {
		setActualDate(locale, model);
		
		model.addAttribute("books", bookService.getBooks());
		
		return "books";
	}
	
	@RequestMapping(value = "/removebook", method = RequestMethod.GET)
	public String removeBook(final Locale locale, final Model model) {
		setActualDate(locale, model);
		
		model.addAttribute("books", bookService.getBooks());
		
		return "removebook";
	}
	
	@RequestMapping(value = "/removebook/{id}", method = RequestMethod.GET)
	public String removeBookById(@PathVariable Long id, final Locale locale, final Model model) {
		setActualDate(locale, model);
		
		bookService.removeBookById(id);
		
		return "redirect:/removebook";
	}
	
	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String createForm(final Locale locale, final Model model) {
		setActualDate(locale, model);
		
		model.addAttribute("addBookForm", new AddBookForm());
		
		return "addbook";
	}
	
	@RequestMapping(value = "/addbook", method = RequestMethod.POST)
	public String addBook(@Valid @ModelAttribute("addBookForm") AddBookForm bookForm, BindingResult bindingResult, final Model model, final Locale locale) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("status", bindingResult);
			setActualDate(locale, model);
			
			return "addbook";
		}
		
		Book book = createBook(bookForm);
		
		bookService.save(book);
		
		return "books";
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
	
	private Book createBook(AddBookForm bookForm) {
		Book book = new Book();
		
		book.setAuthor(bookForm.getAuthor());
		book.setDescription(bookForm.getDescription());
		book.setIsbn(bookForm.getIsbn());
		book.setTitle(bookForm.getTitle());
		book.setYear(bookForm.getYear());
		
		return book;
	}
	
	private String getDate(final Locale locale) {
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT, locale);
		String today = dateFormat.format(new Date());
		
		return today;
	}
	
	private void setActualDate(final Locale locale, final Model model) {
		String today = getDate(locale);
		model.addAttribute("today", today);
	}
}
