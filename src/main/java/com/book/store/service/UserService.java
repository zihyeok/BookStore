package com.book.store.service;

import java.util.Optional;

import com.book.store.user.UserData;

public interface UserService {

	
	public Optional<UserData> findbyUserName(String name) throws Exception;
}
