package com.kitware.board.vo;

public class NoticeBoard {
	
	private String number; 
	private String seq;     //실제 DB상 글 번호
	private String emp_num;
	private String name;
	private String title;
	private String content;
	private String hit;
	private String log_time;
	
	public NoticeBoard() {
	}

	public NoticeBoard(String number, String seq, String emp_num, String name, String title, String content, String hit,
			String log_time) {
		super();
		this.number = number;
		this.seq = seq;
		this.emp_num = emp_num;
		this.name = name;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.log_time = log_time;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
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

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getLog_time() {
		return log_time;
	}

	public void setLog_time(String log_time) {
		this.log_time = log_time;
	}

	@Override
	public String toString() {
		return "NoticeBoard [number=" + number + ", seq=" + seq + ", emp_num=" + emp_num + ", name=" + name + ", title="
				+ title + ", content=" + content + ", hit=" + hit + ", log_time=" + log_time + "]";
	}
	
	
}
