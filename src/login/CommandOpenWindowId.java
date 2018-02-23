package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;

public class CommandOpenWindowId implements CommandAction{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Throwable{
		return "/login/OpenWindowId.jsp";
	}  
 
}
