package com.book.store.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.book.store.service.UserService;
import com.book.store.user.UserData;

@Controller
public class UserController {
	
	@Resource
	private UserService userService;

	@GetMapping("/user/loginpage")
	public String loginpage() throws Exception{
		
		return "minsungTest";
	}
	
	@PostMapping("/user/loginpage")
	public String logingo(UserData userData) throws Exception{
		
		userService.insertData(userData);
		
		
		return "redirect:main.html";
	}
}
