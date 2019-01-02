/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.follow.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.wsp.user.entity.WUser;

/**
 * 关注Entity
 * @author lyf
 * @version 2018-12-27
 */
public class WFollow extends DataEntity<WFollow> {
	
	private static final long serialVersionUID = 1L;
	private WUser followUser;		// 关注人
	private WUser user;		// 当前用户
	
	public WFollow() {
		super();
	}

	public WFollow(String id){
		super(id);
	}

	public WUser getFollowUser() {
		return followUser;
	}

	public void setFollowUser(WUser followUser) {
		this.followUser = followUser;
	}
	
	public WUser getUser() {
		return user;
	}

	public void setUser(WUser user) {
		this.user = user;
	}
	
}