package com.mitrais.registration.controller;

import java.text.DateFormatSymbols;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mitrais.registration.model.Customer;

@Controller
public class MainController {

	@GetMapping(value="/")
	public String register(Model model) {
		
		model = getYearMonthDate(model);
		model.addAttribute("customer", new Customer());
		
		return "register";
	}
	
	@GetMapping(value="/login")
	public String login(Model model) {
		
		return "login";
	}
	
	private Model getYearMonthDate(Model model) {
		
		model.addAttribute("months", new DateFormatSymbols().getMonths());
		Integer[] dates = new Integer[31];
		for (int i = 0; i < dates.length; i++) {
			dates[i] = i+1;
		}
		model.addAttribute("dates", dates);
		
		int year = 2020;
		Integer[] years = new Integer[50];
		for (int i = 0; i < years.length; i++) {
			years[i] = year-i;
		}
		model.addAttribute("years", years);
		
		return model;
	}
	
}
