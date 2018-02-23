package approval.db;

public class RequestForVacationDTO {
	private int v_num;
	private String documentNum;
	private int type;
	private int deptno;
	private String v_type;
	private String v_cause;
	private String v_start;
	private String v_end;
	private String v_emergencyNumber;
	private String v_otherDetail;
	private String job;
	
	
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}


	private int empno;
	
	public String getV_otherDetail() {
		return v_otherDetail;
	}
	public void setV_otherDetail(String v_otherDetail) {
		this.v_otherDetail = v_otherDetail;
	}
	public int getV_num() {
		return v_num;
	}
	public void setV_num(int v_num) {
		this.v_num = v_num;
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
	public String getV_type() {
		return v_type;
	}
	public void setV_type(String v_type) {
		this.v_type = v_type;
	}
	public String getV_cause() {
		return v_cause;
	}
	public void setV_cause(String v_cause) {
		this.v_cause = v_cause;
	}
	public String getV_start() {
		return v_start;
	}
	public void setV_start(String v_start) {
		this.v_start = v_start;
	}
	public String getV_end() {
		return v_end;
	}
	public void setV_end(String v_end) {
		this.v_end = v_end;
	}
	public String getV_emergencyNumber() {
		return v_emergencyNumber;
	}
	public void setV_emergencyNumber(String v_emergencyNumber) {
		this.v_emergencyNumber = v_emergencyNumber;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	

}
