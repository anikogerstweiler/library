package com.epam.smvc.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.smvc.form.BorrowForm;
import com.epam.smvc.model.HiredBook;
import com.epam.smvc.service.BookService;
import com.epam.smvc.service.HiredBookService;

@Controller
public class BorrowController {

	@Autowired
	private HiredBookService hiredBookService;
	
	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/borrow", method = RequestMethod.GET)
	public String listBooks(final Locale locale, Long id, final Model model) {
		
		model.addAttribute("book", bookService.find(id));

		return "borrow";
	}

	@RequestMapping(value = "/borrow", method = RequestMethod.POST, params = "step=done")
	public String borrowSuccess(final BorrowForm borrowForm, final Model model) {
		HiredBook book = new HiredBook();
		book.setBookid(borrowForm.getId());
		book.setFromdate(borrowForm.getFromDate());
		book.setTodate(borrowForm.getToDate());
		book.setUsername(borrowForm.getUser().getUsername());
		
		hiredBookService.save(book);
		
		return "borrow";
	}
}
