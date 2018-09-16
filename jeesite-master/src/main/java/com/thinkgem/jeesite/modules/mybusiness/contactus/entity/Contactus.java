/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mybusiness.contactus.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 联系我们表Entity
 * @author liyongfang
 * @version 2018-03-16
 */
public class Contactus extends DataEntity<Contactus> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 联系人
	private String phone;		// 联系电话
	private String detail;		// 联系内容
	
	public Contactus() {
		super();
	}

	public Contactus(String id){
		super(id);
	}

	@Length(min=0, max=50, message="联系人长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="联系电话长度必须介于 0 和 100 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=200, message="联系内容长度必须介于 0 和 200 之间")
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}