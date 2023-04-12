package com.book.store.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.store.service.BookItemService;
import com.book.store.service.ReviewService;
import com.book.store.service.UserService;
import com.book.store.util.MyUtil;

@RestController
public class ReviewController {

	@Resource
	private ReviewService reviewService; //reviewServiceImpl 호출
	
	@Resource
	private BookItemService bookItemService;
	
	@Resource
	private UserService userService;

	@Autowired
	MyUtil myUtil;
	
	@GetMapping("/review")
	public ModelAndView index() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("review");
		
		return mav;
	}
	
	@GetMapping("/reviewCreated")
	public ModelAndView created() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("reviewCreated");
		
		return mav;
	}
	
	

	
	
	
	
	
}
