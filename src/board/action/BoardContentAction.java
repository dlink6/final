package board.action;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.comment.model.CommentDAO;
import board.file.model.FileDAO;
import board.file.model.FileDataBean;
import board.model.BoardDAO;
import board.model.BoardDataBean;
import command.CommandAction;
import board.file.action.SizeCaculation;

public class BoardContentAction implements CommandAction{
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		int board_num = Integer.parseInt(request.getParameter("board_num")); //해당 글 번호
		String pageNum = request.getParameter("pageNum"); // 해당 페이지 번호
		
		BoardDAO bdao = new BoardDAO();
		BoardDataBean article = bdao.getArticle(board_num);
		 
		//첨부파일 출력
		FileDAO fdao = FileDAO.getInstance();
		FileDataBean fileData = fdao.getFile(board_num);
		
		//파일 용량 계산(파일이 있는경우)
		if(fileData!=null) {
		SizeCaculation calcu = new SizeCaculation();
		String CalcuSize = calcu.SizeCalcu(fileData.getFile_size());
		
		request.setAttribute("CalcuSize", CalcuSize);
		}
		    
		    
		//코멘트 출력		
		CommentDAO cdao = CommentDAO.getInstance();
		List commentList = cdao.getComments(board_num);

		//코멘트 갯수
		int comment_count = cdao.getCommentCount(board_num);
		if(request.getParameter("comment_num") != null && request.getParameter("comment_num")!=""){
			int comment_num = Integer.parseInt(request.getParameter("comment_num"));
	        request.setAttribute("comment_num", new Integer(comment_num));
		}

		
        //해당 뷰에서 사용할 속성
        request.setAttribute("board_num", new Integer(board_num));
        if(pageNum!=null && pageNum!="") {
        request.setAttribute("pageNum", new Integer(pageNum));
        }
        request.setAttribute("comment_count", new Integer(comment_count));
        request.setAttribute("article", article);
        request.setAttribute("commentList", commentList);
        
        request.setAttribute("fileData", fileData);
		return "/definition/board/BoardContent.jsp";
	}

}
