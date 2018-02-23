package approval.db;

import java.sql.Date;

public class SpendingResolutionDTO {
	private int s_num;
	private String documentNum;
	private int type;
	private int deptno;
	private String s_category;
	private String s_spendingDate;
	private int empno;
	private Date s_writeDate;
	private String a_checkDate;
	
	
	public Date getS_writeDate() {
		return s_writeDate;
	}
	public void setS_writeDate(Date s_writeDate) {
		this.s_writeDate = s_writeDate;
	}
	public String getA_checkDate() {
		return a_checkDate;
	}
	public void setA_checkDate(String a_checkDate) {
		this.a_checkDate = a_checkDate;
	}
	private String s_day;
	private String s_goods;
	private int s_amount;
	private int s_money;
	private String s_content;
	
	public String getS_day() {
		return s_day;
	}
	public void setS_day(String s_day) {
		this.s_day = s_day;
	}
	public String getS_goods() {
		return s_goods;
	}
	public void setS_goods(String s_goods) {
		this.s_goods = s_goods;
	}
	public int getS_amount() {
		return s_amount;
	}
	public void setS_amount(int s_amount) {
		this.s_amount = s_amount;
	}
	public int getS_money() {
		return s_money;
	}
	public void setS_money(int s_money) {
		this.s_money = s_money;
	}
	public String getS_content() {
		return s_content;
	}
	public void setS_content(String s_content) {
		this.s_content = s_content;
	}

	public int getS_num() {
		return s_num;
	}
	public void setS_num(int s_num) {
		this.s_num = s_num;
	}
	public String getDocumentNum() {
		return documentNum;
	}
	public void setDocumentNum(String documentNum) {
		this.documentNum = documentNum;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	
	public String getS_category() {
		return s_category;
	}
	public void setS_category(String s_category) {
		this.s_category = s_category;
	}
	public String getS_spendingDate() {
		return s_spendingDate;
	}
	public void setS_spendingDate(String s_spendingDate) {
		this.s_spendingDate = s_spendingDate;
	}

	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	

}
