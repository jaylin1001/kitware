package com.kitware.schedule.vo;


public class Schedule {
	private String sch_no;
	private String emp_num;
	private String sch_name;
	private String sch_type;
	private String sch_startdate;	
	private String sch_starthour;
	private String sch_startmin;
	private String sch_enddate;
	private String sch_endhour;
	private String sch_endmin;
	private String sch_repeat;
	private String sch_repeatcycle;
	private String sch_contents;
	private String sch_code;
	private String sch_useyn;
	public Schedule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Schedule(String sch_no, String emp_num, String sch_name, String sch_type, String sch_startdate,
			String sch_starthour, String sch_startmin, String sch_enddate, String sch_endhour, String sch_endmin,
			String sch_repeat, String sch_repeatcycle, String sch_contents, String sch_code, String sch_useyn) {
		super();
		this.sch_no = sch_no;
		this.emp_num = emp_num;
		this.sch_name = sch_name;
		this.sch_type = sch_type;
		this.sch_startdate = sch_startdate;
		this.sch_starthour = sch_starthour;
		this.sch_startmin = sch_startmin;
		this.sch_enddate = sch_enddate;
		this.sch_endhour = sch_endhour;
		this.sch_endmin = sch_endmin;
		this.sch_repeat = sch_repeat;
		this.sch_repeatcycle = sch_repeatcycle;
		this.sch_contents = sch_contents;
		this.sch_code = sch_code;
		this.sch_useyn = sch_useyn;
	}
	public String getSch_no() {
		return sch_no;
	}
	public void setSch_no(String sch_no) {
		this.sch_no = sch_no;
	}
	public String getEmp_num() {
		return emp_num;
	}
	public void setEmp_num(String emp_num) {
		this.emp_num = emp_num;
	}
	public String getSch_name() {
		return sch_name;
	}
	public void setSch_name(String sch_name) {
		this.sch_name = sch_name;
	}
	public String getSch_type() {
		return sch_type;
	}
	public void setSch_type(String sch_type) {
		this.sch_type = sch_type;
	}
	public String getSch_startdate() {
		return sch_startdate;
	}
	public void setSch_startdate(String sch_startdate) {
		this.sch_startdate = sch_startdate;
	}
	public String getSch_starthour() {
		return sch_starthour;
	}
	public void setSch_starthour(String sch_starthour) {
		this.sch_starthour = sch_starthour;
	}
	public String getSch_startmin() {
		return sch_startmin;
	}
	public void setSch_startmin(String sch_startmin) {
		this.sch_startmin = sch_startmin;
	}
	public String getSch_enddate() {
		return sch_enddate;
	}
	public void setSch_enddate(String sch_enddate) {
		this.sch_enddate = sch_enddate;
	}
	public String getSch_endhour() {
		return sch_endhour;
	}
	public void setSch_endhour(String sch_endhour) {
		this.sch_endhour = sch_endhour;
	}
	public String getSch_endmin() {
		return sch_endmin;
	}
	public void setSch_endmin(String sch_endmin) {
		this.sch_endmin = sch_endmin;
	}
	public String getSch_repeat() {
		return sch_repeat;
	}
	public void setSch_repeat(String sch_repeat) {
		this.sch_repeat = sch_repeat;
	}
	public String getSch_repeatcycle() {
		return sch_repeatcycle;
	}
	public void setSch_repeatcycle(String sch_repeatcycle) {
		this.sch_repeatcycle = sch_repeatcycle;
	}
	public String getSch_contents() {
		return sch_contents;
	}
	public void setSch_contents(String sch_contents) {
		this.sch_contents = sch_contents;
	}
	public String getSch_code() {
		return sch_code;
	}
	public void setSch_code(String sch_code) {
		this.sch_code = sch_code;
	}
	public String getSch_useyn() {
		return sch_useyn;
	}
	public void setSch_useyn(String sch_useyn) {
		this.sch_useyn = sch_useyn;
	}
	@Override
	public String toString() {
		return "Schedule [sch_no=" + sch_no + ", emp_num=" + emp_num + ", sch_name=" + sch_name + ", sch_type="
				+ sch_type + ", sch_startdate=" + sch_startdate + ", sch_starthour=" + sch_starthour + ", sch_startmin="
				+ sch_startmin + ", sch_enddate=" + sch_enddate + ", sch_endhour=" + sch_endhour + ", sch_endmin="
				+ sch_endmin + ", sch_repeat=" + sch_repeat + ", sch_repeatcycle=" + sch_repeatcycle + ", sch_contents="
				+ sch_contents + ", sch_code=" + sch_code + ", sch_useyn=" + sch_useyn + "]";
	}


}