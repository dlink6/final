package message;

import java.sql.*;
import java.util.*;

import login.*;

public class UserTDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static UserTDAO instance = new UserTDAO();
	   
    public static UserTDAO getInstance() {
return instance;
    }
   
    private UserTDAO(){}
   
    private Connection getConnection() throws Exception {
    	String jdbcDriver = "jdbc:apache:commons:dbcp:pool";        
    	return DriverManager.getConnection(jdbcDriver); 
    }
	public int login(String userID, String userPassword) {        
		String SQL = "select userpassword from userT where userid = ?";
		try {
			conn= getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,  userID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; 
				}else
					return 0; 
			}
			return -1; 
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
	
	public int join(Emp2DTO user) {
		String SQL = "insert into userT values (?,?,?,?,?)";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(SQL);

		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //
	}
	
	public ArrayList<LoginDTO> search(String userName, int sessionId){
		String sql = "select * from EMP2 where ENAME Like '%"+userName+"%'  and empno <> ? order by ename asc";
	
		ArrayList<LoginDTO> emp = new ArrayList<LoginDTO>();
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sessionId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				LoginDTO t = new LoginDTO();
				t.setEname(rs.getString("ENAME"));
				t.setEmpno(rs.getInt("EMPNO"));
				t.setDeptno(rs.getInt("deptno"));
				t.setProfilePath(rs.getString("ProfilePath"));
				//System.err.println(rs.getString("profilePath"));
				emp.add(t);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null){try{rs.close();}catch(SQLException e) {}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException e) {}}
			if(conn != null){try{conn.close();}catch(SQLException e) {}}
		}
		
		return emp;
	}
	
	public ArrayList<LoginDTO> search(int sessionId){
		String sql = "select * from EMP2 where empno <> ? order by ename asc";
		
		ArrayList<LoginDTO> emp = new ArrayList<LoginDTO>();
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sessionId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				LoginDTO t = new LoginDTO();
				t.setEname(rs.getString("ENAME"));
				t.setEmpno(rs.getInt("EMPNO"));
				t.setDeptno(rs.getInt("deptno"));
				//System.err.println(rs.getString("profilePath"));
				t.setProfilePath(rs.getString("ProfilePath"));
				emp.add(t);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null){try{rs.close();}catch(SQLException e) {}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException e) {}}
			if(conn != null){try{conn.close();}catch(SQLException e) {}}
		}
		
		return emp;
	}
	


}


