package board.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.comment.model.CommentDAO;
import board.comment.model.CommentDataBean;
import command.CommandAction;
import login.LoginDTO;

public class CommentWriteAction implements CommandAction{
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		CommentDataBean comment = new CommentDataBean();
		comment.setComment_content(request.getParameter("comment_content"));
		
		/*if(request.getParameter("comment_num") == null && request.getParameter("comment_num")!=""){
			int comment_num = 1;
			comment.setComment_num(comment_num);
		}else {*/
		comment.setComment_num(Integer.parseInt(request.getParameter("comment_num")));
		comment.setEmpno(Integer.parseInt(request.getParameter("empno")));
		
		//comment.setEmpno(Integer.parseInt(request.getParameter("empno")));
		comment.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
		//comment.setComment_date(new Timestamp(System.currentTimeMillis()));
		
		CommentDAO cdao = CommentDAO.getInstance();
		cdao.insertComment(comment);
		
		return "/board/BoardContent.jsp";
}
}