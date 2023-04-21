/*
 * package com.book.store.controller;
 * 
 * import java.net.URLDecoder; import java.net.URLEncoder; import
 * java.util.List;
 * 
 * import javax.annotation.Resource; import
 * javax.servlet.http.HttpServletRequest; import javax.servlet.http.HttpSession;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.RestController; import
 * org.springframework.web.multipart.MultipartFile; import
 * org.springframework.web.servlet.ModelAndView;
 * 
 * import com.book.store.dto.BookDTO; import com.book.store.dto.EventBoardDTO;
 * import com.book.store.service.EventBoardService; import
 * com.book.store.user.UserData; import com.book.store.util.FileManager_evt;
 * import com.book.store.util.MyUtil;
 * 
 * @RestController public class EventBoardController {
 * 
 * @Resource private EventBoardService eventBoardService;
 * 
 * @Resource HttpSession httpSession;
 * 
 * @Autowired private MyUtil myUtil;
 * 
 * @GetMapping("/event") public ModelAndView index() throws Exception{
 * 
 * ModelAndView mav = new ModelAndView();
 * 
 * mav.setViewName("#");
 * 
 * return mav;
 * 
 * }
 * 
 * @GetMapping("/eventCreated.action") public ModelAndView created() throws
 * Exception{ //신규상품 등록 홈페이지로 이동 ModelAndView mav = new ModelAndView();
 * 
 * mav.setViewName("event/eventCreated");
 * 
 * return mav;
 * 
 * }
 * 
 * @PostMapping("/eventCreated.action") public ModelAndView
 * created_ok(EventBoardDTO dto,@RequestParam(value = "upload",required = false)
 * List<MultipartFile> upload, HttpServletRequest request) throws Exception{
 * //값을 html에서 보내면 자동으로 EventBoardDTO에 장착 //requestparam(value="html에서의 name",
 * required 는 해당 매개변수가 필수면 true 아니면 false
 * 
 * ModelAndView mav = new ModelAndView();
 * 
 * int maxNum = eventBoardService.maxNum();
 * 
 * dto.setBoardId(maxNum+1); dto.setIpAddr(request.getRemoteAddr());
 * 
 * eventBoardService.insertData(dto);
 * 
 * FileManager_evt.doFileUpload(dto, upload);
 * 
 * eventBoardService.insertData(dto);
 * 
 * mav.setViewName("redirect:/eventList.action");
 * 
 * return mav;
 * 
 * }
 * 
 * @GetMapping("/eventList.action") //html form에 POST>GET으로 바꿔야 검색 됨 public
 * ModelAndView list(EventBoardDTO dto, HttpServletRequest request) throws
 * Exception{
 * 
 * String pageNum = request.getParameter("pageNum");
 * 
 * int currentPage = 1; //첫화면은 1페이지
 * 
 * if(pageNum!=null) {
 * 
 * currentPage = Integer.parseInt(pageNum); //1페이지가 아닌 get방식 주소로 받은 pageNum로 변경
 * }
 * 
 * String searchKey = request.getParameter("searchKey"); //searchKey는 작가,제목,도서번호
 * String searchValue = request.getParameter("searchValue");
 * 
 * if(searchValue==null) {
 * 
 * searchKey = "subject"; searchValue = "";
 * 
 * }else {//get방식으로 오는거 대소문자 상관없이 searchValue를 utf-8로 디코드
 * if(request.getMethod().equalsIgnoreCase("GET")) { searchValue =
 * URLDecoder.decode(searchValue,"utf-8"); } }
 * 
 * int dataCount = eventBoardService.getDataCount(searchKey, searchValue);
 * //searchKey를 매개변수로 인식하지 못하는 현상 발행 - Mapper.java에 @Param을 붙여서 인식하게 만듬 int
 * numPerPage = 9; //한페이지에 9개의 아이템
 * 
 * int totalPage = myUtil.getPageCount(numPerPage, dataCount);
 * 
 * if(currentPage>totalPage) { currentPage=totalPage; }
 * 
 * int start = (currentPage-1)*numPerPage+1; int end = currentPage*numPerPage;
 * 
 * List<EventBoardDTO> lists = eventBoardService.getLists(start, end,
 * searchKey,searchValue); //Mapper.xml에서 TO_CHAR부분 에러발생 데이터 형식이 이미
 * 2023-04-05형태여서 그런듯
 * 
 * for (int i = lists.size(); i < numPerPage; i++) {
 * 
 * lists.add(null); //list칸수 맞출려고 강제로 null값 주입 }
 * 
 * String param = ""; if(searchValue!=null&&!searchValue.equals("")) { param =
 * "searchKey=" + searchKey; param+= "&searchValue=" +
 * URLEncoder.encode(searchValue,"utf-8"); }
 * 
 * String listUrl = "/eventList.action";
 * 
 * if(!param.equals("")) { listUrl += "?" + param; }
 * 
 * String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
 * 
 * String articleUrl = "/eventArticle.action?pageNum=" + currentPage;
 * 
 * if(!param.equals("")) { articleUrl += "&" + param; }
 * 
 * ModelAndView mav = new ModelAndView();
 * 
 * mav.addObject("lists", lists); mav.addObject("pageIndexList", pageIndexList);
 * mav.addObject("articleUrl",articleUrl); mav.addObject("pageNum",
 * currentPage);
 * 
 * //통합검색 결과 갯수 확인 mav.addObject("dataCount", dataCount);
 * mav.addObject("searchValue", searchValue); //이걸로 통합검색 창 뜨게할지 안 할지 확인
 * mav.addObject("searchKey", searchKey);
 * 
 * 
 * mav.setViewName("event/eventList"); //진짜 주소로 가서 이걸 뿌려줘야 함
 * 
 * return mav; }
 * 
 * 
 * @GetMapping("/eventArticle.action") public ModelAndView
 * article(HttpServletRequest request) throws Exception{ //값을 html에서 보내면 자동으로
 * BoardDTO에 장착
 * 
 * ModelAndView mav = new ModelAndView();
 * 
 * 
 * UserData user = null; if(httpSession.getAttribute("user") != null) { user =
 * (UserData) httpSession.getAttribute("user"); mav.addObject("userId",
 * user.getUserId()); }else if(httpSession.getAttribute("OauthUser") != null) {
 * user = (UserData) httpSession.getAttribute("OauthUser");
 * mav.addObject("userId", user.getUserId()); }
 * 
 * 
 * int boardId = Integer.parseInt(request.getParameter("boardId"));
 * 
 * String pageNum = request.getParameter("pageNum");
 * 
 * //pageNum을 무조건 받아야 article로 들어오는데 주소에 &pageNum 일일이 쓰기 귀찮아서 이렇게 함
 * if(pageNum==null) {
 * 
 * pageNum = "1"; Integer.parseInt(pageNum);
 * 
 * }else {
 * 
 * Integer.parseInt(pageNum);
 * 
 * }
 * 
 * String searchKey = request.getParameter("searchKey"); String searchValue =
 * request.getParameter("searchValue");
 * 
 * if(searchValue!=null) { searchValue = URLDecoder.decode(searchValue,"utf-8");
 * }
 * 
 * EventBoardDTO dto = eventBoardService.getReadData(boardId);
 * 
 * if(dto==null) {
 * 
 * mav.setViewName("redirect:eventList.action?pageNum=" + pageNum +
 * "&searchKey=" + searchKey + "&searchValue=" + searchValue);
 * 
 * return mav;
 * 
 * }
 * 
 * String param = "pageNum=" + pageNum;
 * 
 * if(searchValue!=null && !searchValue.equals("")) { param += "&searchKey=" +
 * searchKey; param += "&searchValue=" + URLEncoder.encode(searchValue,
 * "utf-8"); }
 * 
 * 
 * mav.addObject("dto", dto); mav.addObject("params", param);
 * mav.addObject("pageNum", pageNum);
 * 
 * mav.setViewName("event/eventArticle");
 * 
 * return mav;
 * 
 * }
 * 
 * @GetMapping("/BookUpdate.action") public ModelAndView
 * updated(HttpServletRequest request) throws Exception{
 * 
 * int seq_No = Integer.parseInt(request.getParameter("seq_No")); String pageNum
 * = request.getParameter("pageNum");
 * 
 * String searchKey = request.getParameter("searchKey"); String searchValue =
 * request.getParameter("searchValue");
 * 
 * if(searchValue!=null) { searchValue = URLDecoder.decode(searchValue,"utf-8");
 * }
 * 
 * EventBoardDTO dto = eventBoardService.getReadData(seq_No);
 * 
 * if(dto==null) { ModelAndView mav = new ModelAndView();
 * mav.setViewName("redirect:BookList.action?pageNum=" + pageNum + "&searchKey="
 * + searchKey + "&searchValue=" + searchValue); return mav; }
 * 
 * String param = "pageNum=" + pageNum;
 * 
 * if(searchValue!=null && !searchValue.equals("")) { param += "&searchKey=" +
 * searchKey; param += "&searchValue=" + URLEncoder.encode(searchValue,
 * "utf-8"); }
 * 
 * ModelAndView mav = new ModelAndView();
 * 
 * mav.addObject("dto", dto); mav.addObject("pageNum", pageNum);
 * mav.addObject("params", param); mav.addObject("searchKey", searchKey);
 * mav.addObject("searchValue", searchValue);
 * 
 * mav.setViewName("BookUpdate");
 * 
 * return mav;
 * 
 * }
 * 
 * @PostMapping("/eventUpdated.action") public ModelAndView
 * updated_ok(HttpServletRequest request,BookDTO dto,
 * 
 * @RequestParam(value = "upload",required = false) List<MultipartFile> upload)
 * throws Exception{ //MultipartFile는 받을 때 List 형식으로 받아줘야함
 * 
 * String pageNum = request.getParameter("pageNum"); String searchKey =
 * request.getParameter("searchKey"); String searchValue =
 * request.getParameter("searchValue"); String image_Url =
 * request.getParameter("image_Url");
 * 
 * //upload된게 없으면 그대로 두고 있으면 지워야됨 //multipart는 list에서 하나씩 꺼내서 null값을 체크해줘야 함 int
 * checkEmpty = 1;
 * 
 * for(MultipartFile image:upload){
 * 
 * if(image.isEmpty()) {
 * 
 * checkEmpty = 0;
 * 
 * } }
 * 
 * if(checkEmpty != 0) {
 * 
 * FileManager.doFileDelete(image_Url); FileManager.doFileUpload(dto, upload);
 * 
 * }else {
 * 
 * dto.setImage_Url(image_Url);
 * 
 * }
 * 
 * eventBoardService.updateData(dto);
 * 
 * String param = "pageNum=" + pageNum;
 * 
 * if(searchValue!=null && !searchValue.equals("")) { param += "&searchKey=" +
 * searchKey; param += "&searchValue=" + URLEncoder.encode(searchValue,
 * "utf-8"); }
 * 
 * 
 * ModelAndView mav = new ModelAndView();
 * 
 * mav.setViewName("redirect:BookList.action?" + param);
 * 
 * return mav;
 * 
 * }
 * 
 * @GetMapping("/eventDeleted_ok.action") public ModelAndView
 * deleted_ok(HttpServletRequest request) throws Exception{
 * 
 * int boardId = Integer.parseInt(request.getParameter("boardId")); String
 * pageNum = request.getParameter("pageNum");
 * 
 * String searchKey = request.getParameter("searchKey"); String searchValue =
 * request.getParameter("searchValue");
 * 
 * if(searchValue!=null) { searchValue = URLDecoder.decode(searchValue,
 * "UTF-8");
 * 
 * }
 * 
 * eventBoardService.deleteData(boardId);
 * 
 * String param = "pageNum=" + pageNum;
 * 
 * if(searchValue!=null&&!searchValue.equals("")) { param += "&searchKey=" +
 * searchKey; param += "&searchValue=" + URLEncoder.encode(searchValue,
 * "UTF-8"); }
 * 
 * ModelAndView mav = new ModelAndView();
 * 
 * mav.setViewName("redirect:/eventList.action?" + param);
 * 
 * return mav;
 * 
 * }
 * 
 * }
 */