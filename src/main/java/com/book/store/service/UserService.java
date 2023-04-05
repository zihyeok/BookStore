package com.book.store.service;

import java.util.Optional;

import com.book.store.user.UserData;

public interface UserService {

	
	public Optional<UserData> findUserName(String userId) throws Exception;
}
