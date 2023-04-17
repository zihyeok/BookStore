package com.book.store.dto;

import lombok.Data;

@Data
public class BoardCommentDTO {
	

	private int commentNum;//boardNum에 따라 댓글 수가 달라야함
	private int boardNum;
	private String name;
	//private String email;
	private String content;
	private String created;
	
	//private String pageNum;
}
