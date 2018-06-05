package com.kitware.guntae.vo;

public class Yeoncha {
	
	private int years;
	private int emp_num;
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

	@Override
	public String toString() {
		return "Yeoncha [years=" + years + ", emp_num=" + emp_num + ", all_yeoncha=" + all_yeoncha + ", use_yeoncha="
				+ use_yeoncha + "]";
	}
	
}