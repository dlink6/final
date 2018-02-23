package board.comment.model;

import java.sql.*;
import java.util.*;

public class CommentDAO {
	
	private static CommentDAO instance = new CommentDAO();
	
	public static CommentDAO getInstance() {
		return instance;
	}

	private CommentDAO() {}

	private Connection getConnection() throws Exception{
		String jdbcDriver="jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	//코멘트 작성
	public void insertComment(CommentDataBean cdb) throws Exception{
		
		//boolean result = false;
		
		Connection conn=null;
		PreparedStatement pstmt=null;

		try{
			conn=getConnection();
			String sql="insert into board_comment(comment_num, board_num, empno, comment_content, comment_date) values(comment_num_seq.NEXTVAL,?,?,?,sysdate)";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, cdb.getBoard_num());
			pstmt.setInt(2, cdb.getEmpno());
			pstmt.setString(3, cdb.getComment_content());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		//return result;
	}
	
	
	//코멘트 리스트 저장
	public List getComments(int board_num)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List commentList = null;
		try{
			conn=getConnection();
			String sql="select comment_num, board_num, empno, comment_content, comment_date, (select ename from emp2 e where b.empno=e.empno) ename, (select deptno from emp2 e where b.empno=e.empno) deptno from board_comment b where board_num="+board_num+" order by comment_date asc";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				commentList=new ArrayList();
				do{
					CommentDataBean cdb=new CommentDataBean();
					cdb.setComment_num(rs.getInt("comment_num"));
					cdb.setBoard_num(rs.getInt("board_num"));
					cdb.setComment_content(rs.getString("comment_content"));
					cdb.setEmpno(rs.getInt("empno"));
					cdb.setComment_date(rs.getTimestamp("comment_date"));
					cdb.setEname(rs.getString("ename"));
					String dname="";
					if(rs.getInt("deptno")==1) {
						dname = "관리";
					}else if(rs.getInt("deptno")==10) {
						dname = "인사팀";
					}else if(rs.getInt("deptno")==20) {
						dname = "영업팀";
					}else if(rs.getInt("deptno")==30) {
						dname = "개발팀";
					}else if(rs.getInt("deptno")==40) {
						dname = "홍보팀";
					}else if(rs.getInt("deptno")==50) {
						dname = "기획팀";
					}
					cdb.setDname(dname);
					commentList.add(cdb);
				}while(rs.next());
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return commentList;
	}
	
	
	//코멘트 카운트
	public int getCommentCount(int board_num)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList cm=null;
		int count=0;
		
		try{
			conn=getConnection();
			String sql="select * from board_comment where board_num="+board_num+" order by comment_date desc";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				cm=new ArrayList();
				do{
					CommentDataBean cdb=new CommentDataBean();
										
					cdb.setEmpno(rs.getInt("empno"));
					cdb.setComment_content(rs.getString("comment_content"));
					cdb.setComment_date(rs.getTimestamp("comment_date"));
					cm.add(cdb);
				}while(rs.next());
				count=cm.size();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return count;
	}
	

	//코멘트삭제
	public int deleteComment(int board_num, int comment_num, int empno)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int dbempno;
		int x=-1;
		
		try{
			conn=getConnection();
			pstmt=conn.prepareStatement("select empno from board_comment where board_num=? and comment_num=?");
			
			pstmt.setInt(1, board_num);
			pstmt.setInt(2, comment_num);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dbempno=rs.getInt("empno");
				if(dbempno == empno){
					pstmt=conn.prepareStatement("delete from board_comment where board_num=? and comment_num=?");
					pstmt.setInt(1, board_num);
					pstmt.setInt(2, comment_num);
					
					pstmt.executeUpdate();
					x=1;
				}else {
					x=0;
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x;
	}
	
	
	//관리자용 코멘트 삭제
	public int deleteComment(int board_num, int comment_num) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		int x=0;
		
		try{
			conn=getConnection();
			pstmt=conn.prepareStatement("delete from board_comment where board_num=? and comment_num=?");
			pstmt.setInt(1, board_num);
			pstmt.setInt(2, comment_num);
			pstmt.executeUpdate();
			
			x=1;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x;
	}
}
	
	


