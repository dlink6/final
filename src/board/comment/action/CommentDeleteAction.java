package board.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.comment.model.CommentDAO;
import command.CommandAction;


public class CommentDeleteAction implements CommandAction{
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		int empno = Integer.parseInt(request.getParameter("empno"));
		
		CommentDAO cdao = CommentDAO.getInstance();
		if(empno == 1000) {
		cdao.deleteComment(board_num, comment_num);
		}else {
		cdao.deleteComment(board_num, comment_num, empno);
		}
		
		
		
		return "/board/BoardContent.jsp";
	}
}