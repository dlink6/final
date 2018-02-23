package approval.action;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class CommandSpendingResolution implements CommandAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		Date a = new Date(System.currentTimeMillis());
		request.setAttribute("time", s.format(a));
		
		return "/definition/electronicApproval/spendingResolution.jsp";
	}

}
  