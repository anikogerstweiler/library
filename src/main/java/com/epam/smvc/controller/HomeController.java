package com.epam.smvc.controller;

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
	
}
