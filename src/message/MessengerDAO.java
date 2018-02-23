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
import java.util.StringTokenizer;

import connection.Util;
import login.LoginDTO;

public class MessengerDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static MessengerDAO instance = new MessengerDAO();

	public static MessengerDAO getInstance() {
		return instance;
	}

	private MessengerDAO() {
	}

	private Connection getConnection() throws Exception {
		String jdbcdriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcdriver);
	}

	public synchronized  ArrayList<Emp2DTO> search(String deptno) {
		String sql = "select * from emp2 where deptnon = ?";
		ArrayList<Emp2DTO> list = new ArrayList<Emp2DTO>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Emp2DTO dto = new Emp2DTO();
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setPhone1(rs.getInt("phone1"));
				dto.setPhone2(rs.getInt("phone2"));
				dto.setEmail(rs.getString("email"));
				dto.setMgr(rs.getInt("mgr"));
				dto.setHiredate(rs.getTimestamp("hiredate"));
				dto.setStatus(rs.getString("status"));
				dto.setJumin1(rs.getString("jumin1"));
				dto.setJumin2(rs.getString("jumin2"));
				dto.setDeptno(rs.getInt("deptno"));
				dto.setZipcode(rs.getString("zipcode"));
				list.add(dto);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return list;
	}

	public synchronized RoomDTO checkRoom(String participant,String fromId) {
		RoomDTO dto = new RoomDTO();
		
		String sql = "select  distinct r_name, r.roomno from  (select * from r_detailFinal where empno_join = ?) r inner join r_detailFinal d on r.roomno = d.roomno inner join roomFinal f on r.roomno = f.roomno  and d.empno_join = ? where emp_cnt = 2";
		
		try {
			////System.out.println("checkRoom 占쏙옙占쏙옙 占쌨아울옙 participants :: " +participant +"," +fromId);
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, participant);
			pstmt.setString(2, fromId);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 占쏙옙화占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙
				dto.setRoomNo(rs.getInt("roomno"));
				dto.setR_name(rs.getString("R_NAME"));
			} else { // 占쏙옙화占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 1:1 占쏙옙화占썸에 占쏙옙占쌔쇽옙?
				////System.out.println("占쏙옙화占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 if 占쏙옙占쏙옙 占쏙옙占쏙옙絳求占�. ");
				sql = "insert into roomFinal (roomno,r_name,EMP_CNT) values(roomFINAL_SEQ.NEXTVAL,?,2)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,participant+","+fromId);
				int x = pstmt.executeUpdate();
				////System.out.println("占쏙옙占쏙옙占�::" + x);
				if(x==1) {
					pstmt = conn.prepareStatement("select roomFINAL_SEQ.CURRVAL FROM DUAL");
					rs = pstmt.executeQuery();
					if (rs.next()) {
						dto.setRoomNo(rs.getInt(1));
					}
				}else {
					return null;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}

		return dto;
	}

	public synchronized  int checkRoom(String participants, int personNo, String roomname) {
		int roomno = -1;
		try {
				conn = getConnection();
				String sql = "INSERT INTO roomFinal values (roomFinal_SEQ.NEXTVAL,?,NULL,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, roomname);
				pstmt.setInt(2, personNo);
				pstmt.executeUpdate();

				pstmt = conn.prepareStatement("select roomFinal_seq.currval from dual");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					roomno = rs.getInt(1);
				}
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return roomno;
	}
	
	
	public List<LoginDTO> changeEname(String participants,int sessionId) {
		StringTokenizer token = new StringTokenizer(participants, ",");
		int count = token.countTokens();
		StringBuffer sb = new StringBuffer();
		
		sb.append("select ename,empno,profilePath,dname  from emp2 e inner join dept2 d on e.deptno = d.deptno and empno in(");
		
		for(int i = 0; i<count; i++) {
			if(i== 0) {
				sb.append(token.nextToken());
			}else {
				sb.append(","+token.nextToken());
			}
		}
		sb.append(") and empno <> ?");
		String sql =sb.toString();
		
		List<LoginDTO> list = new ArrayList<LoginDTO>();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sessionId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				LoginDTO dto = new LoginDTO();
				dto.setEname(rs.getString("ename"));
				dto.setEmpno(rs.getInt("empno"));
				dto.setProfilePath(rs.getString("profilEpath"));
				dto.setDname(rs.getString("dname"));
				list.add(dto);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}

		return list;
	}
	
	
	   public String changeEname(String participant) {
		      String sql ="select * from emp2 where empno = ?";
		      String name = "";
		      
		      try {
		         conn = getConnection();
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, participant);
		         rs =pstmt.executeQuery();
		         if(rs.next()) {
		            name = rs.getString("ename");
		         }

		   } catch (Exception ex) {
		         ex.printStackTrace();
		   } finally {
		      Util.close(rs);
		      Util.close(pstmt);
		      Util.close(conn);
		   }
		   return name;
		   }
	   
	   
	public synchronized  ArrayList<ChatDTO> getChatListByRecent(int roomno, int row,int empno_join) {
		ArrayList<ChatDTO> chatList = null;
		//占쌔댐옙 SESSION 占쏙옙 占쏙옙화占쏙옙占쏙옙 占싻억옙占쏙옙占쏙옙 . 占쌓삼옙占� 占쏙옙占쏙옙 표占쏙옙 占쏙옙占쌍깍옙占쏙옙占쏙옙 占쌨쇽옙占쏙옙
		readChatRoom(empno_join,roomno);    
		String sql = 
				"select ROOMNO , CHATNO , NVL(CNT, 0) CNT,DEPTNO ,EMPNO_TO , nvl(CONTENT,' ') content , SENDDATE , DNAME , rownum ,ENAME ,profilePath from ( "
						+ "select *from "
						+ "(SELECT *  FROM "
						+ "(select C.ROOMNO ,C.CHATNO, CNT, EMPNO_TO,CONTENT , SENDDATE , DNAME from " 	
						+ "(select  distinct roomno, chatno, read_yn , count(*) cnt "
						+ "from r_detailFinal  group by roomno, chatno, read_yn having read_yn = 'N') s  "
						+ "right outer join  "
						+ "(select CHATNO,EMPNO_TO ,CONTENT , SENDDATE, DNAME , ROOMNO  "
						+ "from chatFinal c inner join emp2 e on c.empno_to = e.empno inner join dept2 d on e.deptno = d.deptno AND ROOMNO = ? order by senddate asc) c  "
						+ "on s.chatno = c.chatno "
						+ "ORDER BY chatno asc)  order by  chatno asc) t1 , emp2 "
						+"where t1.empno_to = emp2.empno  order by chatno desc ) where rownum <? order by chatno asc";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomno);
			pstmt.setInt(2, row);
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatDTO>();
			while (rs.next()) {
				ChatDTO chat = new ChatDTO();
				chat.setEname(rs.getString("ename"));
				chat.setDeptno(rs.getInt("deptno"));
				chat.setDname(rs.getString("dname"));
				chat.setChatNumber(rs.getInt("chatno"));
				chat.setProfilPath(rs.getString("profilePath"));
				chat.setEmpno(rs.getInt("empno_to")); // 占쏙옙占쏙옙占쏙옙占�
				chat.setC_cnt(rs.getInt("cnt"));
				chat.setRoomno(rs.getString("roomno"));
				chat.setContent(rs.getString("content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt").replaceAll("\n", "<br>"));
				String String_time = rs.getString("senddate");
				if (String_time != null) {
					int recent = Integer.parseInt(
							String_time.substring(0, 4) + String_time.substring(5, 7) + String_time.substring(8, 10));
					//System.out.println("String_time" + recent);
					Date date = new Date(System.currentTimeMillis());
					SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
					int sysdate = Integer.parseInt(simple.format(date).toString());
					//System.out.println("sysdate" + sysdate);

					if ((sysdate - recent) >= 1) { // 占싹쇽옙占쏙옙 1占싱삼옙 占쏙옙占싱놂옙占쏙옙
						//System.out.println("占싹쇽옙占쏙옙 1占싱삼옙 占쏙옙占싱놂옙占쏙옙");
						chat.setFormatTime(String_time.substring(0, 10));
						//System.out.println(String_time.substring(0, 10));
					} else { // 占싹쇽옙占쏙옙 1占쏙옙占쏙옙占싹뗰옙
						int chatTime = Integer.parseInt((String_time).substring(11, 13));
						String timeType = "오후";
						if (chatTime > 12) {
							timeType = "오전 ";
							chatTime -= 12;
						}
						chat.setFormatTime(timeType + chatTime + ":" + rs.getString("senddate").substring(14, 16));
					}
				}
				chatList.add(chat);
			}
			// //System.out.println(chatList.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return chatList; // 占쏙옙占쏙옙트 占쏙옙환  
	}

	public synchronized ArrayList<ChatDTO> getChatListByChatNumber(int roomno, String chatId,int empno_join){
		ArrayList<ChatDTO> chatList = null;
		readChatRoom(empno_join,roomno);    
		int chatNumber = Integer.parseInt(chatId);
		String sql =
				"select ROOMNO , CHATNO , NVL(CNT, 0) CNT,DEPTNO ,EMPNO_TO , nvl(CONTENT,' ') content , SENDDATE , DNAME , rownum ,ENAME from ( "
						+ "select ROOMNO , CHATNO , NVL(CNT, 0) CNT,DEPTNO ,EMPNO_TO , nvl(CONTENT,' ') content , SENDDATE , DNAME ,ENAME from "
						+ "(SELECT *  FROM "
						+ "(select C.ROOMNO ,C.CHATNO, CNT, EMPNO_TO,CONTENT , SENDDATE , DNAME from "
						+ "(select  distinct roomno, chatno, read_yn , count(*) cnt "
						+ "from r_detailFinal  group by roomno, chatno, read_yn having read_yn = 'N') s  "
						+ "right outer join  "
						+ "(select CHATNO,EMPNO_TO ,CONTENT , SENDDATE, DNAME , ROOMNO  "
						+ "from chatFinal c inner join emp2 e on c.empno_to = e.empno inner join dept2 d on e.deptno = d.deptno AND ROOMNO = ? order by senddate asc) c  "
						+ "on s.chatno = c.chatno "
						+ "ORDER BY chatno asc)  order by  chatno asc) t1 , emp2 "
						+"where t1.empno_to = emp2.empno  order by chatno desc ) where rownum <? order by chatno asc";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomno);
			pstmt.setInt(2, chatNumber);
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatDTO>();
			while (rs.next()) {
				ChatDTO chat = new ChatDTO();
				//System.out.println("ename ::" + rs.getString("ename"));
				//System.out.println("deptno ::" + rs.getInt("deptno"));
				//System.out.println("dname ::" + rs.getString("dname"));
				//System.out.println("chatnumber ::" + rs.getInt("chatno"));
				//System.out.println("empno ::" + rs.getInt("empno_to"));
				chat.setEname(rs.getString("ename"));
				chat.setDeptno(rs.getInt("deptno"));
				chat.setDname(rs.getString("dname"));
				chat.setChatNumber(rs.getInt("chatno"));
				chat.setEmpno(rs.getInt("empno_to")); // 占쏙옙占쏙옙占쏙옙占�
				chat.setC_cnt(rs.getInt("cnt"));
				chat.setContent(rs.getString("content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt").replaceAll("\n", "<br>"));
				String String_time = rs.getString("senddate");
				if (String_time != null) {
					int recent = Integer.parseInt(
							String_time.substring(0, 4) + String_time.substring(5, 7) + String_time.substring(8, 10));
					//System.out.println("String_time" + recent);
					Date date = new Date(System.currentTimeMillis());
					SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
					int sysdate = Integer.parseInt(simple.format(date).toString());
					//System.out.println("sysdate" + sysdate);

					if ((sysdate - recent) >= 1) { // 占싹쇽옙占쏙옙 1占싱삼옙 占쏙옙占싱놂옙占쏙옙
						//System.out.println("占싹쇽옙占쏙옙 1占싱삼옙 占쏙옙占싱놂옙占쏙옙");
						chat.setFormatTime(String_time.substring(0, 10));
						//System.out.println(String_time.substring(0, 10));
					} else { // 占싹쇽옙占쏙옙 1占쏙옙占쏙옙占싹뗰옙
						int chatTime = Integer.parseInt((String_time).substring(11, 13));
						String timeType = "오후 ";
						if (chatTime > 12) {
							timeType = "오전";
							chatTime -= 12;
						}
						chat.setFormatTime(timeType + chatTime + ":" + rs.getString("senddate").substring(14, 16));
					}
				}
				chatList.add(chat); // 1:占쏙옙占쏙옙占쏙옙占� , 2:占쏙옙占쏙옙 , 3:占쏙옙占� 占시곤옙 //empno , content,formatTIME
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return chatList; // 占쏙옙占쏙옙트 占쏙옙환
	}

	public synchronized int submit(ChatDTO dto) {
		String sql = "insert into chatFinal values (chatFinal_seq.nextval,?,?,?,?)";
		String participants = dto.getParticipants();
		//System.out.println("participants :: " + participants);
		StringTokenizer token = new StringTokenizer(participants, ",");
		List<Integer> list = new ArrayList<Integer>();
		int k = 0;
		System.out.println("token 占쏙옙 ::" + token.countTokens());
		while (token.hasMoreTokens()) {
			int x = Integer.parseInt(token.nextToken());	
			//System.out.println(x);
			list.add(x);
			//System.out.println(list.get(k));
			k++;
		}
		//System.out.println(list.size() + "占쏙옙占쏙옙트 占쏙옙占쏙옙占쏙옙 占쏙옙");

		try {// 채占시뱄옙호 , 채占시뱄옙 , 占쏙옙占쏙옙占쏙옙 占쏙옙占�, 占쏙옙占쏙옙 , 占쏙옙占쏙옙占쏙옙 占시곤옙
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getRoomno());
			pstmt.setInt(2, dto.getEmpno());
			pstmt.setString(3, dto.getContent());
			pstmt.setTimestamp(4, dto.getSenddate());
			int check = pstmt.executeUpdate();
			if (check == 1) {
				for (int i = 0; i < dto.getPersonNo(); i++) {
					// 占쏙옙占쏙옙占쏙옙 占쏙옙호 , 占쌔댐옙 占쏙옙 占쏙옙호 , 채占시뱄옙호 , 占쏙옙占쏙옙占쏙옙占쏙옙 , 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占� 占쏙옙호
					pstmt = conn.prepareStatement(
							"insert into r_detailFinal values (r_detail_seq.nextval,?,chatFinal_seq.currval,'N',?)");
					pstmt.setString(1, dto.getRoomno());
					pstmt.setInt(2, list.get(i));
					pstmt.executeUpdate();
					//System.out.println("for占쏙옙占쏙옙 占쏙옙占싣곤옙占싹댐옙.");
				}
			} else {
				return -1;
			}
			conn.commit();
			conn.setAutoCommit(true);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return -1; // 占쏙옙占쏙옙
	}
	
	
	
	

	public int communication(String participant, String formId) {

		return 0;
	}

	public String changeRoomname(String roomno) {
		String roomname = "";
		String sql = "select r_name from roomFinal where roomno =?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(roomno));

			rs = pstmt.executeQuery();
			if (rs.next()) {
				roomname = rs.getString("r_name");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return roomname; // 占쏙옙占쏙옙
		// roomno 占쏙옙 占쏙옙占쏙옙 占쌔억옙占쏙옙
	}
	
	public void readChatRoom(int empno_join , int roomno) {
		String sql = "update (select * from r_detailFinal where empno_join= ? and roomno =?)set read_yn = 'Y'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno_join);
			pstmt.setInt(2, roomno);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
	}

	
	
	
}
