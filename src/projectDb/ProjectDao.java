package projectDb;

import java.util.List;

import connection.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
 

public class ProjectDao {

	private static ProjectDao instance = new ProjectDao();

	
	public static ProjectDao getInstance() {
		return instance;
	}

	private ProjectDao() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	//write
	public void insertArticle(ProjectDataBean article) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		String sql = "";  
		
		try {
			conn = getConnection();
			
			sql = "insert into project(proj_num, proj_subject, empno, ename, proj_reg_date, proj_day, proj_ing) values(project_sq.NEXTVAL,?,?,?,?,?,?)";  
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, article.getProj_subject());
			pstmt.setInt(2, article.getEmpno());  
			pstmt.setString(3, article.getEname()); 
			pstmt.setTimestamp(4, article.getProj_reg_date());
			pstmt.setString(5, article.getProj_day());
			pstmt.setInt(6, article.getProj_ing());      
			
			pstmt.executeUpdate();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
public void insertArticle(DivDataBean article) throws Exception {
			
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		try {
			conn = getConnection();
			
			sql = "insert into project_div(div_num, div_subject, empno, ename, div_reg_date, div_content, div_day, div_ing, proj_num) values(project_div_sq.NEXTVAL,?,?,?,?,?,?,?,?)"; 
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, article.getDiv_subject());
			pstmt.setInt(2, article.getEmpno());
			pstmt.setString(3, article.getEname());
			pstmt.setTimestamp(4, article.getDiv_reg_date());
			pstmt.setString(5, article.getDiv_content());
			pstmt.setString(6, article.getDiv_day());
			pstmt.setInt(7, article.getDiv_ing()); 
			pstmt.setInt(8, article.getProj_num());  
			
			
			pstmt.executeUpdate();
			getDiv_ing(article.getProj_num());
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}  
//����
public DivDataBean updateGetArticle(int num) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			DivDataBean article = null;
			try {
				conn = getConnection ();
				
				pstmt = conn.prepareStatement(
						"select * from project_div where div_num = ?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					article = new DivDataBean();
					article.setProj_num(rs.getInt("proj_num"));
					article.setDiv_num(rs.getInt("div_num"));
					article.setDiv_subject(rs.getString("div_subject"));
					article.setEmpno(rs.getInt("empno"));
					article.setEname(rs.getString("ename"));
					article.setDiv_reg_date(rs.getTimestamp("div_reg_date"));
					article.setDiv_content(rs.getString("div_content"));
					article.setDiv_day(rs.getString("div_day"));
					article.setDiv_ing(rs.getInt("div_ing"));
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			return article;
		} 


	public int updateArticle(DivDataBean article) throws Exception {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs= null;
	
	String sql="";
	int x=-1;
	try {
	conn = getConnection();
	         
	pstmt = conn.prepareStatement("select * from project_div where div_num=?");
	pstmt.setInt(1, article.getDiv_num());
	rs = pstmt.executeQuery();
	         
	if(rs.next()){
	    
	pstmt = conn.prepareStatement("update project_div set div_subject=?, div_content=?, div_day=?, div_ing=? where div_num=?");
	
	pstmt.setString(1, article.getDiv_subject());
	pstmt.setString(2, article.getDiv_content());
	pstmt.setString(3, article.getDiv_day());
	pstmt.setInt(4, article.getDiv_ing());
	pstmt.setInt(5, article.getDiv_num());
	pstmt.executeUpdate();  
	x= 1; 
	
	 
	}else
	x=0; 
	   
	} catch(Exception ex) {
	ex.printStackTrace();
	} finally {
	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	      }
	  return x; 
	  }  
	
	public int getArticleCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from project");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x=rs.getInt(1);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
		return x;   
		} 
	
	public List getArticles(int start, int end) throws Exception { 
			Connection conn = null; 
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List articleList = null;
			try {
				conn = getConnection();
				
				pstmt = conn.prepareStatement(
	"select proj_num,proj_subject,empno,ename,proj_reg_date,proj_day,proj_ing,r "+
	"from (select proj_num,proj_subject,empno,ename,proj_reg_date,proj_day,proj_ing, rownum r " +
	"from (select proj_num,proj_subject,empno,ename,proj_reg_date,proj_day,proj_ing " +
	"from project order by proj_num desc) order by proj_num desc) where r >= ? and r <= ? ");
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs = pstmt.executeQuery();
				 
				if(rs.next()) { 
					articleList = new ArrayList(end);  
					do {
						ProjectDataBean article = new ProjectDataBean(); 
						article.setProj_num(rs.getInt("proj_num")); 
						article.setProj_subject(rs.getString("proj_subject"));
						article.setEmpno(rs.getInt("empno"));
						article.setEname(rs.getString("ename"));
						article.setProj_reg_date(rs.getTimestamp("proj_reg_date"));
						article.setProj_day(rs.getString("proj_day"));
						article.setProj_ing(rs.getInt("proj_ing")); 
						
						
						articleList.add(article); 
					
					}while(rs.next());
	}
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		if (rs != null) try {rs.close(); } catch (SQLException ex) {}
		if (pstmt != null) try { pstmt.close(); } catch (SQLException ex) {}
		if (conn != null) try{ conn.close(); } catch (SQLException ex) {}
	}
	return articleList;
	}  
		
	public int getArticleCountDiv() throws Exception{  
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
						
			int x = 0;
			try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from project_div");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt(1);
			}
			} catch (Exception e) {
			e.printStackTrace();
			}finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			return x;
			}
	
	public List getArticlesDiv(int start, int end,int proj_num) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List articleList = null;
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement("select div_num,div_subject,empno,ename,div_reg_date,div_content,div_day,div_ing,r,proj_num  " +
				            "from (select div_num,div_subject,empno,ename,div_reg_date,div_content,div_day,div_ing,rownum r,proj_num " +
				            "from (select div_num,div_subject,empno,ename,div_reg_date,div_content,div_day,div_ing,proj_num " +
				            "from project_div where  proj_num=? order by div_num desc) order by div_num desc) where r >= ? and r <= ? ");
					pstmt.setInt(1, proj_num);
				    pstmt.setInt(2, start);
				    pstmt.setInt(3, end);
				    rs = pstmt.executeQuery();
				    if(rs.next()) {
				    	articleList = new ArrayList(end);
				    	do{
			                  DivDataBean article= new DivDataBean();
			                  article.setDiv_num(rs.getInt("div_num"));
			                  article.setDiv_subject(rs.getString("div_subject"));
			                  article.setEmpno(rs.getInt("empno"));
			                  article.setEname(rs.getString("ename"));
			                  article.setDiv_reg_date(rs.getTimestamp("div_reg_date"));
			                  article.setDiv_content(rs.getString("div_content"));
			                  article.setDiv_day(rs.getString("div_day"));
			                  article.setDiv_ing(rs.getInt("div_ing")); 
			                  article.setProj_num(rs.getInt("proj_num"));
	
			                  articleList.add(article);
					}while(rs.next());
				    }
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
			        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			        if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			        if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			    }
				return articleList;
			}
	
	public DivDataBean GetArticle(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DivDataBean article = null;
		try {
			conn = getConnection ();
			
			pstmt = conn.prepareStatement(
					"select div_num, div_subject, empno, ename, div_reg_date, div_content, div_day, div_ing, proj_num from project_div where div_num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery(); 
			
			if (rs.next()) {
				article = new DivDataBean();
				article.setDiv_num(rs.getInt("div_num"));
				article.setDiv_subject(rs.getString("div_subject"));
				article.setEmpno(rs.getInt("empno"));
				article.setEname(rs.getString("ename"));
				article.setDiv_reg_date(rs.getTimestamp("div_reg_date"));
				article.setDiv_content(rs.getString("div_content"));
				article.setDiv_day(rs.getString("div_day"));
				article.setDiv_ing(rs.getInt("div_ing"));
				article.setProj_num(rs.getInt("proj_num"));
				
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return article;
	} 
	
	
	//������Ʈ�� ������
	public void getDiv_ing(int proj_num) {
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 int proj_ing=0;
	 try {
	    conn = getConnection();
	    String sql = "select sum(div_ing),count(*) from project_div where proj_num="+proj_num;
	    pstmt = conn.prepareStatement(sql);
	    rs = pstmt.executeQuery();
	    
	    if(rs.next()) {
	    	int a = rs.getInt(1);
	    	int b = rs.getInt(2);
	       proj_ing = a/b;
	       insertProject_ing(proj_ing,proj_num);
	    }
	    
	    
	    
	 }catch(Exception e) {
	    e.printStackTrace();
	 }finally {
	    if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	    if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	    if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	 }
	}
	
	
	//�����
	public void insertProject_ing(int proj_ing,int proj_num) {
	 Connection conn =null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 try {
	    conn = getConnection();
	    String sql  ="update project set proj_ing=? where proj_num=?";
	    pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1, proj_ing);
	    pstmt.setInt(2, proj_num);
	    pstmt.executeUpdate();
	 }catch(Exception e) {
	    e.printStackTrace();
	 }finally {
	    if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	    if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	    if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	 }
	}
	
	
	public int getDivCount(int proj_num) {
		Connection conn= null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = getConnection();
			String sql ="select count(*) from project_div where proj_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, proj_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return count;
	}
	}