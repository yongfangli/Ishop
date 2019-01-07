/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.chapter.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.wsp.novel.entity.WNovel;

/**
 * 章节Entity
 * @author lyf
 * @version 2019-01-06
 */
public class WNovelChapter extends DataEntity<WNovelChapter> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// title
	private WNovel novel;		// novelid
	private String pre;		// pre
	private String next;		// next
	private String content;		// content
	
	public WNovelChapter() {
		super();
	}

	public WNovelChapter(String id){
		super(id);
	}

	@Length(min=0, max=255, message="title长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public WNovel getNovel() {
		return novel;
	}

	public void setNovel(WNovel novel) {
		this.novel = novel;
	}

	@Length(min=0, max=64, message="pre长度必须介于 0 和 64 之间")
	public String getPre() {
		return pre;
	}

	public void setPre(String pre) {
		this.pre = pre;
	}
	
	@Length(min=0, max=255, message="next长度必须介于 0 和 255 之间")
	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}
	
	@Length(min=0, max=255, message="content长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}