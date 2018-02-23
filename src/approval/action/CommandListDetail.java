package approval.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import approval.db.ApprovalDAO;
import approval.db.SpendingResolutionDTO;
import approval.db.Spending_RecordDTO;
import command.CommandAction;

public class CommandListDetail implements CommandAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num"));
		ApprovalDAO dao = ApprovalDAO.getInstance();
		String document = request.getParameter("documentnum");
		List list = null;
		if(num ==1) {//휴가신청서
			request.setAttribute("detailList",dao.getV_List(document));
		}else if(num ==2) {//기안서
			request.setAttribute("detailList",dao.getD_List(document));
		}else if(num ==3) {//지출결의서
			list = dao.getS_List(document);
			request.setAttribute("detailList",list);
			SpendingResolutionDTO dto = (SpendingResolutionDTO) list.get(0);
			request.setAttribute("s_list",dao.getSpending_Record(dto.getS_num()));
		}else if(num ==4) {//사직서
			request.setAttribute("detailList",dao.getR_List(document));
		}
		
		request.setAttribute("ename", request.getParameter("ename"));
		request.setAttribute("dname", request.getParameter("dname"));
		request.setAttribute("check",dao.getApprovalCheck(document));
		
		return "/definition/electronicApproval/electronicApprovalListDetail.jsp";
	}

}
  