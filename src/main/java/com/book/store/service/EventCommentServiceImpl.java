package com.book.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.store.dto.EventCommentDTO;
import com.book.store.mapper.EventCommentMapper;

@Service
public class EventCommentServiceImpl implements EventCommentService{

	@Autowired
	private EventCommentMapper eventCommentMapper;
	
	@Override
	public int maxNum(int boardId) throws Exception {
		return eventCommentMapper.maxNum(boardId);
	}

	@Override
	public void insertData(EventCommentDTO dto) throws Exception {
		eventCommentMapper.insertData(dto);		
	}

	@Override
	public int getDataCount(int boardId) throws Exception {
		return eventCommentMapper.getDataCount(boardId);
	}

	@Override
	public List<EventCommentDTO> getLists(int start, int end, int boardId) throws Exception {
		return eventCommentMapper.getLists(start, end, boardId);
	}

	@Override
	public void deleteData(int commentId) throws Exception {
		eventCommentMapper.deleteData(commentId);		
	}

	@Override
	public void updateData(EventCommentDTO dto) throws Exception {
		eventCommentMapper.updateData(dto);
	}

	@Override
	public EventCommentDTO getReadData(int commentId) throws Exception {
		return eventCommentMapper.getReadData(commentId);
	}

}
