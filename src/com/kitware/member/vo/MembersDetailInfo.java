package com.kitware.member.vo;

public class MembersDetailInfo {
	
	private String emp_num;
	private String birth1;	
	private String birth2;
	private String birth3;
	private String hire_date1;
	private String hire_date2;
	private String hire_date3;
	private String out_date1;
	private String out_date2;
	private String out_date3;
	private String zip1;
	private String zip2;
	private String addr1;
	private String addr2;
	private String marriage;
	public MembersDetailInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MembersDetailInfo(String emp_num, String birth1, String birth2, String birth3, String hire_date1,
			String hire_date2, String hire_date3, String out_date1, String out_date2, String out_date3, String zip1,
			String zip2, String addr1, String addr2, String marriage) {
		super();
		this.emp_num = emp_num;
		this.birth1 = birth1;
		this.birth2 = birth2;
		this.birth3 = birth3;
		this.hire_date1 = hire_date1;
		this.hire_date2 = hire_date2;
		this.hire_date3 = hire_date3;
		this.out_date1 = out_date1;
		this.out_date2 = out_date2;
		this.out_date3 = out_date3;
		this.zip1 = zip1;
		this.zip2 = zip2;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.marriage = marriage;
	}
	public String getEmp_num() {
		return emp_num;
	}
	public void setEmp_num(String emp_num) {
		this.emp_num = emp_num;
	}
	public String getBirth1() {
		return birth1;
	}
	public void setBirth1(String birth1) {
		this.birth1 = birth1;
	}
	public String getBirth2() {
		return birth2;
	}
	public void setBirth2(String birth2) {
		this.birth2 = birth2;
	}
	public String getBirth3() {
		return birth3;
	}
	public void setBirth3(String birth3) {
		this.birth3 = birth3;
	}
	public String getHire_date1() {
		return hire_date1;
	}
	public void setHire_date1(String hire_date1) {
		this.hire_date1 = hire_date1;
	}
	public String getHire_date2() {
		return hire_date2;
	}
	public void setHire_date2(String hire_date2) {
		this.hire_date2 = hire_date2;
	}
	public String getHire_date3() {
		return hire_date3;
	}
	public void setHire_date3(String hire_date3) {
		this.hire_date3 = hire_date3;
	}
	public String getOut_date1() {
		return out_date1;
	}
	public void setOut_date1(String out_date1) {
		this.out_date1 = out_date1;
	}
	public String getOut_date2() {
		return out_date2;
	}
	public void setOut_date2(String out_date2) {
		this.out_date2 = out_date2;
	}
	public String getOut_date3() {
		return out_date3;
	}
	public void setOut_date3(String out_date3) {
		this.out_date3 = out_date3;
	}
	public String getZip1() {
		return zip1;
	}
	public void setZip1(String zip1) {
		this.zip1 = zip1;
	}
	public String getZip2() {
		return zip2;
	}
	public void setZip2(String zip2) {
		this.zip2 = zip2;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	@Override
	public String toString() {
		return "MembersDetailInfo [emp_num=" + emp_num + ", birth1=" + birth1 + ", birth2=" + birth2 + ", birth3="
				+ birth3 + ", hire_date1=" + hire_date1 + ", hire_date2=" + hire_date2 + ", hire_date3=" + hire_date3
				+ ", out_date1=" + out_date1 + ", out_date2=" + out_date2 + ", out_date3=" + out_date3 + ", zip1="
				+ zip1 + ", zip2=" + zip2 + ", addr1=" + addr1 + ", addr2=" + addr2 + ", marriage=" + marriage + "]";
	}
	
	
	

}
