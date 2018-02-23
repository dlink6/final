package schedule;

import java.sql.*;
import java.util.*;
//import schedule.ScheduleDataBean;

public class ScheduleDBBean {
	//Ŭ���� ��ü ����
	private static ScheduleDBBean instance = new ScheduleDBBean();
	
	//�޼��� ����(Ŭ���� ��ü�� ��ȯ)
	public static ScheduleDBBean getInstance() {
		return instance;
	}
	
	ScheduleDBBean() {} //�⺻ ������
	
	//Ŀ�ؼ�Ǯ ����
	private Connection getConnection() throws Exception{
		String jdbcDriver="jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	//������ �Է�
	public void insertArticle(ScheduleDataBean article) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="";
		
		try {
			conn = getConnection();
			
			sql="insert into schedule(s_num,s_title,s_start,s_end,s_content,sortation,empno,deptno) values(s_num_seq.NEXTVAL,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getS_title());
			pstmt.setString(2, article.getS_start());
			pstmt.setString(3, article.getS_end());
			pstmt.setString(4, article.getS_content());
			pstmt.setString(5, article.getSortation());
			pstmt.setInt(6, article.getEmpno());
			pstmt.setInt(7, article.getDeptno());
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
	         if (pstmt != null) 
	        	 try { pstmt.close(); } catch(SQLException ex) {}
	         if (conn != null) 
	        	 try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	// ������ ���� ���� �ش� ���� ��������
	public ScheduleDataBean getInfo(String s_title, String s_start, String s_end) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ScheduleDataBean schedule = new ScheduleDataBean();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select s_content, sortation from SCHEDULE where s_title=? and s_start=? and s_end=?");
			pstmt.setString(1, s_title);
			pstmt.setString(2, s_start);
			pstmt.setString(3, s_end);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				schedule.setS_content(rs.getString(1));
				schedule.setSortation(rs.getString(2));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			 if (rs != null) 
				 try { rs.close(); } catch(SQLException ex) {}
	         if (pstmt != null) 
	        	 try { pstmt.close(); } catch(SQLException ex) {}
	         if (conn != null) 
	        	 try { conn.close(); } catch(SQLException ex) {}
		}
		
		return schedule;
	}
	
	//������ ����
	public int deleteSchedule(ScheduleDataBean article) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			int x = 0;
			
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement("delete from SCHEDULE where s_title=? and s_start=? and s_end=? and s_content=?");
				pstmt.setString(1, article.getS_title());
				pstmt.setString(2, article.getS_start());
				pstmt.setString(3, article.getS_end());
				pstmt.setString(4, article.getS_content());
				
				x = pstmt.executeUpdate(); //x�� 0���� ũ�� delete ���� ����
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if (pstmt != null) 
	            	try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) 
	            	try { conn.close(); } catch(SQLException ex) {}
	        }
			
			return x;
		}
	
	/*// ������ ����
	public ScheduleDataBean getInfo(String s_title, String s_start, String s_end, String s_content) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ScheduleDataBean schedule = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from SCHEDULE where s_title=?");
			pstmt.setString(1, s_title);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				schedule = new ScheduleDataBean();
				schedule.setS_num(rs.getInt("s_num"));
				schedule.setS_title(rs.getString("s_title"));
				schedule.setS_start(rs.getString("s_start"));
				schedule.setS_end(rs.getString("s_end"));
				schedule.setS_content(rs.getString("s_content"));
				schedule.setSortation(rs.getString("sortation"));
				schedule.setEmpno(rs.getInt("empno"));
				schedule.setDeptno(rs.getInt("deptno"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			 if (rs != null) 
				 try { rs.close(); } catch(SQLException ex) {}
	         if (pstmt != null) 
	        	 try { pstmt.close(); } catch(SQLException ex) {}
	         if (conn != null) 
	        	 try { conn.close(); } catch(SQLException ex) {}
		}
		
		return schedule;
	}*/
	
	
	//sch_updePro.jsp
	/*public int updateSchedule(ScheduleDataBean article) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int x = 0;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("update SCHEDULE set s_title=?, s_start=?, s_end=?, s_content=?, sortation=?");
			pstmt.setString(1, article.getS_title());
			pstmt.setString(2, article.getS_start());
			pstmt.setString(3, article.getS_end());
			pstmt.setString(4, article.getS_content());
			pstmt.setString(5, article.getSortation());
			
			x = pstmt.executeUpdate(); //x�� 0���� ũ�� update ���� ����
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
		
		return x;
	}*/
	
	//������ �����Ҷ� �θ��� ����Ʈ
	public ArrayList<ScheduleDataBean> getList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ScheduleDataBean schedule = null;
		
		String sql = "select s_title, s_start, s_end, s_content, sortation, empno from SCHEDULE";
		ArrayList<ScheduleDataBean> scheList = new ArrayList<ScheduleDataBean>();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				schedule = new ScheduleDataBean();
				schedule.setS_title(rs.getString(1));
				schedule.setS_start(rs.getString(2));
				schedule.setS_end(rs.getString(3));
				schedule.setS_content(rs.getString(4));
				schedule.setSortation(rs.getString(5));
				schedule.setEmpno(rs.getInt(6));
				
				scheList.add(schedule);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
		
		return scheList;
	}
	
	//ī�װ��� ����Ʈ(json�� �θ� ��)
	public ArrayList<ScheduleDataBean> getList(int empno, int deptno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ScheduleDataBean schedule = null;
		
		String sql = "select s_title, s_start, s_end, s_content, sortation from schedule where deptno=? and sortation='team' or empno=? or sortation= 'company'";
		ArrayList<ScheduleDataBean> scheList = new ArrayList<ScheduleDataBean>();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			pstmt.setInt(2, empno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				schedule = new ScheduleDataBean();
				schedule.setS_title(rs.getString(1));
				schedule.setS_start(rs.getString(2));
				schedule.setS_end(rs.getString(3));
				schedule.setS_content(rs.getString(4));
				schedule.setSortation(rs.getString(5));
				
				scheList.add(schedule);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
		
		return scheList;
	}
	
	
	//알림 기능에서 오늘 날짜를 받아서 일정 리스트를 가져오는 메서드
			public ArrayList<ScheduleDataBean> getList(String strList, int empno) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				ScheduleDataBean schedule = null;
				
				String sql = "select s_title from SCHEDULE where s_start like ?||'%' and empno=? or s_start like ?||'%' and sortation='company'";

				ArrayList<ScheduleDataBean> scheList = new ArrayList<ScheduleDataBean>();
				
				try {
					conn = getConnection();
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, strList);
					pstmt.setInt(2, empno);
					pstmt.setString(3, strList);
					
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						schedule = new ScheduleDataBean();
						schedule.setS_title(rs.getString(1));
						
						scheList.add(schedule);
					}
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					if (pstmt != null) 
		            	try { pstmt.close(); } catch(SQLException ex) {}
		            if (conn != null) 
		            	try { conn.close(); } catch(SQLException ex) {}
		        }
		
				return scheList;
			}
}