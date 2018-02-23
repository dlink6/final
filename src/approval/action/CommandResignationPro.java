package approval.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import approval.db.ApprovalDAO;
import approval.db.ResignationDTO;
import command.CommandAction;

public class CommandResignationPro implements CommandAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		ApprovalDAO dao = ApprovalDAO.getInstance();
		ResignationDTO dto = new ResignationDTO();
		
		int empno = dao.empnoSearch(request.getParameter("ename"));
		int deptno = dao.deptnoSearch(request.getParameter("dname"));
		
		dto.setDeptno(deptno);
		dto.setEmpno(empno);
		dto.setR_cause(request.getParameter("r_cause"));
		dto.setEndDate(request.getParameter("enddate"));
		dao.ResignationInsert(dto);
		dao.documentUpdate();
		return "/electronicApproval/ListRenewal.jsp";
	}

}
