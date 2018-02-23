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
	public class PbUpdateActionPro implements CommandAction{
		
		public String execute(HttpServletRequest request, HttpServletResponse response)	throws Throwable {
			request.setCharacterEncoding("UTF-8");
			
		//
			String savePath = request.getRealPath("/upload");
			response.setContentType("text/html; charset=UTF-8");
			
			int sizeLimit = 1024*1024*50; // 파일 최대사이즈 50MB
			PfDao pfdao = PfDao.getInstance();
			PfBean file = new PfBean();
			
			MultipartRequest multi = new MultipartRequest
					(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
			
			Enumeration fileNames=multi.getFileNames();
			boolean save = false; //파일저장성공
			
			String file_input = ""; //받아온 파일이름
			String pf_name = ""; //저장된 파일이름
			String type = ""; //파일종류
			File fileObj = null; //저장된 파일 객체
			String originFileName = ""; //원본 파일 이름
			String fileExtend = ""; //확장자
			String file_size = ""; //저장된 파일 사이즈
			String newFileName = "file_"+System.currentTimeMillis();
			
			while(fileNames.hasMoreElements()) {
				file_input = (String)fileNames.nextElement();
				pf_name = multi.getFilesystemName(file_input);
				if(pf_name != null && pf_name != "") {
					type = multi.getContentType(file_input);
					fileObj = multi.getFile(file_input);
					
					//기존 파일 삭제
					PfBean oldfileData = pfdao.getFile(Integer.parseInt(multi.getParameter("pb_num")));
									
					if(oldfileData != null) {
					//기존에 저장된 파일을 객체로 지정
					File oldfile = new File(oldfileData.getPf_realpath()+"/"+oldfileData.getPf_save());
					oldfile.delete();//기존 파일 삭제
					//db 삭제
					pfdao.deleteFile(Integer.parseInt(multi.getParameter("pb_num")));
					}
					
					originFileName = multi.getOriginalFileName(file_input);
					fileExtend = pf_name.substring(pf_name.lastIndexOf(".")+1);
					file_size = String.valueOf(fileObj.length());
					
					newFileName+="."+fileExtend;
					fileObj.renameTo(new File(savePath+"/"+newFileName));
					save = true;
				}
			}		
					
			//String pageNum = request.getParameter("pageNum");
			
			PbBean article = new PbBean();
					
			article.setPb_num(Integer.parseInt(multi.getParameter("pb_num")));
			/*article.setEname(multi.getParameter("ename"));*/
			article.setPb_subject(multi.getParameter("pb_subject"));
	        article.setPb_content(multi.getParameter("pb_content"));	        
	        article.setPb_date(new Timestamp(System.currentTimeMillis()));
			
	        PbDao pbdao = new PbDao();
	        int check = pbdao.updateArticle(article);
	        
	        //request.setAttribute("pageNum", new Integer(pageNum));
	        request.setAttribute("check", new Integer(check));
	        
			//파일 저장 성공시
	        if(save) { 
				file.setPf_size(Integer.parseInt(file_size));
				file.setPf_realpath(savePath);
				file.setPf_name(pf_name);
				file.setPf_save(newFileName);
				file.setPb_num(article.getPb_num()); 
				pfdao.insertFile(file);
				}
	        request.setAttribute("pb_num", Integer.parseInt(multi.getParameter("pb_num")));	        
	        return "/pb/PbUpdatePro.jsp";
		}
	} 