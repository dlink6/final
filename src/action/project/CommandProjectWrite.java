package action.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;
public class CommandProjectWrite implements CommandAction  {
	
public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {

return "/definition/ab/mainWrite.jsp";
}
}  

