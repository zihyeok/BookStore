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

<<<<<<< HEAD
		return reviewMapper.maxNum();
	}

	@Override
	public void insertData(ReviewDTO dto) throws Exception {
		reviewMapper.insertData(dto);
		
	}

	@Override
	public int getDataCount(int seq_No) throws Exception {
		
		return reviewMapper.getDataCount(seq_No);
	}

	@Override
	public List<ReviewDTO> getLists(int start, int end, int seq_No) throws Exception {
		
		return reviewMapper.getLists(start, end, seq_No);
	}

	@Override
	public ReviewDTO getReadData(int num) throws Exception {
		
		return reviewMapper.getReadData(num);
	}

	@Override
	public int updateData(ReviewDTO dto) throws Exception {
		
		return reviewMapper.updateData(dto);
	}

	@Override
	public int deleteData(int reviewId) throws Exception {
		
		return reviewMapper.deleteData(reviewId);
	}

	@Override
	public ReviewDTO getAvgStar(int seq_No) throws Exception {
		
		return reviewMapper.getAvgStar(seq_No);
	}

	@Override
	public ReviewDTO checkUserId(int seq_No, String userId) throws Exception {
		
		return reviewMapper.checkUserId(seq_No, userId);
	}
=======
    @Override
    public void insertReview(ReviewDTO review) {
        reviewMapper.insertReview(review);
    }
>>>>>>> 05c698529a9eaf2e9e1ae2024591b9a32f13f822

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
