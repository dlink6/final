package message;

import java.sql.*;

public class ChatDTO {
	
	private int chatNumber;
	private String content;
	private int empno_to;
	private Timestamp senddate;
	private String available;
	private String roomno;
	private String formatTime;
	private String ename;
	private int deptno;
	private String dname;
	private String participants;
	private int personNo;
	private int c_cnt;
	private String ProfilPath;
	
	
	
	public String getProfilPath() {
		return ProfilPath;
	}
	public void setProfilPath(String profilPath) {
		ProfilPath = profilPath;
	}
	public int getC_cnt() {
		return c_cnt;
	}
	public void setC_cnt(int c_cnt) {
		this.c_cnt = c_cnt;
	}
	public int getEmpno_to() {
		return empno_to;
	}
	public void setEmpno_to(int empno_to) {
		this.empno_to = empno_to;
	}
	public int getPersonNo() {
		return personNo;
	}
	public void setPersonNo(int personNo) {
		this.personNo = personNo;
	}
	public String getParticipants() {
		return participants;
	}
	public void setParticipants(String participants) {
		this.participants = participants;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getFormatTime() {
		return formatTime;
	}
	public void setFormatTime(String string) {
		this.formatTime = string;
	}
	public int getChatNumber() {
		return chatNumber;
	}
	public void setChatNumber(int chatNumber) {
		this.chatNumber = chatNumber;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getEmpno() {
		return empno_to;
	}
	public void setEmpno(int empno_to) {
		this.empno_to = empno_to;
	}
	public Timestamp getSenddate() {
		return senddate;
	}
	public void setSenddate(Timestamp senddate) {
		this.senddate = senddate;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getRoomno() {
		return roomno;
	}
	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}
	
	
}
