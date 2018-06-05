package com.kitware.member.vo;

public class StatusBoard {
	
	private String number;	
	private String emp_num;
	private String dept_name;
	private String position_name;
	private String id;
	private String name;
	private String gender;
	private String email1;
	private String tel1;
	private String tel2;
	private String tel3;	
	
	public StatusBoard() {
	}
	
	

	public StatusBoard(String number, String emp_num, String dept_name, String position_name, String id, String name, String gender,
			String email1, String tel1, String tel2, String tel3) {
		super();
		this.number = number;
		this.emp_num = emp_num;
		this.dept_name = dept_name;
		this.position_name = position_name;
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.email1 = email1;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.tel3 = tel3;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getEmp_num() {
		return emp_num;
	}

	public void setEmp_num(String emp_num) {
		this.emp_num = emp_num;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getPosition_name() {
		return position_name;
	}

	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getTel3() {
		return tel3;
	}

	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}



	@Override
	public String toString() {
		return "StatusBoard [number=" + number + ", emp_num=" + emp_num + ", dept_name=" + dept_name
				+ ", position_name=" + position_name + ", id=" + id + ", name=" + name + ", gender=" + gender
				+ ", email1=" + email1 + ", tel1=" + tel1 + ", tel2=" + tel2 + ", tel3=" + tel3 + "]";
	}



	

	
	
	
	
	
	
}
