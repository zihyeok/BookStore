package com.book.util;

import org.springframework.stereotype.Service;

@Service
public class MyUtil {//Page처리
	
	//전체 페이지의 갯수를 구하는 메소드
	public int getPageCount(int numPerPage, int dataCount) {
		
		int pageCount = 0;
		
		pageCount = dataCount / numPerPage;
		
		if(dataCount % numPerPage != 0) {
			
			pageCount++;
			
		}
		
		return pageCount;
		
	}
	
	//페이징 링크
	
	public String pageIndexList(int currentPage,int totalPage,String listUrl) {
		
		int numPerBlock = 5;
		int currentPageSetup;
		int page;
		
		StringBuffer sb = new StringBuffer();
		
		if(currentPage==0||totalPage==0) {
			return "";
		}
		
		//listUrl
		//list.jsp?pageNum=3
		//list.jsp?searchKey=subject&searchValue=suzi&pageNum=3
		if(listUrl.indexOf("?")!=-1) {
			
			listUrl = listUrl + "&";
			
		}else {
			
			listUrl = listUrl + "?";
			
		}
		//이전 페이지 번호 생성
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		
		if(currentPage % numPerBlock == 0) {
			//10%5
			currentPageSetup = currentPageSetup-numPerBlock;
			
		}
		
		//◀이전
		if(totalPage>numPerBlock && currentPageSetup>0) {
			//get방식으로 pageNum 넘기는 거임 그래서 servlet에서 req.getparameter(pageNum)이 가능한것
			//currentPageSetup은 ◀이전 을 생기게할지 말지 판단하는 조건임
			sb.append("<a href=\"" + listUrl + "pageNum=" + 
						currentPageSetup + "\">◀이전</a>&nbsp;");
			//<a href="list.jsp?pageNum=5">◀이전</a>&nbsp;
			//\"는 "를 문자로 보라는 표시
		}
		
		//바로가기 페이지
		page = currentPageSetup + 1;
										//numPerBlock의 최대값 나오게하려고
		while(page<=totalPage && page<=(currentPageSetup+numPerBlock)) {//이조건을 만족할때까지 계속 찍어
			
			if(page == currentPage) {
				
				sb.append("<font color=\"Fuchsia\">" + page + "</font>&nbsp;");
				
			}else {
				
				sb.append("<a href=\""+ listUrl + "pageNum=" + page + "\">" +
								page + "</a>&nbsp;");
				//<a href="list.jsp?pageNum=7">7</a>&nbsp;
				
			}
			
			page++;//이게 있어야 
		}
		
		//다음▶
		if(totalPage-currentPageSetup>numPerBlock) {
			
			sb.append("<a href=\"" + listUrl + "pageNum=" + page + 
						"\">다음▶</a>&nbsp;");
			
		}
		
		return sb.toString();
		
	}
	

}