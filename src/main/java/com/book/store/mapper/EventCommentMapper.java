package com.book.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.book.store.dto.EventCommentDTO;

@Mapper
public interface EventCommentMapper {
	
	public int maxNum(int boardId) throws Exception;
	
	public void insertData(EventCommentDTO dto) throws Exception;
	
	public int getDataCount(@Param("boardId")int boardId) throws Exception;
	
	public List<EventCommentDTO> getLists(@Param("start")int start,@Param("end")int end,@Param("boardId")int boardId) throws Exception;
	
	public void deleteData(int commentId) throws Exception;
	
	public void updateData(EventCommentDTO dto) throws Exception;
	
	public EventCommentDTO getReadData(int commentId) throws Exception;

}
