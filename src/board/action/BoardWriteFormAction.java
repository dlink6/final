package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class BoardWriteFormAction implements CommandAction {
	
	public String execute(HttpServletRequest request,
	        HttpServletResponse response)throws Throwable {
		response.setContentType("text/html; charset=UTF-8");
		int board_num=0, board_ref=1,board_re_seq=0, board_re_lev=0; 
		String board_cat = "";
		
        try{
        
        //답글인경우
          if(request.getParameter("board_num")!=null){
         board_num=Integer.parseInt(request.getParameter("board_num"));
         board_ref=Integer.parseInt(request.getParameter("board_ref"));
         board_re_seq=Integer.parseInt(request.getParameter("board_re_seq"));
         board_re_lev=Integer.parseInt(request.getParameter("board_re_lev"));
         board_cat = request.getParameter("board_cat");
          }
          
        }catch(Exception e){e.printStackTrace();}
		//해당 뷰에서 사용할 속성
		request.setAttribute("board_num", new Integer(board_num));
		request.setAttribute("board_ref", new Integer(board_ref));
		request.setAttribute("board_re_seq", new Integer(board_re_seq));
		request.setAttribute("board_re_lev", new Integer(board_re_lev));
		request.setAttribute("board_cat", board_cat);

		return "/definition/board/BoardWriteForm.jsp"; //해당 뷰
	}
}
