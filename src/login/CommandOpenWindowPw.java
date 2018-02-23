package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class CommandOpenWindowPw implements CommandAction{
	public String execute(HttpServletRequest request,HttpServletResponse response)throws Throwable{
		return "/login/OpenWindowPw.jsp";
	}

}
 