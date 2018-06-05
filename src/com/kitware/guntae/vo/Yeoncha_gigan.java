package com.kitware.guntae.vo;

public class Yeoncha_gigan {
	
	private int emp_num;
	private int years;
	private String start_date;
	private String end_date;
	
	public Yeoncha_gigan() {
		super();
	}

	public Yeoncha_gigan(int emp_num, int years, String start_date, String end_date) {
		super();
		this.emp_num = emp_num;
		this.years = years;
		this.start_date = start_date;
		this.end_date = end_date;
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

	@Override
	public String toString() {
		return "Yeoncha_gigan [emp_num=" + emp_num + ", years=" + years + ", start_date=" + start_date + ", end_date="
				+ end_date + "]";
	}
	
}