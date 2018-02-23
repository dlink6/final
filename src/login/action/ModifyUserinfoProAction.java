package login.action;

import logon.LogonDBBean;
import command.CommandAction;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.LoginDTO;
import logon.LogonDBBean;

public class ModifyUserinfoProAction implements CommandAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		LogonDBBean dao = LogonDBBean.getInstance();
		LoginDTO em = new LoginDTO();

		int empno = 0;
		if (request.getParameter("empno") == null || request.getParameter("empno").equals("")) {
			empno = Integer.parseInt(request.getParameter("empno1"));
		} else {
			empno = Integer.parseInt(request.getParameter("empno"));
		}
		em.setEmpno(empno);
		
		String address0 = "";
		if (request.getParameter("address") == null || request.getParameter("address").equals("")) {
			address0 = request.getParameter("address1");
		} else {
			address0 = request.getParameter("address");
		}
		em.setAddress(address0);
		
		String phone = "";
		if (request.getParameter("phone") == null || request.getParameter("phone").equals("")) {
			phone = request.getParameter("phone1");
		} else {
			phone = request.getParameter("phone");
		}
		em.setPhone1(phone);
		
		
		String passwd = "";
		if (request.getParameter("passwd") == null || request.getParameter("passwd").equals("")) {
			passwd = request.getParameter("passwd1");
		} else {
			passwd = request.getParameter("passwd");
		}
		em.setPasswd(passwd);
		
		
		String email = "";
		if (request.getParameter("email") == null || request.getParameter("email").equals("")) {
			email = request.getParameter("email1");
		} else {
			email = request.getParameter("email");
		}
		em.setEmail(email);
		
		
		String zipcode = "";
		
		if (request.getParameter("zipcode") == null || request.getParameter("zipcode").equals("")) {
			zipcode = request.getParameter("zipcode1");
		} else {
			zipcode = request.getParameter("zipcode");
		}
		em.setZipcode(zipcode);
		

		int deptno = 0; 
		if (request.getParameter("deptno") == null || request.getParameter("deptno").equals("")) {
			deptno = Integer.parseInt(request.getParameter("deptno1"));
		} else {
			deptno = Integer.parseInt(request.getParameter("deptno"));
		}
		em.setDeptno(deptno);
		
		
		
		String job ="";
		if (request.getParameter("job") == null || request.getParameter("job").equals("")) {
			job =request.getParameter("job1");
		} else {
			job = request.getParameter("job");
		}
		em.setJob(job);
		
		int status = 0;
		
		if (request.getParameter("status") == null || request.getParameter("status").equals("")) {
			status = Integer.parseInt(request.getParameter("status1"));
		} else {
			status = Integer.parseInt(request.getParameter("status"));
		}
		em.setStatus(status);
		
		String hiredate = "";
		
		if (request.getParameter("hiredate") == null || request.getParameter("hiredate").equals("")) {
			hiredate = request.getParameter("hiredate1");
		} else {
			hiredate = request.getParameter("hiredate");
		}
		em.setHiredate(hiredate);
		
		
		LoginDTO dto = dao.getDTO(empno);
		request.setAttribute("dto", dto);
		
		int check = dao.updateemp2(em);
		System.err.println(check);

		request.setAttribute("check", check);

		return "/INPUT/ModifyUserinfoPro.jsp";
	}

}
