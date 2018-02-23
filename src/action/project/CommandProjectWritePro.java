package action.project;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projectDb.ProjectDao;
import projectDb.ProjectDataBean;
import command.CommandAction;
public class CommandProjectWritePro implements CommandAction {
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Throwable { 
		request.setCharacterEncoding("UTF-8");
		
		String proj_day1 = request.getParameter("proj_day_start");
		String proj_day2 = request.getParameter("proj_day_end");
		String proj_day = proj_day1+"~"+proj_day2;
		ProjectDataBean article = new ProjectDataBean();
		article.setProj_subject(request.getParameter("proj_subject"));
		article.setEmpno(Integer.parseInt(request.getParameter("empno")));
		article.setEname(request.getParameter("ename")); 
		article.setProj_reg_date(new Timestamp(System.currentTimeMillis()));		
		article.setProj_day(proj_day);
		article.setProj_ing(0); 


		ProjectDao dbPro = ProjectDao.getInstance();
		dbPro.insertArticle(article);
		return "/ab/Renewal1.jsp";    
} 
} 

