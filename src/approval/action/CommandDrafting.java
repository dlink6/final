package approval.action;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class CommandDrafting implements CommandAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/definition/electronicApproval/drafting.jsp";
	}
}
 