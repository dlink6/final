package approval.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class CommandRequestForVacation implements CommandAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		return "/definition/electronicApproval/requestForVacation.jsp";
	}

}
   