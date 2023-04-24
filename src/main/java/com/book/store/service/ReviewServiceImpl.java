package com.book.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.store.dto.ReviewDTO;
import com.book.store.mapper.ReviewMapper;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewMapper reviewMapper;

	@Override
	public int maxNum() throws Exception {

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

	@Override
	public List<ReviewDTO> userReview(int start, int end, String userId) throws Exception {
		
		return reviewMapper.userReview(start, end, userId);
	}

	@Override
	public int getReviewCount(String userId) throws Exception {
		
		return reviewMapper.getReviewCount(userId);
	}



}
