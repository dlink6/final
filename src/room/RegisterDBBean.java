package room;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schedule.ScheduleDataBean;

public class RegisterDBBean {
		//클래스 객체 생성
		private static RegisterDBBean instance = new RegisterDBBean();
		
		//메서드 정의(클래스 객체를 반환)
		public static RegisterDBBean getInstance() {
			return instance;
		}
		
		RegisterDBBean() {} //기본 생성자
		
		//커넥션풀 생성
		private Connection getConnection() throws Exception{
			String jdbcDriver="jdbc:apache:commons:dbcp:pool";
			return DriverManager.getConnection(jdbcDriver);
		}
		
		//sch_regPro.jsp, 데이터 입력
		public void insertArticle(RegisterDataBean article) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql="";
			
			try {
				conn = getConnection();
				
				sql="insert into reservation(r_num,empno,member_name,room,member_count,r_start,r_end) values(s_num_seq.NEXTVAL,?,?,?,?,?,?)";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, article.getEmpno());
				pstmt.setString(2, article.getMember_name());
				pstmt.setString(3, article.getRoom());
				pstmt.setInt(4, article.getMember_count());
				pstmt.setString(5, article.getR_start());
				pstmt.setString(6, article.getR_end());
				
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
		
		
		// 데이터 삭제 전에 해당 예약 가져오기(멤버수 가져오기)
				public int getCount(String room, String r_start, String r_end) throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					int a = 0;
					
					try {
						conn = getConnection();
						pstmt = conn.prepareStatement("select member_count from reservation where room=? and r_start=? and r_end=?");
						pstmt.setString(1, room);
						pstmt.setString(2, r_start);
						pstmt.setString(3, r_end);
						
						rs = pstmt.executeQuery();
						
						if(rs.next()) {
							a = rs.getInt(1);
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
					
					return a; //참여 인원 리턴
				}
				
				// 데이터 삭제 전에 해당 예약 가져오기(예약자명 가져오기)
						public String getName(String room, String r_start, String r_end) throws Exception{
							Connection conn = null;
							PreparedStatement pstmt = null;
							ResultSet rs = null;
							String a = "";
							
							try {
								conn = getConnection();
								pstmt = conn.prepareStatement("select member_name from reservation where room=? and r_start=? and r_end=?");
								pstmt.setString(1, room);
								pstmt.setString(2, r_start);
								pstmt.setString(3, r_end);
								
								rs = pstmt.executeQuery();
								
								if(rs.next()) {
									a = rs.getString(1);
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
							
							return a; //회의실 예약자명 리턴
						}
		
		public int deleteSchedule(RegisterDataBean article) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			int x = 0;
			
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement("delete from reservation where room=? and r_start=? and r_end=?");
				pstmt.setString(1, article.getRoom());
				pstmt.setString(2, article.getR_start());
				pstmt.setString(3, article.getR_end());
				
				x = pstmt.executeUpdate(); //x가 0보다 크면 delete 실행 성공
				
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
		
		
		
		
		
		
		//json으로 뽑아올때 사용할 디비 list
		public ArrayList<RegisterDataBean> getList() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			RegisterDataBean reservation = null;
			
			String sql = "select member_name, room, r_start, r_end from reservation";
			ArrayList<RegisterDataBean> regList = new ArrayList<RegisterDataBean>();
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					reservation = new RegisterDataBean();
					reservation.setMember_name(rs.getString(1));
					reservation.setRoom(rs.getString(2));
					reservation.setR_start(rs.getString(3));
					reservation.setR_end(rs.getString(4));
					
					regList.add(reservation);
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if (pstmt != null) 
	            	try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) 
	            	try { conn.close(); } catch(SQLException ex) {}
	        }
			
			return regList;
		}
		
		
}
