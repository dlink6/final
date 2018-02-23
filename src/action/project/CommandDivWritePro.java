package action.project;

import java.sql.Timestamp; 

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projectDb.ProjectDao;
import projectDb.DivDataBean;
import command.CommandAction;
public class CommandDivWritePro implements CommandAction {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {   
		
		request.setCharacterEncoding("UTF-8");
		
		String div_day1 = request.getParameter("div_day_start");
		String div_day2 = request.getParameter("div_day_end");
		String div_day = div_day1+"~"+div_day2;
		
		DivDataBean article = new DivDataBean();
		article.setDiv_subject(request.getParameter("div_subject"));
		article.setEmpno(Integer.parseInt(request.getParameter("empno")));
		article.setEname(request.getParameter("ename"));
		article.setDiv_reg_date(new Timestamp(System.currentTimeMillis()));
		article.setDiv_content(request.getParameter("div_content"));
		article.setDiv_day(div_day);
		article.setDiv_ing(Integer.parseInt(request.getParameter("div_ing")));
		article.setProj_num(Integer.parseInt(request.getParameter("proj_num")));
		ProjectDao dbPro = ProjectDao.getInstance(); 
		dbPro.insertArticle(article);
		request.setAttribute("proj_num", request.getParameter("proj_num"));
		 	
		return "/ab/Renewal2.jsp";  
	} 
}
