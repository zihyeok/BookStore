package com.book.store.user;

import lombok.Getter;

@Getter
public enum UserRole {
	
	//Spring이 이해하기위해 "ROLE_"은 값이 바뀌면 안된다
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	private String value;
	
	UserRole(String value){
		this.value = value;
	}
	

}
