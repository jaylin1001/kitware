package com.kitware.board.vo;

public class Comment {
	private int c_seq;
	private int p_seq;
	private int dept_num;
	private String emp_num;
	private String name;
	private String content;
	private String log_time;

	public Comment() {
	}

	public Comment(int c_seq, int p_seq, int dept_num, String emp_num, String name, String content, String log_time) {
		super();
		this.c_seq = c_seq;
		this.p_seq = p_seq;
		this.dept_num = dept_num;
		this.emp_num = emp_num;
		this.name = name;
		this.content = content;
		this.log_time = log_time;
	}

	public int getC_seq() {
		return c_seq;
	}

	public void setC_seq(int c_seq) {
		this.c_seq = c_seq;
	}

	public int getP_seq() {
		return p_seq;
	}

	public void setP_seq(int p_seq) {
		this.p_seq = p_seq;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLog_time() {
		return log_time;
	}

	public void setLog_time(String log_time) {
		this.log_time = log_time;
	}
}

/*
 * CREATE TABLE d_board_comment( c_seq number, p_seq number, dept_num number,
 * emp_num varchar2(10), name varchar2(20), content varchar2(600), log_time
 * date,
 * 
 * CONSTRAINT comment_pk PRIMARY KEY(c_seq, p_seq), CONSTRAINT comment_p_fk
 * FOREIGN KEY(p_seq,dept_num) REFERENCES department_board(seq,dept_num), CO
 * 
 */
