package com.zelong.lin.admin;

import java.util.List;

import com.zelong.lin.dao.User;

public class PageBean {
	private int pageNum;
	private int currentPage;
	private int totalPage;
	private int totalCount;
	private List<User> u;
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
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
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<User> getU() {
		return u;
	}
	public void setU(List<User> u) {
		this.u = u;
	}
	
}
