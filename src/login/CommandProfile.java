package login;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import logon.LogonDBBean;
import command.CommandAction;

public class CommandProfile implements CommandAction{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Throwable{
		
		String realFolder ="";
		String saveFolder ="fileSave";
		String encType ="utf-8";
		int maxSize =5*1024*1024; //�ִ� ���ε� ���� ũ�� 5mb

		ServletContext context = request.getServletContext(); 
		
		realFolder = context.getRealPath(saveFolder);
		System.out.print("the real path is  : " + realFolder);

		String fileName = "";
		try{
			MultipartRequest multi = null;
			multi = new MultipartRequest(request,realFolder,maxSize,encType,new DefaultFileRenamePolicy());
			Enumeration params =multi.getParameterNames();
			while(params.hasMoreElements()){
				String name =(String)params.nextElement(); //���۵Ǵ� �Ķ���� �̸�
				String value =multi.getParameter(name);//���۵Ǵ� �Ķ���Ͱ�
				System.out.print(name + "  =  " +value +"<br>");
			}
			System.out.println("--------------------------<br>");
			
			//������ ���� ������ ������ ����Ѵ�.
			Enumeration files = multi.getFileNames();
			//����Ÿ���� �Ķ������ �̸��� �����´� uploadFile �� �����´�. �̤���~!~!~! Enumeration ���� ��� �����´�.
			
			//���� ������ �ִٸ� 
			while(files.hasMoreElements()){
				//input�±��� �Ӽ��� file �� �±���name  �Ӽ��� : �Ķ�����̸�
				String name =(String)files.nextElement();
			//uploadFile �̶�� ���۵� �Ķ���� �̸�
			//������ ����� ���� �̸�
			 fileName =multi.getFilesystemName(name);
			//������ ������ ���� �����̸�
			String original = multi.getContentType(name);
			//���۵� ���� �Ӽ��� file �� �±��� name  �Ӽ����� �̿��� ���� ��ü ����
			
			//�̷����̸����� ���۵� file�� file��ü�� ��Ƽ� �����ü��ִ�.
			//�� file�� ���� ������ ���Ҽ��ִ�.
			File file = multi.getFile(name);
			//���۵� ������ ���� Ÿ��
		    String type = multi.getContentType(name);
		    
			
		    System.out.println("�Ķ��Ƽ �̸�: " +name + "<br>");
		    System.out.println("���� ���� �̸�: " +original + "<br>");
		    System.out.println("����� ���� �̸�: " +fileName+ "<br>");
		    System.out.println("���� Ÿ��: " +type + "<br>");
			if(file != null){
				System.out.println("ũ��  :" + file.length());
				System.out.println("<br>");
			}
			}
		}catch(IOException ioe){
			 System.out.println(ioe);
		}catch(Exception ex){
			System.out.print(ex);
		}
		
		LogonDBBean dao = LogonDBBean.getInstance();
		LoginDTO dto = (LoginDTO) request.getSession().getAttribute("memId");
		dto.setProfileName(fileName);
		String fullPath = "/semi/fileSave/"+fileName;
		int index = fullPath.indexOf("semi");
		String filePath = fullPath.substring(index-1);
		System.err.println(filePath);
		
		dto.setProfilePath(filePath);
		
		int check = dao.updateProFile(dto);
		request.setAttribute("check",check);
		//-1,1
		return "/login/profilePro.jsp";  
}
}
