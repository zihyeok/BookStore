package com.book.store.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.store.util.MyUtil;

import lombok.RequiredArgsConstructor;

@RestController
public class ReviewController {
	
	@Resource
	private final ReviewService reviewService;
	
	@Autowired
	MyUtil myUtil;
	
	@GetMapping("/")
	public ModelAndView index() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("review");
		
		return mav;
	}

}
