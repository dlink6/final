package board.comment.model;

import java.sql.Timestamp;

public class CommentDataBean {
	
	//테이블 이름 : BOARD_COMMENT
	private int comment_num;	//코멘트 번호
	private int board_num;	//게시글 번호
	private int empno;	//사원번호
	private String comment_content;	//코멘트 내용
	private Timestamp comment_date;	//코멘트 작성일
	private String ename;	//사원이름
	private String dname;
	
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Timestamp getComment_date() {
		return comment_date;
	}
	public void setComment_date(Timestamp comment_date) {
		this.comment_date = comment_date;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	

}
