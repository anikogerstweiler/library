package com.epam.smvc.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(final Model model) {

		return "redirect:" + "/books";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(final Model model) {
		
		return "register";
	}
	
	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String accessdenied(final Model model) {
		Authentication user = SecurityContextHolder.getContext().getAuthentication();
		if (user != null) {
			model.addAttribute("msg", user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addAttribute("msg", "You do not have permission to access this page!");
		}
		return "accessdenied";
	}
	
}
