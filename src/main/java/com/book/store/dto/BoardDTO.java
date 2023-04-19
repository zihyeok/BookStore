package com.book.store.dto;

import lombok.Data;

@Data
public class BoardDTO {
	
	
	//private int commentCount;//boardNum에 따라 댓글 수가 달라야함
	
	private int boardId;
	private String userId;
	private String pwd;
	private String subject;
	private String content;
	private String created;
	private String fix_created;
	private int hitCount;
	
	
}
