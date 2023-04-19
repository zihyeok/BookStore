package com.book.store.dto;

import lombok.Data;

@Data
public class ReviewDTO {		
	
	private int reviewId;

	private int seq_No;	
	private int reviewStar;
	
	private String userId;

	private String userName;

	private String content;	
	
	private String ipAddr;
	
	private String created;	
	private String fix_created;	
	
	
}