package com.epam.smvc.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

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
	
	@RequestMapping(value = "/bookAJAX", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String loadBooks() {
		System.out.println("Heyho");
		List<Book> books = bookService.getBooks();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String result = "";
		try {
			result = mapper.writeValueAsString(books);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("result: " + result);
		
		return result;
	}
}
