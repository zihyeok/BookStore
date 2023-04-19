package com.book.store.service;

import java.util.List;

import com.book.store.dto.BoardAnswerDTO;

public interface BoardAnswerService {
	
	public int maxNum() throws Exception;
	
	public void insertData(BoardAnswerDTO dto) throws Exception;
	
	public int getDataCount(int boardId) throws Exception;
	
	public List<BoardAnswerDTO> getLists(int start,int end,int boardId) throws Exception;
	
	public void deleteData(int answerNum) throws Exception;
	
	public void updateData(BoardAnswerDTO dto) throws Exception;
	
	public BoardAnswerDTO getReadData(int answerNum) throws Exception;
	
}
