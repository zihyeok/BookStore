package com.book.store.dto;

import lombok.Data;

@Data
public class BoardDTO {
	
	private int num;
	private String name;
	
	private String email;
	private String subject;
	private String content;

	private String created;
	private int hitCount;
}
