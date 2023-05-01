package com.book.store.util;

import org.springframework.stereotype.Service;

@Service
public class BoardUtil {
	
	//전체 페이지 갯수를 구하는 메소드
		public int getPageCount(int numPerPage, int dataCount) {
			
								//      3            34 
								// numPerPage : 한 페이지에 몇개의 데이터
								// 전체 데이터 개수와 몇개를 뿌릴건지
								// 34개와 하나의 페이지에 몇개를 뿌릴건지 
			
			int pageCount = 0;
			
			// 전체 데이터 개수를 하나의 페이지에 몇개를 뿌릴건지 나눔
			pageCount = dataCount / numPerPage;// 페이지 몇장이 필요한지 알아냄
			
			if(dataCount%numPerPage != 0) {// 34 % 3 == 0으로 떨어지지않으면 페이지 하나더 필요
				pageCount++;
			}
			
			return pageCount; // 전체 페이지 개수
		}
		
		//Ajax페이징 링크
		//자바스크립트로 페이징 처리
		// 현재 내가 어떤페이지를 보고있는지 , 전체페이지 몇장 , list.jsp
		public String pageIndexList(int currentPage, int totalPage) {
			
			int numPerBlock = 5; //선택하는 페이지의 갯수 
			// 5개 표시 ◀이전 6 7 8 9 10 다음▶
			
			int currentPageSetUp; //이전 페이지의 번호(현재가 6~10이면 이전페이지는 5)
			//  ◀   내가 이전을 누르게되면 5페이지가 나옴 (왼쪽 화살표 값)
			
			int page; // for문의 i 6 7 8 9 10
			String strList = "";
			
			if (currentPage==0) {
				// 현재페이지또는 전체페이지 0이면 null 반환
				return "";
			}
			
			//표시할 첫 페이지
			currentPageSetUp = (currentPage/numPerBlock)*numPerBlock;
			
			if (currentPage%numPerBlock==0) {
				//currentPage가 5페이지 numPerBlock 5면 numPerBlock마지막인 5 다음 페이지가
				//없기때문에 강제로
				//setup을 0으로 만들어서 다음▶를 안나타나게
				
				currentPageSetUp = currentPageSetUp-numPerBlock;
			}
			
			//이전
			if (totalPage>numPerBlock && currentPageSetUp>0) {
				strList += "<a onclick='listPage(" + currentPageSetUp +
						");'>◀이전</a>&nbsp;";
			}
			
			//page
			page = currentPageSetUp + 1;
			
			while((page<=totalPage)&&(page<=currentPageSetUp+numPerBlock)) {
				
				if(page==currentPage) {
					
					strList += "<font color='gray'>" + page + "</font>&nbsp;";
					
				}else {
					strList += "<a onclick='listPage(" + page + ");'>" + page + "</a>&nbsp;"; 
				}
				
				page++;
				
			}
			
			//다음
			if (totalPage-currentPageSetUp > numPerBlock) {
				strList += "<a onclick='listPage(" + page +");'>다음▶</a>&nbsp;";
			}
			
			
			
			return strList;
		}
		
		
		//페이징 링크
		public String pageIndexList(int currentPage, int totalPage, String listUrl) {
			
			int numPerBlock = 5; //선택하는 페이지의 갯수
			int currentPageSetup; //이전 페이지의 번호(현재가 6~10이면 이전페이지는 5)
			int page;
			
			StringBuffer sb = new StringBuffer();
			
			if(currentPage==0 || totalPage==0) {
				return "";
			}
			
			//listUrl
			//list.jsp //그냥 할시
			//list.jsp?searchKey=subject&seatchValue=suzi //검색할시
			
			
			// ? 가 있는지 찾아본다. 
			if(listUrl.indexOf("?")!=-1) { //뒤에 ?,& 뭘 붙일지 구분하기 위해
				listUrl += "&"; //?가 있다면
			}else {
				listUrl += "?"; //?가 없다면
			}
			
			//이전 페이지 번호 생성
			// ◀이전값  5           6           5            5 
			currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
			
			//현재 페이지가 보여지는 페이지에서 가장 큰 값일때
			//결과값에 numPerBlock을 빼줘야 한다.
			if(currentPage % numPerBlock ==0) {// 10으로 나눴을때 0이므로
//					5				5		5
				currentPageSetup = currentPageSetup-numPerBlock; // 10이여도 5가 나옴
			}
			
			// 위에 공식으로 5가 들어가면 ◀이전
			
			// ◀이전
			//		12         5       뒤에 조건 5 이면  ◀이전 만들어라.   
						
			//◀이전 필요하면 한번만 만들고 필요하지않으면 안만듬
			
			
			//◀이전
			//전체 페이지가 보여질 페이지 수보다 크고,
			//이전 페이지가 0보다 커야만 이전 버튼을 넣을 수 있다
			//12>5 && 5>0 : 이전페이지 필요, 12>5 && 0>0 : 이전 페이지 필요 없음
			if(totalPage>numPerBlock && currentPageSetup>0) {
//				15			5				6
				
				sb.append("<a href=\"" + listUrl + "pageNum=" + 
						currentPageSetup + "\">◀이전</a>&nbsp;");
				//<a href="list.jsp?pageNum=5">◀이전<a/>nbsp;
				//\" : 따옴표의 기능을 하지 않고, 글자 자체가 따옴표라는 표시
				//앞의 "<a의 따옴표와 엮이면 안되므로 저렇게 선언
				// \">◀이전 의 \"와 엮이는 따옴표
			}
			// 바로가기 페이지  6 7 8 9 10
			// 5개찍어야함
			// page = 현재페이지
		//   6           5
			page = currentPageSetup + 1; //시작점은 이전 페이지의 +1
			// 시작값 ◀이전 부터  6,7,8,9,10
			
			//표시할 페이지가 전체 페이지보다 작아야하고
			//설정한 페이지의 개수만큼 보여주기 위해
//			 		  6        12       6       5(◀이전값)      5
			while(page<=totalPage && page<=(currentPageSetup+numPerBlock)) {
				// 만족하면 6부터 10까지 찍힘
				
				// 현재 페이지가 내가 보고있는 페이지랑 같으면 색깔로 지정 
				if(page == currentPage) {
					
					sb.append("<font color=\"Black\">" + page + "</font>&nbsp;");
					//<font color="Fuchsia">6</font>&nbsp;
				}else {
					// 같지않으면
					// 7 페이지면?
					//<a href="list.jsp?pageNum=7">7</a>&nbsp;
					sb.append("<a href=\""+ listUrl + "pageNum=" + page + "\">" +
							page + "</a>&nbsp;");
					//<a href=list.jsp?pageNum=7">7</a>&nbsp;
				}
				
				page++; // 6 7 8 9 10 찍음
				
			}
			
			// 다음▶
					// ◀이전 6 7 8 9 10 다음▶
					// ◀이전 11 12
					//(위에꺼)   12             5              5   다음버튼생김
					//(밑에꺼)   35            10              5   다음버튼안생김
			if(totalPage-currentPageSetup>numPerBlock) {
				
				sb.append("<a href=\"" + listUrl + "pageNum=" + page + 
						"\">다음▶</a>&nbsp;");
				//<a href="list.jsp?pageNum=11">다음▶</a>&nbsp;
			}
			
			return sb.toString();
			
		}
	
}
