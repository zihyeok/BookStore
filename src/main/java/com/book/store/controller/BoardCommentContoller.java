package com.book.store.controller;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.store.dto.BoardCommentDTO;
import com.book.store.service.BoardCommentService;
import com.book.store.service.BoardCommentServiceImpl;
import com.book.store.util.BoardUtil;


@RestController
public class BoardCommentContoller {

	@Resource
	BoardCommentService boardCommentService = new BoardCommentServiceImpl();
	
	@Autowired
	BoardUtil myUtil;
	
	@RequestMapping("/CommentCreated.action")
	public ModelAndView commentCreated(BoardCommentDTO dto,HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		
		int maxNum = boardCommentService.maxNum();
		
		dto.setCommentNum(maxNum+1);
		dto.setBoardNum(boardNum);
		
		boardCommentService.insertData(dto);
		//System.out.println(dto+"=======================");
		
		//주소 확인
		mav.setViewName("redirect:/CommentList.action");

		return mav;

	}
	
	@RequestMapping("/CommentList.action")
	public ModelAndView CommentList(BoardCommentDTO dto,HttpServletRequest request) throws Exception{
		
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
	
		
		String pageNum = request.getParameter("pageNum");//문자만 따온건가?
		System.out.println(pageNum+"1923091203909103");
		System.out.println(boardNum+"========================");
		
		int currentPage = 1;
		int numPerPage = 3;
		int totalPage = 0;

		
		if(pageNum!=null && !pageNum.equals("")) {
			currentPage = Integer.parseInt(pageNum);
		}
		
		
		int dataCount = boardCommentService.getDataCount(boardNum);
		//DataCount가 boardNum을 받았기 때문에  
		//일련번호는 각 boardNum마다 다르게 출력됨
		
		if (dataCount!=0) {
			totalPage = myUtil.getPageCount(numPerPage, dataCount);
		}
		

		if(currentPage>totalPage) {
			currentPage=totalPage;
		}
		
		int start = (currentPage-1)*numPerPage+1;
		int end = currentPage*numPerPage;	
		
		List<BoardCommentDTO> lists = boardCommentService.getLists(start, end, boardNum);
		//System.out.println(lists);
		
		int listNum = 0;
		int commentCount = 0;
		
		//일련번호
		for (int i = 0; i < lists.size(); i++) {
		    BoardCommentDTO vo = lists.get(i);
		    listNum = dataCount - (start + i + 1);
		    vo.setListnum(listNum);
		    vo.setContent(vo.getContent().replaceAll("\n", "<br/>"));
		}
		
		//System.out.println(it+"--------------------------");
		
		String pageIndexList = myUtil.pageIndexList(currentPage, totalPage);
		
		//ModelAndView로 전송
		ModelAndView mav = new ModelAndView();
		
		
		//포워딩할 데이터
		mav.addObject("lists", lists);
		mav.addObject("pageIndexList", pageIndexList);
		mav.addObject("dataCount", dataCount);
		mav.addObject("pageNum", pageNum);
		
		//mav.addObject("pageNum", currentPage);//3번째 방법시 같이넘겨야함
		//System.out.println(pageNum);
		
		
		mav.setViewName("boardCommentList");

		return mav;
		
	}
	
	@RequestMapping("/CommentDeleted.action")
	public ModelAndView CommentDeleted(BoardCommentDTO dto,HttpServletRequest request) throws Exception{
		
		int num = Integer.parseInt(request.getParameter("boardNum"));
	
		boardCommentService.deleteData(num);
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/CommentList.action");
		
		return mav;
		
	}
	
}
