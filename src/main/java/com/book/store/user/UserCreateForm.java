package com.book.store.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm { 
	
	
	private String userId;
	private String password1;
	private String password2;
	private String userName;
	private String userTel;
	private String userAddr;
	private String addr_detail;
	private String birth_year;
	private String birth_month;
	private String birth_day;
	private String userEmail;
}
