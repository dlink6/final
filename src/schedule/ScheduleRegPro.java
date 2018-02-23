package schedule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class ScheduleRegPro implements CommandAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		ScheduleDataBean article = new ScheduleDataBean();
		
		article.setSortation(request.getParameter("sortation"));
		article.setS_title(request.getParameter("s_title"));
		article.setS_start(request.getParameter("s_start"));
		article.setS_end(request.getParameter("s_end"));
		article.setS_content(request.getParameter("s_content"));
		article.setEmpno(Integer.parseInt(request.getParameter("empno")));
		article.setDeptno(Integer.parseInt(request.getParameter("deptno")));
		
		ScheduleDBBean dbPro = ScheduleDBBean.getInstance();
		dbPro.insertArticle(article);
		
		return "/scheduler/s_page/sch_regPro.jsp";
	}
}