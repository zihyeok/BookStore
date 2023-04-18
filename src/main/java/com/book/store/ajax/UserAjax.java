package com.book.store.ajax;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.store.dto.BookDTO;
import com.book.store.service.BagService;
import com.book.store.service.BookItemService;
import com.book.store.service.UserService;
import com.book.store.user.UserData;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserAjax {
	
	private final UserService userService;
	private final BagService bagService;
	private final HttpSession httpsession;
	private final BookItemService bookitemservice;

	@PostMapping("/findId")
	public Map<String, Object> findId(HttpServletRequest request) throws Exception {
		
		String userid = request.getParameter("userid");
		
		Optional<UserData> data = userService.findUserName(userid);
		
		if(data.isPresent()) {
			if(data.get().getUserId().equals(userid)) {
				
				Map<String,Object> map = new HashMap<String, Object>();
				String alert = userid + " 는 사용할수 없는 아이디입니다";
				
				map.put("data",alert);
				map.put("ok",false);
				
				//System.out.println(alert);
				return map;
			}
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		String alert = userid + " 는 사용가능한 아이디입니다";
		
		map.put("data",alert);
		map.put("ok",true);
		//System.out.println(alert);
		return map;
	}
	
	//카카오페이 결제
	@RequestMapping("/kakopay")
	public String kakopay() {
		
//		try {
//			URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
//			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//			connection.setRequestMethod("POST");
//			connection.setRequestProperty("Authorization", "KakaoAK 051b33bdbbe0ba43924808b0a780bb6c");
//			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset='utf-8'");
//			connection.setDoOutput(true);
//			
//			String parameter = "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&item_name=초코파이&quantity=1&total_amount=2200&tax_free_amount=0&approval_url=http://localhost:8080/success&fail_url=http://localhost:8080/fail&cancel_url=http://localhost:8080/cancel";
//			
//			OutputStream ops = connection.getOutputStream();
//			
//			DataOutputStream dop = new DataOutputStream(ops);
//			
//			dop.writeBytes(parameter);
//			dop.close();
//			
//			int result = connection.getResponseCode();
//			
//			InputStream ips;
//			
//			if(result==200) {
//				
//				ips = connection.getInputStream();
//			}else {
//				
//				ips = connection.getErrorStream(); 
//			}
//			
//			InputStreamReader isr = new InputStreamReader(ips);
//			
//			BufferedReader bfr = new BufferedReader(isr);
//			
//			return bfr.readLine();
//			
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
		
		return "hello";
	}
	
	@RequestMapping("/success")
	public ModelAndView success(HttpServletRequest request) throws NumberFormatException, Exception {
		
		UserData user = null;

		if(httpsession.getAttribute("user")!="") {

			user = (UserData) httpsession.getAttribute("user");


		}else if(httpsession.getAttribute("OauthUser")!="") {

			user = (UserData) httpsession.getAttribute("OauthUser");

		}
		
		if(user==null) {
		
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName("redirect:/user/login");

			return mav;

		}
		
		String pg_token = request.getParameter("pg_token");
		String tid = (String) httpsession.getAttribute("tid");
		String orderId = (String) httpsession.getAttribute("orderId");
		String[] book_No = ((String) httpsession.getAttribute("bookNum")).split(",");
		
		int orderGroup = bagService.findOG(user.getUserId());
		
		List<BookDTO> lists = new ArrayList<BookDTO>();
		
		for(String title_No : book_No) {
			//장바구니 아이템 삭제
			bagService.deleteData(Integer.parseInt(title_No), user.getUserId());
			
			//결제완료 데이터 추가
			bagService.insertOrderData(Integer.parseInt(orderId), user.getUserId(), Integer.parseInt(title_No), orderGroup+1);
			
			//결제완료 페이지에서 보여질 구매한 List데이터 추가
			lists.add(bookitemservice.getReadData(Integer.parseInt(title_No)));

			
			//결제완료후 VIP점수 누적
			userService.updateVip(user.getUserId());
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("tid", tid);
		mav.addObject("user", user.getUserId());
		mav.addObject("orderId", orderId);
		mav.addObject("lists", lists);
		mav.setViewName("kakaopay_success");
		
//		try {
//			URL url = new URL("https://kapi.kakao.com/v1/payment/approve");
//			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//			connection.setRequestMethod("POST");
//			connection.setRequestProperty("Authorization", "KakaoAK 051b33bdbbe0ba43924808b0a780bb6c");
//			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//			connection.setDoOutput(true);
//			
//			String parameter = "cid=TC0ONETIME&tid="+tid+"&partner_order_id=partner_order_id&partner_user_id=partner_user_id&pg_token="+pg_token;
//			
//			OutputStream ops = connection.getOutputStream();
//			
//			DataOutputStream dop = new DataOutputStream(ops);
//			
//			dop.writeBytes(parameter);
//			dop.close();
//			
//			int result = connection.getResponseCode();
//			
//			InputStream ips;
//			
//			if(result==200) {
//				
//				ips = connection.getInputStream();
//			}else {
//				
//				ips = connection.getErrorStream(); 
//			}
//			
//			InputStreamReader isr = new InputStreamReader(ips);
//			
//			BufferedReader bfr = new BufferedReader(isr);
//			
//			return bfr.readLine();
//			
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		
		return mav;
	}

	@RequestMapping("/fail")
	public String fail() {
		return "실패";
	}
	
	@RequestMapping("/cancel")
	public String cancel() {
		return "취소";
	}
	
	//결제가 완료될때까지 대기하는 페이지
	@RequestMapping("/ready")
	public ModelAndView ready(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		httpsession.setAttribute("tid", request.getParameter("tid"));
		httpsession.setAttribute("bookNum", request.getParameter("bookNum"));
		httpsession.setAttribute("orderId", request.getParameter("orderId"));
		mav.setViewName("kakaopay_ready");
		return mav;
	}
	
	
}
