package board.file.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Util;

public class FileDAO {
	
	private static FileDAO instance = new FileDAO();
	public static FileDAO getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws Exception{
		String jdbcDriver="jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	private FileDAO() {
	}
	
	public void insertFile(FileDataBean file) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into board_file "
					+ "(file_num, file_name, file_save, file_realpath, file_size, file_count, "
					+ "board_num) "
					+ "values (file_num_seq.NEXTVAL, ?, ?, ?,?, 0, board_num_seq.CURRVAL)");
			pstmt.setString(1, file.getFile_name());
			pstmt.setString(2, file.getFile_save());
			pstmt.setString(3, file.getFile_realpath());
			pstmt.setInt(4, file.getFile_size());
			
			pstmt.executeUpdate();
		}catch(Exception ex) {
            ex.printStackTrace();
			
		} finally {
			Util.close(conn);
			Util.close(rs);
			Util.close(pstmt);
			
		}
	}
	
	  public FileDataBean getFile(int board_num) throws Exception{
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		  ResultSet rs = null;
		  FileDataBean fileData = null;
		  try {
			  conn = getConnection();
			  
			  /*pstmt = conn.prepareStatement("update board_file set file_count=file_count+1 where board_num = ?");
			  pstmt.setInt(1, board_num);
			  pstmt.executeUpdate();*/
			  
			  pstmt = conn.prepareStatement("select file_num, file_name, file_save, file_realpath, file_size, file_count from board_file where board_num=?");
			  pstmt.setInt(1, board_num);
			  rs = pstmt.executeQuery();
			  
			  if(rs.next()) {
				fileData = new FileDataBean();
				fileData.setFile_count(rs.getInt("file_count"));
				fileData.setFile_num(rs.getInt("file_num"));
				fileData.setFile_name(rs.getString("file_name"));
				fileData.setFile_save(rs.getString("file_save"));
				fileData.setFile_realpath(rs.getString("file_realpath"));
				fileData.setFile_size(rs.getInt("file_size"));
				
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
	  
	  public void updateFileCount(int board_num) throws Exception{
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		  ResultSet rs = null;
		  
		  try {
			  conn=getConnection();
			  
			  pstmt=conn.prepareStatement("update board_file set file_count=file_count+1 where board_num=?");
			  pstmt.setInt(1, board_num);
			  rs = pstmt.executeQuery();
			  
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }finally {
				Util.close(conn);
				Util.close(rs);
				Util.close(pstmt);
				
			}
		  
	  }
	  
	  //파일 삭제
	  	public int deleteFile(int board_num) throws SQLException{
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    int x = -1;
		    try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select*from board_file where board_num=?");
				pstmt.setInt(1, board_num);
				rs = pstmt.executeQuery();
				 if(rs.next()) {					
					 pstmt = conn.prepareStatement("delete from board_file where board_num=?");
					 	pstmt.setInt(1, board_num);
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
	  
	  //파일 수정
		public void updateFile(FileDataBean file) throws SQLException {
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
			try {conn = getConnection();
			pstmt = conn.prepareStatement("insert into board_file "
					+ "(file_num, file_name, file_save, file_realpath, file_size, file_count, "
					+ "board_num) "
					+ "values (file_num_seq.NEXTVAL, ?, ?, ?,?, 0, ?)");
			pstmt.setString(1, file.getFile_name());
			pstmt.setString(2, file.getFile_save());
			pstmt.setString(3, file.getFile_realpath());
			pstmt.setInt(4, file.getFile_size());
			pstmt.setInt(5, file.getBoard_num());
			
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
