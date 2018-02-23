package schedule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class ScheduleDel implements CommandAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setAttribute("s_title", request.getParameter("title"));
		request.setAttribute("s_start", request.getParameter("start"));
		request.setAttribute("s_end", request.getParameter("end"));
		
		ScheduleDBBean db = new ScheduleDBBean();
		ScheduleDataBean content = db.getInfo(request.getParameter("title"), request.getParameter("start"), request.getParameter("end"));
		
		request.setAttribute("s_content", content.getS_content());
		request.setAttribute("sortation", content.getSortation());
		
		return "/scheduler/s_page/sch_delete.jsp";
	}
}