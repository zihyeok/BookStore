package com.book.store.dto;

import lombok.Data;

@Data
public class BookDTO {
		
	private int seq_No;
	private String isbn_Thirteen_No;
	private String title_Nm;
	private String authr_Nm;
	private String publisher_Nm;
	private int prc_Value;
	private String image_Url;
	private String book_Intrcn_Cn;
	private String kdc_Nm;
	private String pblicte_De;
	private int sal_Count;
	
}
