package vo;

import java.sql.Date;

public class Gunte {
	
	private int emp_num;
	private Date in_day;
	private Date in_time;
	private Date out_time;
	private int doc_kind;
	
	public Gunte() {
		super();
	}

	public Gunte(int emp_num, Date in_day, Date in_time, Date out_time, int doc_kind) {
		super();
		this.emp_num = emp_num;
		this.in_day = in_day;
		this.in_time = in_time;
		this.out_time = out_time;
		this.doc_kind = doc_kind;
	}

	public int getEmp_num() {
		return emp_num;
	}

	public void setEmp_num(int emp_num) {
		this.emp_num = emp_num;
	}

	public Date getIn_day() {
		return in_day;
	}

	public void setIn_day(Date in_day) {
		this.in_day = in_day;
	}

	public Date getIn_time() {
		return in_time;
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}

	public Date getOut_time() {
		return out_time;
	}

	public void setOut_time(Date out_time) {
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
				+ ", doc_kind=" + doc_kind + "]";
	}

}
