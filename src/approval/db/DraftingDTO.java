package approval.db;

public class DraftingDTO {
	private int d_num;
	private String documentNum;
	private int type;
	private int deptno;
	private String d_subject;
	private String d_category;
	private String d_content;
	private String d_date;
	public String getD_date() {
		return d_date;
	}
	public void setD_date(String d_date) {
		this.d_date = d_date;
	}
	private int empno;
	public int getD_num() {
		return d_num;
	}
	public void setD_num(int d_num) {
		this.d_num = d_num;
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
	public String getD_subject() {
		return d_subject;
	}
	public void setD_subject(String d_subject) {
		this.d_subject = d_subject;
	}
	public String getD_category() {
		return d_category;
	}
	public void setD_category(String d_category) {
		this.d_category = d_category;
	}
	public String getD_content() {
		return d_content;
	}
	public void setD_content(String d_content) {
		this.d_content = d_content;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	

}
