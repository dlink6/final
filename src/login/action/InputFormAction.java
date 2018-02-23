
package login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logon.LogonDBBean;
import command.CommandAction;
public class InputFormAction implements CommandAction {
 
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		LogonDBBean logdb = LogonDBBean.getInstance();
		request.setAttribute("list", logdb.getEmpList());
		
		return "/definition/INPUT/inputForm.jsp";
	}

}
