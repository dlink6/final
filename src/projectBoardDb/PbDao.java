package projectBoardDb;

import java.sql.*;
import java.util.*;

import projectBoardDb.PbBean;
import projectDb.ProjectDataBean;


public class PbDao { 
		
	 private Connection getConnection() throws Exception {
			String jdbcDriver = "jdbc:apache:commons:dbcp:pool";         
		        return DriverManager.getConnection(jdbcDriver);
		    }
	 
	 // 게시판 받아오는 갯수
	 public int getArticleCount() throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			int count = 0;

			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*) from project_board");
				rs = pstmt.executeQuery();

				if (rs.next()) { 
					count = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null)	try {rs.close();} catch (SQLException e) {}
				if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
				if (conn != null) try {conn.close();} catch (SQLException e) {}
			}
			return count;
		}

	 // 검색 한 글 갯수
	 public int getArticleCount(int n, String searchKeyword) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String[] column_name = {"ename","pb_subject","pb_content"};

			int count = 0;  

			try {
				conn = getConnection(); 
				
					pstmt = conn.prepareStatement("select count(*) from project_board where "+column_name[n]+" like '%"+searchKeyword+"%'");

				
				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt(1);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null)	try {rs.close();} catch (SQLException e) {}
				if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
				if (conn != null) try {conn.close();} catch (SQLException e) {}
			}
			return count;
		}
	 	
//
public List getArticles(int startRow, int endRow) throws Exception{
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
List articleList = null;
String sql ="select PB_NUM, PB_SUBJECT, PB_CONTENT, PB_READCOUNT, PB_DATE, ENAME, empno , r from (select PB_NUM, PB_SUBJECT, PB_CONTENT, PB_READCOUNT, PB_DATE, ENAME,empno, rownum r from (select * from PROJECT_BOARD order by pb_num desc) order by pb_num desc) where r >= ? and r <=?";
 
try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					articleList = new ArrayList(endRow);
					do {
						PbBean article = new PbBean(); 
						article.setPb_num(rs.getInt("pb_num"));
						article.setEmpno(rs.getInt("empno")); 
						article.setPb_subject(rs.getString("pb_subject")); 
						article.setPb_content(rs.getString("pb_content"));
						article.setPb_readcount(rs.getInt("pb_readcount"));
						article.setPb_date(rs.getTimestamp("pb_date"));
						article.setEname(rs.getString("ename"));
						
						
						articleList.add(article); 
					} while (rs.next());
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null)	try {rs.close();} catch (SQLException e) {}
				if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
				if (conn != null) try {conn.close();} catch (SQLException e) {}
				
			}
			return articleList;
		}
		
		//
public List getArticles(int startRow, int endRow, int n, String searchKeyword) throws Exception{
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
List articleList = null;
String[] column_name = {"ename","pb_subject","pb_content"};
System.out.println(n);
System.out.println(searchKeyword);
try {				
	conn = getConnection();
pstmt = conn.prepareStatement("select PB_NUM, PB_SUBJECT, PB_CONTENT, PB_READCOUNT, PB_DATE, ENAME, r " +
							 "from (select PB_NUM, PB_SUBJECT, PB_CONTENT, PB_READCOUNT, PB_DATE, ENAME, rownum r " +
							 "from project_board where "+column_name[n]+" like '%"+searchKeyword+"%' order by pb_num desc) where r >= ? and r <= ? ");  
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();  

				if (rs.next()) {
					articleList = new ArrayList(endRow); 
					do { 
						PbBean article = new PbBean();  
						article.setPb_num(rs.getInt("pb_num"));
						article.setPb_subject(rs.getString("pb_subject")); 
						article.setPb_content(rs.getString("pb_content"));
						article.setPb_readcount(rs.getInt("pb_readcount"));
						article.setPb_date(rs.getTimestamp("pb_date"));
						article.setEname(rs.getString("ename")); 
						
						articleList.add(article);         
					} while (rs.next()); 
				} 

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null)	try {rs.close();} catch (SQLException e) {}
				if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
				if (conn != null) try {conn.close();} catch (SQLException e) {}
				
			}
			return articleList;
		}  

		
		public void insertArticle(PbBean article) throws Exception {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null; 
			
			String sql = "";  
			
			try {
				conn = getConnection();
				
				sql = "insert into project_board(pb_num, pb_subject, ename, empno, pb_content, pb_date) values(project_board_num.NEXTVAL,?,?,?,?,?)";  
				
				pstmt = conn.prepareStatement(sql); 
				pstmt.setString(1, article.getPb_subject());
				pstmt.setString(2, article.getEname());
				pstmt.setInt(3, article.getEmpno());
				pstmt.setString(4, article.getPb_content());				
				pstmt.setTimestamp(5, article.getPb_date());      
				
				pstmt.executeUpdate(); 
				
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
		}
			
		  //DB 좋
		  public PbBean getArticle(int pb_num) throws Exception{
			  Connection conn = null;
			  PreparedStatement pstmt = null;
			  ResultSet rs = null;
			  PbBean article = null;
			  try {
				  conn = getConnection();
				  
				  pstmt = conn.prepareStatement("update project_board set pb_readcount=pb_readcount+1 where pb_num = ?");
				  pstmt.setInt(1, pb_num);
				  pstmt.executeUpdate(); 
				  
				  pstmt = conn.prepareStatement("select PB_NUM, ENAME, PB_SUBJECT, PB_CONTENT, PB_READCOUNT, PB_DATE,empno from project_board where pb_num=?");
				  pstmt.setInt(1, pb_num); 
				  rs = pstmt.executeQuery();
				  
				  if(rs.next()) {
					article = new PbBean();
					article.setPb_num(rs.getInt("pb_num"));
					article.setEname(rs.getString("ename"));
					article.setPb_subject(rs.getString("pb_subject"));
					article.setPb_content(rs.getString("pb_content"));
					article.setPb_readcount(rs.getInt("pb_readcount"));
					article.setPb_date(rs.getTimestamp("pb_date"));
					article.setEmpno(rs.getInt("empno"));
					  
				  }
			  }catch(Exception ex) {
				  ex.printStackTrace();
			  }finally {
				  if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		          if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		          if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			  }
		  return article;
		  }
		  
		  // 수정
		  public PbBean updateGetArticle(int pb_num) throws Exception{
			  Connection conn = null;
			  PreparedStatement pstmt = null;
			  ResultSet rs = null;
			  PbBean article =  new PbBean();
			  try {
				  conn = getConnection();
				  pstmt = conn.prepareStatement("select * from project_board where pb_num = ?");
				  pstmt.setInt(1, pb_num);
				  rs=pstmt.executeQuery();  
				   System.out.println("수정실행되는지");
				  if(rs.next()) {
						article.setPb_num(rs.getInt("pb_num"));
						article.setEmpno(rs.getInt("empno"));
						article.setPb_subject(rs.getString("pb_subject"));
						article.setPb_content(rs.getString("pb_content"));
						article.setPb_readcount(rs.getInt("pb_readcount"));
						article.setPb_date(rs.getTimestamp("pb_date"));
						article.setEname(rs.getString("ename"));
				  }
				  }catch(Exception ex) {
					  ex.printStackTrace();
				  }finally {
					  if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			          if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			          if (conn != null) try { conn.close(); } catch(SQLException ex) {}
				  }
			  return article;
			  }
	 
		  
		  //
		  public int updateArticle(PbBean article) throws Exception {
		        Connection conn = null;
		        PreparedStatement pstmt = null;
		        ResultSet rs= null;

			int x=-1;
		        try {
		            conn = getConnection();
		           
		            pstmt = conn.prepareStatement("select * from project_board where pb_num=?");
		            pstmt.setInt(1, article.getPb_num());
		            rs = pstmt.executeQuery();
		            if(rs.next()){
			   
						pstmt = conn.prepareStatement("update project_board set pb_subject=?, pb_content=?, pb_date=? where pb_num=?");

						
						pstmt.setString(1, article.getPb_subject());
		                pstmt.setString(2, article.getPb_content());
		                pstmt.setTimestamp(3, article.getPb_date());	              
		                pstmt.setInt(4, article.getPb_num()); 
		                
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
		  
		  //
		  public int deleteArticle(int pb_num) throws Exception {
		        Connection conn = null;
		        PreparedStatement pstmt = null;
		        ResultSet rs= null;
		       
		        int x=-1;
		        try {
			    conn = getConnection();
			    	System.out.println("여기!");
		            pstmt = conn.prepareStatement(
		            "select pb_num from project_board where pb_num=?");
		            pstmt.setInt(1, pb_num);
		            rs = pstmt.executeQuery();
		           
		            if(rs.next()){				
				    pstmt = conn.prepareStatement("delete from project_board where pb_num=?");
		                    pstmt.setInt(1, pb_num);
		                    pstmt.executeUpdate();		            
				    x= 1;
				}else
				    x= 0;
			    } catch(Exception ex) {
		            ex.printStackTrace();
		        } finally {
		            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		        }
			return x;
		    }		  
}   