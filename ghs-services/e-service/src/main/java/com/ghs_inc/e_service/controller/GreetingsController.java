package com.ghs_inc.e_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GreetingsController {
	
	@GetMapping
	public String greetings() {
		return "Welcome to E-Service i-e: Employee Service";
	}

}
