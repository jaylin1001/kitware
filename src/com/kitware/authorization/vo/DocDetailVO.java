package com.kitware.authorization.vo;

import java.util.Date;

import com.kitware.member.vo.Members;

public class DocDetailVO {
	private String doc_num;
	private String conf_num;
	private int sunbeon;
	private String acs_yn;
	private String cmt;
	private Date rcv_date;
	private Date acs_date;
	private Members members;

	public DocDetailVO() {
		super();
	}

	public DocDetailVO(String doc_num, String conf_num, int sunbeon, String acs_yn, String cmt, Date rcv_date,
			Date acs_date, Members members) {
		super();
		this.doc_num = doc_num;
		this.conf_num = conf_num;
		this.sunbeon = sunbeon;
		this.acs_yn = acs_yn;
		this.cmt = cmt;
		this.rcv_date = rcv_date;
		this.acs_date = acs_date;
		this.members = members;
	}

	public String getDoc_num() {
		return doc_num;
	}

	public void setDoc_num(String doc_num) {
		this.doc_num = doc_num;
	}

	public String getConf_num() {
		return conf_num;
	}

	public void setConf_num(String conf_num) {
		this.conf_num = conf_num;
	}

	public int getSunbeon() {
		return sunbeon;
	}

	public void setSunbeon(int sunbeon) {
		this.sunbeon = sunbeon;
	}

	public String getAcs_yn() {
		return acs_yn;
	}

	public void setAcs_yn(String acs_yn) {
		this.acs_yn = acs_yn;
	}

	public String getCmt() {
		return cmt;
	}

	public void setCmt(String cmt) {
		this.cmt = cmt;
	}

	public Date getRcv_date() {
		return rcv_date;
	}

	public void setRcv_date(Date rcv_date) {
		this.rcv_date = rcv_date;
	}

	public Date getAcs_date() {
		return acs_date;
	}

	public void setAcs_date(Date acs_date) {
		this.acs_date = acs_date;
	}

	public Members getMembers() {
		return members;
	}

	public void setMembers(Members members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return "DocDetailVO [doc_num=" + doc_num + ", conf_num=" + conf_num + ", sunbeon=" + sunbeon + ", acs_yn="
				+ acs_yn + ", cmt=" + cmt + ", rcv_date=" + rcv_date + ", acs_date=" + acs_date + ", members=" + members
				+ "]";
	}
	
	
}
	