/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.collection.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.wsp.post.entity.WPost;
import com.thinkgem.jeesite.modules.wsp.user.entity.WUser;

/**
 * 收藏Entity
 * @author lyf
 * @version 2018-12-27
 */
public class WPostCollection extends DataEntity<WPostCollection> {
	
	private static final long serialVersionUID = 1L;
	private WPost post;		// 帖子
	private WUser user;		// 用戶
	
	public WPostCollection() {
		super();
	}

	public WPostCollection(String id){
		super(id);
	}


	public WPost getPost() {
		return post;
	}

	public void setPost(WPost post) {
		this.post = post;
	}

	public WUser getUser() {
		return user;
	}

	public void setUser(WUser user) {
		this.user = user;
	}
	
}