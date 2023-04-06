package com.book.store.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.store.dto.BookDTO;
import com.book.store.service.BookItemService;
import com.book.store.util.MyUtil;

//패키지 이름 com.book.store이여야만 함

@RestController
public class BookController {

	@Resource
	private BookItemService bookItemService;

	@Autowired
	private MyUtil myUtil;

	@RequestMapping("/main")
	public ModelAndView home() throws Exception{
		//메인화면으로 이동
		ModelAndView mav = new ModelAndView();

		mav.setViewName("Main");

		return mav;
	}

	@GetMapping("/created.action")
	public ModelAndView itemCreated() throws Exception{
		//신규상품 등록 홈페이지로 이동
		ModelAndView mav = new ModelAndView();

		mav.setViewName("created");

		return mav;

	}

	@PostMapping("/created.action")
	public ModelAndView itemCreated_ok(BookDTO dto) throws Exception{
		//새로운 책을 등록하고자 할때
		ModelAndView mav = new ModelAndView();

		int maxNum = bookItemService.maxNum();

		dto.setSeq_No(maxNum);//일련번호 매기기

		bookItemService.insertData(dto);

		mav.setViewName("/BookList.action");

		return mav;

	}

	@GetMapping("/BookList.action")
	public ModelAndView list(HttpServletRequest request) throws Exception{

		String pageNum = request.getParameter("pageNum");

		int currentPage = 1; //첫화면은 1페이지 

		if(pageNum!=null) {

			currentPage = Integer.parseInt(pageNum);
			//1페이지가 아닌 get방식 주소로 받은 pageNum로 변경
		}

		String searchKey = request.getParameter("searchKey");
		//searchKey는 작가,제목,도서번호
		String searchValue = request.getParameter("searchValue");

		if(searchValue==null) {

			searchKey = "title_Nm";
			searchValue = "";

		}else {//get방식으로 오는거 대소문자 상관없이 searchValue를 utf-8로 디코드
			if(request.getMethod().equalsIgnoreCase("GET")) {
				searchValue = URLDecoder.decode(searchValue,"utf-8");
			}
		}

		int dataCount = bookItemService.getDataCount(searchKey, searchValue);
		//searchKey를 매개변수로 인식하지 못하는 현상 발행 - Mapper.java에 @Param을 붙여서 인식하게 만듬
		int numPerPage = 9;
		//한페이지에 9개의 아이템

		int totalPage = myUtil.getPageCount(numPerPage, dataCount);

		if(currentPage>totalPage) { //항목 삭제해서 페이지에 아무것도 없는데 페이지가 유지되는 상황을 막기위해
			currentPage=totalPage; }

		int start = (currentPage-1)*numPerPage+1; int end = currentPage*numPerPage;
		

		List<BookDTO> lists = bookItemService.getLists(start, end, searchKey,searchValue);
		//Mapper.xml에서 TO_CHAR부분 에러발생 데이터 형식이 이미 2023-04-05형태여서 그런듯
	
		String param = ""; if(searchValue!=null&&!searchValue.equals("")) { param =
				"searchKey=" + searchKey; param+= "&searchValue=" +
						URLEncoder.encode(searchValue,"utf-8"); }

		String listUrl = "/BookList.action";

		if(!param.equals("")) { listUrl += "?" + param; }

		String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);

		String articleUrl = "/BookArticle.action?pageNum=" + currentPage;

		if(!param.equals("")) { articleUrl += "&" + param; }

		ModelAndView mav = new ModelAndView();

		mav.addObject("lists", lists); 
		mav.addObject("pageIndexList", pageIndexList);
		mav.addObject("dataCount", dataCount); 
		mav.addObject("articleUrl",articleUrl); 
		mav.addObject("pageNum", currentPage);

		mav.setViewName("BookList");
		//진짜 주소로 가서 이걸 뿌려줘야 함

		return mav;
	}

	@GetMapping("/BookArticle.action")
	public ModelAndView article(HttpServletRequest request) throws Exception{

		int seq_No = Integer.parseInt(request.getParameter("seq_No"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));

		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");

		if(searchValue!=null) {
			searchValue = URLDecoder.decode(searchValue,"utf-8");
		}

		BookDTO dto = bookItemService.getReadData(seq_No);

		if(dto==null) {

			ModelAndView mav = new ModelAndView();

			mav.setViewName("redirect:BookList.action?pageNum=" + pageNum 
					+ "&searchKey=" + searchKey + "&searchValue=" + searchValue);

			return mav;

		}

		String param = "pageNum=" + pageNum;

		if(searchValue!=null && !searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		}

		ModelAndView mav = new ModelAndView();

		mav.addObject("dto", dto);
		mav.addObject("params", param);
		mav.addObject("pageNum", pageNum);

		mav.setViewName("BookArticle");

		return mav;

	}

	@GetMapping("/BookUpdate.action")
	public ModelAndView updated(HttpServletRequest request) throws Exception{

		int seq_No = Integer.parseInt(request.getParameter("seq_No"));
		String pageNum = request.getParameter("pageNum");

		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");

		if(searchValue!=null) {
			searchValue = URLDecoder.decode(searchValue,"utf-8");
		}

		BookDTO dto = bookItemService.getReadData(seq_No);

		if(dto==null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:BookList.action?pageNum=" + pageNum 
					+ "&searchKey=" + searchKey + "&searchValue=" + searchValue);
			return mav;
		}

		String param = "pageNum=" + pageNum;

		if(searchValue!=null && !searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		}

		ModelAndView mav = new ModelAndView();

		mav.addObject("dto", dto);
		mav.addObject("pageNum", pageNum);
		mav.addObject("params", param);
		mav.addObject("searchKey", searchKey);
		mav.addObject("searchValue", searchValue);

		mav.setViewName("BookUpdate");

		return mav;

	}

	@PostMapping("/BookUpdate.action")
	public ModelAndView updated_ok(HttpServletRequest request,BookDTO dto) throws Exception{

		String pageNum = request.getParameter("pageNum");
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");

		bookItemService.updateData(dto);

		String param = "pageNum=" + pageNum;

		if(searchValue!=null && !searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		}


		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:BookList.action?" + param);

		return mav;

	}

	@GetMapping("/BookDelete.action")
	public ModelAndView deleted_ok(HttpServletRequest request) throws Exception{

		int seq_No = Integer.parseInt(request.getParameter("seq_No"));
		String pageNum = request.getParameter("pageNum");
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");

		bookItemService.deleteData(seq_No);

		String param = "pageNum=" + pageNum;

		if(searchValue!=null && !searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		}

		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:BookList.action?" + param);

		return mav;

	}


}
