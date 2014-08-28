package com.epam.smvc.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.smvc.form.LoanForm;
import com.epam.smvc.model.HiredBook;
import com.epam.smvc.service.BookService;
import com.epam.smvc.service.HiredBookService;

@Controller
public class BorrowController {

	@Autowired
	private HiredBookService hiredBookService;
	
	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/loan", method = RequestMethod.GET)
	public String listBooks(final Locale locale, @RequestParam Long id, @RequestParam(defaultValue="true") boolean bookAvailable, 
			@RequestParam(defaultValue="") String notAvailableTill, final Model model) throws ParseException {
		setActualDate(locale, model);
		model.addAttribute("book", bookService.find(id));
		model.addAttribute("loanForm", new LoanForm());
		
		model.addAttribute("bookAvailable", bookAvailable);
		model.addAttribute("notAvailableTill", notAvailableTill);
		
		return "loan";
	}

	@RequestMapping(value = "/loan", method = RequestMethod.POST)
	public String borrow(@ModelAttribute("loanForm") LoanForm loanForm, final Model model) {
		HiredBook book = new HiredBook();
		book.setBookid(loanForm.getId());
		Date loanFrom = buildDate(loanForm.getFromDate());
		book.setFromdate(loanFrom);
		book.setTodate(calculateEndDate(loanFrom));
		book.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		String returnPage = "books";
		String notAvailableTill = hiredBookService.save(book);
		if (notAvailableTill != "") {
			returnPage = "redirect:loan?id=" + loanForm.getId() + "&bookAvailable=false" + "&notAvailableTill=" + notAvailableTill;
		}
		
		
		return returnPage;
	}
	
	@RequestMapping(value = "/loanedbooks", method = RequestMethod.GET)
	public String listLoanedBooks(final Locale locale, final Model model) {
		setActualDate(locale, model);
		
		model.addAttribute("mybooks", hiredBookService.listBooksByUser(SecurityContextHolder.getContext().getAuthentication().getName()));
		
		return "loanedbooks";
	}

	private Date buildDate(String date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setLenient(false);
		
		String[] splittedDate = date.split("-");
		calendar.set(GregorianCalendar.YEAR, Integer.valueOf(splittedDate[0]));
		calendar.set(GregorianCalendar.MONTH, Integer.valueOf(splittedDate[1]) - 1);
		calendar.set(GregorianCalendar.DATE, Integer.valueOf(splittedDate[2]));
		
		return calendar.getTime();
	}
	
	private Date calculateEndDate(Date startDate) {
		Calendar calendar = new GregorianCalendar();
		calendar.setLenient(false);
		calendar.setTime(startDate);
		calendar.add(GregorianCalendar.MONTH, 1);
		
		return calendar.getTime();
	}
	
	private void setActualDate(final Locale locale, final Model model) {
		String today = getDate(locale);
		model.addAttribute("today", today);
	}
	
	private String getDate(final Locale locale) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT, locale);
		String today = dateFormat.format(date);
		
		return today;
	}
}
