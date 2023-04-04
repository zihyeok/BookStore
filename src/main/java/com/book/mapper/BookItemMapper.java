package com.book.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.book.dto.BookDTO;

@Mapper
public interface BookItemMapper {

	public int maxNum() throws Exception;

	public void insertData(BookDTO dto) throws Exception;

	public int getDataCount(String searchKey,String searchValue) throws Exception;

	public List<BookDTO> getLists(int start,int end,String searchKey,String searchValue) throws Exception;

	public BookDTO getReadData(int SEQ_NO) throws Exception;

	public void updateSal_Count(int SEQ_NO) throws Exception;

	public void updateData(BookDTO dto) throws Exception;

	public void deleteData(int SEQ_NO) throws Exception;

}
