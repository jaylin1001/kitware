package com.kitware.board.vo;

public class DepartBoard {
	private String num;
	private int seq;
	private int p_seq;
	private int level;
	private String name;
	private int dept_num;
	private String emp_num;
	private String title;
	private String content;
	private int hit;
	private String log_time;
	private String originFileName;
	private String saveFileName;
	private String path;
	private int comment;

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	public DepartBoard() {
	}

	public DepartBoard(String num, int seq, int p_seq, int level, String name, int dept_num, String emp_num,
			String title, String content, int hit, String log_time, String originFileName, String saveFileName,
			String path) {
		super();
		this.seq = seq;
		this.p_seq = p_seq;
		this.name = name;
		this.dept_num = dept_num;
		this.emp_num = emp_num;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.log_time = log_time;
		this.originFileName = originFileName;
		this.saveFileName = saveFileName;
		this.path = path;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getP_seq() {
		return p_seq;
	}

	public void setP_seq(int p_seq) {
		this.p_seq = p_seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDept_num() {
		return dept_num;
	}

	public void setDept_num(int dept_num) {
		this.dept_num = dept_num;
	}

	public String getEmp_num() {
		return emp_num;
	}

	public void setEmp_num(String emp_num) {
		this.emp_num = emp_num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getLog_time() {
		return log_time;
	}

	public void setLog_time(String log_time) {
		this.log_time = log_time;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
