package com.book.store.dto;

import lombok.Data;

@Data
public class ReviewDTO {		
	
	private int reviewId;
<<<<<<< HEAD
	private int seq_No;	
	private int reviewStar;
	
	private String userId;
=======
	private int seq_No;
	private String userName;
>>>>>>> 05c698529a9eaf2e9e1ae2024591b9a32f13f822
	private String content;	
	
	private String ipAddr;
	
	private String created;	
	private String fix_created;	
	
	
}