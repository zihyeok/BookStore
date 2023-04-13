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
	public int getDataCount(String searchKey, String searchValue) throws Exception {
		
		return reviewMapper.getDataCount(searchKey, searchValue);
	}

	@Override
	public List<ReviewDTO> getLists(int start, int end, String searchKey, String searchValue) throws Exception {
		
		return reviewMapper.getLists(start, end, searchKey, searchValue);
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
	public int deleteData(int num) throws Exception {
		
		return reviewMapper.deleteData(num);
	}



}
