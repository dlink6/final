package approval.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import approval.db.ApprovalDAO;
import approval.db.RequestForVacationDTO;
import command.CommandAction;

public class CommandRequestForVacationPro implements CommandAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		ApprovalDAO dao = ApprovalDAO.getInstance();
		RequestForVacationDTO dto = new RequestForVacationDTO();
		
		int empno = dao.empnoSearch(request.getParameter("ename"));
		int deptno = dao.deptnoSearch(request.getParameter("dname"));
		
		dto.setEmpno(empno);
		dto.setDeptno(deptno);
		dto.setV_cause(request.getParameter("v_cause"));
		dto.setV_emergencyNumber(request.getParameter("v_emergencyNumber"));
		dto.setV_start(request.getParameter("v_start"));
		dto.setV_end(request.getParameter("v_end"));
		dto.setV_type(request.getParameter("v_type"));
		dto.setV_otherDetail(request.getParameter("v_otherDetail"));
		dao.requestForVacationInsert(dto);
		dao.documentUpdate();
		return "/electronicApproval/ListRenewal.jsp";
	}

}
