package schedule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import login.LoginDTO;

import java.util.*;
import java.text.*;

public class AlarmPage implements CommandAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		Date date = new Date();
		SimpleDateFormat simple1 = new SimpleDateFormat("yyyy년 MM월 dd일");
		SimpleDateFormat simple2 = new SimpleDateFormat("yyyy-MM-dd");
		String str = simple1.format(date);
		String strList = simple2.format(date);
		
		ScheduleDBBean dbpro = ScheduleDBBean.getInstance();
		
		LoginDTO dto = (LoginDTO)request.getSession().getAttribute("memId");
		int empno = dto.getEmpno();
		
		ArrayList<ScheduleDataBean> data = dbpro.getList(strList, empno);

		if(data.isEmpty()) {
			request.setAttribute("emptyData", data.isEmpty());
		} else {
			request.setAttribute("emptyData", data.isEmpty());
			request.setAttribute("data", data);
		}
		
		request.setAttribute("str", str);
		
		return "/scheduler/Alarm.jsp";
	}
}
