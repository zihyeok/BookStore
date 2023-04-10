package com.book.store.ajax;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.book.store.dto.BookDTO;
import com.book.store.service.BookItemService;
import com.book.store.service.BookItemServiceImpl;


@RestController
public class SearchFormAjax {
	
	@Resource
	BookItemService bookItemService = new BookItemServiceImpl();
	
	@PostMapping("/SuggestClient.action")
	public ModelAndView suggestClient(HttpServletRequest request) throws Exception{
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
	
		List<BookDTO>lists =bookItemService.getLists(1,5, searchKey, searchValue);
		
		ModelAndView mav = new ModelAndView("jsonView");
		//securityConfig.java에 configuration이 있어서 @bean으로  MappingJackson2JsonView을 추가함
		// MappingJackson2JsonView은 modelandview를 json형태로 변환해줌
		mav.addObject("lists", lists);
		
		return mav;
		
	}

}
