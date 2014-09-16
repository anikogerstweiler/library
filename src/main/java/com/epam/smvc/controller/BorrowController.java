package com.epam.smvc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
			@RequestParam(defaultValue="available") String available, final Model model) throws ParseException {
		
		setActualDate(locale, model);
		model.addAttribute("book", bookService.find(id));
		model.addAttribute("loanForm", new LoanForm());
		
		model.addAttribute("bookAvailable", bookAvailable);
		model.addAttribute("available", available);
		
		String actualDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());
		model.addAttribute("date", actualDate);
		
		return "loan";
	}

	@RequestMapping(value = "/loan", method = RequestMethod.POST)
	public String borrow(@ModelAttribute("loanForm") LoanForm loanForm, final Model model) {
		HiredBook book = createHiredBook(loanForm);
		
		String available = hiredBookService.save(book);
		
		if (!"available".equals(available)) {
			return "redirect:/loan?id=" + loanForm.getId() + "&bookAvailable=false" + "&available=" + available;
		}
		
		return "redirect:/books";
	}
	
	@RequestMapping(value = "/loanedbooks", method = RequestMethod.GET)
	public String listLoanedBooks(final Locale locale, final Model model) {
		setActualDate(locale, model);
		
		model.addAttribute("mybooks", hiredBookService.listBooksByUser(SecurityContextHolder.getContext().getAuthentication().getName()));
		
		return "loanedbooks";
	}
	
	private HiredBook createHiredBook(LoanForm loanForm) {
		HiredBook book = new HiredBook();
		
		Date borrowable = buildDate(loanForm.getFromDate());
		book.setBookid(loanForm.getId());
		book.setFromdate(borrowable);
		book.setTodate(calculateEndDate(borrowable));
		book.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		return book;
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
		String actualDate = new SimpleDateFormat("EEE MMM d, yyyy", Locale.ENGLISH).format(new Date());
		
		return actualDate;
	}
}
