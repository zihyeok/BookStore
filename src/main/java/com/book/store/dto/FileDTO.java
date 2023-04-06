package com.book.store.dto;

import lombok.Data;

@Data
public class FileDTO {
	
	private String fileName;
	private String contentType;
	private String filePath;
}
