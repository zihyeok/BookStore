package com.book.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.book.store.dto.BagDTO;
import com.book.store.dto.BookDTO;
import com.book.store.dto.FindOrderDTO;

public interface BagService {

	public int maxNum() throws Exception;

	public void insertData(BagDTO dto) throws Exception;

	public List<BookDTO> getLists(String userId) throws Exception;

	public BookDTO getReadData(int seq_No) throws Exception;

	public void deleteData(@Param("seq_No")int seq_No,@Param("userId")String userId) throws Exception;
	
//	이 하단은 주문완료 테이블 메소드
	
	public int findOG(String userId) throws Exception;
	
	public void insertOrderData(@Param("orderId")int orderId,@Param("userId")String userId,@Param("seq_No")int seq_No,@Param("orderGroup")int orderGroup) throws Exception;

	public int findOrderCount(String userId) throws Exception;
	
	public List<FindOrderDTO> findAllOrders(String userId) throws Exception;
	
	public String findOrderTitle(int orderId) throws Exception;
	
	public int findOrderSu(String userId,int orderId) throws Exception;
	
	public List<BookDTO> findOrderGroup(@Param("userId")String userId,@Param("orderId")int orderId) throws Exception;
}
