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
		request.setCharacterEncoding("UTF-8"); //�Ķ���� ���ڵ�(�Ѿ���� ��� �Ű������� ó���Ҷ�)
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(getJSON1()); //����ڰ� �Է��� �Ű������� ���� �˻��� ������ json���·� ȭ�鿡 ��� -> ������ �ϴ� ����
	}
	
	public String getJSON1() {
		StringBuffer result = new StringBuffer("");
		result.append("["); //result�� ���� ����� ����
		RegisterDBBean bean = new RegisterDBBean();
		ArrayList<RegisterDataBean> regList = bean.getList();
		for(int i=0; i<regList.size(); i++) { //��ü ����� ����(��� ����� ������ ����)��ŭ �����ͺ��̽����� �̾Ƴ�
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
