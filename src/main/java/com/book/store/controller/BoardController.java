package com.book.store.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.store.dto.BoardCommentDTO;
import com.book.store.dto.BoardDTO;
import com.book.store.service.BoardCommentService;
import com.book.store.service.BoardService;
import com.book.store.util.MyUtil;
import com.sun.xml.bind.v2.runtime.reflect.ListIterator;

//@RequestMapping("/board") 나중에 쓸꺼
@RestController// Json 형태로 객체 데이터를 반환
public class BoardController {
	
	@Resource
	private BoardService boardService;//호출하면 BoardServiceImpl이 딸려들어옴
	
	private BoardCommentService boardCommentService;
	
	@Autowired
	MyUtil myUtil; //@Service로 구현된 MyUtil을 불러온것
	
	
	@GetMapping("/")
	public ModelAndView index() throws Exception{

		ModelAndView mav = new ModelAndView();

		mav.setViewName("Main");
		//jsp(html)로 갈때는 setViewName /class로 갈때는 setView
		
		return mav;

	}
		
		@GetMapping("/Board.action")
		public ModelAndView created() throws Exception{

			ModelAndView mav = new ModelAndView();

			mav.setViewName("boardcreated");
			 //jsp(html)로 갈때는 setViewName /class로 갈때는 setView
			
			return mav;

		}
		
		@PostMapping("/BoardCreated.action")
		public ModelAndView created_ok(BoardDTO dto,HttpServletRequest request) throws Exception{

			ModelAndView mav = new ModelAndView();
			
			int maxNum = boardService.maxNum();
			
			dto.setBoardNum(maxNum+1);
			
			
			boardService.insertData(dto);

			mav.setViewName("redirect:/BoardList.action");

			return mav;

		}
		
	
		@GetMapping("/BoardList.action")
		public ModelAndView list(BoardDTO dto,HttpServletRequest request) throws Exception{
			
			
			String pageNum = request.getParameter("pageNum");//문자만 따온건가?
			
			int currentPage = 1;
			
			if(pageNum!=null) {
				currentPage = Integer.parseInt(pageNum);
			}
			
			String searchKey = request.getParameter("searchKey");
			String searchValue = request.getParameter("searchValue");
			
			if(searchValue==null) {
				searchKey = "subject";
				searchValue = "";
			}else {
				if(request.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue,"utf-8");
				}
			}
			
			int dataCount = boardService.getDataCount(searchKey, searchValue);
			
			int numPerPage = 3;
			int totalPage = 0;
			
			if (dataCount!=0) {
				totalPage = myUtil.getPageCount(numPerPage, dataCount);
			}
			
			
			if(currentPage>totalPage) {
				currentPage=totalPage;
			}
			
			int start = (currentPage-1)*numPerPage+1;
			int end = currentPage*numPerPage;
			
		
			List<BoardDTO> lists = boardService.getLists(start, end, searchKey, searchValue);
			
			int listNum,n = 0;
			int commentCount = 0;
			//일련번호
			
			/*88888888888888888888888888888888888888888888888888888888888888888
			 
			ListIterator<Object> it = (ListIterator<Object>) lists.listIterator(); 
			
			while (it.hasNext()) {

				BoardDTO vo = (BoardDTO)it.next();
				
				listNum = dataCount-(start+n-1);
				commentCount = boardCommentService.getDataCount(vo.getBoardNum());
				//boardNum에 따라 댓글 수가 달라야함
				vo.setListnum(listNum);
				vo.setCommentCount(commentCount);
				n++;
			}
			*/
			
			String param = "";
			
			if(searchValue!=null&&!searchValue.equals("")) {
				param = "searchKey=" + searchKey;
				param+= "&searchValue=" + URLEncoder.encode(searchValue,"utf-8");
			}
			
			String listUrl = "/BoardList.action";
			if(!param.equals("")) {
				
				listUrl += "?" + param;
			}
			
			String pageIndexList = 
					myUtil.pageIndexList(currentPage, totalPage, listUrl);
			
			String articleUrl = "/BoardArticle.action?pageNum=" + currentPage;
			
			if(!param.equals("")) {
				articleUrl += "&" + param;
			}
			
			
			//ModelAndView로 전송
			ModelAndView mav = new ModelAndView();
			
			
			//포워딩할 데이터
			mav.addObject("lists", lists);
			mav.addObject("pageIndexList", pageIndexList);
			mav.addObject("dataCount", dataCount);
			mav.addObject("articleUrl", articleUrl);
			//mav.addObject("pageNum", currentPage);//3번째 방법시 같이넘겨야함
			

			mav.setViewName("boardlist");

			return mav;
			
		}
		
	
		@GetMapping("/BoardArticle.action")
		public ModelAndView article(HttpServletRequest request) throws Exception{
			
			ModelAndView mav = new ModelAndView();
			
			int num = Integer.parseInt(request.getParameter("num"));
			
			String pageNum = request.getParameter("pageNum");
			
			String searchKey = request.getParameter("searchKey");
			String searchValue = request.getParameter("searchValue");
			
			if(searchValue!=null) {
				searchValue = URLDecoder.decode(searchValue,"utf-8");
			}
			
			boardService.updateHitCount(num);
			
			BoardDTO dto = boardService.getReadData(num);
			
			if(dto==null) {
				
				
				mav.setViewName("redirect:BoardList.action?pageNum=" + pageNum 
						+ "&searchKey=" + searchKey + "&searchValue=" + searchValue);
				
				return mav;
			}
			
			
			//dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
			
			String param = "pageNum=" + pageNum;
			
			if(searchValue!=null && !searchValue.equals("")) {
				param += "&searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
			}
			
//------------------------------------------------------------- 이전글
		
			
			String boardNum = request.getParameter("boardNum");
			String subject = request.getParameter("subject");
			
			BoardDTO preDTO = boardService.preReadData(boardNum, subject, searchKey, searchValue);
			
			int preNum=0;
			String preSubject = "";
			if(preDTO!=null) {
			    preNum = preDTO.getBoardNum();
			    preSubject=preDTO.getSubject();
			}
		
//-------------------------------------------------------------			
			
			
			/*
			 * request.setAttribute("preNum", preNum); 
			 * request.setAttribute("preSubject",preSubject);
			 * request.setAttribute("nextNum", nextNum);
			 * request.setAttribute("nextSubject", nextSubject);
			 */
			
			mav.addObject("preNum", preNum);
			mav.addObject("preSubject", preSubject);
			
			
			mav.addObject("dto", dto);
			mav.addObject("params", param);
			mav.addObject("pageNum", pageNum);
			
			mav.setViewName("boardarticle");
			
			return mav;
		}
		
		
		@GetMapping("/BoardUpdated.action")
		public ModelAndView updated(HttpServletRequest request) throws Exception{
			
			ModelAndView mav = new ModelAndView();
			
			int num = Integer.parseInt(request.getParameter("num"));
			String pageNum = request.getParameter("pageNum");
			
			String searchKey = request.getParameter("searchKey");
			String searchValue = request.getParameter("searchValue");
			
			if(searchValue!=null) {
				searchValue = URLDecoder.decode(searchValue,"utf-8");
			}
		
			BoardDTO dto = boardService.getReadData(num);
			
			if(dto==null) {
				
				mav.setViewName("redirect:BoardList.action?pageNum=" + pageNum);
				
				return mav;
			}
			
			String param = "pageNum=" + pageNum;
			
			if(searchValue!=null && !searchValue.equals("")) {
				param += "&searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
			}
			
			
			mav.addObject("dto", dto);
			mav.addObject("pageNum", pageNum);
			mav.addObject("params", param);
			mav.addObject("searchKey", searchKey);
			mav.addObject("searchValue", searchValue);
			
			mav.setViewName("boardupdated");
			
			return mav;
		}
		
		@RequestMapping(value = "/BoardUpdated_ok.action",method = {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView updated_ok(BoardDTO dto,HttpServletRequest request) throws Exception{
		
			String pageNum = request.getParameter("pageNum");
			
			String searchKey = request.getParameter("searchKey");
			String searchValue = request.getParameter("searchValue");
			
			if(searchValue!=null) {
				searchValue = URLDecoder.decode(searchValue,"utf-8");
			}
			
			boardService.updateData(dto);
			
			String param = "pageNum=" + pageNum;
			
			if(searchValue!=null && !searchValue.equals("")) {
				param += "&searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
			}
			
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName("redirect:/BoardList.action?" + param);
			
			return mav;
			
		}
		

		@GetMapping("/BoardDeleted_ok.action")
		public ModelAndView deleted_ok(HttpServletRequest request) throws Exception{
			
			int num = Integer.parseInt(request.getParameter("num"));
			String pageNum = request.getParameter("pageNum");
			
			String searchKey = request.getParameter("searchKey");
			String searchValue = request.getParameter("searchValue");
			
			if(searchValue!=null) {
				searchValue = URLDecoder.decode(searchValue,"utf-8");
			}
			
			boardService.deleteData(num);
			
			String param = "pageNum=" + pageNum;
			
			if(searchValue!=null && !searchValue.equals("")) {
				param += "&searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
			}
			
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName("redirect:/BoardList.action?" + param);
			
			return mav;
			
		}

	
	
}
