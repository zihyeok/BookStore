package com.book.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.store.dto.BoardAnswerDTO;
import com.book.store.mapper.BoardAnswerMapper;

@Service
public class BoardAnswerServiceImpl implements BoardAnswerService{
	
	
	@Autowired
	private BoardAnswerMapper BoardAnswerMapper;
	
	
	@Override
	public int maxNum() throws Exception {
		
		return BoardAnswerMapper.maxNum();
	}

	@Override
	public void insertData(BoardAnswerDTO dto) throws Exception {
		BoardAnswerMapper.insertData(dto);
		
	}

	@Override
	public List<BoardAnswerDTO> getLists(int start, int end, int boardId) throws Exception {
		
		return BoardAnswerMapper.getLists(start, end, boardId);
	}

	@Override
	public void deleteData(int boardId) throws Exception {
		BoardAnswerMapper.deleteData(boardId);
		
	}

	@Override
	public int getDataCount(int answerNum) throws Exception {
		return BoardAnswerMapper.getDataCount(answerNum);
	}

	@Override
	public void updateData(BoardAnswerDTO dto) throws Exception {
		BoardAnswerMapper.updateData(dto);
		
	}

	@Override
	public BoardAnswerDTO getReadData(int answerNum) throws Exception {
		return BoardAnswerMapper.getReadData(answerNum);
	}


	
	
}
