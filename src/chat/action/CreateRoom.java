package chat.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import login.LoginDTO;
import message.MessengerDAO;
import message.RoomDTO;

// ��ȭ�� ���� 
public class CreateRoom implements CommandAction{   
	
	
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Throwable{
		String roomname  = request.getParameter("roomname");
		//���� ,�� ����Ǿ��ֱ� ������ , ������ �߶��࿩�Ѵ�. (empno ��)
		
		String participants =  request.getParameter("participants");
		String personNo_s = request.getParameter("personNo");
		LoginDTO login =(LoginDTO)request.getSession().getAttribute("memId");
		
		
		int loginEmpno = login.getEmpno();
		// ���� �� �����ڵ� �� ��Ƴ��� �� �����ڵ��� �̿��ؼ� �˻����ؼ� ���´� ���̾�����  ���� �����  row ���� insert ���ش�.
		
		int personNo = Integer.parseInt(personNo_s) + 1; //��ȭ���� �����ϴ� ����� �� , ���ڽű��� �����ؾ��Ѵ�.
		
		
		MessengerDAO msg = MessengerDAO.getInstance();
		int roomno = 0;
		if(personNo == 2) {
			RoomDTO dto  = new RoomDTO();
			dto = msg.checkRoom(participants,""+loginEmpno);
			roomno=dto.getRoomNo();
			participants += ","+loginEmpno;
		}else {
			participants += ","+loginEmpno;
			roomno  =  msg.checkRoom(participants,personNo,roomname);
			
		}
		
		
		List<LoginDTO> list =msg.changeEname(participants,loginEmpno);
		
		request.getSession().setAttribute("memID", "1001");
		request.setAttribute("roomName",roomname);
		request.setAttribute("roomno", roomno);
		request.setAttribute("participants",participants);
		request.setAttribute("personNo", personNo);
		request.setAttribute("list", list);
		
		return "chat/chat2.jsp";
		
}
 

}
