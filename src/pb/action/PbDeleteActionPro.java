package pb.action;

	import java.io.File;

	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import project.file.PfDao;
	import project.file.PfBean;
	import projectBoardDb.PbDao;
	import command.CommandAction;
	public class PbDeleteActionPro implements CommandAction{
		
		public String execute(HttpServletRequest request, HttpServletResponse response)	throws Throwable {
			
			request.setCharacterEncoding("UTF-8");
			
			// 첨부파일 삭제
			PfDao pfdao = PfDao.getInstance();
			PfBean oldfileData = pfdao.getFile(Integer.parseInt(request.getParameter("pb_num")));
			if (oldfileData != null) {
			// 파일을 객체로 지정
			File oldfile = new File(oldfileData.getPf_realpath()+"/"+oldfileData.getPf_save());
			oldfile.delete();	// 기존 파일 삭제
			//DB삭제 불필요(board 테이블의 pb_num이 삭제되면 project_file의 pb_num도 자동으로 삭제되는 cascade 설정됨.
			}
			int pb_num = Integer.parseInt(request.getParameter("pb_num"));
			//String pageNum = request.getParameter("pageNum");
			
			PbDao pbdao = new PbDao();
			int check  = pbdao.deleteArticle(pb_num); 
			
			/*if(pageNum!=null && pageNum!="") {
			request.setAttribute("pageNum", new Integer(pageNum));
			}*/  
			request.setAttribute("check", new Integer(check)); 
			
			return "/pb/PbDeletePro.jsp";
	}
	}  