package schedule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class SchedulePage implements CommandAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/definition/scheduler/Schedule.jsp";
	}
}