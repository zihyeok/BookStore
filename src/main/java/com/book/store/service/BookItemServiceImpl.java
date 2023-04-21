package com.book.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.store.dto.BookDTO;
import com.book.store.mapper.BookItemMapper;


@Service
public class BookItemServiceImpl implements BookItemService{
	
	@Autowired
	private BookItemMapper bookItemMapper;
	
	@Override
	public int maxNum() throws Exception {
		
		return bookItemMapper.maxNum();
	}

	@Override
	public void insertData(BookDTO dto) throws Exception {
		
		bookItemMapper.insertData(dto);
		
	}

	@Override
	public int getDataCount(String searchKey, String searchValue) throws Exception {
		
		return bookItemMapper.getDataCount(searchKey, searchValue);
	}

	@Override
	public List<BookDTO> getLists(int start, int end, String searchKey, String searchValue) throws Exception {
		
		return bookItemMapper.getLists(start, end, searchKey, searchValue);
	}

	@Override
	public BookDTO getReadData(int SEQ_NO) throws Exception {
		
		return bookItemMapper.getReadData(SEQ_NO);
	}

	@Override
	public void updateSal_Count(int SEQ_NO) throws Exception {
		
		bookItemMapper.updateSal_Count(SEQ_NO);
	}

	@Override
	public void updateData(BookDTO dto) throws Exception {
		
		bookItemMapper.updateData(dto);
		
	}

	@Override
	public void deleteData(int SEQ_NO) throws Exception {
		
		bookItemMapper.deleteData(SEQ_NO);
		
	}

	@Override
	public List<BookDTO> recentLists(int start, int end) throws Exception {
		
		return bookItemMapper.recentLists(start, end);
	}

	@Override
	public List<BookDTO> topSalLists(int start, int end) throws Exception {
	
		return bookItemMapper.topSalLists(start, end);
	}

	@Override
	public List<BookDTO> categoryLists(int start, int end, String searchKey, String searchValue) throws Exception {
	
		return bookItemMapper.categoryLists(start, end, searchKey, searchValue);
	}

	@Override
	public int backUpMaxNum() throws Exception {
		
		return bookItemMapper.backUpMaxNum();
	}

	@Override
	public void insertBackUp(BookDTO dto) throws Exception {
		
		bookItemMapper.insertBackUp(dto);
		
	}

}
