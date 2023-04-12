package com.book.store.service;

import java.util.List;

import com.book.store.dto.BagDTO;
import com.book.store.dto.BookDTO;

public interface BagService {

	public int maxNum() throws Exception;

	public void insertData(BagDTO dto) throws Exception;

	public List<BookDTO> getLists(String userId) throws Exception;

	public BookDTO getReadData(int seq_No) throws Exception;

	public void deleteData(int seq_No) throws Exception;

}
