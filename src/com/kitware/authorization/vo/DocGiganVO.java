package com.kitware.authorization.vo;

import java.util.List;

public class DocGiganVO {
	private String doc_num;
	private String start_date;
	private String end_date;
	private List<DocVO> document;
	
	
	public DocGiganVO() {
		super();
	}
	public DocGiganVO(String start_date, String end_date) {
		super();
		this.start_date = start_date;
		this.end_date = end_date;
	}
	public DocGiganVO(String doc_num, String start_date, String end_date) {
		super();
		this.doc_num = doc_num;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	public DocGiganVO(String doc_num, String start_date, String end_date, List<DocVO> document) {
		super();
		this.doc_num = doc_num;
		this.start_date = start_date;
		this.end_date = end_date;
		this.document = document;
	}
	public String getDoc_num() {
		return doc_num;
	}
	public void setDoc_num(String doc_num) {
		this.doc_num = doc_num;
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
	public List<DocVO> getDocument() {
		return document;
	}
	public void setDocument(List<DocVO> document) {
		this.document = document;
	}
	@Override
	public String toString() {
		return "DocGiganVO [doc_num=" + doc_num + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", document=" + document + "]";
	}
	
	
}
