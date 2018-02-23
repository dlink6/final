package schedule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class ScheduleDelPro implements CommandAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		ScheduleDataBean article = new ScheduleDataBean();
		
		article.setS_title(request.getParameter("s_title"));
		article.setS_start(request.getParameter("s_start"));
		article.setS_end(request.getParameter("s_end"));
		article.setS_content(request.getParameter("s_content"));
		
		ScheduleDBBean dbPro = ScheduleDBBean.getInstance();
		dbPro.deleteSchedule(article);
		
		return "/scheduler/s_page/sch_delPro.jsp";
	}
}