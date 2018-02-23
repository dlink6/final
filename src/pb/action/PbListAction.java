package pb.action;

import java.util.Date;
import java.util.List; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.file.PfDao;
import project.file.PfBean;
import projectBoardDb.PbDao;
import command.CommandAction;
public class PbListAction implements CommandAction { //
		
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
			
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			
			//현재 날짜
			Date today = new Date();
			//화면에 보여질 게시글의 갯수를 지정
			int pageSize = 10;
			//현재 보여지고 있는 페이지의 넘버값을 읽음
			String pageNum = request.getParameter("pageNum");
			//검색		
			String search = request.getParameter("search");
			
			int searchn = 0;
			
			//null 처리
			if(pageNum==null) {
				pageNum="1";
			}
					
			if(search == null) {
				search="";
			}else {
				searchn = Integer.parseInt(request.getParameter("searchn"));
			}
			
			//
			PfDao pfdao = PfDao.getInstance(); 
			PfBean fileData = new PfBean();
			
			// 전체 게시글의 갯수
			int count=0;
			// jsp페이지 내에서 보여질 넘버링 숫자값을 저장하는 변수
			int number=0;
			
			// 전체 게시글의 갯수를 가져와야 하므로 데이터베이스 객체 생성.
			PbDao pbdao = new PbDao();

			// 현재 보여지고 있는 페이지 문자를 숫자로 형변환
			int currentPage=Integer.parseInt(pageNum);

			// 현재 보여질 페이지 시작 번호를 설정
			int startRow = (currentPage-1)*pageSize+1;
			int endRow = currentPage*pageSize;
			
			//
			List articleList=null;
			
			//
			if(search.equals("")){
				count = pbdao.getArticleCount();
			}
			//전체게시글 검색
		      if(!search.equals("")){
		         count = pbdao.getArticleCount(searchn, search);
		      } 
			
			if(count > 0)
			{	
				/*int pageCount = count / pageSize + (count % pageSize == 0 ? 0 :1);
				int pageBlock = 10;
				int result = Math.round(currentPage / 10);
				int startPage = result*10+1;
				int endPage = startPage +pageBlock-1;
				if(endPage>pageCount) {
					endPage = pageCount;
				}*/
			
				//전체게시글
				if(search.equals(""))
					articleList = pbdao.getArticles(startRow, endRow);		
				//전체게시글 검색
				if(!search.equals(""))
					articleList = pbdao.getArticles(startRow, endRow, searchn, search);			
			}		 
			number = count-(currentPage -1)*pageSize;
								
			//PbListForm.jsp로  request 객체에 담아서 넘겨줌!
			request.setAttribute("articleList", articleList);
			request.setAttribute("number", number); 
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("count", count);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("today", today);
			request.setAttribute("startRow", new Integer(startRow));
	        request.setAttribute("endRow", new Integer(endRow));
	        request.setAttribute("fileData", fileData);    
	        request.setAttribute("pageNum", pageNum);

	        return "/definition/pb/PbListForm.jsp";  
}
}    