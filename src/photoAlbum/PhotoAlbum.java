package photoAlbum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class PhotoAlbum implements CommandAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		PhotoDAO dao = PhotoDAO.getInstance();
		request.setAttribute("photoList", dao.getPhoto());
		return "/definition/photoAlbum/photoAlbum.jsp";
	}
	

}
