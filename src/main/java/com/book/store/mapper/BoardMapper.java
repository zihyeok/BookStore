package com.book.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.book.store.dto.BoardDTO;


//정의해놓은 sql와 개발할 때 사용하는 메소드를 연결하고 
// 결과 값을 정의해놓은 타입으로 매핑 시켜주는 것
@Mapper //Mapper로 등록 시킨다.
public interface BoardMapper {

	
	public int maxNum() throws Exception;
	
	public void insertData(BoardDTO dto) throws Exception;
	
	public int getDataCount(@Param("searchKey")String searchKey,@Param("searchValue")String searchValue) throws Exception;
	
	public List<BoardDTO> getLists(@Param("start")int start,@Param("end")int end,@Param("searchKey")String searchKey,@Param("searchValue")String searchValue) throws Exception;
	
	public BoardDTO getReadData(int boardId) throws Exception;
	
	public void updateHitCount(int boardId) throws Exception;
	
	public void updateData(BoardDTO dto) throws Exception;
	
	public void deleteData(int boardId) throws Exception;
	
	public BoardDTO preReadData(@Param("boardId")int boardId,@Param("subject")String subject,@Param("searchKey")String searchKey,@Param("searchValue")String searchValue) throws Exception;
	
	public BoardDTO nextReadData(@Param("boardId")int boardId,@Param("subject")String subject,@Param("searchKey")String searchKey,@Param("searchValue")String searchValue) throws Exception;
	
}
