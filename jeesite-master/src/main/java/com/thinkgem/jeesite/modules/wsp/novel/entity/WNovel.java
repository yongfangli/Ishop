/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.novel.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.modules.wsp.chapter.entity.WNovelChapter;
import com.thinkgem.jeesite.modules.wsp.user.entity.WUser;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 小说Entity
 * @author lyf
 * @version 2019-01-06
 */
public class WNovel extends DataEntity<WNovel> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String cover;		// 封面
	private WUser user;		// 作者
	private Long fontnum;		// fontnum
	private List<WNovelChapter> chapters;
	private String updateStr;
	public WNovel() {
		super();
	}

	public WNovel(String id){
		super(id);
	}

	@Length(min=0, max=255, message="标题长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="封面长度必须介于 0 和 255 之间")
	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}
	
	
	public WUser getUser() {
		return user;
	}

	public void setUser(WUser user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getFontnum() {
		return fontnum;
	}

	public void setFontnum(Long fontnum) {
		this.fontnum = fontnum;
	}

	public List<WNovelChapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<WNovelChapter> chapters) {
		this.chapters = chapters;
	}

	public String getUpdateStr() {
		return updateStr;
	}

	public void setUpdateStr(String updateStr) {
		this.updateStr = updateStr;
	}
	
}