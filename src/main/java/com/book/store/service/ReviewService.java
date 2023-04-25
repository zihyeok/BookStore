package com.book.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.book.store.dto.ReviewDTO;


public interface ReviewService {

	public int maxNum() throws Exception;
	
	//댓글 작성
	public void insertData(ReviewDTO dto) throws Exception;
	
	//해당 글의 댓글 개수
	public int getDataCount(@Param("seq_No")int seq_No) throws Exception;
	
	//해당 유저의 댓글 개수
	public int getReviewCount(@Param("userId")String userId) throws Exception;
	
	public List<ReviewDTO> getLists(@Param("start")int start,@Param("end")int end,@Param("seq_No")int seq_No) throws Exception;
	
	//유저별 리뷰
	public List<ReviewDTO> userReview(@Param("start")int start,@Param("end")int end,@Param("userId")String userId) throws Exception;
    
	//댓글 목록
	public ReviewDTO getReadData(int num) throws Exception;
	
	//별점 평균
	public double getAvgStar(@Param("seq_No")int seq_No) throws Exception;
	
	//리뷰 1개만 쓸 수 있게 id 체크할거임
	public ReviewDTO checkUserId(@Param("seq_No")int seq_No,@Param("userId")String userId) throws Exception;
	
    // 댓글 수정
    public int updateData(ReviewDTO dto) throws Exception;
 
    // 댓글 삭제
    public int deleteData(int reviewId) throws Exception;

 

	
}
