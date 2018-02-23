package chat.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import login.LoginDTO;
import message.*;

public class CheckRoom implements CommandAction{   
	
	
	//占싹놂옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占� 
	//1:1占쏙옙화占쏙옙 占쏙옙占쏙옙 占쌍는곤옙占� 占쏙옙 占쏙옙占승곤옙占�  
	
	
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Throwable{
		String participant = request.getParameter("participant");//占쏙옙占쏙옙占쏙옙 占쏙옙占썩에 sessionId 占쏙옙占싱띰옙 占쏙옙占쏙옙 2占쏙옙占싱삼옙占쏙옙 占쏙옙占쌍댐옙.

		
		MessengerDAO msg = MessengerDAO.getInstance();
		
		LoginDTO formId =(LoginDTO) request.getSession().getAttribute("memId");
		
		
		int sessionId = formId.getEmpno();
		//占쏙옙占쏙옙占쌘듸옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占싯뤄옙占쌍깍옙 占쏙옙占쏙옙 占쌘듸옙
		String participants =  participant+","+sessionId;
		
		RoomDTO dto  =msg.checkRoom(participant , ""+formId.getEmpno());
		int roomno = 0;
		if(dto != null) { //
			roomno = dto.getRoomNo();
		}else {
			return "";
		}
		
		List<LoginDTO> list =msg.changeEname(participants,sessionId);
		  
		String ename = msg.changeEname(participant);
		request.setAttribute("roomName",ename+"("+participant+")님 과의대화");

		request.setAttribute("roomName",participant+"님과의대화");
		request.setAttribute("participants", participants);
		request.setAttribute("roomno", roomno);
		request.setAttribute("personNo", 2);
		request.setAttribute("list", list);
		return "chat/chat2.jsp";
		
}


}
