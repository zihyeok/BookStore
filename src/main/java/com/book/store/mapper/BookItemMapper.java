package com.book.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.book.store.dto.BookDTO;

@Mapper
public interface BookItemMapper {

	public int maxNum() throws Exception;

	public void insertData(BookDTO dto) throws Exception;

	public int getDataCount(@Param("searchKey")String searchKey,@Param("searchValue")String searchValue) throws Exception;

	public List<BookDTO> getLists(@Param("start")int start,@Param("end")int end,@Param("searchKey")String searchKey,@Param("searchValue")String searchValue) throws Exception;
	
	public List<BookDTO> recentLists(@Param("start")int start,@Param("end")int end) throws Exception;
	
	public List<BookDTO> topSalLists(@Param("start")int start,@Param("end")int end) throws Exception;

	public BookDTO getReadData(int seq_No) throws Exception;

	public void updateSal_Count(int seq_No) throws Exception;

	public void updateData(BookDTO dto) throws Exception;

	public void deleteData(int seq_No) throws Exception;

}
