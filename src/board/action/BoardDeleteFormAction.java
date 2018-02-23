package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class BoardDeleteFormAction implements CommandAction{
	
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Throwable {
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String pageNum = request.getParameter("pageNum");
		
		request.setAttribute("board_num", new Integer(board_num));
        if(pageNum!=null && pageNum!="") {
        request.setAttribute("pageNum", new Integer(pageNum));
        }
		
		return "/board/BoardDeleteForm.jsp";
}
}