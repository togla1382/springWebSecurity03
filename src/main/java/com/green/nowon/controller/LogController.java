package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogController {
	
	@GetMapping("/signin")
	public String signin() {
		
		return "/sign/signin";
	}

}
