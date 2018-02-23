package chat.servlet;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import login.*;
import message.*;

@WebServlet("/ChatListServlet")
public class ChatListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");

		String roomno_s = request.getParameter("roomno");
		// System.out.println(roomno_s);

		LoginDTO login = (LoginDTO) request.getSession().getAttribute("memId"); // login.jsp �� ������ �������� �ش� ���ο��� ������ ����
																				// ����:
		if (login != null) {

			int empno_join = login.getEmpno();
			if (roomno_s == null || roomno_s.equals("")) {
				roomno_s = "0";
			}
			int roomno = Integer.parseInt(roomno_s);
			// System.out.println(listType+" type");
			// ������ Ÿ���� ������ , ���� �ʾ����� ����
			if (roomno == 0) {
				response.getWriter().write("");
			} else {
				try {
					response.getWriter().write(getTen(roomno, empno_join));
//					response.getWriter().write(getID(roomno, listType, empno_join)); // listType =lastID caht
				} catch (Exception e) {
					response.getWriter().write("");
				}
			}
		}
	}

	public String getTen(int roomno, int empno_join) { // ó���� ������ ����� (���ѹ� �����)
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		MessengerDAO chatDAO = MessengerDAO.getInstance();
		System.out.println("���ȣ ::" + roomno + "empno_join :::" + empno_join);
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByRecent(roomno, 10, empno_join);
		if (chatList.size() == 0) {
			return "";
		}
		// empno , content,formatTIME
		// System.out.println("��� ���� list �뷮"+chatList);
		for (int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getEmpno() + "\"},"); // �����ȣ,����̸� ,�μ���ȣ,�μ��̸�,����,�����ð�
			result.append("{\"value\": \"" + chatList.get(i).getEname() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getDeptno() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getDname() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getContent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getFormatTime() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getC_cnt() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getProfilPath() + "\"}]");
			if (i != chatList.size() - 1) {
				result.append(",");
			}
		}
		result.append("]}");
		System.err.println(result.toString());
		return result.toString();
	}
	/*
	 * { "result" :[
	 * [{:value" : "empno"},{"value" : "ename"}, {"value" : "deptno"} ,...,{"
	 * value" : "formatTime"}], ],"last" :"lastNumber"}
	 */

	public String getID(int roomno, String chatID, int empno_join) { // ���ο� ������ ����� ������ �����
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		MessengerDAO chatDAO = MessengerDAO.getInstance();
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByChatNumber(roomno, chatID, empno_join);
		// System.out.println("�������list �뷮"+chatList);
		if (chatList.size() == 0) {
			return "";
		}
		for (int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getEmpno() + "\"},"); // �����ȣ,����̸� ,�μ���ȣ,�μ��̸�,����,�����ð�
			result.append("{\"value\": \"" + chatList.get(i).getEname() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getDeptno() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getDname() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getContent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getFormatTime() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getC_cnt() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getProfilPath() + "\"}]");
			if (i != chatList.size() - 1) {
				result.append(",");
			}
		}
		result.append("],\"last\":\"" + chatList.get(0).getChatNumber() + "\"}");

		return result.toString();
	}

}
