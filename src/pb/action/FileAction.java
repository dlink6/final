package pb.action;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.file.PfDao;
import project.file.PfBean;
import command.CommandAction;
public class FileAction implements CommandAction {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
				
		// 다운로드할 파일명을 가져온다.
        String pf_name = request.getParameter("pf_name");
        String pf_save = request.getParameter("pf_save"); 
      
        // 파일이 있는 절대경로를 가져온다.
        // 현재 업로드된 파일은 upload 폴더에 있다.
        String savePath = request.getRealPath("/upload"); 
        // 파일의 절대경로를 만든다.
        String filePath = savePath + "\\" + pf_save;
        System.out.println("filePath ::: " + filePath);
        try {
            File file = new File(filePath);
            byte b[] = new byte[(int) file.length()];
            
            // page의 ContentType등을 동적으로 바꾸기 위해 초기화시킴
            response.reset();
            response.setContentType("application/octet-stream"); 
            
            // 한글 인코딩
            String encoding = new String(pf_name.getBytes("utf-8"),"iso-8859-1");
            System.out.println("encoding :::" + encoding);
            // 파일 링크를 클릭했을 때 다운로드 저장 화면이 출력되게 처리하는 부분
            response.setHeader("Content-Disposition", "attachment;filename="+ encoding);
            response.setHeader("Content-Length", String.valueOf(file.length()));
            
            if (file.isFile()) // 파일이 있을경우
            {
                FileInputStream fileInputStream = new FileInputStream(file);
                ServletOutputStream servletOutputStream = response.getOutputStream(); 
                
                //  파일을 읽어서 클라이언트쪽으로 저장한다.
                int readNum = 0;
                while ( (readNum = fileInputStream.read(b)) != -1) {
                    servletOutputStream.write(b, 0, readNum); 
                }
                
                servletOutputStream.close();
                fileInputStream.close();
                
                int pf_count =+ 1;
                
              PfDao pfdao = PfDao.getInstance();
              PfBean fileData = new PfBean();  
              
              System.out.println(pf_count);
              
                pfdao.updateFileCount(pf_count, Integer.parseInt(request.getParameter("pb_num")));                               
            }         
        } catch (Exception e) {
            System.out.println("Download Exception : " + e.getMessage());
        } 
      return null;
   }
}  