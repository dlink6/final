package logon;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.Util;
import login.LoginDTO;

public class LogonDBBean {// DB占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈윥占쎈옘占쎈눇�뙼�맪占썩넀逾녜뇡�빘�굲�뜝�럥痢� 占쎈쐻占쎈윥占쎈였占쎈쐻占쎈윥筌띾맕�쐻占쎈윪獄�占� 占쎈쐻占쎈윥�뵳�뜴�쐻占쎈윥�젆占� DAO
	private static LogonDBBean instance = new LogonDBBean(); 

	// LogonDBBean m = LogonDBBean.getInstance();
	public static LogonDBBean getInstance() {
		return instance;
	}

	private LogonDBBean() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public int insertemp(LogonDataBean emp) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int check = -1;
		try {
			conn = getConnection();// 獄쏆룇占� 占쎌젟癰귣�占쏙옙 占쎈탵�뜮袁⑸퓠 占쏙옙占쎌삢占쎈맙
			// DriverManager.getConnection(jdbc:apache:commons:dbcp:pool);
			pstmt = conn.prepareStatement("insert into EMP2(empno,ename,passwd,phone1,address,job,email,hiredate,jumin1,jumin2,deptno,zipcode,prog_num)"
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setString(3, emp.getPasswd());
			pstmt.setString(4, emp.getPhone1());
			pstmt.setString(5, emp.getAddress());
			pstmt.setString(6, emp.getJob());
			pstmt.setString(7, emp.getEmail());
			pstmt.setString(8, emp.getHiredate());
			pstmt.setString(9, emp.getJumin1());
			pstmt.setString(10, emp.getJumin2());
			pstmt.setInt(11, emp.getDeptno());
			pstmt.setString(12, emp.getZipcode());
			pstmt.setInt(13, emp.getProg_num());

			return pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			Util.close(pstmt);
			Util.close(conn);
		}
		return check;
	}
	
	public List<LogonDataBean> getEmpList() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LogonDataBean> articleList = new ArrayList<LogonDataBean>();

		LogonDataBean dto = null;

		try {
			conn = getConnection();
			String sql = "select * from emp2 natural join dept2";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new LogonDataBean();
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setDname(rs.getString("dname"));
				dto.setJob(rs.getString("job"));
				dto.setPhone1(rs.getString("phone1"));
				dto.setEmail(rs.getString("email"));
				dto.setStatus(rs.getInt("status"));
				dto.setHiredate(rs.getString("hiredate"));
				articleList.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return articleList;
	}

	public int updateemp(LogonDataBean emp) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int check1 = -1;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update emp2 set phone1=?,passwd= ?,email= ?, address =? where empno =?"); // 占쎈？
			pstmt.setString(1, emp.getPhone1());
			pstmt.setString(2, emp.getPasswd());
			pstmt.setString(3, emp.getEmail());
			pstmt.setString(4, emp.getAddress());
			pstmt.setInt(5, emp.getEmpno());
			return pstmt.executeUpdate(); // 1占쎌뱽 獄쏆꼹�넎
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.close(pstmt);
			Util.close(conn);
		}
		return check1; // -1 占쎌뱽獄쏆꼹�넎 占쎈툧占쎈쭫占쎌뱽占쎈뻻
	}

	public int deleteEmp(LogonDataBean emp) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int check1 = -1;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from emp2 where empno =?");
			pstmt.setInt(1, emp.getEmpno());
			return pstmt.executeUpdate(); // 1占쎌뱽 獄쏆꼹�넎
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.close(pstmt);
			Util.close(conn);
		}
		return check1; // -1 占쎌뱽獄쏆꼹�넎 占쎈툧占쎈쭫占쎌뱽占쎈뻻
	}

	public List<LogonDataBean> search(String userName, int startRow, int endRow) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LogonDataBean> list = new ArrayList<LogonDataBean>();

		String sql = "select empno,ename,phone1,address,job,email,hiredate,deptno ,r,prog_num from (select empno,ename,phone1,address,job,email,hiredate,deptno,prog_num ,rownum r from (select * from emp2 where ename like '%"+userName+"%' order by empno asc) order by empno asc) where r >= ? and r <=?";

		try {
			conn = getConnection();
			System.out.println("startRow ::" + startRow);
			System.out.println("endRow :: " + endRow);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LogonDataBean dto = new LogonDataBean();
				dto.setEmpno(rs.getInt("empno"));
				dto.setPhone1(rs.getString("phone1"));
				dto.setAddress(rs.getString("address"));
				dto.setJob(rs.getString("job"));
				dto.setEname(rs.getString("ename"));
				String date = rs.getString("hiredate");
				if (date == null) {
					date = "";
				} else {
					date = date.substring(0, 10);
				}
				dto.setHiredate(date);
				dto.setEmail(rs.getString("email"));
				dto.setDeptno(rs.getInt("deptno"));
				dto.setProg_num(rs.getInt("prog_num"));
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return list;
	}

	public List<LogonDataBean> search(int startRow, int endRow) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LogonDataBean> list = new ArrayList<LogonDataBean>();
		String sql = "select empno,ename,phone1,address,job,email,hiredate,deptno ,r ,prog_num from (select empno,ename,phone1,address,job,email,hiredate,deptno ,rownum r,prog_num from (select * from emp2  order by empno asc) order by empno asc) where r >= ? and r <=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LogonDataBean dto = new LogonDataBean();
				dto.setEmpno(rs.getInt("empno"));
				dto.setPhone1(rs.getString("phone1"));
				dto.setAddress(rs.getString("address"));
				dto.setJob(rs.getString("job"));
				dto.setEname(rs.getString("ename"));
				String date = rs.getString("hiredate");
				if (date == null) {
					date = "";
				} else {
					date = date.substring(0, 10);
				}
				dto.setHiredate(date);
				dto.setEmail(rs.getString("email"));
				dto.setDeptno(rs.getInt("deptno"));
				dto.setProg_num(rs.getInt("prog_num"));
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}

		return list;
	}

	public int getCount(String userName) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from emp2 where ename like '%"+userName+"%'";
		int check1 = -1;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.close(pstmt);
			Util.close(conn);
		}
		return check1; 
	}

	public int getCount() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from emp2";
		int check1 = -1;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
					if(rs.next()) {
						return rs.getInt(1);
					}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.close(pstmt);
			Util.close(conn);
		}
		return check1; 
	}
	//�븘�씠�뵒李얘린
	public String getSearchId(LogonDataBean getsearchId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String userId = null;
		
		String sql = "select empno from emp2 where ename = ? and jumin1= ? and jumin2=?";
		try {  
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, getsearchId.getEname());
			pstmt.setString(2, getsearchId.getJumin1());
			pstmt.setString(3, getsearchId.getJumin2());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userId = ""+rs.getInt("empno");
				System.err.println(userId + "userId");
			}	
			
			}catch (Exception e) {
				e.printStackTrace();			
		}finally {
			Util.close(pstmt);
			Util.close(rs);
			Util.close(conn);
		}
		return userId;
	}
	
	
	public int checkId(LogonDataBean getsearchId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check = -1;
		
		String sql = "select * from emp2 where ename = ?";
		try {  
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, getsearchId.getEname());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				check  = 0;
				System.out.println("결과 ::"+rs.getString("jumin1")+'-'+rs.getString("jumin2"));
				System.out.println("가져온값 ::"+getsearchId.getJumin1()+'-'+getsearchId.getJumin2());
				if((rs.getString("jumin1")+'-'+rs.getString("jumin2")).equals((getsearchId.getJumin1()+'-'+getsearchId.getJumin2()))){
					check  = 1;
				}
			}	
			
			}catch (Exception e) {
				e.printStackTrace();			
		}finally {
			Util.close(pstmt);
			Util.close(rs);
			Util.close(conn);
		}
		return check;
	}
	
	
	
	
	
	//鍮꾨�踰덊샇李얘린
	public String getSerchPw(LogonDataBean getsearchPw) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String userPw = null;
		
		try {
		String sql = "select passwd From emp2 where empno = ? and jumin1= ? and jumin2 = ?";
		conn = getConnection();
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, getsearchPw.getEmpno());
		pstmt.setString(2, getsearchPw.getJumin1());
		pstmt.setString(3, getsearchPw.getJumin2());
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			userPw = rs.getString("passwd");
			
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		Util.close(pstmt);
		Util.close(rs);
		Util.close(conn);
		}
		return userPw;
	}
	
	public int checkUser(LogonDataBean getsearchPw) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check = -1;
		try {
			String sql = "select * from emp2 where empno = ? or phone1 = ?";
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getsearchPw.getEmpno());
			pstmt.setString(2, getsearchPw.getPhone1());
			rs = pstmt.executeQuery();
		if(rs.next()) {
			check =0;
			if((rs.getString("jumin1")+'-'+rs.getString("jumin2")).equals((getsearchPw.getJumin1()+'-'+getsearchPw.getJumin2()))){
				check  = 1;
			}
		}
			
		}catch(Exception e) {  
			e.printStackTrace();
		}finally {
			Util.close(pstmt);
			Util.close(conn);
			}
		return check;
	}
	
	
	public int updateProFile(LoginDTO dto) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		int check = -1;
		try {
			String sql = "update emp2 set PROFILENAME = ? , PROFILEPATH = ? where empno = ?";
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getProfileName());
			pstmt.setString(2, dto.getProfilePath());
			pstmt.setInt(3, dto.getEmpno());
			check = pstmt.executeUpdate();
		}catch(Exception e) {  
			e.printStackTrace();
		}finally {
			Util.close(pstmt);
			Util.close(conn);
			}
		return check;
	}
	

	public LoginDTO getDTO(int empno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LoginDTO dto = null;
		try {
			String sql = "select * From emp2 where empno = ?";
			conn = getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new LoginDTO();
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				//dto.setDname(rs.getString("dname"));
				dto.setJob(rs.getString("job"));
				dto.setPhone1(rs.getString("phone1"));
				dto.setEmail(rs.getString("email"));
				dto.setStatus(rs.getInt("status"));
				dto.setHiredate(rs.getString("hiredate"));
				dto.setDeptno(rs.getInt("deptno"));
				dto.setJumin1(rs.getString("jumin1"));
				dto.setJumin2(rs.getString("jumin2"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddress(rs.getString("address"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(pstmt);
			Util.close(rs);
			Util.close(conn);
			}
		return dto;
	}
	public int updateemp2(LoginDTO mdto) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int check = -1;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"update emp2 set deptno=?,job= ?,status= ?, phone1 =?, hiredate =?"
					+ ", email =?, zipcode =?, address =? where empno =?");
			
			pstmt.setInt(1, mdto.getDeptno());
			pstmt.setString(2, mdto.getJob());
			pstmt.setInt(3, mdto.getStatus());
			pstmt.setString(4, mdto.getPhone1());
			pstmt.setDate(5, Date.valueOf(mdto.getHiredate().substring(0,10)));
			pstmt.setString(6, mdto.getEmail());
			pstmt.setString(7, mdto.getZipcode());
			pstmt.setString(8, mdto.getAddress());
			pstmt.setInt(9, mdto.getEmpno());
			return pstmt.executeUpdate(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.close(pstmt);
			Util.close(conn);
		}
		return check;
	}

}
