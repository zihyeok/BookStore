package com.book.store.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserData {
	
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
	public UserData(String email,String name,String role) {
		this.userId = email;
		this.userName = name;
		this.userRole = role;
	}

}
