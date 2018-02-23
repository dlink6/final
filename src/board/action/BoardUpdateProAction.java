package board.action;

import java.io.File;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.file.model.FileDAO;
import board.file.model.FileDataBean;
import board.model.BoardDAO;
import board.model.BoardDataBean;
import command.CommandAction;

public class BoardUpdateProAction implements CommandAction{
	
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
	//파일 수정부분
		String savePath = request.getSession().getServletContext().getRealPath("/uploadFiles");
		response.setContentType("text/html; charset=UTF-8");
		
		int sizeLimit = 1024*1024*20; //파일 최대사이즈 20MB
		FileDAO fdao = FileDAO.getInstance();
		FileDataBean file = new FileDataBean();
		
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

		Enumeration fileNames=multi.getFileNames();
		boolean save = false; //파일저장성공
		
		String file_input = ""; //받아온 파일이름
		String file_name = ""; //저장된 파일이름
		String type = ""; //파일종류
		File fileObj = null; //저장된 파일 객체
		String originFileName = ""; //원본 파일 이름
		String fileExtend = ""; //확장자
		String file_size = ""; //저장된 파일 사이즈
		String newFileName = "file_"+System.currentTimeMillis();
		
		while(fileNames.hasMoreElements()) {
			file_input = (String)fileNames.nextElement();
			file_name = multi.getFilesystemName(file_input);
			if(file_name != null && file_name != "") {
				type = multi.getContentType(file_input);
				fileObj = multi.getFile(file_input);
				
				//기존파일 삭제
				FileDataBean oldfileData = fdao.getFile(Integer.parseInt(multi.getParameter("board_num")));
								
				if(oldfileData != null) {
				//기존에 저장된 파일을 객체로 지정
				File oldfile = new File(oldfileData.getFile_realpath()+"/"+oldfileData.getFile_save());
				oldfile.delete();//기존파일 삭제
				//db삭제
				fdao.deleteFile(Integer.parseInt(multi.getParameter("board_num")));
				}
				
				originFileName = multi.getOriginalFileName(file_input);
				fileExtend = file_name.substring(file_name.lastIndexOf(".")+1);
				file_size = String.valueOf(fileObj.length());
				
				newFileName+="."+fileExtend;
				fileObj.renameTo(new File(savePath+"/"+newFileName));
				save = true;
			}
		}		
				
		String pageNum = request.getParameter("pageNum");
		
		BoardDataBean article = new BoardDataBean();
		
		//상단공지여부(기본값 0)
		if(multi.getParameter("board_notice_top")!="" && multi.getParameter("board_notice_top")!=null) {
			article.setBoard_notice_top(Integer.parseInt(multi.getParameter("board_notice_top")));
		}
		article.setBoard_num(Integer.parseInt(multi.getParameter("board_num")));
		article.setBoard_subject(multi.getParameter("board_subject"));
        article.setBoard_content(multi.getParameter("board_content"));
        article.setBoard_cat(multi.getParameter("board_cat"));
        article.setBoard_mod_date(new Timestamp(System.currentTimeMillis()));
		
        BoardDAO bdao = new BoardDAO();
        int check = bdao.updateArticle(article);
        if(pageNum!=null && pageNum!="") {
        request.setAttribute("pageNum", new Integer(pageNum));
        }
        request.setAttribute("check", new Integer(check));
        request.setAttribute("board_num", multi.getParameter("board_num"));
		//파일 저장 성공시
		if(save) { 
			
			file.setFile_size(Integer.parseInt(file_size));
			file.setFile_realpath(savePath);
			file.setFile_name(file_name);
			file.setFile_save(newFileName);
			file.setBoard_num(Integer.parseInt(multi.getParameter("board_num")));
			fdao.updateFile(file);
		}        

        return "/board/BoardUpdatePro.jsp";
	}
}
