package approval.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import approval.db.ApprovalDAO;
import approval.db.SpendingResolutionDTO;
import approval.db.Spending_RecordDTO;
import command.CommandAction;

public class CommandSpendingResolutionPro implements CommandAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		ApprovalDAO dao = ApprovalDAO.getInstance();
		SpendingResolutionDTO dto;
		
		String [] days = request.getParameterValues("s_day");
		String [] goods = request.getParameterValues("s_goods");
		String [] content = request.getParameterValues("s_content");
		String [] money  = request.getParameterValues("s_money");
		String [] amount = request.getParameterValues("s_amount");
		
		dto = new SpendingResolutionDTO();
		int empno = dao.empnoSearch(request.getParameter("ename"));
		int deptno = dao.deptnoSearch(request.getParameter("dname"));
		
		//지출결의서 insert
		dto.setEmpno(empno);
		dto.setDeptno(deptno);
		dto.setS_spendingDate(request.getParameter("s_spendingDate"));
		dto.setS_category(request.getParameter("s_category"));
		dao.spendingResolutionInsert(dto);
		
		//지출결의서내역 insert 비칸은 insert  no!!
		for(int i=0;i<days.length; i++) {
			if(!days[i].equals("")) {
				Spending_RecordDTO dto1 = new Spending_RecordDTO();
				dto1.setS_day(days[i]);
				dto1.setS_goods(goods[i]);
				dto1.setS_content(content[i]);
				dto1.setS_money(Integer.parseInt(money[i]));
				dto1.setS_amount(Integer.parseInt(amount[i]));
				
				dao.spendingRecordInsert(dto1);
			}
		}
		dao.documentUpdate();
		
		return "/electronicApproval/ListRenewal.jsp";
	}

}
