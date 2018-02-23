package main;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import login.LoginDTO;

public class MainPage implements CommandAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		MainDAO dao = MainDAO.getInstance();
		LoginDTO dto = (LoginDTO)request.getSession().getAttribute("memId");
		
		int empno = dto.getEmpno();
		int deptno= dto.getDeptno();
		
		

		//현재날짜
		Date today = new Date();
		
		request.setAttribute("approvalList",dao.getMainApprovalList(empno, deptno));
		request.setAttribute("noticeList",dao.getMainNoticeList());
		request.setAttribute("projList", dao.GetMainProjArticles());
		request.setAttribute("today",today);
		
		return "/definition/main.jsp";
	}
	
}
