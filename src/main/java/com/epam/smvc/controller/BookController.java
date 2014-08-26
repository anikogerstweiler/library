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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.smvc.form.AddBookForm;
import com.epam.smvc.form.LoanForm;
import com.epam.smvc.model.Book;
import com.epam.smvc.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String listBooks(final Locale locale, final Model model) {
		Date date = new Date();
		
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT, locale);
		
		String today = dateFormat.format(date);
		
		model.addAttribute("today", today);
		
		model.addAttribute("books", bookService.getBooks());
		
		return "books";
	}
	
	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String createForm(final Locale locale, final Model model) {
		model.addAttribute("addBookForm", new AddBookForm());
		
		return "addbook";
	}
	
	@RequestMapping(value = "/addbook", method = RequestMethod.POST)
	public String addBook(@Valid @ModelAttribute("addBookForm") AddBookForm bookForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "addbook";
		}
		
		Book book = new Book();
		book.setAuthor(bookForm.getAuthor());
		book.setDescription(bookForm.getDescription());
		book.setIsbn(bookForm.getIsbn());
		book.setTitle(bookForm.getTitle());
		book.setYear(bookForm.getYear());
		
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
}
