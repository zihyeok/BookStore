package com.book.store.service;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.book.store.mapper.UserMapper;
import com.book.store.user.UserData;

@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	public UserMapper userMapper;

	@Override
	public Optional<UserData> findUserName(String userId) throws Exception {
		
		Optional<UserData> userData = userMapper.findUserName(userId);
		
		return userData;
	}

	@Override
	public Optional<UserData> findUserEmail(String email) throws Exception {
		
		Optional<UserData> userData = userMapper.findUserEmail(email);
		
		return userData;
	}
	
	@Override
	public void insertData(UserData userData) throws Exception {
		
		userMapper.insertData(userData);
		
	}

	@Override
	public void insertOAuth(UserData userData) throws Exception {

		userMapper.insertOAuth(userData);
		
	}

	

}
