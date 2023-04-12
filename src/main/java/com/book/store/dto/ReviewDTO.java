package com.book.store.dto;

import lombok.Data;

@Data
public class ReviewDTO {		
	
	private int num;
	private int seq_No;	
	private String userName;
	private String userPwd;
	private String content;	
	
	private String ipAddr;
	
	private String created;	
	private String fix_created;	
	
}