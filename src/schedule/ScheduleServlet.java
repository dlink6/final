package schedule;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.LoginDTO;

@WebServlet("/ScheduleServlet")
public class ScheduleServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		LoginDTO dto = (LoginDTO) request.getSession().getAttribute("memId");
		
		int a = dto.getEmpno();
		int b = dto.getDeptno();
		
		response.getWriter().write(getJSON(a, b)); 
	}
	
	public String getJSON(int a, int b) { //json : 占식쏙옙占싹깍옙 占쏙옙占쏙옙 占싹놂옙占쏙옙 占쏙옙占쏙옙
		StringBuffer result = new StringBuffer("");
		result.append("["); //result占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙
		ScheduleDBBean bean = new ScheduleDBBean(); //회占쏙옙占쏙옙占쏙옙트占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쌉쇽옙占쏙옙 占쏙옙占쏙옙歐占� 占쏙옙占쏙옙
		
		//System.out.println(a);
		//System.out.println(b);
		
		ArrayList<ScheduleDataBean> scheList = bean.getList(a, b);
		String color = "";
		
		for(int i=0; i<scheList.size(); i++) {
			if((scheList.get(i).getSortation()).equals("company")) {
				color = "#C90000";
			} else if((scheList.get(i).getSortation()).equals("team")) {
				color = "#1F51B7";
			} else {
				color = "#3E9D37";
			}
			
			if(i==0) { 
				result.append("{\"title\":\""+scheList.get(i).getS_title()+"\",");
				result.append("\"id\":\""+scheList.get(i).getSortation()+"\",");
				result.append("\"start\":\""+scheList.get(i).getS_start()+"\",");
				result.append("\"end\":\""+scheList.get(i).getS_end()+"\",");
				result.append("\"color\":\""+color+"\"}");
			}else { 
				result.append(",{\"title\":\""+scheList.get(i).getS_title()+"\",");
				result.append("\"id\":\""+scheList.get(i).getSortation()+"\",");
				result.append("\"start\":\""+scheList.get(i).getS_start()+"\",");
				result.append("\"end\":\""+scheList.get(i).getS_end()+"\",");
				result.append("\"color\":\""+color+"\"}");
				}
			//System.out.println(result);
		}
		result.append("]");
	
		return result.toString();
	} 
}
