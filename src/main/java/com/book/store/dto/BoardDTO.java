package com.book.store.dto;

import lombok.Data;

@Data
public class BoardDTO {
	
	
	private int commentCount;//boardNum에 따라 댓글 수가 달라야함
	
	private int boardNum;
	private String name;
	private String email;
	private String subject;
	private String content;
	private String created;
	private int hitCount;
	
	private String pageNum;
	private String searchKey;
	private String searchValue;
	
}
