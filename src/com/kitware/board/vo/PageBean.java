package com.kitware.board.vo;

import java.util.List;

public class PageBean<T> {
	private int currentPage;
	private int totalPage;
	private List<T> list;
	private int startPage;
	private int endPage;
	private int totalCount;  //총 게시물 갯수
	private int cntPerPage;  //한 페이지당 보여줄 갯수
	public PageBean() {
		super();
	}
	public PageBean(int currentPage, int totalPage, List<T> list, int startPage, int endPage, int totalCount,
			int cntPerPage) {
		super();
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.list = list;
		this.startPage = startPage;
		this.endPage = endPage;
		this.totalCount = totalCount;
		this.cntPerPage = cntPerPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCntPerPage() {
		return cntPerPage;
	}
	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}
	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", totalPage=" + totalPage + ", list=" + list + ", startPage="
				+ startPage + ", endPage=" + endPage + ", totalCount=" + totalCount + ", cntPerPage=" + cntPerPage
				+ "]";
	}
	
	
}
