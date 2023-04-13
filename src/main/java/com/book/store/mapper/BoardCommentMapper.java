package com.book.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.book.store.dto.BoardCommentDTO;


@Mapper
public interface BoardCommentMapper {

	public int maxNum() throws Exception;
	
	public void insertData(BoardCommentDTO dto) throws Exception;
	
	public int getDataCount(@Param("boardNum")int boardNum) throws Exception;
	
	public List<BoardCommentDTO> getLists(@Param("start")int start,@Param("end")int end,@Param("boardNum")int boardNum) throws Exception;
	
	public void deleteData(int boardNum) throws Exception;
	
	
}
