package pb.action;

import java.io.File;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import project.file.PfDao;
import project.file.PfBean;
import projectBoardDb.PbDao;
import projectBoardDb.PbBean;
import command.CommandAction;	
	public class PbUpdateAction implements CommandAction {
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Throwable {
			
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			int pb_num = Integer.parseInt(request.getParameter("pb_num"));
			//String pageNum = request.getParameter("pageNum");
			
			PbDao pbdao = new PbDao();
			PbBean article = pbdao.updateGetArticle(pb_num);
			
			//
			PfDao pfdao = PfDao.getInstance();
			PfBean fileData = pfdao.getFile(pb_num);  
			
			
			//request.setAttribute("pageNum", new Integer(pageNum));
			request.setAttribute("article", article);
		    request.setAttribute("fileData", fileData);
		    request.setAttribute("pb_num", pb_num);
	return "/definition/pb/PbUpdateForm.jsp";
	}
}  
