package photoAlbum;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import command.CommandAction;

public class PhotoAlbumWrite implements CommandAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		String savePath = request.getSession().getServletContext().getRealPath("/uploadPhotos");//절대경로 폴더까지
		int maxsize = 1024*1024*20;
		
		MultipartRequest multi = new MultipartRequest(request,savePath,maxsize,"utf-8", new DefaultFileRenamePolicy());
		
		Enumeration fileNames= multi.getFileNames();
		
		
		String file_input ="";  //받아온 파일이름
		String file_name ="";  //저장된 파일이름
		File fileObj = null;    //저장된 파일객체
		String fileExtend =""; //확장자                                    
		String newFileName = System.currentTimeMillis()+"";    //파일이름   
		
		
		PhotoDAO dao = PhotoDAO.getInstance();
		PhotoDTO dto = new PhotoDTO();
		
		if(fileNames.hasMoreElements()) {
			file_input =(String)fileNames.nextElement();
			file_name= multi.getFilesystemName(file_input);
			fileObj = multi.getFile(file_input);
			fileExtend = file_name.substring(file_name.lastIndexOf(".")+1);
			newFileName +="."+fileExtend;
			dao.insertPhoto(file_name);
		}
		
		
		return "/photoAlbum/photoRenewal.jsp";
	}

}
