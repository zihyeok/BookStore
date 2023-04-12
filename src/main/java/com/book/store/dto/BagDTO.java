package com.book.store.dto;

import lombok.Data;

@Data
public class BagDTO {
	
	private int bagId;
	private int seq_No;//bookitem테이블의 기본키
	private String userId;
	
}
