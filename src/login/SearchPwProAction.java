package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import logon.LogonDBBean;
import logon.LogonDataBean;
import java.util.*;

public class SearchPwProAction implements CommandAction{
	public String execute(HttpServletRequest request,HttpServletResponse response)throws Throwable{
		  
		LogonDataBean getsearchpw = new LogonDataBean();
		LogonDBBean dao = LogonDBBean.getInstance();
		
		StringTokenizer token = new StringTokenizer(request.getParameter("jumins"),"-");
		List list = new ArrayList();
		
		while(token.hasMoreTokens()) {
			list.add(token.nextToken());
		}
		
		getsearchpw.setEmpno(Integer.parseInt(request.getParameter("empno")));
		getsearchpw.setJumin1((String)list.get(0));
		getsearchpw.setJumin2((String)list.get(1));

		
		int check = dao.checkUser(getsearchpw);
		System.out.println("check::"+check);
		String userPw = dao.getSerchPw(getsearchpw);
		
		request.setAttribute("userPw", userPw);
		request.setAttribute("check", check);
		
		return "/login/SearchPwForm.jsp";
	}

}
