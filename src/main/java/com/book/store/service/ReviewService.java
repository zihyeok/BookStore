package com.book.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.book.store.dto.ReviewDTO;


public interface ReviewService {

	public int maxNum() throws Exception;
	
	//댓글 작성
	public void insertData(ReviewDTO dto) throws Exception;
	
	//댓글 개수
	public int getDataCount(@Param("seq_No")int seq_No) throws Exception;
	
	public List<ReviewDTO> getLists(@Param("start")int start,@Param("end")int end,@Param("seq_No")int seq_No) throws Exception;
    
	//댓글 목록
	public ReviewDTO getReadData(int num) throws Exception;
	
	//별점 평균
	public ReviewDTO getAvgStar(@Param("searchKey")String searchKey,@Param("searchValue")String searchValue) throws Exception;
	
    // 댓글 수정
    public int updateData(ReviewDTO dto) throws Exception;
 
    // 댓글 삭제
    public int deleteData(int num) throws Exception;

 

	
}
