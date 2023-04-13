package com.book.store.service;

import java.util.List;

import com.book.store.dto.BoardDTO;


public interface BoardService {
	
	//Mapper에써있는걸 똑같이 써놓으면 스피링이 알아서 인식함
	public int maxNum() throws Exception;
	
	public void insertData(BoardDTO dto) throws Exception;
	 
	public int getDataCount(String searchKey,String searchValue) throws Exception;
	
	public List<BoardDTO> getLists(int start,int end,String searchKey,String searchValue) throws Exception;
	
	public BoardDTO getReadData(int boardNum) throws Exception;
	
	public void updateHitCount(int boardNum) throws Exception;
	
	public void updateData(BoardDTO dto) throws Exception;
	
	public void deleteData(int boardNum) throws Exception;
	
	public BoardDTO preReadData(int boardNum,String subject,String searchKey,String searchValue) throws Exception;
	
	public BoardDTO nextReadData(int boardNum,String subject,String searchKey,String searchValue) throws Exception;
	
}
