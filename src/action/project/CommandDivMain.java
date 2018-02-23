package action.project;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;
import projectDb.ProjectDao;

public class CommandDivMain implements CommandAction {
	
public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {

	String pageNum = request.getParameter("pageNum");
	String proj_num_ = request.getParameter("proj_num");
	int proj_num = Integer.parseInt(proj_num_);
	if (pageNum == null) {
		pageNum = "1";
	} 
	int pageSize = 10;
	int currentPage = Integer.parseInt(pageNum);
	int startRow = (currentPage - 1) * pageSize + 1;
	int endRow = currentPage * pageSize;
	int count = 0;
	int number = 0;
	int divNumber= 0;
	
	List articleList = null;
	
	ProjectDao dbPro = ProjectDao.getInstance();
	count = dbPro.getArticleCountDiv(); 
	
	if (count > 0) {
		
		articleList = dbPro.getArticlesDiv(startRow, endRow,proj_num);
	} else {
		System.out.println("empty");
		articleList = Collections.EMPTY_LIST; 
		
	}
	
	number=count-(currentPage-1)*pageSize; 
	
	divNumber = dbPro.getDivCount(proj_num);
	
    request.setAttribute("currentPage", new Integer(currentPage));
    request.setAttribute("startRow", new Integer(startRow));
    request.setAttribute("endRow", new Integer(endRow));
    request.setAttribute("count", new Integer(count));
    request.setAttribute("pageSize", new Integer(pageSize));
    request.setAttribute("number", new Integer(number));
    request.setAttribute("articleList", articleList);
    request.setAttribute("proj_num", proj_num);
    request.setAttribute("divNumber", divNumber);
    
	return "/definition/ab/main02.jsp";
}
}