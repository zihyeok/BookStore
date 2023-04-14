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

	@Override
	public void updateOAUData(String name,String id) throws Exception {

		userMapper.updateOAUData(name,id);
		
	}

	@Override
	public String findUserId(String userName, String userTel) throws Exception {

		String userid = userMapper.findUserId(userName, userTel);
		
		return userid;
	}

	@Override
	public String findUserPwd(String userId, String userName, String userTel) throws Exception {
		
		return userMapper.findUserPwd(userId, userName, userTel);
	}

	@Override
	public int findUserBagItem(String userId) throws Exception {
		
		return userMapper.findUserBagItem(userId);
	}

	

	

}
