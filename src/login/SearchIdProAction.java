 package login;

 import command.CommandAction;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logon.LogonDBBean;
import logon.LogonDataBean;

public class SearchIdProAction implements CommandAction{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Throwable{
		
		request.setCharacterEncoding("utf-8");
		
		LogonDataBean getsearch = new LogonDataBean();
		LogonDBBean dao = LogonDBBean.getInstance();
		
		String ename =request.getParameter("ename");
		System.out.println("ename ::"+ename);
		
		StringTokenizer token = new StringTokenizer(request.getParameter("jumins"),"-");
		List list = new ArrayList();
		
		while(token.hasMoreTokens()) {
			list.add(token.nextToken());
		}
		
		String jumins = request.getParameter("jumins");
		System.out.println("jumins ::" + jumins);
		
		getsearch.setEname(ename);
		getsearch.setJumin1((String)list.get(0));
		getsearch.setJumin2((String)list.get(1));
		
		//searchlist.add(getsearch);//
		
		
		int check  = dao.checkId(getsearch);
		System.out.println("check"+check);
		
		String userId = dao.getSearchId(getsearch);
		System.out.println("userId ::" + userId);
		
		request.setAttribute("userId", userId);
		request.setAttribute("check", check);
		
		return "/login/SearchIdForm.jsp";
		
	}
}
