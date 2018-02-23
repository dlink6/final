package login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;

import logon.LogonDBBean;
import logon.LogonDataBean;
import sun.invoke.empty.Empty;
import java.util.*;
import command.CommandAction;

public class InputProAction implements CommandAction{
	 
	public String execute(HttpServletRequest request , 
			HttpServletResponse response) throws Throwable{
		
		request.setCharacterEncoding("UTF-8");
		
		LogonDataBean em = new LogonDataBean();
		
		Random random = new Random();

		int randomValue = random.nextInt(100)+10;
		if (randomValue>10000) {
			randomValue = randomValue - 10;
		}
		System.out.println(randomValue);
			
		StringTokenizer token = new StringTokenizer(request.getParameter("jumins"),"-");
		
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String address = address1+address2;
		
		StringTokenizer hiredate = new StringTokenizer(request.getParameter("hiredate"),"-");
		
		int prog = 00; 
		List list1 = new ArrayList();
		List list2 = new ArrayList();
		
		while(token.hasMoreTokens()) {
			list1.add(token.nextToken());
		}
		while(hiredate.hasMoreTokens()) {
			list2.add(hiredate.nextToken());
		}
		
		String hire1 = (String)list2.get(0)+"";
		System.out.println("list2.get(0)"+list2.get(0));
		String hire2 = (String)list2.get(1)+"";
		String hire3 = (String)list2.get(2)+"";
		String hire = hire1+hire2+hire3;
		System.out.println("hire::"+hire);
				
		String empno1 = hire.substring(2,6);
		System.out.println("empno1 ::"+ empno1);
		
		String empno = request.getParameter("deptno")+Integer.toString(randomValue)+empno1;
		
		System.out.println("empno(부서번호+입사일+랜덤2자리):::"+empno);
		
		em.setEmpno(Integer.parseInt(empno));
		System.out.println(empno);
		em.setEname(request.getParameter("ename"));
		em.setPasswd((String)list1.get(0));
		em.setPhone1(request.getParameter("phone1"));
		em.setAddress(address);
		em.setJob(request.getParameter("job"));
		em.setEmail(request.getParameter("email"));
		em.setHiredate(request.getParameter("hiredate"));;
		em.setJumin1((String)list1.get(0));
		em.setJumin2((String)list1.get(1));
		em.setDeptno(Integer.parseInt(request.getParameter("deptno")));
		em.setZipcode(request.getParameter("zipcode"));
		em.setProg_num(prog);
		LogonDBBean dao = LogonDBBean.getInstance();
		
		int check = dao.insertemp(em);
		
		request.setAttribute("check", check);
		
		
	return "/INPUT/inputPro.jsp";
	}

}
