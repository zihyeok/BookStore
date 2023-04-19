package com.book.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.book.store.dto.BoardAnswerDTO;


@Mapper
public interface BoardAnswerMapper {

	public int maxNum() throws Exception;
	
	public void insertData(BoardAnswerDTO dto) throws Exception;
	
	public int getDataCount(@Param("boardId")int boardId) throws Exception;
	
	public List<BoardAnswerDTO> getLists(@Param("start")int start,@Param("end")int end,@Param("boardId")int boardId) throws Exception;
	
	public void deleteData(int answerNum) throws Exception;
	
	public void updateData(BoardAnswerDTO dto) throws Exception;
	
	public BoardAnswerDTO getReadData(int answerNum) throws Exception;
}
