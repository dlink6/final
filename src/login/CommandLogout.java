package login;

import javax.servlet.http.HttpServletRequest;
import command.CommandAction;

import javax.servlet.http.HttpServletResponse;


public class CommandLogout implements CommandAction{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		
		request.getSession().invalidate();
		
		return "login/login.jsp";
	 
	}  

}
