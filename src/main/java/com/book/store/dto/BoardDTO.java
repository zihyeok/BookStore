package com.book.store.dto;

import lombok.Data;



@Data
public class BoardDTO {
	
	

	
	private int boardId;
	private String userId;
	private String pwd;
	private String subject;
	private String content;
	private String created;
	private String fix_created;
	private int hitCount;
	
	
}
