package projectDb;

import java.sql.Timestamp;

public class ProjectDataBean {

	private int proj_num;
	private String proj_subject;
	private int empno;
	private String ename;
	private Timestamp proj_reg_date;
	private String proj_day;
	private int proj_ing; 
	
	public int getProj_num() {
		return proj_num;
	}
	public void setProj_num(int proj_num) {
		this.proj_num = proj_num;
	}
	public String getProj_subject() {
		return proj_subject;
	}
	public void setProj_subject(String proj_subject) {
		this.proj_subject = proj_subject;
	}
	public int getEmpno() { 
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Timestamp getProj_reg_date() {
		return proj_reg_date;
	}
	public void setProj_reg_date(Timestamp proj_reg_date) {
		this.proj_reg_date = proj_reg_date;
	}
	public String getProj_day() {
		return proj_day;
	}
	public void setProj_day(String proj_day) {
		this.proj_day = proj_day;
	}
	public int getProj_ing() {
		return proj_ing;
	}
	public void setProj_ing(int proj_ing) {
		this.proj_ing = proj_ing;
	}
}
 