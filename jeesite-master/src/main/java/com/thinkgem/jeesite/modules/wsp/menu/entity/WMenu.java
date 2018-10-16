/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.menu.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.thinkgem.jeesite.common.persistence.TreeEntity;
import com.thinkgem.jeesite.modules.wsp.post.entity.WPostType;

/**
 * 前台菜单Entity
 * @author lyf
 * @version 2018-10-08
 */
public class WMenu extends TreeEntity<WMenu> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String href;		// href
	private WMenu parent;		// parent_id
	private String parentIds;		// parent_ids
	private Integer sort;		// sort
	private List<WMenu> child;
	public WMenu() {
		super();
	}

	public WMenu(String id){
		super(id);
	}

	@Length(min=0, max=255, message="name长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="href长度必须介于 0 和 255 之间")
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	@JsonBackReference
	public WMenu getParent() {
		return parent;
	}

	public void setParent(WMenu parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=255, message="parent_ids长度必须介于 0 和 255 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}

	public List<WMenu> getChild() {
		return child;
	}

	public void setChild(List<WMenu> child) {
		this.child = child;
	}
	
}