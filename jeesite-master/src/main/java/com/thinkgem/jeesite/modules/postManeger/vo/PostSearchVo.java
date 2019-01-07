package com.thinkgem.jeesite.modules.postManeger.vo;

public class PostSearchVo {
	Integer pageNo=1;
	String typeId;
	String scont;
	String userId;
	String[] orderBy;
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getScont() {
		return scont;
	}
	public void setScont(String scont) {
		this.scont = scont;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String[] getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String[] orderBy) {
		this.orderBy = orderBy;
	}
	
}
