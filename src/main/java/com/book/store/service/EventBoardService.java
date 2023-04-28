package com.book.store.service;

import java.util.List;

import com.book.store.dto.EventBoardDTO;

//BoardService 이곳이 BoardMapper.java와 BoardServiceImpl.javav를 연결하는 매개체
//BoardMapper.java와 똑같이 생겨야함
public interface EventBoardService {
	
	public int maxNum() throws Exception;
	
	public int backUpMaxNum() throws Exception;
	
	public void insertBackUp(EventBoardDTO dto) throws Exception;

	public void insertData(EventBoardDTO dto) throws Exception;

	public int getDataCount(String searchKey,String searchValue) throws Exception;

	public List<EventBoardDTO> getLists(int start,int end,String searchKey,String searchValue) throws Exception;

	public EventBoardDTO getReadData(int boardId) throws Exception;

	public void updateHitCount(int boardId) throws Exception;
	
	public void updateData(EventBoardDTO dto) throws Exception;
	
	public void deleteData(int boardId) throws Exception;

	public EventBoardDTO preReadData(int boardId,String subject,String searchKey,String searchValue) throws Exception;
	
	public EventBoardDTO nextReadData(int boardId,String subject,String searchKey,String searchValue) throws Exception;

	public List<EventBoardDTO> categoryLists(int start,int end,String searchKey,String searchValue) throws Exception;
	
}
