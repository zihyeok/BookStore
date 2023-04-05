package com.book.store.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.book.store.user.UserData;

@Mapper
public interface UserMapper {

	
	public Optional<UserData> findUserName(String name) throws Exception;
}
