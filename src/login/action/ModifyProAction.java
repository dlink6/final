package login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.LoginDAO;
import logon.LogonDBBean;
import logon.LogonDataBean;
import command.CommandAction;

public class ModifyProAction implements CommandAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");

		LogonDataBean em1 = new LogonDataBean();
		//
		String address0 = "";
		if (request.getParameter("address1") == null || request.getParameter("address1").equals("")) {
			address0 = request.getParameter("address2");
		} else {
			address0 = request.getParameter("address1");
		}
		em1.setAddress(address0);
		System.out.println(address0);
		int empno =0;
		
		if(request.getParameter("empno") == null || request.getParameter("empno").equals("")) {
			empno = Integer.parseInt(request.getParameter("empno1"));
		}else {
			empno =Integer.parseInt(request.getParameter("empno"));
		}
		em1.setEmpno(empno);

		String phone = "";
		if (request.getParameter("phone") == null || request.getParameter("phone").equals("")) {
			phone = request.getParameter("phone1");
		} else {
			phone = request.getParameter("phone");
		}
		em1.setPhone1(phone);
		
		
		String passwd = "";
		if (request.getParameter("passwd") == null || request.getParameter("passwd").equals("")) {
			passwd = request.getParameter("passwd1");
		} else {
			passwd = request.getParameter("passwd");
		}
		em1.setPasswd(passwd);
		
		
		String email = "";
		
		if (request.getParameter("email") == null || request.getParameter("email").equals("")) {
			email = request.getParameter("email1");
		} else {
			email = request.getParameter("email");
		}
		em1.setEmail(email);
		
		String hiredate = "";
		
		if (request.getParameter("hiredate") == null || request.getParameter("hiredate").equals("")) {
			hiredate = request.getParameter("hiredate1");
		} else {
			hiredate = request.getParameter("hiredate");
		}
		em1.setHiredate(hiredate);
		
		LogonDBBean dao1 = LogonDBBean.getInstance();

		int check = dao1.updateemp(em1);

		
		System.err.println(em1.getAddress());
		LoginDAO dao = LoginDAO.getInstance();
		request.setAttribute("check", check);
		request.getSession().setAttribute("memId", dao.getLogin(empno));

		return "/INPUT/UserModifyPro.jsp";
	}

}
