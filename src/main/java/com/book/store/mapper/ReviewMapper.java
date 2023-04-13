package com.book.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.book.store.dto.ReviewDTO;

@Mapper
public interface ReviewMapper {

	// 댓글 작성
    void insertReview(ReviewDTO review);
    // 댓글 목록 조회
    List<ReviewDTO> selectReviewBySeqNo(int seq_No);
    // 댓글 수정
    void updateReview(ReviewDTO review);
    // 댓글 삭제
    void deleteReview(int reviewId);

}
