package com.book.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.book.store.dto.ReviewDTO;

@Mapper
public interface ReviewMapper {

<<<<<<< HEAD
	public int maxNum() throws Exception;
	
	//댓글 작성
	public void insertData(ReviewDTO dto) throws Exception;
	
	//댓글 개수
	public int getDataCount(@Param("seq_No")int seq_No) throws Exception;
	
	public List<ReviewDTO> getLists(@Param("start")int start,@Param("end")int end,@Param("seq_No")int seq_No) throws Exception;
    
	//댓글 목록
	public ReviewDTO getReadData(int num) throws Exception;
	
	//별점 평균
	public ReviewDTO getAvgStar(@Param("seq_No")int seq_No) throws Exception;
	
	//리뷰 1개만 쓸 수 있게 id 체크할거임
	public ReviewDTO checkUserId(@Param("seq_No")int seq_No,@Param("userId")String userId) throws Exception;
	
=======
	// 댓글 작성
    void insertReview(ReviewDTO review);
    // 댓글 목록 조회
    List<ReviewDTO> selectReviewBySeqNo(int seq_No);
>>>>>>> 05c698529a9eaf2e9e1ae2024591b9a32f13f822
    // 댓글 수정
    void updateReview(ReviewDTO review);
    // 댓글 삭제
<<<<<<< HEAD
    public int deleteData(int reviewId) throws Exception;

=======
    void deleteReview(int reviewId);
>>>>>>> 05c698529a9eaf2e9e1ae2024591b9a32f13f822

}
