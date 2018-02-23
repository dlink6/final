package board.file.action;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.file.model.FileDAO;
import board.file.model.FileDataBean;
import command.CommandAction;

public class FileDeleteAction implements CommandAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		FileDAO fdao = FileDAO.getInstance();
		FileDataBean oldfileData = fdao.getFile(board_num);

		File oldfile = new File(oldfileData.getFile_realpath()+"/"+oldfileData.getFile_save());
		oldfile.delete();//기존파일 삭제
		
		fdao.deleteFile(board_num); //db삭제

		return "/board/BoardUpdateForm.jsp";
	
}
}