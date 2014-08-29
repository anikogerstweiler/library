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
		user.setUsername(userForm.getUsername());
		user.setFirstname(userForm.getFirstName());
		user.setLastname(userForm.getLastName());
		user.setPassword(encoder.encode(userForm.getPwd()));
		user.setEnabled(true);
		
		userService.save(user);
		
		Authority authority = new Authority();
		authority.setUsername(userForm.getUsername());
		authority.setAuthority("ROLE_USER");
		
		userService.save(authority);
		
		return "redirect:/" + "login";
	}
	
	@RequestMapping(value="/error", method = RequestMethod.GET)
	public String error() {
		return "error";
	}
	
	private String getDate(final Locale locale) {
		String actualDate = new SimpleDateFormat("EEE MMM d, yyyy", Locale.ENGLISH).format(new Date());
		
		return actualDate;
	}
}
