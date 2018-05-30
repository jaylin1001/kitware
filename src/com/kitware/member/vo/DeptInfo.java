package com.kitware.member.vo;

public class DeptInfo {
	private String dept_num;
	private String dept_name;
	public DeptInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DeptInfo(String dept_num, String dept_name) {
		super();
		this.dept_num = dept_num;
		this.dept_name = dept_name;
	}
	public String getDept_num() {
		return dept_num;
	}
	public void setDept_num(String dept_num) {
		this.dept_num = dept_num;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	@Override
	public String toString() {
		return "DeptInfo [dept_num=" + dept_num + ", dept_name=" + dept_name + "]";
	}
	
	
}
