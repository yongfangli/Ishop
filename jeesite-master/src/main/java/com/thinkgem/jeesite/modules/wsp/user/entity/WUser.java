/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.user.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 网站用户Entity
 * @author lyf
 * @version 2018-09-12
 */
public class WUser extends DataEntity<WUser> {
	
	private static final long serialVersionUID = 1L;
	private String firstIp;		// first_ip
	private String browserType;		// browser_type
	private String client;		// client
	private String nickname;		// nickname
	private String username;		// username
	private String phone;		// phone
	private String portrait;		// portrait
	private String remark;		// remark
	private String email;		// email
	private String userType;    //userType 
	private String passwordMd5;
	private String password;
	public WUser() {
		super();
	}

	public WUser(String id){
		super(id);
	}

	@Length(min=0, max=1000, message="first_ip长度必须介于 0 和 1000 之间")
	public String getFirstIp() {
		return firstIp;
	}

	public void setFirstIp(String firstIp) {
		this.firstIp = firstIp;
	}
	
	@Length(min=1, max=255, message="browser_type长度必须介于 1 和 255 之间")
	public String getBrowserType() {
		return browserType;
	}

	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}
	
	@Length(min=0, max=255, message="client长度必须介于 0 和 255 之间")
	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}
	
	@Length(min=0, max=255, message="nickname长度必须介于 0 和 255 之间")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Length(min=0, max=255, message="username长度必须介于 0 和 255 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=0, max=255, message="phone长度必须介于 0 和 255 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=255, message="portrait长度必须介于 0 和 255 之间")
	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	
	@Length(min=0, max=1000, message="remark长度必须介于 0 和 1000 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=255, message="email长度必须介于 0 和 255 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPasswordMd5() {
		return passwordMd5;
	}

	public void setPasswordMd5(String passwordMd5) {
		this.passwordMd5 = passwordMd5;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}