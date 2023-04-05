package com.book.store.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
	
	//Spring이 이해하기위해 "ROLE_"은 값이 바뀌면 안된다
	ADMIN("ROLE_ADMIN","어드민"),
	USER("ROLE_USER","유저");
	
	private final String value;
	private final String title;
	
	
	

}
