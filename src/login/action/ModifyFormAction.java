package login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;

public class ModifyFormAction implements CommandAction{
	public String execute(HttpServletRequest request , 
			HttpServletResponse response) throws Throwable{
				 
		return "/definition/INPUT/UserModifyForm.jsp";
	}

}
