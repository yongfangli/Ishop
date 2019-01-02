/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.comments.entity;

import com.thinkgem.jeesite.modules.wsp.user.entity.WUser;
import com.thinkgem.jeesite.modules.wsp.post.entity.WPost;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 帖子评论生成Entity
 * @author lyf
 * @version 2018-12-25
 */
public class WComments extends DataEntity<WComments> {
	
	private static final long serialVersionUID = 1L;
	private WUser user;		// 用户
	private String comments;		// 评论
	private WPost post;		// 帖子
	private String createStr;
	public WComments() {
		super();
	}

	public WComments(String id){
		super(id);
	}

	public WUser getUser() {
		return user;
	}

	public void setUser(WUser user) {
		this.user = user;
	}
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public WPost getPost() {
		return post;
	}

	public void setPost(WPost post) {
		this.post = post;
	}

	public String getCreateStr() {
		return createStr;
	}

	public void setCreateStr(String createStr) {
		this.createStr = createStr;
	}
	
}