package action.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projectDb.DivDataBean;
import projectDb.ProjectDao; 
import command.CommandAction;
public class CommandDivContent implements CommandAction {
	
public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
	int num = Integer.parseInt(request.getParameter("div_num"));
	request.setCharacterEncoding("UTF-8");
	ProjectDao dao = ProjectDao.getInstance();
	DivDataBean article = dao.GetArticle(num);
	/*request.setAttribute();*/
	
	request.setAttribute("article", article); 
		
return "/definition/ab/main02Content.jsp";
	} 
}