package com.book.store.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.store.dto.BagDTO;
import com.book.store.service.BagService;
import com.book.store.service.BagServiceImpl;
import com.book.store.user.UserData;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BagController {

	private final HttpSession httpSession;
	
	@Resource
	BagService bagService = new BagServiceImpl();
	
	@GetMapping("/selectItem.action")
	public ModelAndView selectItem(HttpServletRequest request,BagDTO dto) throws Exception{

		UserData user = null;
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		String pageNum = request.getParameter("pageNum");
		int seq_No = Integer.parseInt(request.getParameter("seq_No"));
		
		if(httpSession.getAttribute("user")!="") {
			
			user = (UserData) httpSession.getAttribute("user");
			
		}else if(httpSession.getAttribute("OauthUser")!="") {
			
			user = (UserData) httpSession.getAttribute("OauthUser");
			
		}else {
			
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName("redirect:/user/loginpage");
			
			return mav;
			
		}
		
		dto.setBagId(bagService.maxNum()+1);
		dto.setSeq_No(seq_No);
		
		System.out.println(user.getUserId());
		dto.setUserId(user.getUserId());
		
		ModelAndView mav = new ModelAndView("jsonView");
		//ajax로 가져와서 userid의 seq_no를 조회후 해당 seq_no가 있으면 찜 버튼에 불이 들어와있게 설정해야됨
		
		return mav;
		
	}
	
}
