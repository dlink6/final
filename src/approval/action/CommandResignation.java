package approval.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class CommandResignation implements CommandAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		return "/definition/electronicApproval/resignation.jsp";
	}

}
  