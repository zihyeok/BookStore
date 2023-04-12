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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.store.service.UserService;
import com.book.store.user.UserData;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserAjax {
	
	private final UserService userService;
	
	
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
	@ResponseBody
	public String kakopay() {
		
		try {
			URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "KakaoAK 051b33bdbbe0ba43924808b0a780bb6c");
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			connection.setDoOutput(true);
			
			String parameter = "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&item_name=초코파이&quantity=1&total_amount=2200&tax_free_amount=0&approval_url=http://localhost:8080/success&fail_url=http://localhost:8080/fail&cancel_url=http://localhost:8080/cancel";
			
			OutputStream ops = connection.getOutputStream();
			
			DataOutputStream dop = new DataOutputStream(ops);
			
			dop.writeBytes(parameter);
			dop.close();
			
			int result = connection.getResponseCode();
			
			InputStream ips;
			
			if(result==200) {
				
				ips = connection.getInputStream();
			}else {
				
				ips = connection.getErrorStream(); 
			}
			
			InputStreamReader isr = new InputStreamReader(ips);
			
			BufferedReader bfr = new BufferedReader(isr);
			
			return bfr.readLine();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return "hello";
	}
	
	@RequestMapping("/success")
	public String success() {
		return "성공";
	}

	@RequestMapping("/fail")
	public String fail() {
		return "실패";
	}
	
	@RequestMapping("/cancel")
	public String cancel() {
		return "취소";
	}
}
