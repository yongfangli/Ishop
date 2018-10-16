/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.session.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.wsp.user.entity.WUser;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * session管理Entity
 * @author lyf
 * @version 2018-10-04
 */
public class WSession extends DataEntity<WSession> {
	
	private static final long serialVersionUID = 1L;
	private String sessionId;		// session_id
	private WUser user;		// 当前用户
	private long expired;		// 失效时间
	
	public WSession() {
		super();
	}

	public WSession(String id){
		super(id);
	}

	@Length(min=0, max=64, message="session_id长度必须介于 0 和 64 之间")
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public WUser getUser() {
		return user;
	}

	public void setUser(WUser user) {
		this.user = user;
	}
	
	public long getExpired() {
		return expired;
	}

	public void setExpired(long expired) {
		this.expired = expired;
	}
	
}