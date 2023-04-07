package com.book.store.user;

import com.book.store.service.UserService;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserData {
	
	private UserService userService;
	
	private String userId;
	private String userPwd;
	private String userName;
	private String userAddr;
	private String userEmail;
	private String userBirth;
	private String userTel;
	private String userVip;
	private String userRole;
	private String realPwd;
	
	@Builder
	public UserData(String email,String name,String role,String registrationId) {
		this.userId = email;
		this.userName = name;
		this.userRole = role;
		this.userPwd = registrationId;
	}
	
	public UserData update(String name) {
		
		this.userName = name;
		
		try {
			userService.updateOAUData(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this;
	}

}
