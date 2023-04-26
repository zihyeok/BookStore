package com.book.store.dto;

import lombok.Data;

@Data
public class EventBoardDTO {
	
	private int boardId;
	private String userId;
	private String pwd;
	private String subject;
	private String content;
	private String image_Url;
	private String ipAddr;
	private String created;
	private int hitCount;

}
