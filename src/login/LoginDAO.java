package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import connection.Util;


public class LoginDAO {
	  
	private static LoginDAO instance = new LoginDAO();
	private LoginDAO() {}
	public static LoginDAO getInstance() {
		return instance; 
	}
	private static Connection getConnection() throws Exception {
		String driver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(driver);
	}
	public int Login(int id,String passwd) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		//id 占쎈뮉 phone1 揶쏉옙 占쎌굢占쎈뮉 empno 揶쏅�れ뵠 占쎌긾
		int x= 0; //占쎈툡占쎌뵠占쎈탵 占쎈씨占쎌벉
		String dbpasswd =null;
		try {
			conn =getConnection();
			String sql ="select empno,phone1,passwd from emp2 where empno=? or phone1=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x=-1; //�뜮袁⑨옙甕곕뜇�깈 占쏙옙�뵳占�
				dbpasswd = rs.getString("passwd");
				if(dbpasswd.equals(passwd)) {
					x=1;  //嚥≪뮄�젃占쎌뵥 占쎄쉐�⑨옙
				}
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return x;	
	}
	public int Login(String id,String passwd) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		//id 占쎈뮉 phone1 揶쏉옙 占쎌굢占쎈뮉 empno 揶쏅�れ뵠 占쎌긾
		int x= 0; //占쎈툡占쎌뵠占쎈탵 占쎈씨占쎌벉
		String dbpasswd =null;
		try {
			conn =getConnection();
			String sql ="select empno,phone1,passwd from emp2 where phone1=?";
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, id);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x=-1; //�뜮袁⑨옙甕곕뜇�깈 占쏙옙�뵳占�
				dbpasswd = rs.getString("passwd");
				if(dbpasswd.equals(passwd)) {
					x=1;  //嚥≪뮄�젃占쎌뵥 占쎄쉐�⑨옙
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return x;	
	}
	
	public LoginDTO getLogin(int id) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		LoginDTO dto = new LoginDTO();
		
		try {
			conn =getConnection();
			String sql ="select * from emp2 inner join dept2 on emp2.deptno = dept2.deptno and empno=? or phone1=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setAddress(rs.getString("address"));
				dto.setDeptno(rs.getInt("deptno"));
				dto.setEmail(rs.getString("email"));
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setHiredate(rs.getString("hiredate"));
				dto.setJob(rs.getString("job"));
				dto.setJumin1(rs.getString("jumin1"));
				dto.setJumin2(rs.getString("jumin2"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setPhone1(rs.getString("phone1"));
				dto.setProg_num(rs.getInt("prog_num"));  
				dto.setStatus(rs.getInt("status"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setDname(rs.getString("dname"));
				dto.setProfilePath(rs.getString("profilePath"));
				dto.setProfileName(rs.getString("profileName"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return dto;
	}
	
	
	public LoginDTO getLogin(String id) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		LoginDTO dto = new LoginDTO();
		
		try {
			conn =getConnection();
			String sql ="select * from emp2 inner join dept2 on emp2.deptno = dept2.deptno and phone1=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setAddress(rs.getString("address"));
				dto.setDeptno(rs.getInt("deptno"));
				dto.setEmail(rs.getString("email"));
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setHiredate(rs.getString("hiredate")); 
				dto.setJob(rs.getString("job"));
				dto.setJumin1(rs.getString("jumin1"));
				dto.setJumin2(rs.getString("jumin2"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setPhone1(rs.getString("phone1"));
				dto.setProg_num(rs.getInt("prog_num"));
				dto.setStatus(rs.getInt("status"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setDname(rs.getString("dname"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return dto;
		
	}

}
