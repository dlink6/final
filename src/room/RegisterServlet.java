package room;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //파라미터 인코딩(넘어오는 모든 매개변수를 처리할때)
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(getJSON1()); //사용자가 입력한 매개변수를 토대로 검색된 내용을 json형태로 화면에 출력 -> 서블릿이 하는 역할
	}
	
	public String getJSON1() {
		StringBuffer result = new StringBuffer("");
		result.append("["); //result에 응답 결과를 저장
		RegisterDBBean bean = new RegisterDBBean();
		ArrayList<RegisterDataBean> regList = bean.getList();
		for(int i=0; i<regList.size(); i++) { //전체 결과의 개수(디비에 저장된 일정의 개수)만큼 데이터베이스에서 뽑아냄
			if(i==0) { 
				result.append("{\"title\":\"["+regList.get(i).getRoom()+"] "+regList.get(i).getMember_name()+"\",");
				result.append("\"resourceId\":\""+regList.get(i).getRoom()+"\",");
				result.append("\"start\":\""+regList.get(i).getR_start()+"\",");
				result.append("\"end\":\""+regList.get(i).getR_end()+"\"}");
			}else {
				result.append(",{\"title\":\"["+regList.get(i).getRoom()+"] "+regList.get(i).getMember_name()+"\",");
				result.append("\"resourceId\":\""+regList.get(i).getRoom()+"\",");
				result.append("\"start\":\""+regList.get(i).getR_start()+"\",");
				result.append("\"end\":\""+regList.get(i).getR_end()+"\"}");
			}
			//System.out.println(result);
		}
		result.append("]");
	
		return result.toString();
	} 
}
