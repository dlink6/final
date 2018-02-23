package room;

public class RegisterDataBean {
	private int r_num; //예약 번호
	private int empno; //예약자 사원번호
	private String member_name; //예약자 이름(자동 출력)
	private String room; //회의실 이름
	private int member_count; //참여 인원수
	private String r_start; //예약 시작 날짜
	private String r_end; //예약 종료 날짜
	
	public int getR_num() {
		return r_num;
	}
	public void setR_num(int r_num) {
		this.r_num = r_num;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public int getMember_count() {
		return member_count;
	}
	public void setMember_count(int member_count) {
		this.member_count = member_count;
	}
	public String getR_start() {
		return r_start;
	}
	public void setR_start(String r_start) {
		this.r_start = r_start;
	}
	public String getR_end() {
		return r_end;
	}
	public void setR_end(String r_end) {
		this.r_end = r_end;
	}
	
}
