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
		
		return null;
	}

	@Override
	public void insertData(UserData userData) throws Exception {
		
		userMapper.insertData(userData);
		
	}

}
