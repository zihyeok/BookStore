package com.book.store.controller;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.store.dto.BagDTO;
import com.book.store.dto.BookDTO;
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
	
	@GetMapping("/bag.action")
	public ModelAndView goBag(HttpServletRequest request) throws Exception{
		
		UserData user = null;

		if(httpSession.getAttribute("user")!=null) {

			user = (UserData) httpSession.getAttribute("user");


		}else if(httpSession.getAttribute("OauthUser")!=null) {
			System.out.println("sendbag의 oauth");
			user = (UserData) httpSession.getAttribute("OauthUser");

		}
		
		if(user==null) {

			ModelAndView mav = new ModelAndView();
			
			mav.setViewName("redirect:/user/login");

			return mav;

		}
		
		String userId = user.getUserId();
		
		List<BookDTO> lists = bagService.getLists(userId);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("userId", user.getUserId());
		mav.addObject("lists", lists);
		
		mav.setViewName("basket");
		
		return mav;
		
	}

	@RequestMapping("/insertItem.action")
	public ModelAndView insertItem(HttpServletRequest request,BagDTO dto) throws Exception{
				//여기에 request 말고도 매개변수로 bagdto dto를 받게되면 ajax로 넘어올때 bagdto정보도 같이 넘어옴
		
		UserData user = null;

		int seq_No = Integer.parseInt(request.getParameter("seq_No"));

		if(httpSession.getAttribute("user")!=null) {
			
			user = (UserData) httpSession.getAttribute("user");


		}else if(httpSession.getAttribute("OauthUser")!=null) {
			
			user = (UserData) httpSession.getAttribute("OauthUser");

		}
		
		if(user==null) {
		//else if는 안되고 if는 됨
			ModelAndView mav = new ModelAndView();
			System.out.println("값 없음");
			mav.setViewName("redirect:/user/login");

			return mav;

		}
		
		String userId = user.getUserId();
		
		dto.setBagId(bagService.maxNum()+1);
		dto.setSeq_No(seq_No);
		dto.setUserId(userId);

		bagService.insertData(dto);
		
		List<BookDTO> lists = bagService.getLists(userId);
		
		ModelAndView mav = new ModelAndView("jsonView");
		//ajax로 가져와서 userid의 seq_no를 조회후 해당 seq_no가 있으면 찜 버튼에 불이 들어와있게 설정해야됨
		//getLists 처음에는 하위쿼리로 실행하려했으나 하위쿼리로 가져오는 값이 2개이상이 되어서 에러가남
		//그래서 inner join사용함
		mav.addObject("lists", lists);
		//ajax로 booklist.html로 lists 쏠거임
		return mav;		

	}
	
	@RequestMapping("/selectItem.action")
	public ModelAndView allItem(HttpServletRequest request) throws Exception{
		
		UserData user = null;

		if(httpSession.getAttribute("user")!=null) {

			user = (UserData) httpSession.getAttribute("user");


		}else if(httpSession.getAttribute("OauthUser")!=null) {

			user = (UserData) httpSession.getAttribute("OauthUser");

		}
		
		if(user==null) {
			
			ModelAndView mav = new ModelAndView();
			
			//json계속 보내주는데 고쳐야됨 진행되는데 문제는 없음
			//->해결함 BookList.html의 selectAll js함수를 session.user 값이 있을때만 실행시키면 해결
			mav.setViewName("redirect:/user/login");

			return mav;

		}
		
		String userId = user.getUserId();

		List<BookDTO> lists = bagService.getLists(userId);
		
		ModelAndView mav = new ModelAndView("jsonView");
		
		mav.addObject("lists", lists);
		
		return mav;		
		
	}
	

	@RequestMapping("/deleteItem.action")
	public ModelAndView deleteItem(HttpServletRequest request,BagDTO dto) throws Exception{
		//BagDTO를 매개변수로 받게되면 AJAX에서 params을 이곳으로 보낼때 params에 담긴 값이
		//BagDTO에 담긴다 그래서 이 상태로 되돌려 보내면 
		//JSON에 {"bagDTO":{"bagId":0,"seq_No":11505,"userId":null},"lists":[]} 이런 형태로 뜨게 됨
		UserData user = null;

		int seq_No = Integer.parseInt(request.getParameter("seq_No"));

		if(httpSession.getAttribute("user")!=null) {

			user = (UserData) httpSession.getAttribute("user");


		}else if(httpSession.getAttribute("OauthUser")!=null) {

			user = (UserData) httpSession.getAttribute("OauthUser");

		}
		
		if(user==null) {
		//else if는 안되고 if는 됨
			ModelAndView mav = new ModelAndView();

			mav.setViewName("redirect:/user/login");

			return mav;

		}
		
		String userId = user.getUserId();
		
		bagService.deleteData(seq_No,userId);
		
		List<BookDTO> lists = bagService.getLists(userId);

		ModelAndView mav = new ModelAndView("jsonView");
		//ajax로 가져와서 userid의 seq_no를 조회후 해당 seq_no가 있으면 찜 버튼에 불이 들어와있게 설정해야됨
		//getLists 처음에는 하위쿼리로 실행하려했으나 하위쿼리로 가져오는 값이 2개이상이 되어서 에러가남
		//그래서 inner join사용함
		mav.addObject("lists", lists);
		//ajax로 booklist.html로 lists 쏠거임
		return mav;		

		
	}
	
	@GetMapping("deleteBag.action")
	public ModelAndView deleteItem(HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView();
		UserData user = null;
		
		if(httpSession.getAttribute("user")!=null) {

			user = (UserData) httpSession.getAttribute("user");


		}else if(httpSession.getAttribute("OauthUser")!=null) {

			user = (UserData) httpSession.getAttribute("OauthUser");

		}
		
		if(user==null) {
		//else if는 안되고 if는 됨
			

			mav.setViewName("redirect:/user/login");

			return mav;

		}
		
		String userId = user.getUserId();
		
		String[] value = request.getParameter("data").split(",");
		
		for(String seq_No : value) {
			
			bagService.deleteData(Integer.parseInt(seq_No),userId);			
		}
		
		mav.setViewName("redirect:/bag.action");
		
		return mav;

	}
	
}
