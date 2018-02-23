package message;

import java.sql.*;

public class RoomDTO {
	
	private int roomNo;
	private String r_name;
	private Timestamp recent;
	private int emp_cnt;
	private String empno_join;
	private String formatTime;
	private int r_cnt;
	private String participants; 
	
	
	public String getParticipants() {
		return participants;
	}
	public void setParticipants(String participants) {
		this.participants = participants;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public int getEmp_cnt() {
		return emp_cnt;
	}
	public void setEmp_cnt(int emp_cnt) {
		this.emp_cnt = emp_cnt;
	}
	public String getEmpno_join() {
		return empno_join;
	}
	public void setEmpno_join(String empno_join) {
		this.empno_join = empno_join;
	}
	public int getR_cnt() {
		return r_cnt;
	}
	public void setR_cnt(int r_cnt) {
		this.r_cnt = r_cnt;
	}
	public String getFormatTime() {
		return formatTime;
	}
	public void setFormatTime(String formatTime) {
		this.formatTime = formatTime;
	}
	public String getempno_join() {
		return empno_join;
	}
	public void setempno_join(String empno_join) {
		this.empno_join = empno_join;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getr_name() {
		return r_name;
	}
	public void setr_name(String r_name) {
		this.r_name = r_name;
	}
	public Timestamp getRecent() {
		return recent;
	}
	public void setRecent(Timestamp recent) {
		this.recent = recent;
	}
	

}
