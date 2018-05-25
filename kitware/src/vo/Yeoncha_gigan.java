package vo;

public class Yeoncha_gigan {
	
	private int seq;
	private int emp_num;
	private int years;
	private String start_date;
	private String end_date;
	private int use_period;
	
	public Yeoncha_gigan() {
		super();
	}

	public Yeoncha_gigan(int seq, int emp_num, int years, String start_date, String end_date, int use_period) {
		super();
		this.seq = seq;
		this.emp_num = emp_num;
		this.years = years;
		this.start_date = start_date;
		this.end_date = end_date;
		this.use_period = use_period;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getEmp_num() {
		return emp_num;
	}

	public void setEmp_num(int emp_num) {
		this.emp_num = emp_num;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public int getUse_period() {
		return use_period;
	}

	public void setUse_period(int use_period) {
		this.use_period = use_period;
	}

	@Override
	public String toString() {
		return "Yeoncha_gigan [seq=" + seq + ", emp_num=" + emp_num + ", years=" + years + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", use_period=" + use_period + "]";
	}
	
}
