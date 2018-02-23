package chat.action;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import login.LoginDTO;
import message.MessengerDAO;

// ��ȭ�� ���� 
public class forRoom implements CommandAction{   
	
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Throwable{
		String room  = request.getParameter("room");
		System.err.println("room:: "+room);
		StringTokenizer token = new StringTokenizer(room, "/");
		//���ȣ ,, �����ڵ� ,, �����ο�
		String roomno = token.nextToken();
		String participants = token.nextToken();
		String emp_cnt = token.nextToken();
		
		MessengerDAO msg = MessengerDAO.getInstance();
		String roomname = msg.changeRoomname(roomno);
		
		
		System.err.println(participants);
		LoginDTO formId =(LoginDTO) request.getSession().getAttribute("memId");
		int sessionId = formId.getEmpno();
		List<LoginDTO> list = msg.changeEname(participants, sessionId);
		
		request.setAttribute("roomName",roomname);
		request.setAttribute("roomno", roomno);
		request.setAttribute("participants", participants);
		request.setAttribute("personNo", emp_cnt);
		request.setAttribute("list", list);
		
		return "chat/chat2.jsp";
		
}


}
