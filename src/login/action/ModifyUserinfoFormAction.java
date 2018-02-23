package login.action;

import javax.servlet.http.HttpServletRequest;
import command.CommandAction;

import javax.servlet.http.HttpServletResponse;

import login.LoginDTO;
import logon.LogonDBBean;
//인사페이지에서 사원 정보 수정
public class ModifyUserinfoFormAction implements CommandAction{
	public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Throwable{
		
		request.setCharacterEncoding("UTF-8");
		
		LogonDBBean dao = LogonDBBean.getInstance();
		LoginDTO dto = dao.getDTO(Integer.parseInt(request.getParameter("empno")));
		
		request.setAttribute("dto", dto);
		
		return "/definition/INPUT/ModifyUserinfoForm.jsp";
	}

}
