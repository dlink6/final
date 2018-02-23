package board.model;

import java.sql.Timestamp;

public class BoardDataBean {
	//테이블 이름 : BOARD
	private int board_num; //게시글 번호(pk)
	private int empno;	//사원 번호
	private String board_subject;	//게시글 제목
	private String board_content;	//게시글 내용
	private int board_readcount;	//게시글 조회수	
	private Timestamp board_date;	//게시글 작성일
	private Timestamp board_mod_date;	//게시글 수정일
	private int board_ref; //게시글 그룹번호
	private int board_re_lev; //답변글 깊이
	private int board_re_seq; //답변글 순서
	private int board_notice_top; //상단게시 공지
	private int recnt; //코멘트 갯수
	private String ename;	//사원이름
	private String f_name;	//첨부파일이름
	private String board_cat;	//게시글 카테고리
	private String dname;
	
	public String getBoard_cat() {
		return board_cat;
	}
	public void setBoard_cat(String board_cat) {
		this.board_cat = board_cat;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getRecnt() {
		return recnt;
	}
	public void setRecnt(int recnt) {
		this.recnt = recnt;
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
	public String getBoard_subject() {
		return board_subject;
	}
	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public int getBoard_readcount() {
		return board_readcount;
	}
	public void setBoard_readcount(int board_readcount) {
		this.board_readcount = board_readcount;
	}
	public Timestamp getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Timestamp board_date) {
		this.board_date = board_date;
	}
	public Timestamp getBoard_mod_date() {
		return board_mod_date;
	}
	public void setBoard_mod_date(Timestamp board_mod_date) {
		this.board_mod_date = board_mod_date;
	}
	public int getBoard_ref() {
		return board_ref;
	}
	public void setBoard_ref(int board_ref) {
		this.board_ref = board_ref;
	}
	public int getBoard_re_lev() {
		return board_re_lev;
	}
	public void setBoard_re_lev(int board_re_lev) {
		this.board_re_lev = board_re_lev;
	}
	public int getBoard_re_seq() {
		return board_re_seq;
	}
	public void setBoard_re_seq(int board_re_seq) {
		this.board_re_seq = board_re_seq;
	}
	public int getBoard_notice_top() {
		return board_notice_top;
	}
	public void setBoard_notice_top(int board_notice_top) {
		this.board_notice_top = board_notice_top;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	
	

}
