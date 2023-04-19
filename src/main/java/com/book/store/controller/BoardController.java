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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.store.dto.BoardAnswerDTO;
import com.book.store.dto.BoardDTO;
import com.book.store.service.BoardAnswerService;
import com.book.store.service.BoardAnswerServiceImpl;
import com.book.store.service.BoardService;
import com.book.store.util.BoardUtil;
import com.book.store.util.MyUtil;

//@RequestMapping("/board") 나중에 쓸꺼
@RestController// Json 형태로 객체 데이터를 반환
public class BoardController {
	
	@Resource
	private BoardService boardService;//호출하면 BoardServiceImpl이 딸려들어옴
	
	@Resource
	BoardAnswerService BoardAnswerService = new BoardAnswerServiceImpl();
	
	@Autowired
	BoardUtil BoardUtil;
	
	
	@Autowired
	MyUtil myUtil; //@Service로 구현된 MyUtil을 불러온것
	
	
	@GetMapping("/")
	public ModelAndView index() throws Exception{

		ModelAndView mav = new ModelAndView();

		mav.setViewName("paymentlist");
		//jsp(html)로 갈때는 setViewName /class로 갈때는 setView
		
		return mav;

	}
		
		@GetMapping("/Board.action")
		public ModelAndView created(BoardDTO dto,HttpServletRequest request) throws Exception{
				
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName("boardcreated");
			 //(html)로 갈때는 setViewName /class로 갈때는 setView
			
			return mav;

		}
		
		@PostMapping("/BoardCreated.action")
		public ModelAndView created_ok(BoardDTO dto,HttpServletRequest request) throws Exception{

			ModelAndView mav = new ModelAndView();
			
			int maxNum = boardService.maxNum();
			
			dto.setBoardId(maxNum+1);
			
			boardService.insertData(dto);

			mav.setViewName("redirect:/BoardList.action");

			return mav;

		}
		
	
		@RequestMapping("/BoardList.action")
		public ModelAndView list(BoardDTO dto,HttpServletRequest request) throws Exception{
			
			
			String pageNum = request.getParameter("pageNum");
		    int currentPage = 1;
			
			if (pageNum != null) {
		        currentPage = Integer.parseInt(pageNum);
		    }
			
			String searchKey = request.getParameter("searchKey");
		    String searchValue = request.getParameter("searchValue");
		    
			
			if (searchValue == null) {
		        searchKey = "subject";
		        searchValue = "";
		    } else {
		        if (request.getMethod().equalsIgnoreCase("GET")) {
		            searchValue = URLDecoder.decode(searchValue, "utf-8");
		        }
		    }
			
			int dataCount = boardService.getDataCount(searchKey, searchValue);
			//System.out.println(dataCount); 4
			
			int numPerPage = 3;
		    int totalPage = 0;

		    if (dataCount != 0) {
		        totalPage = myUtil.getPageCount(numPerPage, dataCount);
		    }

		    if (currentPage > totalPage) {
		        currentPage = totalPage;
		    }
			
			int start = (currentPage - 1) * numPerPage + 1;
		    int end = currentPage * numPerPage;

		    List<BoardDTO> lists = boardService.getLists(start, end, searchKey, searchValue);
		    

			String param = "";

		    if (searchValue != null && !searchValue.equals("")) {
		        param = "searchKey=" + searchKey;
		        param += "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		    }

		    String listUrl = "/BoardList.action";
		    if (!param.equals("")) {
		        listUrl += "?" + param;
		    }
			
		    String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);

		    String articleUrl = "/BoardArticle.action?pageNum=" + currentPage;

		    if (!param.equals("")) {
		        articleUrl += "&" + param;
		    }
			
		    
			//ModelAndView로 전송
			ModelAndView mav = new ModelAndView();
			
			
			//포워딩할 데이터
			mav.addObject("lists", lists);
			mav.addObject("pageIndexList", pageIndexList);
			mav.addObject("dataCount", dataCount);
			mav.addObject("articleUrl", articleUrl);
			//System.out.println(lists+"김치");
			
			//mav.addObject("pageNum", currentPage);//3번째 방법시 같이넘겨야함
			
			
			mav.setViewName("boardlist");

			return mav;
			
		}
		
	
		@GetMapping("/BoardArticle.action")
		public ModelAndView article(HttpServletRequest request) throws Exception{
			
			ModelAndView mav = new ModelAndView();
			
			int boardId = Integer.parseInt(request.getParameter("num"));
			
			String pageNum = request.getParameter("pageNum");
			
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
			
			boardService.updateHitCount(boardId);
			
			BoardDTO dto = boardService.getReadData(boardId);
			
			if(dto==null) {
				
				mav.setViewName("redirect:BoardList.action?pageNum=" + pageNum 
						+ "&searchKey=" + searchKey + "&searchValue=" + searchValue);
				
				return mav;
			}
			
			
			//dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
			
			
			//이전글
			String subject = request.getParameter("subject");
			
			BoardDTO preDTO = boardService.preReadData(boardId, subject, searchKey, searchValue);
			
			int preNum=0;
			String preSubject = "";
			if(preDTO!=null) {
			    preNum = preDTO.getBoardId();
			    preSubject=preDTO.getSubject();
			}
			
			BoardDTO nextDTO = boardService.nextReadData(boardId, subject, searchKey, searchValue);
		
			int nextNum=0;
			String nextSubject = "";
			if(nextDTO!=null) {
				nextNum = nextDTO.getBoardId();
				nextSubject=nextDTO.getSubject();
			}
			
			
			String param = "pageNum=" + pageNum;
			
			if(searchValue!=null && !searchValue.equals("")) {
				param += "&searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
			}
			
			mav.addObject("preNum", preNum);
			mav.addObject("preSubject", preSubject);
			mav.addObject("nextNum", nextNum);
			mav.addObject("nextSubject", nextSubject);
		
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
		

		@PostMapping("/AnswerCreated.action")
		public ModelAndView AnswerCreated(BoardAnswerDTO dto,HttpServletRequest request) throws Exception{
			
			int boardId = Integer.parseInt(request.getParameter("boardId"));
			
			int maxNum = BoardAnswerService.maxNum();
			
			dto.setAnswerNum(maxNum+1);
			dto.setBoardId(boardId);
			
			BoardAnswerService.insertData(dto);
			
			ModelAndView mav = new ModelAndView();
			
			
			mav.setViewName("redirect:/AnswerList.action");

			return mav;

		}
		
		@RequestMapping("/AnswerList.action")
		public ModelAndView AnswerList(BoardAnswerDTO dto,HttpServletRequest request) throws Exception{
			
			int boardId = dto.getBoardId();

			String pageNum = request.getParameter("pageNum");//문자만 따온건가?
			
			int currentPage = 1;
			int numPerPage = 3;
			
			if(pageNum!=null && !pageNum.equals("")) {
				currentPage = Integer.parseInt(pageNum);
				//1페이지가 아닌 get방식 주소로 받은 pageNum로 변경
			}
			
			int dataCount = BoardAnswerService.getDataCount(boardId);
			//DataCount가 boardNum을 받았기 때문에  
			//일련번호는 각 boardNum마다 다르게 출력됨
			
			int totalPage = myUtil.getPageCount(numPerPage, dataCount);

			if(currentPage>totalPage) {
				currentPage=totalPage;
			}
			
			int start = (currentPage-1)*numPerPage+1;
			int end = currentPage*numPerPage;	
			
			List<BoardAnswerDTO> lists = BoardAnswerService.getLists(start, end, boardId);
			
			
		
			String pageIndexList = BoardUtil.pageIndexList(currentPage, totalPage);
			
			//ModelAndView로 전송
			ModelAndView mav = new ModelAndView();// "jsonView"
			
			
			//포워딩할 데이터
			mav.addObject("lists", lists);
			mav.addObject("pageIndexList", pageIndexList);
			mav.addObject("boardId", boardId);
			mav.addObject("pageNum", currentPage);
			
			
			
			
			mav.setViewName("boardAnswerList");

			return mav;
			
		}
		
		@RequestMapping("/AnswerDeleted.action")
		public ModelAndView AnswerDeleted(BoardAnswerDTO dto,HttpServletRequest request) throws Exception{
			
			int answerNum = Integer.parseInt(request.getParameter("answerNum"));
			
			int boardId = dto.getBoardId();
			
			BoardAnswerService.deleteData(answerNum);
			
			ModelAndView mav = new ModelAndView();
			
			mav.addObject("boardId", boardId);
			mav.setViewName("redirect:/AnswerList.action");
			
			return mav;
			
		}
		
		@GetMapping("/AnswerUpdated.action")
		public ModelAndView AnswerUpdated(HttpServletRequest request) throws Exception{
			
			ModelAndView mav = new ModelAndView();
			
			int answerNum = Integer.parseInt(request.getParameter("answerNum"));
			String pageNum = request.getParameter("pageNum");
			
			int boardId = Integer.parseInt(request.getParameter("num"));
			
		
			BoardAnswerDTO dto = BoardAnswerService.getReadData(answerNum);
			
			if(dto==null) {
				
				mav.setViewName("redirect:BoardList.action?pageNum=" + pageNum + "&num=" + boardId);
				
				return mav;
			}
			
			String param =  "pageNum=" + pageNum + "&num=" + boardId ;
		
			mav.addObject("dto", dto);
			mav.addObject("pageNum", pageNum);
			mav.addObject("params", param);
			mav.addObject("boardId", boardId);
			
			mav.setViewName("boardAnswerUpdated");
			
			return mav;
		}
		
		@RequestMapping(value = "/AnswerUpdated_ok.action",method = {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView AnswerUpdated_ok(BoardAnswerDTO dto,HttpServletRequest request) throws Exception{
			
			
			String pageNum = request.getParameter("pageNum");
			int boardId = Integer.parseInt(request.getParameter("boardId"));
			//int boardId = dto.getBoardId();
		
			BoardAnswerService.updateData(dto);
			
			
			ModelAndView mav = new ModelAndView();
			
			String param =  "pageNum=" + pageNum + "&num=" + boardId ;
			
			
			mav.setViewName("redirect:/BoardArticle.action?" + param);
								
			return mav;
			
		}
		
		
		
}
