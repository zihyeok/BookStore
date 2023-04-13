package com.book.store.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.book.store.dto.BookDTO;
import com.book.store.dto.ReviewDTO;
import com.book.store.service.BookItemService;
import com.book.store.service.ReviewService;
import com.book.store.service.UserService;
import com.book.store.user.UserData;

@Controller
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	private BookItemService bookItemService;

	@GetMapping("/item/{seq_No}")
	public ModelAndView viewItem(@PathVariable int seq_No) throws Exception {
	    ModelAndView mav = new ModelAndView();

	    // 상품 정보 조회
	    BookDTO bookDTO = bookItemService.getReadData(seq_No);
	    mav.addObject("bookDTO", bookDTO);

	    // 댓글 목록 조회
	    List<ReviewDTO> reviewList = reviewService.selectReviewBySeqNo(seq_No);
	    mav.addObject("reviewList", reviewList);

	    mav.setViewName("item");

	    return mav;
	}

	@PostMapping("/item/{seq_No}/review")
	public ModelAndView addReview(HttpServletRequest request, @PathVariable int seq_No, @ModelAttribute ReviewDTO review) {
	    ModelAndView mav = new ModelAndView();

	    UserData user = (UserData) request.getSession().getAttribute("user");
	    if (user == null) {
	        mav.setViewName("redirect:/login");
	        return mav;
	    }

	    String ipAddr = request.getHeader("X-FORWARDED-FOR");
	    if (ipAddr == null) {
	        ipAddr = request.getRemoteAddr();
	    }

	    review.setSeq_No(seq_No);
	    review.setUserName(user.getUserName());
	    review.setIpAddr(ipAddr);
	    reviewService.insertReview(review);

	    mav.setViewName("redirect:/item/" + seq_No);

	    return mav;
	}
}
    