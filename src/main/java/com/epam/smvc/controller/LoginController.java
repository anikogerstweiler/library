package com.epam.smvc.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(final Model model) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("1234"));
		
	    return "login";
	}
	
    @RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
    public String loginFailed(ModelMap model) {

        model.addAttribute("error", true);
        return "login";
    }
}
