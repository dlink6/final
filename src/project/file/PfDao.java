package project.file;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Util; 

public class PfDao {
	
	private static PfDao instance = new PfDao();
	public static PfDao getInstance() {
		return instance;
	} 
	
	private Connection getConnection() throws Exception{
		String jdbcDriver="jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	private PfDao() { 
	} 
	
	public void insertFile(PfBean file) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
		try { 
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into project_file "
					+ "(pf_num, pf_name, pf_realpath, pf_size, pf_save, pf_count, "
					+ "pb_num)"
					+ "values (project_file_num.NEXTVAL, ?, ?, ?, ?, 0, project_board_num.CURRVAL)");
			pstmt.setString(1, file.getPf_name()); 
			pstmt.setString(2, file.getPf_realpath());
			pstmt.setInt(3, file.getPf_size());
			pstmt.setString(4, file.getPf_save()); 
						//파일테이블이랑 보드테이블 각각 번호를 합쳐주는거야? 몇번글에 몇번파일~
			pstmt.executeUpdate();
			
		} catch(Exception ex) {
            ex.printStackTrace();
			
		} finally {
			Util.close(conn);
			Util.close(rs);
			Util.close(pstmt);		 
		}
	}
	
	  public PfBean getFile(int pb_num) throws Exception{
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		  ResultSet rs = null;
		  PfBean fileData = null;
		  try {
			  conn = getConnection();
			  
			  /*pstmt = conn.prepareStatement("update board_file set file_count=file_count+1 where board_num = ?");
			  pstmt.setInt(1, board_num);
			  pstmt.executeUpdate();*/
			  
			  pstmt = conn.prepareStatement("select pf_num, pf_name, pf_save, pf_realpath, pf_size, pf_count from project_file where pb_num=?");
			  pstmt.setInt(1, pb_num);
			  rs = pstmt.executeQuery();
			  
			  if(rs.next()) {
				fileData = new PfBean();
				fileData.setPf_count(rs.getInt("pf_count"));
				fileData.setPf_num(rs.getInt("pf_num"));
				fileData.setPf_name(rs.getString("pf_name"));
				fileData.setPf_save(rs.getString("pf_save"));
				fileData.setPf_realpath(rs.getString("pf_realpath"));
				fileData.setPf_size(rs.getInt("pf_size")); 	
				System.out.println(rs.getString("pf_name"));
				
			  } 
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }finally {
				Util.close(conn);
				Util.close(rs);
				Util.close(pstmt);				
			}
	  return fileData;
	  }
	  
	  public void updateFileCount(int pf_count, int pb_num) throws Exception{
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		  ResultSet rs = null;
		  
		  try {
			  conn=getConnection();
			  System.out.println("카운트 메서드 실행");
			  pstmt=conn.prepareStatement("update project_file set pf_count=? where pb_num=?");
			  pstmt.setInt(1, pf_count);
			  pstmt.setInt(2, pb_num);
			  rs = pstmt.executeQuery();
			  
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }finally {
				Util.close(conn);
				Util.close(rs);
				Util.close(pstmt);				
			}	  
	  }
	  
	  //
	  	public int deleteFile(int pb_num) throws SQLException{
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    int x = -1;
		    try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select*from project_file where pb_num=?");
				pstmt.setInt(1, pb_num);
				rs = pstmt.executeQuery();
				 if(rs.next()) {					
					 pstmt = conn.prepareStatement("delete from project_file where pb_num=?");
					 	pstmt.setInt(1, pb_num);
	                    pstmt.executeUpdate();
	                    x=1;
				 }else {
					 x=0;
				 }
			   }catch(Exception ex) {
				   ex.printStackTrace();
			   }finally {
				  Util.close(conn);
				  Util.close(rs);
				  Util.close(pstmt);
			  	}
			return x;
		    } 
	
	  
	  // 운명이 결정될
		public void updateFile(PfBean file) throws SQLException {
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
			try {conn = getConnection();
			pstmt = conn.prepareStatement("insert into project_file "
					+ "(pf_num, pf_name, pf_save, pf_realpath, pf_size, pf_count, "
					+ "pb_num) "
					+ "values (project_file_num.NEXTVAL, ?, ?, ?, ?, 0, ?)");
			pstmt.setString(1, file.getPf_name());
			pstmt.setString(2, file.getPf_save());
			pstmt.setString(3, file.getPf_realpath());
			pstmt.setInt(4, file.getPf_size());
			pstmt.setInt(5, file.getPb_num());  
			
			pstmt.executeUpdate();
		}catch(Exception ex) {
            ex.printStackTrace();
			
		} finally {
			Util.close(conn);
			Util.close(rs);
			Util.close(pstmt);		
		}
	}	  
} 