package com.kitware.member.vo;

public class GradeInfo {
	private String position_num;
	private String position_name;
	public GradeInfo(String position_num, String position_name) {
		super();
		this.position_num = position_num;
		this.position_name = position_name;
	}
	public GradeInfo() {
		super();
	}
	
	public GradeInfo(String position_name) {
		super();
		this.position_name = position_name;
	}
	public String getPosition_num() {
		return position_num;
	}
	public void setPosition_num(String position_num) {
		this.position_num = position_num;
	}
	public String getPosition_name() {
		return position_name;
	}
	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}
	
	@Override
	public String toString() {
		return "GradeInfo [position_num=" + position_num + ", position_name=" + position_name + "]";
	}
	
	
}
