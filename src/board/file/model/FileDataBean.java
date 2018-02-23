package board.file.model;

public class FileDataBean {
	
	//테이블 이름 : BOARD_FILE
	private int file_num;	//파일 번호(pk)
	private String file_name;	//업로드 파일 이름
	private String file_save;	//저장된 파일 이름 
	private String file_realpath;	//파일 경로
	private int file_size;	//파일 용량
	private int file_count;	//다운로드 횟수
	private int board_num;	//게시글 번호
	
	public String getFile_save() {
		return file_save;
	}
	public void setFile_save(String file_save) {
		this.file_save = file_save;
	}
	public int getFile_num() {
		return file_num;
	}
	public void setFile_num(int file_num) {
		this.file_num = file_num;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_realpath() {
		return file_realpath;
	}
	public void setFile_realpath(String file_realpath) {
		this.file_realpath = file_realpath;
	}
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	public int getFile_count() {
		return file_count;
	}
	public void setFile_count(int file_count) {
		this.file_count = file_count;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	
}
