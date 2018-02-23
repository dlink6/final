package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import approval.db.ApprovalDAO;
import board.model.BoardDataBean;
import connection.Util;
import projectDb.ProjectDataBean;


public class MainDAO {
	private static MainDAO instance = new MainDAO();
	private MainDAO() {}
	public static MainDAO getInstance() {
		return instance;
	}
	private static Connection getConnection() throws Exception {
		String driver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(driver);
	}
	
	//메인 결재함 리스트 저장 5개
	public List<MainApprovalListDTO> getMainApprovalList(int empno , int deptno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MainApprovalListDTO> list = new ArrayList<MainApprovalListDTO>(0);
		MainApprovalListDTO dto = null;
		ApprovalDAO dao = ApprovalDAO.getInstance();
		
		try {
			conn =getConnection();
			if(deptno ==10) {
				String sql = "select * from (select * from approvalList order by a_num desc) where rownum <=5 ";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					dto = new MainApprovalListDTO();
					dto.setA_num(rs.getInt("a_num"));
					dto.setDocumentNum(rs.getString("documentnum"));
					dto.setType(rs.getString("type"));
					dto.setA_check(rs.getString("a_check"));
					dto.setA_subject(rs.getString("a_subject"));
					dto.setEname(dao.enameSearch(rs.getInt("empno")));
					dto.setA_date(rs.getString("a_date").substring(0,10));
					list.add(dto);
				}
			}else {
				String sql = "select * from (select * from approvalList order by a_num desc) where rownum <=5 and empno =? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, empno);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					dto = new MainApprovalListDTO();
					dto.setDocumentNum(rs.getString("documentnum"));
					dto.setType(rs.getString("type"));
					dto.setA_num(rs.getInt("a_num"));
					dto.setA_check(rs.getString("a_check"));
					dto.setA_subject(rs.getString("a_subject"));
					dto.setEname(dao.enameSearch(rs.getInt("empno")));
					dto.setA_date(rs.getString("a_date").substring(0,10));
					list.add(dto);
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(pstmt);
			Util.close(rs);
			Util.close(conn);
		}
		return list;
	}
	
//공지 호출 
	public List getMainNoticeList() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List noticeList = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select board_num, board_subject, board_date, (select file_name from board_file f where a.board_num = f.board_num) f_name, (select count(*) from board_comment b where b.board_num = a.board_num) as recnt from board a where board_notice_top = 1 and rownum <=5 order by board_date desc ");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				noticeList = new ArrayList();
				do {
				BoardDataBean notice = new BoardDataBean();
				notice.setBoard_num(Integer.parseInt(rs.getString("board_num")));
				notice.setBoard_subject(rs.getString("board_subject"));
				notice.setBoard_date(rs.getTimestamp("board_date"));
				notice.setRecnt(rs.getInt("recnt"));
				notice.setF_name(rs.getString("f_name"));
				noticeList.add(notice);
				}while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.close(pstmt);
			Util.close(rs);
			Util.close(conn);
		}
		return noticeList;
	}
	
	   //프로젝트 현황 호출
	   public List GetMainProjArticles() throws Exception {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      List projList = new ArrayList();
	      ProjectDataBean proj = null;
	      try {
	         conn = getConnection ();
	         String sql  ="select * from (select proj_num,proj_subject,ename,proj_reg_date,proj_ing,rownum r ";
	               sql +=" from (select * from project order by proj_num desc)) where r<=5";
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         
	         if (rs.next()) {
	            do {
	            proj = new ProjectDataBean();
	            proj.setProj_num(rs.getInt("proj_num"));
	            proj.setProj_subject(rs.getString("proj_subject"));
	            proj.setEname(rs.getString("ename"));
	            proj.setProj_reg_date(rs.getTimestamp("proj_reg_date"));
	            //article.setDiv_content(rs.getString("div_content"));
	            proj.setProj_ing(rs.getInt("proj_ing"));
	            projList.add(proj);
	            }while (rs.next());
	         }
	      } catch(Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         Util.close(pstmt);
	         Util.close(rs);
	         Util.close(conn);      
	      }
	      return projList;
	   } 
	
}
