package com.book.store.controller;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.store.dto.ReviewDTO;
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
	

	@PostMapping("/ReviewCreated.action")
	public ModelAndView created(HttpServletRequest request,ReviewDTO dto) throws Exception{
		
		int maxNum = reviewService.maxNum();
		
		dto.setReviewId(maxNum+1);
		dto.setIpAddr(request.getRemoteAddr());
		
		
		
		reviewService.insertData(dto);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("dto", dto);
		mav.setViewName("redirect:/BookArticle.action?seq_No=630&pageNum=1");
		
		return mav;
	}
	
	@PostMapping("/ReviewList.action")
	public ModelAndView list(HttpServletRequest request,ReviewDTO dto) throws Exception{

		int seq_No = dto.getSeq_No();
		
		String pageNum = request.getParameter("pageNum");

		int currentPage = 1; //첫화면은 1페이지 

		if(pageNum!=null) {

			currentPage = Integer.parseInt(pageNum);
			//1페이지가 아닌 get방식 주소로 받은 pageNum로 변경
		}
		
		int dataCount = reviewService.getDataCount(seq_No);
		
		int numPerPage = 5;
		
		int totalPage = myUtil.getPageCount(numPerPage, dataCount);
		
		if(currentPage>totalPage) {
			currentPage=totalPage;
		}

		int start = (currentPage-1)*numPerPage+1;
		int end = currentPage*numPerPage;

		List<ReviewDTO> lists = reviewService.getLists(start, end, seq_No);
		
		String reviewUrl = "/ReviewList.action?seq_No=" + seq_No + "&pageNum=" + currentPage;
		
		String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, reviewUrl);
		
		ModelAndView mav = new ModelAndView("jsonView");

		mav.addObject("lists", lists);
		mav.addObject("pageNum", currentPage);
		mav.addObject("pageIndexList", pageIndexList);
		
		mav.setViewName("commentList");

		return mav;
	}


}
