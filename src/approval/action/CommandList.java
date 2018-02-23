package approval.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import approval.db.ApprovalDAO;
import command.CommandAction;
import login.LoginDTO;

public class CommandList implements CommandAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		ApprovalDAO dao = ApprovalDAO.getInstance();
		String start_ =request.getParameter("start");  //11
		String page_ = request.getParameter("page");
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null || pageNum.equals("") ) {
			pageNum = "1";
		}
		request.setAttribute("pageNum",Integer.parseInt(pageNum));
		int page = 1;
		int pagecount=0;
		if(page_ != null) {
			page = Integer.parseInt(page_);
		}
		
		LoginDTO dto =(LoginDTO) request.getSession().getAttribute("memId");
		int empno = dto.getEmpno();
		int deptno =dto.getDeptno();
		
		int start= 0;
		int end = 0; 
		int count =0;
		String search  = request.getParameter("search");
		
		int startcount =0;
		int endcount =0;
		
		
		if(start_ == null) {  //泥섏쓬 �뒪���듃�떆 true
			start = 1;
			end = 10;
		}else {
			start = Integer.parseInt(start_);
			end = start+9;
			if(start == -99) {
				start=1;
				end=start+9;
				page=1;
			}
			
		}
		if(search== null) {  //寃��깋 �뻽�쓣 寃쎌슦 else
			count = dao.getAcountList();
			if(empno ==1000) { //寃��깋�븞�븯怨� 遺��꽌踰덊샇媛� 10�씤寃쎌슦 �떎�뻾
				request.setAttribute("list",dao.getList(start,end));
			}else {  //寃��깋�븞�븯怨� 遺��꽌踰덊샇媛� 10�씠�븘�땶寃쎌슦 �떎�뻾
				request.setAttribute("list",dao.getMy_List(start, end, empno));
			}
		}else {
			count = dao.getSearchCount(deptno, empno, search);
			request.setAttribute("list",dao.getSearchList(search, start, end, empno, deptno)); //遺��꽌媛� 10�씠硫� 紐⑤뱺 �옄猷� 寃��깋 遺��꽌媛� 10�씠�븘�땲硫� �옄湲곗궗踰덇�留� 寃��깋
		}
		
		if(count <= 10) {
			count =1;
		}else {
			if(count%10 ==0 ) { 
				count = count/10;
			}else {
				count = count/10;
				count +=1;
			}
		}
		
		if(count%10 ==0) {
			pagecount = count/10;
		}else {
			pagecount = count/10 +1;
		}
		if(page ==1) {
			startcount = 1;
			endcount =count;
			if(count >=10) {
				endcount=10;
			}
		}else {
			if(page ==pagecount+1) {
				page = page-1;
				startcount = page*10-9;
				endcount=startcount+9;
				request.setAttribute("start",1);
			}else {
				startcount = page*10-9;
				endcount=startcount+9;
			}
		}
		
		
		request.setAttribute("startcount", startcount);
		request.setAttribute("endcount", endcount);
		request.setAttribute("page", page);
		request.setAttribute("search", search);
		
		return "/definition/electronicApproval/electronicApprovalList.jsp";
	
	}
}
   