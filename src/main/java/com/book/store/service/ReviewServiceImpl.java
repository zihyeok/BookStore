package com.book.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.store.dto.ReviewDTO;
import com.book.store.mapper.ReviewMapper;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public void insertReview(ReviewDTO review) {
        reviewMapper.insertReview(review);
    }

    @Override
    public List<ReviewDTO> selectReviewBySeqNo(int seq_No) {
        return reviewMapper.selectReviewBySeqNo(seq_No);
    }

    @Override
    public void updateReview(ReviewDTO review) {
        reviewMapper.updateReview(review);
    }

    @Override
    public void deleteReview(int num) {
        reviewMapper.deleteReview(num);
    }
}
