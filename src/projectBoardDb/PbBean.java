package projectBoardDb;

import java.sql.Timestamp;

public class PbBean {
	
    private int pb_num;
    private int empno; 
    private String pb_subject;
    private String pb_content;
    private int pb_readcount;
    private Timestamp pb_date;
    private String ename;
    
	public int getPb_num() {
		return pb_num;
	}
	public void setPb_num(int pb_num) {
		this.pb_num = pb_num;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getPb_subject() {
		return pb_subject;
	}
	public void setPb_subject(String pb_subject) {
		this.pb_subject = pb_subject;
	}
	public String getPb_content() {
		return pb_content;
	}
	public void setPb_content(String pb_content) {
		this.pb_content = pb_content;
	}
	public int getPb_readcount() {
		return pb_readcount;
	}
	public void setPb_readcount(int pb_readcount) {
		this.pb_readcount = pb_readcount;
	}
	public Timestamp getPb_date() {
		return pb_date;
	}
	public void setPb_date(Timestamp pb_date) {
		this.pb_date = pb_date;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
} 
    
    
	