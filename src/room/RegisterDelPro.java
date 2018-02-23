package room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class RegisterDelPro implements CommandAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		RegisterDataBean article = new RegisterDataBean();
		
		article.setMember_name(request.getParameter("member_name"));
		article.setRoom(request.getParameter("room"));
		article.setMember_count(Integer.parseInt(request.getParameter("member_count")));
		article.setR_start(request.getParameter("r_start"));
		article.setR_end(request.getParameter("r_end"));
		
		RegisterDBBean dbPro = RegisterDBBean.getInstance();
		dbPro.deleteSchedule(article);
		
		return "/scheduler/s_page/sch_delPro.jsp";
	}
}