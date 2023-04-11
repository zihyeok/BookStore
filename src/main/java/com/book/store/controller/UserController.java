package com.book.store.controller;

import java.io.PrintWriter;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.store.service.UserService;
import com.book.store.user.UserCreateForm;
import com.book.store.user.UserData;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final HttpSession httpSession;

	//회원가입 페이지로 이동
		@GetMapping("/member")
		public ModelAndView test1() throws Exception {
			
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName("membership");
			
			return mav;
		}
		
		//회원가입페이지에서 버튼누른후 데이터 입력
		@PostMapping("/member")
		public ModelAndView insertdata(UserCreateForm data) throws Exception {
			
			ModelAndView mav = new ModelAndView();
			
			UserData userdata = new UserData();
			
			userdata.setUserId(data.getUserId());
			userdata.setUserPwd(passwordEncoder.encode(data.getPassword1()));
			userdata.setUserName(data.getUserName());
			userdata.setUserAddr(data.getUserAddr() + "" + data.getAddr_detail());
			userdata.setUserEmail(data.getUserEmail());
			userdata.setUserBirth(data.getBirth_year()+"-"+data.getBirth_month()+"-"+data.getBirth_day());
			userdata.setUserTel(data.getUserTel());
			userdata.setRealPwd(data.getPassword1());
			
			userService.insertData(userdata);
			
			mav.setViewName("redirect:/user/hi");
			
			return mav;
			
		}
	
	@GetMapping("/oaumember")
	public ModelAndView oauthlogin() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
//		UserData OauthUser = (UserData) httpSession.getAttribute("OauthUser");
//		
//		mav.addObject("OauthUser", OauthUser);
//		
		mav.setViewName("membershipOau");
		
		return mav;
	}
	
	@PostMapping("/oaumember")
	public ModelAndView oauthgo(UserCreateForm data) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		UserData oauthUser = (UserData) httpSession.getAttribute("OauthUser");
		
		
//		userService.insertData(userData);
//		httpSession.setAttribute("OauthUser", userData);
//		
		mav.setViewName("redirect:/hi");
		
		return mav;
	}
	
	@GetMapping("/user/login")
	public ModelAndView userlogin() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("minsungTest2");
		
		return mav;
		
	}
	
	@GetMapping("/hi")
	public ModelAndView test() throws Exception {
		
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

		//OAuth로 첫 로그인시 기타 회원정보 추가를 위해 가입페이지로 이동
		if(OauthUser!=null && OauthUser.getUserAddr()==null) {
		
			mav.setViewName("redirect:/user/oaupage");
			return mav;
		}
		
		
		if(OauthUser!=null) {
			mav.addObject("userId", OauthUser.getUserId());
			mav.addObject("userPwd", OauthUser.getUserPwd());
			mav.addObject("userEmail", OauthUser.getUserEmail());
			
			
		}
		
		return mav;
	}
	
	
	
	
	
}
