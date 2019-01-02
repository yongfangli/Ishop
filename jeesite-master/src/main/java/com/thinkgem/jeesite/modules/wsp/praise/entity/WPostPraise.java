/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.praise.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.wsp.post.entity.WPost;
import com.thinkgem.jeesite.modules.wsp.user.entity.WUser;

/**
 * 帖子赞Entity
 * @author lyf
 * @version 2018-12-27
 */
public class WPostPraise extends DataEntity<WPostPraise> {
	
	private static final long serialVersionUID = 1L;
	private WPost post;		// 帖子
	private WUser user;		// 用户
	private String type;		// 类型
	
	public WPostPraise() {
		super();
	}

	public WPostPraise(String id){
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
	
	@Length(min=0, max=1, message="类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}