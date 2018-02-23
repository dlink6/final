package board.model;

import java.sql.*;
import java.util.*;
import board.model.BoardDataBean;


public class BoardDAO {
		
	 private Connection getConnection() throws Exception {
			String jdbcDriver = "jdbc:apache:commons:dbcp:pool";         
		        return DriverManager.getConnection(jdbcDriver);
		    }
	 
	 //게시글 갯수를 count로 리턴
	 public int getArticleCount() throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			int count = 0;

			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*) from board");
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

	 //검색 카운트
	 public int getArticleCount(int n, String searchKeyword) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String[] column_name = {"(select ename from emp2 where emp2.empno = board.empno)","board_subject","board_content"};

			int count = 0;

			try {
				conn = getConnection();
				if(column_name[n] == "(select ename from emp2 where emp2.empno = board.empno)") {
					pstmt = conn.prepareStatement("select count(*) from board where (select ename from emp2 where emp2.empno = board.empno) like '%"+searchKeyword+"%'");
					
				}else {
					pstmt = conn.prepareStatement("select count(*) from board where "+column_name[n]+" like '%"+searchKeyword+"%'");

				}
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
	 
	 
	 
	 //카테고리  카운트
	 public int getArticleCount(String board_cat) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int count = 0;

			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*) from board where board_cat like '%"+board_cat+"%'");
			
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

	 //카테고리 + 검색 카운트
	 public int getArticleCount(int n, String searchKeyword, String board_cat) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String[] column_name = {"(select ename from emp2 where emp2.empno = board.empno)","board_subject","board_content"};
			int count = 0;
			try {
				conn = getConnection();
				if(column_name[n] == "(select ename from emp2 where emp2.empno = board.empno)") {
					pstmt = conn.prepareStatement("select count(*) from board where (select ename from emp2 where emp2.empno = board.empno) like '%"+searchKeyword+"%' and board_num like '%"+board_cat+"%'");
					
				}else {
					pstmt = conn.prepareStatement("select count(*) from board where "+column_name[n]+" like '%"+searchKeyword+"%' and board_cat like '%"+board_cat+"%'");
				}
											
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

	 	
	 	//모든 화면에 보여질 데이터를 10개씩 추출하여 리턴하는 메소드
		public List getArticles(int startRow, int endRow) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List articleList = null;

			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select BOARD_NUM, EMPNO, BOARD_SUBJECT, BOARD_CONTENT, BOARD_READCOUNT, BOARD_DATE, BOARD_MOD_DATE, BOARD_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_NOTICE_TOP,board_cat,f_name,ename,deptno, recnt, r  " +
				"from (select BOARD_NUM, EMPNO, BOARD_SUBJECT, BOARD_CONTENT, BOARD_READCOUNT, BOARD_DATE, BOARD_MOD_DATE, BOARD_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_NOTICE_TOP, board_cat,f_name,ename,deptno, recnt, rownum r " +
				"from (select BOARD_NUM, EMPNO, BOARD_SUBJECT, BOARD_CONTENT, BOARD_READCOUNT, BOARD_DATE, BOARD_MOD_DATE, BOARD_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_NOTICE_TOP, board_cat, (select file_name from board_file f where a.board_num = f.board_num) f_name,(select ename from emp2 e where a.empno=e.empno) ename,(select deptno from emp2 e where a.empno=e.empno) deptno, (select count(*) from board_comment b where b.board_num = a.board_num) as recnt " +
				"from BOARD a order by BOARD_REF desc, BOARD_RE_SEQ asc) order by BOARD_REF desc, BOARD_RE_SEQ asc ) where r >= ? and r <= ? ");
				
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					articleList = new ArrayList(endRow);
					do {
						BoardDataBean article = new BoardDataBean();
						article.setBoard_num(rs.getInt("board_num"));
						article.setEmpno(rs.getInt("empno"));
						article.setBoard_subject(rs.getString("board_subject"));
						article.setBoard_content(rs.getString("board_content"));
						article.setBoard_readcount(rs.getInt("board_readcount"));
						article.setBoard_date(rs.getTimestamp("board_date"));
						article.setBoard_mod_date(rs.getTimestamp("board_mod_date"));
						article.setBoard_ref(rs.getInt("board_ref"));
						article.setBoard_re_lev(rs.getInt("board_re_lev"));
						article.setBoard_re_seq(rs.getInt("board_re_seq"));
						article.setBoard_notice_top(rs.getInt("board_notice_top"));
						article.setRecnt(rs.getInt("recnt"));
						article.setEname(rs.getString("ename"));
						article.setF_name(rs.getString("f_name"));
						article.setBoard_cat(rs.getString("board_cat"));
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
						article.setDname(dname);
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
		
		//검색용 게시글 리스트 메서드
		public List getArticles(int startRow, int endRow, int n, String searchKeyword) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List articleList = null;
			String[] column_name = {"ename","board_subject","board_content"};

			try {
				conn = getConnection();
				
					pstmt = conn.prepareStatement("select BOARD_NUM, EMPNO, BOARD_SUBJECT, BOARD_CONTENT, BOARD_READCOUNT, BOARD_DATE, BOARD_MOD_DATE, BOARD_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_NOTICE_TOP, board_cat,ename, deptno, f_name, recnt, r  " +
							"from (select BOARD_NUM, EMPNO, BOARD_SUBJECT, BOARD_CONTENT, BOARD_READCOUNT, BOARD_DATE, BOARD_MOD_DATE, BOARD_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_NOTICE_TOP, board_cat,f_name, ename, deptno, recnt, rownum r " +
							"from (select BOARD_NUM, EMPNO, BOARD_SUBJECT, BOARD_CONTENT, BOARD_READCOUNT, BOARD_DATE, BOARD_MOD_DATE, BOARD_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_NOTICE_TOP, board_cat,(select file_name from board_file f where a.board_num = f.board_num) f_name,(select ename from emp2 e where a.empno=e.empno) ename,(select deptno from emp2 e where a.empno=e.empno) deptno,(select count(*) from board_comment b where b.board_num = a.board_num) as recnt " +
							"from BOARD a order by BOARD_REF desc, BOARD_RE_SEQ asc) where "+column_name[n]+" like '%"+searchKeyword+"%' order by BOARD_REF desc, BOARD_RE_SEQ asc ) where r >= ? and r <= ? ");
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					articleList = new ArrayList(endRow);
					do {
						BoardDataBean article = new BoardDataBean();
						article.setBoard_num(rs.getInt("board_num"));
						article.setEmpno(rs.getInt("empno"));
						article.setBoard_subject(rs.getString("board_subject"));
						article.setBoard_content(rs.getString("board_content"));
						article.setBoard_readcount(rs.getInt("board_readcount"));
						article.setBoard_date(rs.getTimestamp("board_date"));
						article.setBoard_mod_date(rs.getTimestamp("board_mod_date"));
						article.setBoard_ref(rs.getInt("board_ref"));
						article.setBoard_re_lev(rs.getInt("board_re_lev"));
						article.setBoard_re_seq(rs.getInt("board_re_seq"));
						article.setBoard_notice_top(rs.getInt("board_notice_top"));
						article.setRecnt(rs.getInt("recnt"));
						article.setEname(rs.getString("ename"));
						article.setF_name(rs.getString("f_name"));
						article.setBoard_cat(rs.getString("board_cat"));
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
						article.setDname(dname);
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
		
		//카테고리 게시글 리스트 메서드
		public List getArticles(int startRow, int endRow, String board_cat) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List articleList = null;

			try {
				conn = getConnection();
				
					pstmt = conn.prepareStatement("select BOARD_NUM, EMPNO, BOARD_SUBJECT, BOARD_CONTENT, BOARD_READCOUNT, BOARD_DATE, BOARD_MOD_DATE, BOARD_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_NOTICE_TOP, board_cat,ename, deptno, f_name, recnt, r  " +
							"from (select BOARD_NUM, EMPNO, BOARD_SUBJECT, BOARD_CONTENT, BOARD_READCOUNT, BOARD_DATE, BOARD_MOD_DATE, BOARD_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_NOTICE_TOP, board_cat,f_name, ename, deptno, recnt, rownum r " +
							"from (select BOARD_NUM, EMPNO, BOARD_SUBJECT, BOARD_CONTENT, BOARD_READCOUNT, BOARD_DATE, BOARD_MOD_DATE, BOARD_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_NOTICE_TOP, board_cat,(select file_name from board_file f where a.board_num = f.board_num) f_name,(select ename from emp2 e where a.empno=e.empno) ename,(select deptno from emp2 e where a.empno=e.empno) deptno,(select count(*) from board_comment b where b.board_num = a.board_num) as recnt " +
							"from BOARD a order by BOARD_REF desc, BOARD_RE_SEQ asc) where board_cat like '%"+board_cat+"%' order by BOARD_REF desc, BOARD_RE_SEQ asc ) where r >= ? and r <= ? ");

				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					articleList = new ArrayList(endRow);
					do {
						BoardDataBean article = new BoardDataBean();
						article.setBoard_num(rs.getInt("board_num"));
						article.setEmpno(rs.getInt("empno"));
						article.setBoard_subject(rs.getString("board_subject"));
						article.setBoard_content(rs.getString("board_content"));
						article.setBoard_readcount(rs.getInt("board_readcount"));
						article.setBoard_date(rs.getTimestamp("board_date"));
						article.setBoard_mod_date(rs.getTimestamp("board_mod_date"));
						article.setBoard_ref(rs.getInt("board_ref"));
						article.setBoard_re_lev(rs.getInt("board_re_lev"));
						article.setBoard_re_seq(rs.getInt("board_re_seq"));
						article.setBoard_notice_top(rs.getInt("board_notice_top"));
						article.setRecnt(rs.getInt("recnt"));
						article.setEname(rs.getString("ename"));
						article.setF_name(rs.getString("f_name"));
						article.setBoard_cat(rs.getString("board_cat"));
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
						article.setDname(dname);
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

		//카테고리 + 검색 리스트
		public List getArticles(int startRow, int endRow, int n, String searchKeyword, String board_cat ) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List articleList = null;
			String[] column_name = {"ename","board_subject","board_content"};
			try {
				conn = getConnection();
					pstmt = conn.prepareStatement("select BOARD_NUM, EMPNO, BOARD_SUBJECT, BOARD_CONTENT, BOARD_READCOUNT, BOARD_DATE, BOARD_MOD_DATE, BOARD_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_NOTICE_TOP, board_cat,ename, deptno,f_name, recnt, r  " +
							"from (select BOARD_NUM, EMPNO, BOARD_SUBJECT, BOARD_CONTENT, BOARD_READCOUNT, BOARD_DATE, BOARD_MOD_DATE, BOARD_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_NOTICE_TOP, board_cat,f_name, ename, deptno, recnt, rownum r " +
							"from (select BOARD_NUM, EMPNO, BOARD_SUBJECT, BOARD_CONTENT, BOARD_READCOUNT, BOARD_DATE, BOARD_MOD_DATE, BOARD_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_NOTICE_TOP, board_cat,(select file_name from board_file f where a.board_num = f.board_num) f_name,(select ename from emp2 e where a.empno=e.empno) ename,(select deptno from emp2 e where a.empno=e.empno) deptno,(select count(*) from board_comment b where b.board_num = a.board_num) as recnt " +
							"from BOARD a order by BOARD_REF desc, BOARD_RE_SEQ asc) where "+column_name[n]+" like '%"+searchKeyword+"%' and board_cat like '%"+board_cat+"%' order by BOARD_REF desc, BOARD_RE_SEQ asc ) where r >= ? and r <= ? ");
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					articleList = new ArrayList(endRow);
					do {
						BoardDataBean article = new BoardDataBean();
						article.setBoard_num(rs.getInt("board_num"));
						article.setEmpno(rs.getInt("empno"));
						article.setBoard_subject(rs.getString("board_subject"));
						article.setBoard_content(rs.getString("board_content"));
						article.setBoard_readcount(rs.getInt("board_readcount"));
						article.setBoard_date(rs.getTimestamp("board_date"));
						article.setBoard_mod_date(rs.getTimestamp("board_mod_date"));
						article.setBoard_ref(rs.getInt("board_ref"));
						article.setBoard_re_lev(rs.getInt("board_re_lev"));
						article.setBoard_re_seq(rs.getInt("board_re_seq"));
						article.setBoard_notice_top(rs.getInt("board_notice_top"));
						article.setRecnt(rs.getInt("recnt"));
						article.setEname(rs.getString("ename"));
						article.setF_name(rs.getString("f_name"));
						article.setBoard_cat(rs.getString("board_cat"));
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
						article.setDname(dname);
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
		
		
		
		

		  //글쓰기
		  public void insertArticle(BoardDataBean article) throws Exception{
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    
		    //답변글인지 일반글인지를 구분해서 입력시켜주는 로직!!!
			int board_num=article.getBoard_num();
			int board_ref=article.getBoard_ref();
			int board_re_seq=article.getBoard_re_seq();
			int board_re_lev=article.getBoard_re_lev();
			int number=0;
			
		    String sql="";

		    try {
		         conn = getConnection();
		         pstmt = conn.prepareStatement("select max(board_num) from board");
		         rs = pstmt.executeQuery();
			     if (rs.next()) {
			     
			     
			     number=rs.getInt(1)+1;
			     
			     
			     }else {
			     number=1;
			     }
			     
			     if (board_num!=0)
			     { 
			      sql="update board set board_re_seq=board_re_seq+1 where board_ref=? and board_re_seq>?";
			      pstmt = conn.prepareStatement(sql);
			      pstmt.setInt(1, board_ref);
			      pstmt.setInt(2, board_re_seq);
			  
			      pstmt.executeUpdate();
			      board_re_seq=board_re_seq+1;
			      board_re_lev=board_re_lev+1;
			      
			    }else{
			    	  board_ref=number;
				      board_re_seq=0;
			          board_re_lev=0;
			    }
		            // 쿼리를 작성
		            sql = "insert into board(board_num,board_subject,board_content,empno,";
		            sql+="board_ref,board_re_seq,board_re_lev,board_date,board_cat,board_notice_top) values(board_num_seq.NEXTVAL,?,?,?,?,?,?,?,?,?)";

		            pstmt = conn.prepareStatement(sql);
		            
		            pstmt.setString(1, article.getBoard_subject());
		            pstmt.setString(2, article.getBoard_content());
		            pstmt.setInt(3, article.getEmpno());
		            pstmt.setInt(4, board_ref);
		            pstmt.setInt(5, board_re_seq);
		            pstmt.setInt(6, board_re_lev);
		            pstmt.setTimestamp(7, article.getBoard_date());
		            pstmt.setString(8, article.getBoard_cat());
		            pstmt.setInt(9, article.getBoard_notice_top());

		            pstmt.executeUpdate();
		        } catch(Exception ex) {
		            ex.printStackTrace();
		        } finally {
		        	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		        }
		    }
		  
		  //DB로부터 한줄의 데이터를 가져온다.
		  public BoardDataBean getArticle(int board_num) throws Exception{
			  Connection conn = null;
			  PreparedStatement pstmt = null;
			  ResultSet rs = null;
			  BoardDataBean article = null;
			  try {
				  conn = getConnection();
				  
				  pstmt = conn.prepareStatement("update board set board_readcount=board_readcount+1 where board_num = ?");
				  pstmt.setInt(1, board_num);
				  pstmt.executeUpdate();
				  
				  pstmt = conn.prepareStatement("select BOARD_NUM, EMPNO, BOARD_SUBJECT, BOARD_CONTENT, BOARD_READCOUNT, BOARD_DATE, BOARD_MOD_DATE, BOARD_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_NOTICE_TOP, (select ename from emp2 e where a.empno=e.empno) ename, (select deptno from emp2 e where e.empno=a.empno) deptno,board_cat from board a where board_num=?");
				  pstmt.setInt(1, board_num);
				  rs = pstmt.executeQuery();
				  
				  if(rs.next()) {
					article = new BoardDataBean();
					article.setBoard_num(rs.getInt("board_num"));
					article.setEmpno(rs.getInt("empno"));
					article.setBoard_subject(rs.getString("board_subject"));
					article.setBoard_content(rs.getString("board_content"));
					article.setBoard_readcount(rs.getInt("board_readcount"));
					article.setBoard_date(rs.getTimestamp("board_date"));
					article.setBoard_mod_date(rs.getTimestamp("board_mod_date"));
					article.setBoard_ref(rs.getInt("board_ref"));
					article.setBoard_re_lev(rs.getInt("board_re_lev"));
					article.setBoard_re_seq(rs.getInt("board_re_seq"));
					article.setBoard_notice_top(rs.getInt("board_notice_top"));
					article.setEname(rs.getString("ename"));
					article.setBoard_cat(rs.getString("board_cat"));
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
					article.setDname(dname);
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
		  
		  //수정 폼에 데이터 가져오기
		  public BoardDataBean updateGetArticle(int board_num) throws Exception{
			  Connection conn = null;
			  PreparedStatement pstmt = null;
			  ResultSet rs = null;
			  BoardDataBean article = null;
			  try {
				  conn = getConnection();
				  pstmt = conn.prepareStatement("select BOARD_NUM, EMPNO, BOARD_SUBJECT, BOARD_CONTENT, BOARD_READCOUNT, BOARD_DATE, BOARD_MOD_DATE, BOARD_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_NOTICE_TOP, (select ename from emp2 e where a.empno=e.empno) ename,board_cat from board a where board_num=?");
				  pstmt.setInt(1, board_num);
				  rs=pstmt.executeQuery();
				  
				  if(rs.next()) {
					    article = new BoardDataBean();
					  	article.setBoard_num(rs.getInt("board_num"));
						article.setEmpno(rs.getInt("empno"));
						article.setBoard_subject(rs.getString("board_subject"));
						article.setBoard_content(rs.getString("board_content"));
						article.setBoard_readcount(rs.getInt("board_readcount"));
						article.setBoard_date(rs.getTimestamp("board_date"));
						article.setBoard_mod_date(rs.getTimestamp("board_mod_date"));
						article.setBoard_ref(rs.getInt("board_ref"));
						article.setBoard_re_lev(rs.getInt("board_re_lev"));
						article.setBoard_re_seq(rs.getInt("board_re_seq"));
						article.setBoard_notice_top(rs.getInt("board_notice_top"));
						article.setEname(rs.getString("ename"));
						article.setBoard_cat(rs.getString("board_cat"));
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
	 
		  
		  //글 수정 메서드
		  public int updateArticle(BoardDataBean article) throws Exception {
		        Connection conn = null;
		        PreparedStatement pstmt = null;
		        ResultSet rs= null;

			int x=-1;
		        try {
		            conn = getConnection();
		           
		            pstmt = conn.prepareStatement("select * from board where board_num=?");
		            pstmt.setInt(1, article.getBoard_num());
		            rs = pstmt.executeQuery();
		            if(rs.next()){
			   
						pstmt = conn.prepareStatement("update board set board_subject=?, board_content=?, board_mod_date=?, board_cat=?,board_notice_top=? where board_num=?");

		                pstmt.setString(1, article.getBoard_subject());
		                pstmt.setString(2, article.getBoard_content());
		                pstmt.setTimestamp(3, article.getBoard_mod_date());
		                pstmt.setString(4, article.getBoard_cat());
		                pstmt.setInt(5, article.getBoard_notice_top());
		                pstmt.setInt(6, article.getBoard_num());
		                
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
		  
		  //글삭제
		  public int deleteArticle(int board_num) throws Exception {
		        Connection conn = null;
		        PreparedStatement pstmt = null;
		        ResultSet rs= null;
		        int x=-1;
		        try {
			    conn = getConnection();

		            pstmt = conn.prepareStatement(
		            "select board_ref from board where board_num=?");
		            pstmt.setInt(1, board_num);
		            rs = pstmt.executeQuery();
		            
		            if(rs.next()){
				    pstmt = conn.prepareStatement("delete from board where board_num=?");
		                    pstmt.setInt(1, board_num);
		                    pstmt.executeUpdate();
		            
				    x= 1; //글삭제 성공
				}else
				    x= 0; //글삭제 실패(해당글이 없음)
			    } catch(Exception ex) {
		            ex.printStackTrace();
		        } finally {
		            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		        }
			return x;
		    }
		  
		 	//공지글 리스트 불러오기
			public List getNotices() throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List noticeList = null;

				try {
					conn = getConnection();
					pstmt = conn.prepareStatement("select BOARD_NUM, EMPNO, BOARD_SUBJECT, BOARD_CONTENT, BOARD_READCOUNT, BOARD_DATE, BOARD_MOD_DATE, BOARD_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_NOTICE_TOP,board_cat,f_name,ename,recnt, r  " +
					"from (select BOARD_NUM, EMPNO, BOARD_SUBJECT, BOARD_CONTENT, BOARD_READCOUNT, BOARD_DATE, BOARD_MOD_DATE, BOARD_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_NOTICE_TOP, board_cat,f_name,ename, recnt, rownum r " +
					"from (select BOARD_NUM, EMPNO, BOARD_SUBJECT, BOARD_CONTENT, BOARD_READCOUNT, BOARD_DATE, BOARD_MOD_DATE, BOARD_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_NOTICE_TOP, board_cat, (select file_name from board_file f where a.board_num = f.board_num) f_name,(select ename from emp2 e where a.empno=e.empno) ename,(select count(*) from board_comment b where b.board_num = a.board_num) as recnt " +
					"from BOARD a order by BOARD_REF desc, BOARD_RE_SEQ asc) order by BOARD_REF desc, BOARD_RE_SEQ asc ) where board_notice_top=1");

					/*pstmt.setInt(1, startRow);
					pstmt.setInt(2, endRow);*/
					rs = pstmt.executeQuery();

					if (rs.next()) {
						noticeList = new ArrayList();
						do {
							BoardDataBean article = new BoardDataBean();
							article.setBoard_num(rs.getInt("board_num"));
							article.setEmpno(rs.getInt("empno"));
							article.setBoard_subject(rs.getString("board_subject"));
							article.setBoard_content(rs.getString("board_content"));
							article.setBoard_readcount(rs.getInt("board_readcount"));
							article.setBoard_date(rs.getTimestamp("board_date"));
							article.setBoard_mod_date(rs.getTimestamp("board_mod_date"));
							article.setBoard_ref(rs.getInt("board_ref"));
							article.setBoard_re_lev(rs.getInt("board_re_lev"));
							article.setBoard_re_seq(rs.getInt("board_re_seq"));
							article.setBoard_notice_top(rs.getInt("board_notice_top"));
							article.setRecnt(rs.getInt("recnt"));
							article.setEname(rs.getString("ename"));
							article.setF_name(rs.getString("f_name"));
							article.setBoard_cat(rs.getString("board_cat"));
							noticeList.add(article);
						} while (rs.next());
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs != null)	try {rs.close();} catch (SQLException e) {}
					if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
					if (conn != null) try {conn.close();} catch (SQLException e) {}
					
				}
				return noticeList;
			}

 
		  
}
