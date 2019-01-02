/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.feedback.entity;

import com.thinkgem.jeesite.modules.wsp.user.entity.WUser;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 反馈Entity
 * @author lyf
 * @version 2018-12-27
 */
public class WFeedback extends DataEntity<WFeedback> {
	
	private static final long serialVersionUID = 1L;
	private String content;		// 内容
	private WUser user;		// 反馈用戶
	private String suggest;		// 建议
	private User suggestUser;		// 回复人
	private Date replyDate;		// 回复时间
	private String isRead;		// 是否查看
	
	public WFeedback() {
		super();
	}

	public WFeedback(String id){
		super(id);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public WUser getUser() {
		return user;
	}

	public void setUser(WUser user) {
		this.user = user;
	}
	
	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	
	public User getSuggestUser() {
		return suggestUser;
	}

	public void setSuggestUser(User suggestUser) {
		this.suggestUser = suggestUser;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	
	@Length(min=0, max=1, message="是否查看长度必须介于 0 和 1 之间")
	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	
}