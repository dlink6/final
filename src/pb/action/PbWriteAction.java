package pb.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
public class PbWriteAction implements CommandAction {  
	
	public String execute(HttpServletRequest request,
	        HttpServletResponse response)throws Throwable {
		
		response.setContentType("text/html; charset=UTF-8");
		int pb_num=0; 
		
        try{
         
        // 답글인경우
          if(request.getParameter("pb_num")!=null){
         pb_num=Integer.parseInt(request.getParameter("pb_num"));
         }
          
        }catch(Exception e){e.printStackTrace();}
		// 해당 view에서 사용할 속성
		request.setAttribute("pb_num", new Integer(pb_num));

		return "/definition/pb/PbWriteForm.jsp";  //해당 view
} 
}  