package com.book.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.store.dto.EventBoardDTO;
import com.book.store.mapper.EventBoardMapper;

//boardService를 쓴다고 쓰지만 사실은 impl를 쓰는거라 객체화해줘야됨 쓰려고하는건 객체화하자
//BoardController -> BoardService(I) -> BoardServiceImpl(C) -> BoardMapper(I) -> boardMapper.xml
@Service
public class EventBoardServiceImpl implements EventBoardService{
	
	@Autowired
	private EventBoardMapper eventBoardMapper;

	@Override
	public int maxNum() throws Exception {
		return eventBoardMapper.maxNum();
	}

	@Override
	public void insertData(EventBoardDTO dto) throws Exception {
		eventBoardMapper.insertData(dto);
	}

	@Override
	public int getDataCount(String searchKey, String searchValue) throws Exception {
		return eventBoardMapper.getDataCount(searchKey, searchValue);
	}

	@Override
	public List<EventBoardDTO> getLists(int start, int end, String searchKey, String searchValue) throws Exception {
		return eventBoardMapper.getLists(start, end, searchKey, searchValue);
	}

	@Override
	public EventBoardDTO getReadData(int boardId) throws Exception {
		return eventBoardMapper.getReadData(boardId);
	}

	@Override
	public void updateHitCount(int boardId) throws Exception {
		eventBoardMapper.updateHitCount(boardId);;
	}

	@Override
	public void updateData(EventBoardDTO dto) throws Exception {
		eventBoardMapper.updateData(dto);
	}

	@Override
	public void deleteData(int boardId) throws Exception {
		eventBoardMapper.deleteData(boardId);
	}
	
	
	
	
}
