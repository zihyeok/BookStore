package com.book.store.service;

import java.util.List;

import com.book.store.dto.BookDTO;

public interface BookItemService {

	public int maxNum() throws Exception;

	public void insertData(BookDTO dto) throws Exception;

	public int getDataCount(String searchKey,String searchValue) throws Exception;

	public List<BookDTO> getLists(int start,int end,String searchKey,String searchValue) throws Exception;
	
	public List<BookDTO> categoryLists(int start,int end,String searchKey,String searchValue) throws Exception;
	
	public List<BookDTO> recentLists(int start,int end) throws Exception;
	
	public List<BookDTO> topSalLists(int start,int end) throws Exception;

	public BookDTO getReadData(int SEQ_NO) throws Exception;

	public void updateSal_Count(int SEQ_NO) throws Exception;

	public void updateData(BookDTO dto) throws Exception;

	public void deleteData(int SEQ_NO) throws Exception;

}
