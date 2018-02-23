package schedule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class ScheduleReg implements CommandAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		return "/scheduler/s_page/sch_register.jsp";
	}
}