package room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class RegisterReg implements CommandAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		return "/scheduler/r_page/room_register.jsp";
	}
}