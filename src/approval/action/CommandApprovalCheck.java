package approval.action;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import approval.db.ApprovalDAO;
import command.CommandAction;

public class CommandApprovalCheck implements CommandAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		
		ApprovalDAO dao = ApprovalDAO.getInstance();
		dao.setApprovalCheckUpdate(request.getParameter("doc"),request.getParameter("check"),Integer.parseInt(request.getParameter("type")),request.getParameter("ename"));
		
		return "/electronicApproval/ListRenewal.jsp";
	}

}
