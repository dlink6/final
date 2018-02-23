package login;

import javax.servlet.http.HttpServletRequest;
import command.CommandAction;

import javax.servlet.http.HttpServletResponse;
  
public class CommandMainPage implements CommandAction{
	public String execute(HttpServletRequest request , HttpServletResponse response) throws Throwable {
		return "/login/main.jsp";
	}

}
 