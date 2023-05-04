package com.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Navigate {
	
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
		
	@GetMapping("/dashboard")
	public String dashboardPage() {
		return "dashboard";
	}
	
	@GetMapping("/newpost")
	public String newpostPage() {
		return "newpost";
	}
	
	@GetMapping("/comment")
	public String commentPage() {
		return "comment";
	}
	
	@GetMapping("/logout")
	public String logoutPage() {
		return "index";
	}

	
}
