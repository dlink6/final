package action.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import command.CommandAction;
public class CommandDivWrite implements CommandAction {

public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
	String a = request.getParameter("proj_num");
	
	request.setAttribute("proj_num", a);
	
	return "/definition/ab/main02Write.jsp";	 	
} 
} 

