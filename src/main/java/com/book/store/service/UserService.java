package com.book.store.service;

import java.util.Optional;

import org.apache.ibatis.annotations.Param;

import com.book.store.user.UserData;

public interface UserService {

	
	public Optional<UserData> findUserName(String userId) throws Exception;
	
	public Optional<UserData> findUserEmail(String email)throws Exception;
	
	public void insertData(UserData userData) throws Exception;
	
	public void insertOAuth(UserData userData) throws Exception;
	
	public void updateOAUData(String name,String id) throws Exception;
	
	public String findUserId(@Param("userName") String userName,@Param("userTel")String userTel) throws Exception;
	
	public String findUserPwd(@Param("userId")String userId,@Param("userName")String userName,@Param("userTel")String userTel) throws Exception;
	
	public int findUserBagItem(String userName) throws Exception;
	
	//uservip찾기
	public int findUserVip(String userId) throws Exception;
	
	public void updateVip(String userId) throws Exception;
	
	public void updateUserData(@Param("userId")String userId,@Param("userPwd")String userPwd,
			@Param("userAddr")String userAddr,@Param("userEmail")String userEmail,
			@Param("userBirth")String userBirth,@Param("userTel")String userTel,@Param("realPwd")String realPwd) throws Exception;
	
	public void deleteUserData(String userId) throws Exception;
}
