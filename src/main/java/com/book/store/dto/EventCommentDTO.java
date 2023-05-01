package com.book.store.dto;

import lombok.Data;

@Data
public class EventCommentDTO {
	
	private int commentId;
	private int boardId;
	private String userId;
	private String content;
	private String created;
	private String fix_created;	

}
