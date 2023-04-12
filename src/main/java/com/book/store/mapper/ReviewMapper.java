package com.book.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.book.store.dto.ReviewDTO;

@Mapper
public interface ReviewMapper {

	public int maxNum() throws Exception;
	
	//댓글 작성
	public void insertData(ReviewDTO dto) throws Exception;
	
	//댓글 개수
	public int getDataCount(@Param("searchKey")String searchKey,@Param("searchValue")String searchValue) throws Exception;
	
	public List<ReviewDTO> getLists(@Param("start")int start,@Param("end")int end,@Param("searchKey")String searchKey,@Param("searchValue")String searchValue) throws Exception;
    
	//댓글 목록
	public ReviewDTO getReadData(int num) throws Exception;
	
    // 댓글 수정
    public int updateData(ReviewDTO dto) throws Exception;
 
    // 댓글 삭제
    public int deleteData(int num) throws Exception;
    
    //조회수
    public void updateHitCount(int num) throws Exception;

}
