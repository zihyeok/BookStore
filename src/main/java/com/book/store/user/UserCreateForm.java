package com.book.store.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm { 
	
	//html에 form에 먼저 적용시켜 제약조건으로 검사한뒤 UserData에 set으로 저장
	
	@Size(min = 5,max = 25)
	@NotEmpty(message = "아이디를 입력해주세요!")
	private String userId;
	
	@NotEmpty(message =  "비밀번호를 입력해주세요!")
	private String password1;
	
	@NotEmpty(message =  "비밀번호를 재입력 해주세요!")
	private String password2;

	@NotEmpty(message = "이메일를 입력해주세요!")
	@Email
	private String userEmail;
	
	private String userName;
	private String userAddr;
	private String userBirth;
	private String userTel;
	private String userVip;
}
