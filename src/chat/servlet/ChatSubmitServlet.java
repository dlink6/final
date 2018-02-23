package chat.servlet;

import java.io.*;
import java.net.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import message.*;

@WebServlet("/ChatSubmitServlet")
public class ChatSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			String content = request.getParameter("chatContent");
			String sempno = request.getParameter("empno");
			int empno = Integer.parseInt(sempno);
			String roomno = request.getParameter("roomno");
			String participants = request.getParameter("participants");
			String personNo = request.getParameter("personNo");
			System.out.println(personNo+"personNo");
			System.out.println("chatSubmit");
			System.out.println("content"+content);
			System.out.println("");
			
			//몇개가 올지 몰르기 떄문에 일단 배열로 받는다.
			
			ChatDTO chat = new ChatDTO();
			chat.setPersonNo(Integer.parseInt(URLDecoder.decode(personNo, "utf-8")));
			chat.setContent(URLDecoder.decode(content, "utf-8"));
			chat.setEmpno(empno);
			chat.setRoomno(roomno);
			chat.setParticipants(URLDecoder.decode(participants, "utf-8"));
			Timestamp time = new Timestamp(System.currentTimeMillis());
			chat.setSenddate(time);
			MessengerDAO msg = MessengerDAO.getInstance();
			response.getWriter().write(msg.submit(chat)+"");
			// 내용 , 보내는 사람 
			}
	}
