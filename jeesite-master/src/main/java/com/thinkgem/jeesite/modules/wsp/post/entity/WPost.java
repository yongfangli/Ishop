/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.post.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.modules.wsp.file.entity.WFile;
import com.thinkgem.jeesite.modules.wsp.user.entity.WUser;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 帖子Entity
 * @author lyf
 * @version 2018-09-12
 */
public class WPost extends DataEntity<WPost> {
	
	private static final long serialVersionUID = 1L;
	private String content;		// content
	private String fileIds;		// file_ids
	private WUser user;		// belongUser
	private String createDateStr; //datestr
	private List<WFile> files;
	public WPost() {
		super();
	}

	public WPost(String id){
		super(id);
	}

	@Length(min=0, max=10000, message="content长度必须介于 0 和 10000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=500, message="file_ids长度必须介于 0 和 500 之间")
	public String getFileIds() {
		return fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}
	
	public WUser getUser() {
		return user;
	}

	public void setUser(WUser user) {
		this.user = user;
	}

	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public List<WFile> getFiles() {
		return files;
	}

	public void setFiles(List<WFile> files) {
		this.files = files;
	}


	
}