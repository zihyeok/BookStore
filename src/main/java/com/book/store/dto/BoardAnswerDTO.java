package com.book.store.dto;

import lombok.Data;

@Data
public class BoardAnswerDTO {

	private int answerNum;//boardId에 따라 댓글 수가 달라야함
	private int boardId;
	private String userId;
	private String content;
	private String created;
	private String fix_Created;
	
}
