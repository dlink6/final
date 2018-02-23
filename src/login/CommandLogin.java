package login;

import javax.servlet.http.HttpServletRequest;
import command.CommandAction;

import javax.servlet.http.HttpServletResponse;


public class CommandLogin implements CommandAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		  
	LoginDAO dao = LoginDAO.getInstance();
	 
	String id_ = request.getParameter("id");	
	int id = Integer.parseInt(id_);
	String passwd = request.getParameter("passwd");
	int x = dao.Login(id,passwd);
	if(x > 0) {
		request.getSession().setAttribute("memId", dao.getLogin(id));
	}
		request.setAttribute("x",x);
	
	return "/login/loginPro.jsp";
	}
}
