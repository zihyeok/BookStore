package com.book.store.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.store.service.UserService;
import com.book.store.user.UserData;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final HttpSession httpSession;

	@GetMapping("/user/loginpage")
	public ModelAndView loginpage() throws Exception{
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("minsungTest");
		
		return mav;
	}
	
	@PostMapping("/user/loginpage")
	public String logingo(UserData userData) throws Exception{
		
		
		userData.setRealPwd(userData.getUserPwd());
		userData.setUserPwd(passwordEncoder.encode(userData.getUserPwd()));
		
		
		userService.insertData(userData);
		
		
		return "success";
	}
	
	@GetMapping("/user/login")
	public ModelAndView userlogin() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("minsungTest2");
		
		return mav;
		
	}
	
	@GetMapping("/hi")
	public ModelAndView test() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("minsungTest3");
		
		UserData user = (UserData) httpSession.getAttribute("user");
		UserData OauthUser = (UserData) httpSession.getAttribute("OauthUser");
		
		if(user!=null) {
			mav.addObject("userId", user.getUserId());
			mav.addObject("userPwd", user.getUserPwd());
			mav.addObject("userEmail", user.getUserEmail());
			mav.addObject("realPwd", user.getRealPwd());
			
		}

		if(OauthUser!=null) {
			mav.addObject("oauthId", OauthUser.getUserId());
			mav.addObject("oauthName", OauthUser.getUserName());
		
			mav.setViewName("minsungTest");
			return mav;
		}
		
		return mav;
	}
	
	
	
	
	
	
	
}
