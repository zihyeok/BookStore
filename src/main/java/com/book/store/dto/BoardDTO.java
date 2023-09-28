package com.book.store.dto;

import lombok.Data;

@Data
public class BoardDTO {
	
	
	private int boardId;// 게시글 번호
	private String userId; //유저아디
	private String pwd; //유저비번
	private String subject;	//제목
	private String content; //글
	private String created; //글쓴날 
	private String fix_created; //수정한날 
	private int hitCount;//조회수 
	
	
}
