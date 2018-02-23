package message;
import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import login.*;

/**
 * Servlet implementation class UserSearchSevlet
 */
@WebServlet("/RoomSearchSevlet")
public class RoomSearchSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			//System.out.println("RoomSearchServlet �� ����Ǿ����ϴ�.");
			String searchword = request.getParameter("searchword");
			 LoginDTO  mem = (LoginDTO)request.getSession().getAttribute("memId");
			 //���� �α����� ����� ��ȣ��  memId �� ����
			 if(mem != null) {  
			 int memId = mem.getEmpno(); 
			//System.out.println("searchword"+searchword+"�Դϴ�.");
			//System.out.println("���� �α��ε� ȸ���� " + memId);
			if(searchword == null || searchword.equals("")){   //���ȣ�� �׳� ������� �������� ���ǹ�
				response.getWriter().write(getJSON(memId));   //�׳� �ð������� �� �������� ��ȭ�� ����Ʈ
			}else {
				response.getWriter().write(getJSON(searchword,memId)); //�Է��� ������ �������� ��ȭ�� ����Ʈ
			}
			 }
	}
	public String getJSON(int memId) {  
		//System.out.println("�׳� JSON �޼��� ����");
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		RoomDAO userTDAO = RoomDAO.getInstance();
		List<RoomDTO> userList = userTDAO.search(memId);
		//System.out.println(userList.size()+"���� �޼����� ũ�Ⱚ");
		for(int i =0; i<userList.size(); i++) {  //�ڱ��ڽ�() , ���� �ο� , ���̸� , �ð� , ���ȣ, ������ ���� , �����ڵ�  
			result.append("[{\"value\": \"" + userList.get(i).getEmpno_join() + "\"},");  
			result.append("{\"value\": \"" + userList.get(i).getEmp_cnt() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getr_name()+ "\"},");
			result.append("{\"value\": \"" + userList.get(i).getFormatTime()+ "\"},");
			result.append("{\"value\": \"" + userList.get(i).getRoomNo()+ "\"},");
			result.append("{\"value\": \"" + userList.get(i).getR_cnt()+ "\"},");
			result.append("{\"value\": \"" + userList.get(i).getParticipants() + "\"}],");
		}
		result.append("]}");
		//System.err.println(result.toString());
		return result.toString();
	}
	
	
	public String getJSON(String searchword,int memId) {
	//System.out.println("�Ű����� �ִ� JSON �޼��� ����");
	if(searchword == null) {searchword="";}
	StringBuffer result = new StringBuffer("");
	result.append("{\"result\":[");
	RoomDAO userTDAO = RoomDAO.getInstance();
	List<RoomDTO> userList = userTDAO.search(searchword,memId);
	//System.out.println(userList.size()+"�Ʒ��� �޼����� ũ�Ⱚ");
	for(int i =0; i<userList.size(); i++) {
		result.append("[{\"value\": \"" + userList.get(i).getEmpno_join() + "\"},");  
		result.append("{\"value\": \"" + userList.get(i).getEmp_cnt() + "\"},");
		result.append("{\"value\": \"" + userList.get(i).getr_name()+ "\"},");
		result.append("{\"value\": \"" + userList.get(i).getFormatTime()+ "\"},");
		result.append("{\"value\": \"" + userList.get(i).getRoomNo()+ "\"},");
		result.append("{\"value\": \"" + userList.get(i).getR_cnt()+ "\"},");
		result.append("{\"value\": \"" + userList.get(i).getParticipants() + "\"}],");
	}
	result.append("]}");
	//System.err.println(result.toString());
	return result.toString();
	}
	
}
