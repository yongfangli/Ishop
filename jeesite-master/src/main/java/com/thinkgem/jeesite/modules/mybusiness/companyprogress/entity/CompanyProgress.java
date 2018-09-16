/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mybusiness.companyprogress.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 公司进展Entity
 * @author liyongfang
 * @version 2018-03-23
 */
public class CompanyProgress extends DataEntity<CompanyProgress> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 事件标题
	private String content;		// 事件内容
	private Office company;		// 公司名称
	private Date happenDate;		// 发生时间
	
	public CompanyProgress() {
		super();
	}

	public CompanyProgress(String id){
		super(id);
	}

	@Length(min=0, max=50, message="事件标题长度必须介于 0 和 50 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Office getCompany() {
		return company;
	}

	public void setCompany(Office company) {
		this.company = company;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getHappenDate() {
		return happenDate;
	}

	public void setHappenDate(Date happenDate) {
		this.happenDate = happenDate;
	}
	
}