package pb.action;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.file.PfDao;
import project.file.PfBean;
import command.CommandAction;
public class FileDeleteAction implements CommandAction { 

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int pb_num = Integer.parseInt(request.getParameter("pb_num"));
		PfDao pfdao = PfDao.getInstance();
		PfBean oldfileData = pfdao.getFile(pb_num);

		File oldfile = new File(oldfileData.getPf_realpath()+"/"+oldfileData.getPf_save());
		oldfile.delete();//기존파일 삭제 
		
		pfdao.deleteFile(pb_num); //db삭제

		return "/pb/PbUpdateForm.jsp";  
	
}
}