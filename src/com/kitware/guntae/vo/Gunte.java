package com.kitware.guntae.vo;
public class Gunte {
	
	private String emp_num;
	private String in_day;
	private String in_time;
	private String out_time;
	private int doc_kind;
	private String count;
	
	public Gunte() {
		super();
	}

	public Gunte(String emp_num, String in_day, String in_time, String out_time, int doc_kind, String count) {
		super();
		this.emp_num = emp_num;
		this.in_day = in_day;
		this.in_time = in_time;
		this.out_time = out_time;
		this.doc_kind = doc_kind;
		this.count = count;
	}
	
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getEmp_num() {
		return emp_num;
	}

	public void setEmp_num(String emp_num) {
		this.emp_num = emp_num;
	}

	public String getIn_day() {
		return in_day;
	}

	public void setIn_day(String in_day) {
		this.in_day = in_day;
	}

	public String getIn_time() {
		return in_time;
	}

	public void setIn_time(String in_time) {
		this.in_time = in_time;
	}

	public String getOut_time() {
		return out_time;
	}

	public void setOut_time(String out_time) {
		this.out_time = out_time;
	}

	public int getDoc_kind() {
		return doc_kind;
	}

	public void setDoc_kind(int doc_kind) {
		this.doc_kind = doc_kind;
	}

	@Override
	public String toString() {
		return "Gunte [emp_num=" + emp_num + ", in_day=" + in_day + ", in_time=" + in_time + ", out_time=" + out_time
				+ ", doc_kind=" + doc_kind + ", count=" + count + "]";
	}

	
}