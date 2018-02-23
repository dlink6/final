package login.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.fastinfoset.Decoder;

import logon.LogonDBBean;
import logon.LogonDataBean;
import sun.nio.cs.ext.DoubleByte.Decoder_EUC_SIM;

@WebServlet("/EmpSearchSevlet") 
public class EmpSearchSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String userName = request.getParameter("userName");
		try {
			if (userName == null || userName.equals("")) {
					
				response.getWriter().write(getJSON(request));
			} else {
				response.getWriter().write(getJSON(userName,request));
			}
		} catch (Exception e) {  
			e.printStackTrace();
		}
	}
	public String getJSON(String userName,HttpServletRequest request) throws Exception {
		
		//pagination 에서 기본적으로 설정해야할것들
		//매개변수로 받는 값 pageNum 이걸로 모든것이 시작된다.
		//1 . 몇번쨰 페이지를 보여줄것인지 ?  - > 현재 페이지로 String  - > int 와서 나머지들을 계산하는데 사용된다.  [pageNum] -> [currentPage] 
		//2 . 해당페이지에서 몇개의 article을 보여줄것인지 ->  pageSize  (개발자가 직접 정한다.)
		//3 . 해당페이지에서 article 번호의 시작점 [startRow]   (현재페이지 * 보여줄 행의 개수 ) - (보여줄행의개수 -1)  (currentPage*pageSize)-(pageSize-1)  1페이지면 1이 나올수 있게 해준다. , 2페이지면  4 
		//4 . 해당 페이지에서 article 번호의 마지막지점 [endRow]   (현제페이지*보여줄 행의 개수 ); (currentPage * pageSize)
		//5 . 검색결과의 나온 총 행의 개수 [ count ]   검색로직 에서나온 결과의 값의 수 
		//6 . 전체글 번호 [number]  		(검색결과의 모든행의 개수)- (현재페이지-1)*(보여줄 행의 개수)  (count)-(currentPage-1)*(pageCount)
		//7 . 검색결과의 전체 페이지 의 개수 [pageCount]     검색결과의 모든 행의수 / 보여줄 행의개수 + (검색결과의 모든행의개수의 나머지가있으면 +1 없으면 0)  count/pageSize + (count % pageSize == 0 ? 0: 1);
		//8 . 보여질 페이지의 전체 개수 [pageBlock] (개발자가 직접 정한다.)
		//9 . 시작되는 페이지 [startPage]     (현제페이지/보여질 페이지의 개수)*보여질 페이지  +1  (countPage/pageBlock)pageBlock +1
		//10 . 끝나는 페이지 [endPage]         (시작하는 페이지)+보여질 페이지의 전체 개수 -1   startPage + pageBlock -1
		//11 . 다음 페이징   [nextPage]    	if(endPage<pageCount) 일경우에만  : startPage + pageBlock  을 pageNum 값으로 보냄 
		//12 . 이전 페이지  [Previous]       if(startPage > pageBlock) 일 경우에만 : startPage -pageBlock 을 pageNum 값으로 보냄
		

		String pageNum=request.getParameter("pageNum");
		if(pageNum == null || pageNum.equals("") ||pageNum.equals("undefined")) {
			pageNum ="1"; 
		}
		int pageSize = 7;//하나의 페이지에서 보여줄 행의 수
		int currentPage = Integer.parseInt(pageNum);  
		System.out.println("currentPage ::" + currentPage);
		
		
		int startRow = (currentPage*pageSize)-(pageSize-1);
		//2*3 - 3-1 = 	4 
		int endRow = currentPage*pageSize;
		
		//startRow, endRowm pageSize , count, pageNum
		int count =0 ; //db의 입력된 행의 개수의 값
		int number = 0; // 전체글번호
		number = count-(currentPage-1)*pageSize;
		
		
			
		StringBuffer result = new StringBuffer("");
		LogonDBBean dao = LogonDBBean.getInstance();
		
		count  = dao.getCount(userName);
		List<LogonDataBean> userList = dao.search(userName,startRow,endRow);
		System.err.println("count :: "+ count );
		int pageCount  = count/pageSize + (count % pageSize == 0 ? 0 :1);
		int pageBlock = 5;
		
		int result1  = (int)(currentPage/pageBlock);
		
		//jisoo
		if(currentPage%pageBlock ==0) {
			result1 = currentPage/pageBlock -1;
		}
		
		
		int startPage = result1*pageBlock+1;
		
		//2 / 2 1*2+1 = 3
		
		int endPage = startPage + pageBlock-1;

		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		
		int nextPage = 0;
		if(endPage < pageCount) {
			nextPage = startPage + pageBlock;
		}
		int previous = 0;
		if(startPage > pageBlock) {
			previous = startPage - pageBlock;
		}
		
		
		//페이지 가 몇개 나올지
		result.append("{\"result\":[");
		for (int i = 0; i < userList.size(); i++) {
			result.append("[{\"value\": \"" + userList.get(i).getEmpno() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getEname() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getPhone1() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getAddress() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getJob() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getEmail() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getHiredate() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getDeptno() + "\"},");
			if(i == userList.size()-1) {
				result.append("{\"value\": \"" + userList.get(i).getProg_num() + "\"}]],");
			}else {
				result.append("{\"value\": \"" + userList.get(i).getProg_num() + "\"}],");
			}
		}//empno,ename,phone1,Address,job,email,hiredate,deptno
		if(userList.size() ==0 ) {
			result.append("\"null\"], ");
		}
		result.append("\"endPage\": \""+endPage+"\",");
		result.append("\"startPage\": \""+startPage+"\",");
		result.append("\"pageBlock\": \""+pageBlock+"\",");
		result.append("\"pageCount\": \""+pageCount+"\",");
		result.append("\"endRow\": \""+endRow+"\",");
		result.append("\"startRow\": \""+startRow+"\",");
		result.append("\"pageSize\": \""+pageSize+"\",");
		result.append("\"nextPage\": \""+nextPage+"\",");
		result.append("\"count\": \""+count+"\",");  
		result.append("\"previous\": \""+previous+"\",");
		result.append("\"pageSize\": \""+pageSize+"\",");
		result.append("\"currentPage\": \""+currentPage+"\",");
		result.append("\"pageNum\": \""+pageNum+"\"}");
														
		System.out.println("endPage ::" + endPage);
		System.out.println("startPage ::" + startPage);
		System.out.println("pageBlock ::" + pageBlock);
		System.out.println("pageCount ::" + pageCount);
		System.out.println("endRow ::" + endRow);  
		System.out.println("startRow ::" + startRow);
		System.out.println("pageSize ::" + pageSize);
		System.out.println("currentPage ::" + currentPage);
		System.out.println("pageNum ::"+ pageNum);
		System.out.println("previous ::"+ previous);
		System.out.println("nextPage ::"+ nextPage);
		
		System.err.println(result.toString() + "toString");  
		return result.toString();
	}

	public String getJSON(HttpServletRequest request) throws Exception {
		
		String pageNum=request.getParameter("pageNum");
		if(pageNum == null || pageNum.equals("") ||pageNum.equals("undefined")) {
			pageNum ="1"; 
		}
		
		
		int pageSize = 7;
		int currentPage = Integer.parseInt(pageNum);
		
		int startRow = (currentPage*pageSize)-(pageSize-1); 
		int endRow = currentPage*pageSize;  
		int count =0 ; //db의 입력된 행의 개수의 값
		int number = 0; // 전체글번호
		number = count-(currentPage-1)*pageSize;
		
		
		StringBuffer result = new StringBuffer("");
		LogonDBBean dao = LogonDBBean.getInstance();
		count  = dao.getCount();
		System.out.println("count ::" + count);
		if(count == -1 ) {return "";}
		List<LogonDataBean> userList = dao.search(startRow,endRow);
		
		int pageCount  = count/pageSize + (count % pageSize == 0 ? 0 :1);
		int pageBlock = 5;
		int result1  = (int)(currentPage/pageBlock);  // 1 , 2,
		
		
		//jisoo
		if(currentPage%pageBlock ==0) {
			result1 = currentPage/pageBlock -1;
		}
		
		int startPage = result1*pageBlock+1;
		
		
		
		int endPage = startPage + pageBlock -1;
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		int nextPage = 0;
		if(endPage < pageCount) {
			nextPage = startPage + pageBlock;
		}
		int previous = 0;
		if(startPage > pageBlock) {
			previous = startPage - pageBlock;
		}
		

	
		result.append("{\"result\":[");
		for (int i = 0; i < userList.size(); i++) {
			result.append("[{\"value\": \"" + userList.get(i).getEmpno() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getEname() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getPhone1() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getAddress() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getJob() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getEmail() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getHiredate() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getDeptno() + "\"},");
			if(i == userList.size()-1) {
				result.append("{\"value\": \"" + userList.get(i).getProg_num() + "\"}]],");
			}else {
				result.append("{\"value\": \"" + userList.get(i).getProg_num() + "\"}],");
			}
			//검색결과 제이슨 배열로 저장완료  
			
			
		}//페이징과 관련된 내용 저장
		result.append("\"endPage\": \""+endPage+"\",");
		result.append("\"startPage\": \""+startPage+"\",");
		result.append("\"pageBlock\": \""+pageBlock+"\",");
		result.append("\"pageCount\": \""+pageCount+"\",");
		result.append("\"count\": \""+count+"\",");
		result.append("\"endRow\": \""+endRow+"\",");
		result.append("\"startRow\": \""+startRow+"\",");
		result.append("\"pageSize\": \""+pageSize+"\",");
		result.append("\"previous\": \""+previous+"\",");
		result.append("\"nextPage\": \""+nextPage+"\",");
		result.append("\"currentPage\": \""+currentPage+"\",");
		result.append("\"pageNum\": \""+pageNum+"\"}");
														
		System.out.println("endPage ::" + endPage);
		System.out.println("startPage ::" + startPage);
		System.out.println("pageBlock ::" + pageBlock);
		System.out.println("pageCount ::" + pageCount);
		System.out.println("endRow ::" + endRow);  
		System.out.println("startRow ::" + startRow);
		System.out.println("pageSize ::" + pageSize);
		System.out.println("currentPage ::" + currentPage);
		System.out.println("pageNum ::"+ pageNum);
		System.out.println("previous ::"+ previous);
		System.out.println("nextPage ::"+ nextPage);
		
		System.err.println(result.toString() + "toString");
		return result.toString();
	}

}
