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

		LoginDTO login = (LoginDTO) request.getSession().getAttribute("memId"); // login.jsp 를 실행을 시켯지면 해당 라인에서 에러가 나는
																				// 이유:
		if (login != null) {

			int empno_join = login.getEmpno();
			if (roomno_s == null || roomno_s.equals("")) {
				roomno_s = "0";
			}
			int roomno = Integer.parseInt(roomno_s);
			// System.out.println(listType+" type");
			// 지정된 타입이 왔을떄 , 오지 않았을떄 나눔
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

	public String getTen(int roomno, int empno_join) { // 처음의 가져올 내용들 (딱한번 실행됨)
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		MessengerDAO chatDAO = MessengerDAO.getInstance();
		System.out.println("방번호 ::" + roomno + "empno_join :::" + empno_join);
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByRecent(roomno, 10, empno_join);
		if (chatList.size() == 0) {
			return "";
		}
		// empno , content,formatTIME
		// System.out.println("결과 담은 list 용량"+chatList);
		for (int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getEmpno() + "\"},"); // 사원번호,사원이름 ,부서번호,부서이름,내용,보낸시간
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

	public String getID(int roomno, String chatID, int empno_join) { // 새로운 내용이 생기면 가져올 내용들
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		MessengerDAO chatDAO = MessengerDAO.getInstance();
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByChatNumber(roomno, chatID, empno_join);
		// System.out.println("결과담은list 용량"+chatList);
		if (chatList.size() == 0) {
			return "";
		}
		for (int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getEmpno() + "\"},"); // 사원번호,사원이름 ,부서번호,부서이름,내용,보낸시간
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
