package action.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projectDb.ProjectDao;
import projectDb.DivDataBean;
import command.CommandAction;
public class CommandDivUpdate implements CommandAction {
	
public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {

	int num = Integer.parseInt(request.getParameter("div_num")); 
	//String pageNum = request.getParameter("pageNum");  
	
	ProjectDao db = ProjectDao.getInstance();
	DivDataBean article = db.updateGetArticle(num);
	System.out.println(num);
			
	//request.setAttribute("pageNum", new Integer(pageNum));
	request.setAttribute("article", article);
	
	db.updateArticle(article);
	
	return "/definition/ab/main02Update.jsp"; 
	}
} 


