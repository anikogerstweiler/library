package com.epam.smvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.smvc.form.UserForm;
import com.epam.smvc.model.Authority;
import com.epam.smvc.model.User;
import com.epam.smvc.service.UserService;

@Controller
public class HomeController {
	
	private static final String ACTUAL_DATE_FORMAT = "EEE MMM d, yyyy";
	private static final String END_TAG = ">";
	private static final String START_TAG = "<";
	private static final String ROLE_USER = "ROLE_USER";
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(final Model model) {

		return "redirect:" + "/books";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String loadRegistrationForm(final Locale locale, final Model model) {
		String today = getDate(locale);
		model.addAttribute("today", today);
		
		model.addAttribute("userForm", new UserForm());
		
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult, final Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("status", bindingResult);
			return "register";
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		User user = new User();
		user.setUsername(userForm.getUsername().replaceAll(START_TAG, "&lt;").replaceAll(END_TAG, "&gt;"));
		user.setFirstname(userForm.getFirstName().replaceAll(START_TAG, "&lt;").replaceAll(END_TAG, "&gt;"));
		user.setLastname(userForm.getLastName().replaceAll(START_TAG, "&lt;").replaceAll(END_TAG, "&gt;"));
		user.setPassword(encoder.encode(userForm.getPwd()));
		user.setEnabled(true);
		
		userService.save(user);
		
		Authority authority = new Authority();
		authority.setUsername(userForm.getUsername().replaceAll(START_TAG, "&lt;").replaceAll(END_TAG, "&gt;"));
		authority.setAuthority(ROLE_USER);
		
		userService.save(authority);
		
		return "redirect:/" + "login";
	}
	
	@RequestMapping(value="/error", method = RequestMethod.GET)
	public String error() {
		return "error";
	}
	
	private String getDate(final Locale locale) {
		String actualDate = new SimpleDateFormat(ACTUAL_DATE_FORMAT, Locale.ENGLISH).format(new Date());
		
		return actualDate;
	}
}
