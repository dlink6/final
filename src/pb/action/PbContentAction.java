package pb.action;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.file.PfDao;
import project.file.PfBean;
import projectBoardDb.PbDao;
import projectBoardDb.PbBean;
import command.CommandAction;    
public class PbContentAction implements CommandAction {
		
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable{
			
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");          
			
			int pb_num = Integer.parseInt(request.getParameter("pb_num")); //해당 글 번호
			//String pageNum = request.getParameter("pageNum"); // 해당 페이지 번호
			
			PbDao pbdao = new PbDao(); 
			PbBean article = pbdao.getArticle(pb_num);
			 
			//첨부파일 출력
			PfDao pfdao = PfDao.getInstance(); 
			PfBean fileData = pfdao.getFile(pb_num);
			System.out.println(pb_num);
			
			//파일 용량 계산
		      if(fileData!=null) {
		      String CalcuSize = null;
		      int i = 0;
		      double calcu = (double)fileData.getPf_size();
		      while (calcu >= 1024 && i < 3) { 		// 단위 숫자로 나누고 한번 나눌 때마다 i 증가
		         calcu = calcu / 1024;  
		         i++; }
		          DecimalFormat df = new DecimalFormat("##0.0");
		          switch (i) { 
		              case 0:
		                  CalcuSize = df.format(calcu) + "Byte";
		                  break;
		              case 1:
		                  CalcuSize = df.format(calcu) + "KB";
		                  break;
		              case 2:
		                  CalcuSize = df.format(calcu) + "MB";
		                  break;
		             /* default:
		                  CalcuSize="ZZ"; //용량표시 불가*/         
		      } 
		        request.setAttribute("CalcuSzie", CalcuSize);
		      }
		      
		        //해당 뷰에서 사용할 속성
		        request.setAttribute("pb_num", new Integer(pb_num));
		        /*if(pageNum!=null && pageNum!="") {
		        request.setAttribute("pageNum", new Integer(pageNum));
		        }*/
		        
		        request.setAttribute("article", article);
		        
		        request.setAttribute("fileData", fileData);
		        
		      return "/definition/pb/PbContentForm.jsp";
		   }
}