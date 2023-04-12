package com.book.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.book.store.dto.BagDTO;
import com.book.store.dto.BookDTO;

@Mapper
public interface BagMapper {
	
	public int maxNum() throws Exception;

	public void insertData(BagDTO dto) throws Exception;
	
	public List<BookDTO> getLists(String userId) throws Exception;
	
	public BookDTO getReadData(int seq_No) throws Exception;
	
	public void deleteData(int seq_No) throws Exception;
	
}
