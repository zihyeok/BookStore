package com.book.store.service;

import java.util.List;

import com.book.store.dto.EventCommentDTO;

public interface EventCommentService {
	
	public int maxNum(int boardId) throws Exception;
	
	public void insertData(EventCommentDTO dto) throws Exception;
	
	public int getDataCount(int boardId) throws Exception;
	
	public List<EventCommentDTO> getLists(int start,int end,int boardId) throws Exception;
	
	public void deleteData(int commentId) throws Exception;
	
	public void updateData(EventCommentDTO dto) throws Exception;
	
	public EventCommentDTO getReadData(int commentId) throws Exception;

}
