package pb.action;

import java.io.File;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import project.file.PfDao;
import project.file.PfBean;
import projectBoardDb.PbDao;
import projectBoardDb.PbBean;
import command.CommandAction;
public class PbWriteActionPro implements CommandAction {  
	
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Throwable {
		request.setCharacterEncoding("UTF-8");

// 파일 업로드 => 클라이언트가 선택한 파일을 서버의 하드디스크에 복사하는 기능
// 이러한 기능을 수행 하려면 알아햐 하는 것들
// 1. 목적지(어디로 복사할 것인지) 경로 설정
// 현재 웹어플리케이션을 기준으로 upload폴더를 만들어 두었다.
// 실제 경로는 request 를 통해서 알아온다. 
		String savePath = request.getRealPath("/upload");
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		int sizeLimit = 1024*1024*20; // 파일크기제한 20MB
		PfDao pfdao = PfDao.getInstance();
		PfBean file = new PfBean();

		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

		//MultipartRequest 객체를 생성한 순간 서버에 하드디스크에 파일복사가 일어난다

		Enumeration fileNames=multi.getFileNames();
		//int insert=0;
		boolean save = false; // 파일 저장 성공
		
		String file_input = ""; // 받아온 파일이름
		String file_name = ""; // 저장된 파일이름
		//String type = ""; // 파일 종류
		File fileObj = null; // 저장된 파일 객체
		//String originFileName = ""; // 원본 파일 이름
		String fileExtend = ""; // 확장자
		String file_size = ""; // 저장된 파일 사이즈
		String newFileName = "file_"+System.currentTimeMillis();
		
		while(fileNames.hasMoreElements()) {
			file_input = (String)fileNames.nextElement();
			file_name = multi.getFilesystemName(file_input);
			if(file_name != null && file_name != "") {
				//type = multi.getContentType(file_input);
				fileObj = multi.getFile(file_input);
				//originFileName = multi.getOriginalFileName(file_input);
				fileExtend = file_name.substring(file_name.lastIndexOf(".")+1);
				file_size = String.valueOf(fileObj.length());
				
				newFileName+="."+fileExtend;
				fileObj.renameTo(new File(savePath+"/"+newFileName));
				save = true;
			}
		}
			
			// 게시글 저장
				PbBean article = new PbBean(); 
				if(multi.getParameter("pb_num")!="") {
					article.setPb_num(Integer.parseInt(multi.getParameter("pb_num")));
				}
					
				article.setPb_subject(multi.getParameter("pb_subject"));
				article.setPb_content(multi.getParameter("pb_content"));
				article.setEname(multi.getParameter("ename")); 
				article.setEmpno(Integer.parseInt(multi.getParameter("empno")));
				article.setPb_date(new Timestamp(System.currentTimeMillis()));
				
				PbDao pbdao = new PbDao(); 
				pbdao.insertArticle(article); 	 		
				
			// 파일 저장 성공시!
				if(save) { 
				file.setPf_size(Integer.parseInt(file_size));
				file.setPf_realpath(savePath);  
				file.setPf_name(file_name);
				file.setPf_save(newFileName);
				file.setPb_num(article.getPb_num()); 
				pfdao.insertFile(file);
				}				
	return "/pb/PbWritePro.jsp";   
 }
}    