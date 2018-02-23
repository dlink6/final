package room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class RegisterPage implements CommandAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/definition/scheduler/ReferenceRoom.jsp";
	}
}
