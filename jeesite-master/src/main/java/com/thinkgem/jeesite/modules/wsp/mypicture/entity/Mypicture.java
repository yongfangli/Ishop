/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.mypicture.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.wsp.user.entity.WUser;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 我的图片Entity
 * @author lyf
 * @version 2018-12-02
 */
public class Mypicture extends DataEntity<Mypicture> {
	
	private static final long serialVersionUID = 1L;
	private String topic;		// topic
	private String desc;		// desc
	private String keyword;		// keyword
	private WUser user;		// user_id
	private String imgs;		// imgs
	
	public Mypicture() {
		super();
	}

	public Mypicture(String id){
		super(id);
	}

	@Length(min=0, max=255, message="topic长度必须介于 0 和 255 之间")
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	@Length(min=0, max=255, message="desc长度必须介于 0 和 255 之间")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Length(min=0, max=255, message="keyword长度必须介于 0 和 255 之间")
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public WUser getUser() {
		return user;
	}

	public void setUser(WUser user) {
		this.user = user;
	}
	
	@Length(min=0, max=1000, message="imgs长度必须介于 0 和 1000 之间")
	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	
}