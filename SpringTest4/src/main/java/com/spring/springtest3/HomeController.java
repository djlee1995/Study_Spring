package com.spring.springtest3;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/form_input.me", method = RequestMethod.GET)
	public String form_input() {		
		return "form_input";
	}
	
	@RequestMapping(value = "/input_form.me", method = RequestMethod.POST)
	public String input_form(memberVO memberVO, Model model) {		
		
		model.addAttribute("memberVO", memberVO);
		return "input_form";
	}
	@RequestMapping(value = "/career.me", method = RequestMethod.GET)
	public String career() {		
		return "career";
	}
	@RequestMapping(value = "/register.me", method = RequestMethod.GET)
	public String register() {		
		return "register";
	}
}
