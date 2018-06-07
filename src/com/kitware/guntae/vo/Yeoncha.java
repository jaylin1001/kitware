package com.kitware.guntae.vo;

public class Yeoncha {
	
	private int years;
	private int emp_num;
	private String start_date;
	private String end_date;
	private int all_yeoncha;
	private int use_yeoncha;
	
	public Yeoncha() {
		super();
	}
	
	public Yeoncha(int years, int emp_num, int all_yeoncha, int use_yeoncha) {
		super();
		this.years = years;
		this.emp_num = emp_num;
		this.all_yeoncha = all_yeoncha;
		this.use_yeoncha = use_yeoncha;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public int getEmp_num() {
		return emp_num;
	}

	public void setEmp_num(int emp_num) {
		this.emp_num = emp_num;
	}

	public int getAll_yeoncha() {
		return all_yeoncha;
	}

	public void setAll_yeoncha(int all_yeoncha) {
		this.all_yeoncha = all_yeoncha;
	}

	public int getUse_yeoncha() {
		return use_yeoncha;
	}

	public void setUse_yeoncha(int use_yeoncha) {
		this.use_yeoncha = use_yeoncha;
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

	@Override
	public String toString() {
		return "Yeoncha [years=" + years + ", emp_num=" + emp_num + ", all_yeoncha=" + all_yeoncha + ", use_yeoncha="
				+ use_yeoncha + "]";
	}
	
}