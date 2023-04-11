package com.book.store.ajax;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

}
