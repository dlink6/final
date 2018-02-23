package board.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.file.model.FileDAO;
import board.file.model.FileDataBean;
import board.model.BoardDAO;
import command.CommandAction;

public class BoardDeleteProAction implements CommandAction{
	
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		//첨부파일파일 삭제
		FileDAO fdao = FileDAO.getInstance();
		FileDataBean oldfileData = fdao.getFile(Integer.parseInt(request.getParameter("board_num")));
		if (oldfileData != null) {
			//파일을 객체로 지정
			File oldfile = new File(oldfileData.getFile_realpath()+"/"+oldfileData.getFile_save());
			oldfile.delete();//기존파일 삭제
			//db삭제 불필요(board 테이블의 board_num이 삭제되면 board_file의 board_num도 자동으로 삭제되는 cascade 설정됨
		}
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO bdao = new BoardDAO();
		int check  = bdao.deleteArticle(board_num);
		
        if(pageNum!=null && pageNum!="") {
        request.setAttribute("pageNum", new Integer(pageNum));
        }
		request.setAttribute("check", new Integer(check));
		
		return "/board/BoardDeletePro.jsp";
}
}
