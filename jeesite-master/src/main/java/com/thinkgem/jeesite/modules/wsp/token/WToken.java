package com.thinkgem.jeesite.modules.wsp.token;

import java.util.Date;

public class WToken {
	private String code;
	private String token;
	private long expired;
	private Date createDate;
	private String sessionId;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getExpired() {
		return expired;
	}

	public void setExpired(long expired) {
		this.expired = expired;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
