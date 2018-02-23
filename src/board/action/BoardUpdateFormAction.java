package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.file.action.SizeCaculation;
import board.file.model.FileDAO;
import board.file.model.FileDataBean;
import board.model.BoardDAO;
import board.model.BoardDataBean;
import command.CommandAction;

public class BoardUpdateFormAction implements CommandAction {
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Throwable {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO bdao = new BoardDAO();
		BoardDataBean article = bdao.updateGetArticle(board_num);
		
		//첨부파일 출력
		FileDAO fdao = FileDAO.getInstance();
		FileDataBean fileData = fdao.getFile(board_num);
		
		//파일 용량 계산(파일이 있는경우)
		if(fileData!=null) {
		SizeCaculation calcu = new SizeCaculation();
		String CalcuSize = calcu.SizeCalcu(fileData.getFile_size());
		
		request.setAttribute("CalcuSize", CalcuSize);
		}

		
		
        if(pageNum!=null && pageNum!="") {
        request.setAttribute("pageNum", new Integer(pageNum));
        }
		request.setAttribute("article", article);
	    request.setAttribute("fileData", fileData);

	    return "/definition/board/BoardUpdateForm.jsp";//해당뷰

}
}
