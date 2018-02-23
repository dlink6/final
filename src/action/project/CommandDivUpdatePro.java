package action.project;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projectDb.ProjectDao;
import projectDb.DivDataBean;
import command.CommandAction;
public class CommandDivUpdatePro implements CommandAction {
	
    public String execute( HttpServletRequest request,
            HttpServletResponse response) throws Throwable {

            request.setCharacterEncoding("UTF-8");

            String pageNum = request.getParameter("pageNum");

            DivDataBean article = new DivDataBean();
            request.setCharacterEncoding("UTF-8");
    		article.setDiv_subject(request.getParameter("div_subject"));
    		/*article.setEmpno(Integer.parseInt(request.getParameter("empno"))); 
    		article.setEname(request.getParameter("ename"));  */
    		article.setDiv_reg_date(new Timestamp(System.currentTimeMillis()));
    		article.setDiv_content(request.getParameter("div_content"));
    		article.setDiv_day(request.getParameter("div_day"));
    		article.setDiv_num(Integer.parseInt(request.getParameter("div_num")));
    		article.setDiv_ing(Integer.parseInt(request.getParameter("div_ing")));
    	    ProjectDao dbPro = ProjectDao.getInstance();
            int check = dbPro.updateArticle(article);
            
            int proj_num =Integer.parseInt(request.getParameter("proj_num"));
            
            dbPro.getDiv_ing(proj_num);
            
            request.setAttribute("proj_num", proj_num );
            request.setAttribute("div_num", Integer.parseInt(request.getParameter("div_num")));


            return "/ab/main02UpdatePro.jsp";  
    } 
}