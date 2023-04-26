package com.book.store.ajax;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.store.dto.ReviewDTO;
import com.book.store.service.ReviewService;
import com.book.store.user.UserData;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserReviewAjax {
	
	private final HttpSession httpSession;
	
	@Resource
	private ReviewService reviewService;
	
	@RequestMapping("userReviewSub.action")
	public ModelAndView userReviewSub(HttpServletRequest request) throws Exception{
		
		int count = Integer.parseInt(request.getParameter("count"));
		int numPerPage = Integer.parseInt(request.getParameter("numPerPage"));

		System.out.println(count);
		
		UserData user = null;

		if(httpSession.getAttribute("user")!=null) {

			user = (UserData) httpSession.getAttribute("user");


		}else if(httpSession.getAttribute("OauthUser")!=null) {

			user = (UserData) httpSession.getAttribute("OauthUser");

		}
		
		String userId = user.getUserId();
		
		int start = (count-1)*numPerPage+1;
		int end = count*numPerPage;

		List<ReviewDTO> lists = reviewService.userReview(start, end, userId);
		
		ModelAndView mav = new ModelAndView();

		mav.addObject("lists", lists);

		mav.setViewName("userReviewSub");

		return mav;
	}

}
