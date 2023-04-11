package com.book.store.service;

import java.util.Optional;

import com.book.store.user.UserData;

public interface UserService {

	
	public Optional<UserData> findUserName(String userId) throws Exception;
	
	public Optional<UserData> findUserEmail(String email)throws Exception;
	
	public void insertData(UserData userData) throws Exception;
	
	public void insertOAuth(UserData userData) throws Exception;
	
	public void updateOAUData(String name,String id) throws Exception;
	
	public String findUserId(String userName,String userTel) throws Exception;
	
	
}
