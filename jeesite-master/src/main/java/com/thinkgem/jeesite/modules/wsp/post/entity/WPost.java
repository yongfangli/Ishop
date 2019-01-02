/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.post.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.modules.postManeger.controller.PostTypeController;
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
	private String title;
	private WPostType postType; //帖子类型
	private String fileIds;		// file_ids
	private WUser user;		// belongUser
	private String createDateStr; //datestr
	private Integer praiseNum;//赞的数量
	private Integer stepNum; //踩的数量
	private List<WFile> files;
	private String searchContent;
	private Integer collectionNum;//收藏量
	private Integer commentsNum;//评论数量
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
	@Length(min=0, max=10000, message="title长度必须介于 0 和 100 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public WPostType getPostType() {
		return postType;
	}

	public void setPostType(WPostType postType) {
		this.postType = postType;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	public Integer getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}

	public Integer getStepNum() {
		return stepNum;
	}

	public void setStepNum(Integer stepNum) {
		this.stepNum = stepNum;
	}

	public Integer getCollectionNum() {
		return collectionNum;
	}

	public void setCollectionNum(Integer collectionNum) {
		this.collectionNum = collectionNum;
	}

	public Integer getCommentsNum() {
		return commentsNum;
	}

	public void setCommentsNum(Integer commentsNum) {
		this.commentsNum = commentsNum;
	}
	
}