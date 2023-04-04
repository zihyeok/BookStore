package com.book.dto;

import lombok.Data;

@Data
public class BookDTO {
		
	private int SEQ_NO;
	private String ISBN_THIRTEEN_NO;
	private String TITLE_NM;
	private String AUTHR_NM;
	private String PUBLISHER_NM;
	private int PRC_VALUE;
	private String IMAGE_URL;
	private String BOOK_INTRCN_CN;
	private String KDC_NM;
	private String PBLICTE_DE;
	private int SAL_COUNT;
	
}
