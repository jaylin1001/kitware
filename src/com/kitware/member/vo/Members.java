package com.kitware.member.vo;

import java.util.List;

public class Members {
	private String emp_num;
	private String dept_num;
	private String position_num;
	private String id;
	private String pwd;
	private String name;
	private String gender;
	private String email1;
	private String email2;
	private String tel1;
	private String tel2;
	private String tel3;
	private String picture;
	// 3개 테이블  has a 관계
	private MembersDetailInfo detailinfo;
	private DeptInfo deptinfo;
	private GradeInfo gradeinfo;
	
	
	public Members() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Members(String name) {
		super();
		this.name = name;
	}

	public Members(String emp_num, String dept_num, String position_num, String id, String pwd, String name,
			String gender, String email1, String email2, String tel1, String tel2, String tel3, String picture,
			MembersDetailInfo detailinfo, DeptInfo deptinfo, GradeInfo gradeinfo) {
		super();
		this.emp_num = emp_num;
		this.dept_num = dept_num;
		this.position_num = position_num;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.gender = gender;
		this.email1 = email1;
		this.email2 = email2;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.tel3 = tel3;
		this.picture = picture;
		this.detailinfo = detailinfo;
		this.deptinfo = deptinfo;
		this.gradeinfo = gradeinfo;
	}


	public String getEmp_num() {
		return emp_num;
	}


	public void setEmp_num(String emp_num) {
		this.emp_num = emp_num;
	}


	public String getDept_num() {
		return dept_num;
	}


	public void setDept_num(String dept_num) {
		this.dept_num = dept_num;
	}


	public String getPosition_num() {
		return position_num;
	}


	public void setPosition_num(String position_num) {
		this.position_num = position_num;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
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


	public String getEmail2() {
		return email2;
	}


	public void setEmail2(String email2) {
		this.email2 = email2;
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


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public MembersDetailInfo getDetailinfo() {
		return detailinfo;
	}


	public void setDetailinfo(MembersDetailInfo detailinfo) {
		this.detailinfo = detailinfo;
	}


	public DeptInfo getDeptinfo() {
		return deptinfo;
	}


	public void setDeptinfo(DeptInfo deptinfo) {
		this.deptinfo = deptinfo;
	}


	public GradeInfo getGradeinfo() {
		return gradeinfo;
	}


	public void setGradeinfo(GradeInfo gradeinfo) {
		this.gradeinfo = gradeinfo;
	}


	@Override
	public String toString() {
		return "Members [emp_num=" + emp_num + ", dept_num=" + dept_num + ", position_num=" + position_num + ", id="
				+ id + ", pwd=" + pwd + ", name=" + name + ", gender=" + gender + ", email1=" + email1 + ", email2="
				+ email2 + ", tel1=" + tel1 + ", tel2=" + tel2 + ", tel3=" + tel3 + ", picture=" + picture
				+ ", detailinfo=" + detailinfo + ", deptinfo=" + deptinfo + ", gradeinfo=" + gradeinfo + "]";
	}

	
	
	
	
}
