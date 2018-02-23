package photoAlbum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.Util;

public class PhotoDAO {
	private PhotoDAO() {}
	private static PhotoDAO instance = new PhotoDAO();
	
	private static Connection getConnection() throws Exception {
		String driver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(driver);
	}
	
	public static PhotoDAO getInstance() {
		return instance;
	}
	
	
	
	
	
	//db에 이미지 파일저장
	public void insertPhoto(String filename) {
		Connection conn =  null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "insert into photoAlbum values(?,p_num_seq.nextval) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,filename);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(pstmt);
			Util.close(conn);
		}
	}
	
	
	//db에서 이미지 파일 꺼내오기
	public List<PhotoDTO> getPhoto() {
		Connection conn= null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		PhotoDTO dto = null;
		List<PhotoDTO> list = new ArrayList<PhotoDTO>(0);
		try {
			conn =getConnection();
			String sql ="select * from photoAlbum order by num desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto  = new PhotoDTO();
				dto.setFileName(rs.getString("filename"));
				dto.setNum(rs.getInt("num"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return list;
	}


}
