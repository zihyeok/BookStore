package com.book.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.store.dto.BoardCommentDTO;
import com.book.store.mapper.BoardCommentMapper;

@Service
public class BoardCommentServiceImpl implements BoardCommentService{
	
	
	@Autowired
	private BoardCommentMapper boardCommentMapper;
	
	
	@Override
	public int maxNum() throws Exception {
		
		return boardCommentMapper.maxNum();
	}

	@Override
	public void insertData(BoardCommentDTO dto) throws Exception {
		boardCommentMapper.insertData(dto);
		
	}

	@Override
	public List<BoardCommentDTO> getLists(int start, int end, int boardNum) throws Exception {
		
		return boardCommentMapper.getLists(start, end, boardNum);
	}

	@Override
	public void deleteData(int boardNum) throws Exception {
		boardCommentMapper.deleteData(boardNum);
		
	}

	@Override
	public int getDataCount(int commentNum) throws Exception {
		return boardCommentMapper.getDataCount(commentNum);
	}


	
	
}
