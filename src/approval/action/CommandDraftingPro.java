package approval.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import approval.db.ApprovalDAO;
import approval.db.DraftingDTO;
import command.CommandAction;

public class CommandDraftingPro implements CommandAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		ApprovalDAO dao = ApprovalDAO.getInstance();
		DraftingDTO dto = new DraftingDTO();
		
		int empno = dao.empnoSearch(request.getParameter("ename"));
		int deptno = dao.deptnoSearch(request.getParameter("dname"));
		
		dto.setEmpno(empno);
		dto.setDeptno(deptno);
		dto.setD_category(request.getParameter("d_category"));
		dto.setD_content(request.getParameter("d_content"));
		dto.setD_subject(request.getParameter("d_subject"));
		dto.setD_date(request.getParameter("d_date"));
		dao.DraftingInsert(dto);
		
		dao.documentUpdate();
		
		
		return "/electronicApproval/ListRenewal.jsp";
	}

}
