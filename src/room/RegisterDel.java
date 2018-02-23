package room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class RegisterDel implements CommandAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setAttribute("room", request.getParameter("title"));
		request.setAttribute("r_start", request.getParameter("start"));
		request.setAttribute("r_end", request.getParameter("end"));
		
		RegisterDBBean db = new RegisterDBBean();
		int member_count = db.getCount(request.getParameter("title"), request.getParameter("start"), request.getParameter("end"));
		request.setAttribute("member_count", member_count);
		
		String member_name = db.getName(request.getParameter("title"), request.getParameter("start"), request.getParameter("end"));
		request.setAttribute("member_name", member_name);
		
		return "/scheduler/r_page/room_delete.jsp";
	}
}