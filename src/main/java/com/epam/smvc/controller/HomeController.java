package com.epam.smvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

		if ("admin".equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
			return "redirect:" + "/maintainbook";
		}
		return "redirect:" + "/books";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String loadRegistrationForm(final Locale locale, final Model model) {
		String today = getDate(locale);
		model.addAttribute("today", today);
		
		model.addAttribute("user", new User());
		
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, final Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("status", bindingResult);
			return "register";
		}
		
		setUserProperties(user);
		userService.save(user);
		
		Authority authority = setAuthorityProperties(user);
		userService.save(authority);
		
		return "redirect:/" + "login";
	}

	@RequestMapping(value="/error", method = RequestMethod.GET)
	public String error() {
		return "error";
	}
	
	private Authority setAuthorityProperties(User user) {
		Authority authority = new Authority();
		authority.setUsername(escapeInvalidCharacters(user.getUsername()));
		authority.setAuthority(ROLE_USER);
		
		return authority;
	}

	private void setUserProperties(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();	
		user.setPassword(encoder.encode(escapeInvalidCharacters(user.getPassword())));
		user.setEnabled(true);
	}
	
	private String escapeInvalidCharacters(String input) {
		return input.replaceAll(START_TAG, "&lt;").replaceAll(END_TAG, "&gt;");
	}
	
	private String getDate(final Locale locale) {
		String actualDate = new SimpleDateFormat(ACTUAL_DATE_FORMAT, Locale.ENGLISH).format(new Date());
		
		return actualDate;
	}
}
