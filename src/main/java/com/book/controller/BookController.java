package com.book.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.service.BookItemService;
import com.book.util.MyUtil;

@RestController
public class BookController {
	
	@RequestMapping(value = "/")
	public ModelAndView Home() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("Main");
		
		return mav;
		
	}
	
	@GetMapping("created/action")
	public ModelAndView ItemCreated() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("");
		
		return mav;
		
	}
	
}
