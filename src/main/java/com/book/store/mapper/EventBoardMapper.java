package com.book.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.book.store.dto.EventBoardDTO;

@Mapper
public interface EventBoardMapper {
	
	public int maxNum() throws Exception;

	public void insertData(EventBoardDTO dto) throws Exception;

	public int getDataCount(@Param("searchKey")String searchKey,@Param("searchValue")String searchValue) throws Exception;

	public List<EventBoardDTO> getLists(@Param("start")int start,@Param("end")int end,@Param("searchKey")String searchKey,@Param("searchValue")String searchValue) throws Exception;

	public EventBoardDTO getReadData(int boardId) throws Exception;

	public void updateHitCount(int boardId) throws Exception;
	
	public void updateData(EventBoardDTO dto) throws Exception;
	
	public void deleteData(int boardId) throws Exception;

}
