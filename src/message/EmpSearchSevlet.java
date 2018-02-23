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
@WebServlet("/empSearchSevlet")
public class EmpSearchSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("��!!!!!!��~~~~~~~~!");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String userName = request.getParameter("userName");
		//System.out.println("�޾ƿ� �Ű����� ��::" + userName);
		LoginDTO dto = (LoginDTO) request.getSession().getAttribute("memId");
		if (dto != null) {

			int sessionId = dto.getEmpno();
			if (userName == null) {
				response.getWriter().write(getJSON(sessionId));
			} else {
				response.getWriter().write(getJSON(userName, sessionId));
			}
		}
	}

	public String getJSON(String userName, int sessionId) {
		//System.out.println("�Ű� ���� �ִ� getJSON ����");
		if (userName == null) {
			userName = "";
		}
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		UserTDAO userTDAO = UserTDAO.getInstance();
		ArrayList<LoginDTO> userList = userTDAO.search(userName, sessionId);
		for (int i = 0; i < userList.size(); i++) {
			result.append("[{\"value\": \"" + userList.get(i).getEmpno() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getEname() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getDeptno() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getProfilePath() + "\"}],");
		}
		result.append("]}");
		//System.out.println(result.toString() + "���");
		return result.toString();
	}

	public String getJSON(int sessionId) {
		//System.out.println("�Ű� ���� ���� getJSON ����");
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		UserTDAO userTDAO = UserTDAO.getInstance();
		ArrayList<LoginDTO> userList = userTDAO.search(sessionId);
		for (int i = 0; i < userList.size(); i++) {
			result.append("[{\"value\": \"" + userList.get(i).getEmpno() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getEname() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getDeptno() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getProfilePath() + "\"}],");
		}
		result.append("]}");
		//System.out.println(result.toString() + "���");
		return result.toString();
	}

}
