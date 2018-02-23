package message;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connection.Util;


public class RoomDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static RoomDAO instance = new RoomDAO();
	   
    public static RoomDAO getInstance() {
return instance;
    }
   
    private RoomDAO(){}
   
    private Connection getConnection() throws Exception {
    	String jdbcDriver = "jdbc:apache:commons:dbcp:pool";        
    	return DriverManager.getConnection(jdbcDriver); 
    }
    
    
	public synchronized List<RoomDTO> search(int memId){	
		String sql = "select * from  (select count(*) cnt ,roomno,read_yn,empno_join from r_detailFinal  group by roomno, read_yn,empno_join "
				+ "having read_yn = 'N'"
					+"and empno_join = ?) s right outer join (SELECT distinct r.roomno , r_name,emp_cnt ,recent FROM ROOMfINAL r inner join r_detailFinal d "
					+ "on r.roomno = d.roomno "
					+ "where empno_join =?) r on s.roomno = r.roomno order by read_yn desc ,recent asc";											 
	
		List<RoomDTO> room = new ArrayList<RoomDTO>();
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memId);
			pstmt.setInt(2, memId);
			rs=pstmt.executeQuery();
			int xx = 0;
			while(rs.next()) {
				RoomDTO t = new RoomDTO();
				t.setRoomNo(rs.getInt(5));
				t.setR_name(rs.getString("r_name"));
				t.setEmp_cnt(rs.getInt("emp_cnt"));
				t.setR_cnt(rs.getInt("CNT"));
				String String_time = rs.getString("recent");
				if(String_time != null) {
					int recent = Integer.parseInt(String_time.substring(0,4)+String_time.substring(5,7)+String_time.substring(8,10));																 
					//System.out.println("String_time"+recent);
					Date date = new Date(System.currentTimeMillis());
					SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
					int sysdate = Integer.parseInt(simple.format(date).toString());
					//System.out.println("sysdate"+sysdate);

					if((sysdate - recent) >=1) { // 占싹쇽옙占쏙옙 1占싱삼옙 占쏙옙占싱놂옙占쏙옙 
						//System.out.println("占싯삼옙占싫뱄옙占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙 占쏙옙 占쌉니댐옙.");
						t.setFormatTime(String_time.substring(0,10));
						//System.out.println("占싯삼옙占쏙옙 占쏙옙占쏙옙 占시곤옙 ::"+String_time.substring(0,10));
					}else {  // 占싹쇽옙占쏙옙 1占쏙옙占쏙옙占싹뗰옙
						int chatTime = Integer.parseInt((String_time).substring(11, 13));
						String timeType = "오전";
						if (chatTime >= 12) {
							timeType = "오후";
							chatTime -= 12;
						}
						t.setFormatTime(timeType+chatTime+":"+ String_time.substring(14, 16));
					}
					
				}
				//System.out.println("t.getFormatTime :: "+t.getFormatTime());
				room.add(t);
			}//while end

			for(int i=0; i<room.size(); i++) {
				room.get(i).setParticipants(checkEmpno_join(room.get(i).getRoomNo()));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null){try{rs.close();}catch(SQLException e) {}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException e) {}}
			if(conn != null){try{conn.close();}catch(SQLException e) {}}
		}
		
		return room;
	}
	public synchronized List<RoomDTO> search(String searchWord , int memId){
		String sql = 
					"select distinct q.roomno , q.r_name, q.recent, cnt, emp_cnt from  (select  distinct roomno,  r_name ,cnt , emp_cnt ,recent from "
				+ "(select cnt, r_name, r.roomno, emp_cnt  ,recent from  ( select count(*) cnt ,roomno,read_yn,empno_join from r_detailFinal  group by roomno, read_yn,empno_join 	having read_yn = 'N' "
				+"and empno_join = ?) s right outer join (SELECT distinct r.roomno , r_name , emp_cnt ,recent , d.empno_join  FROM ROOMfINAL r right outer join r_detailFinal d "
				+ "on r.roomno = d.roomno where empno_join = ?) r on s.roomno = r.roomno  order by read_yn desc ,recent asc)) q inner join r_detailFinal b on q.roomno = b.roomno inner join emp2 ee on b.empno_join = ee.empno "
				+ "where ename like '%"+searchWord+"%' or r_name like '%"+searchWord+"%' ";											 
		
		List<RoomDTO> room = new ArrayList<RoomDTO>();
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memId);
			pstmt.setInt(2, memId);
			rs=pstmt.executeQuery();
			int xx = 0;
			while(rs.next()) {
				//System.err.println("占쏙옙占쏙옙 占싯삼옙占실억옙占쏙옙占싹댐옙.  占싯삼옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙:::"+xx++);
				RoomDTO t = new RoomDTO();
				//System.err.println("占쏙옙占싫ｏ옙占� ::" + rs.getInt(5));
				//System.err.println("占쏙옙占싱몌옙占쏙옙 ::" + rs.getString("r_name"));
				//System.err.println("占쏙옙占쏙옙占싹댐옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙 ::"+rs.getInt("emp_cnt"));
				//System.err.println("占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙: "+ rs.getInt("CNT"));
				t.setRoomNo(rs.getInt("ROOMNO"));
				t.setR_name(rs.getString("r_name"));
				t.setEmp_cnt(rs.getInt("emp_cnt"));
				t.setR_cnt(rs.getInt("CNT"));
				String String_time = rs.getString("recent");
				if(String_time != null) {
					int recent = Integer.parseInt(String_time.substring(0,4)+String_time.substring(5,7)+String_time.substring(8,10));																 
					//System.out.println("String_time"+recent);
					Date date = new Date(System.currentTimeMillis());
					SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
					int sysdate = Integer.parseInt(simple.format(date).toString());
					//System.out.println("sysdate"+sysdate);
					
					if((sysdate - recent) >=1) { // 占싹쇽옙占쏙옙 1占싱삼옙 占쏙옙占싱놂옙占쏙옙 
						//System.out.println("占싯삼옙占싫뱄옙占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙 占쏙옙 占쌉니댐옙.");
						t.setFormatTime(String_time.substring(0,10));
						//System.out.println("占싯삼옙占쏙옙 占쏙옙占쏙옙 占시곤옙 ::"+String_time.substring(0,10));
					}else {  // 占싹쇽옙占쏙옙 1占쏙옙占쏙옙占싹뗰옙
						int chatTime = Integer.parseInt((String_time).substring(11, 13));
						String timeType = "오전 ";
						if (chatTime >= 12) {
							timeType = "오후 ";
							chatTime -= 12;
						}
						t.setFormatTime(timeType+chatTime+":"+ String_time.substring(14, 16));
					}
					
				}
				//System.out.println("t.getFormatTime :: "+t.getFormatTime());
				room.add(t);
			}//while end
			
			for(int i=0; i<room.size(); i++) {
				room.get(i).setParticipants(checkEmpno_join(room.get(i).getRoomNo()));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null){try{rs.close();}catch(SQLException e) {}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException e) {}}
			if(conn != null){try{conn.close();}catch(SQLException e) {}}
		}
		
		return room;
	}
	
	//占쌔댐옙 roomno 占쏙옙 占쏙옙占쏙옙占쏙옙占�
	
	public synchronized String checkEmpno_join(int roomno) {
		String sql = "select distinct empno_join from r_detailFinal where roomno =?";
		String participants = ""; 
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomno);
			rs = pstmt.executeQuery();
			int x = 0;
			while(rs.next()) {
				if(x ==0) {
					participants +=""+rs.getInt(1);
				}else{
					participants += ","+rs.getInt(1);
				}
				x += 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return participants;
	}
		
		
}
