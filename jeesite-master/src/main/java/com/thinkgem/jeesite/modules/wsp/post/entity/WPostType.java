/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.post.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 帖子类型Entity
 * @author liyongfang
 * @version 2018-09-29
 */
public class WPostType extends TreeEntity<WPostType> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 类型名称
	private WPostType parent;		// 上级类型
	private Integer sort;		// 排序
	private List<WPostType> child;
	public WPostType() {
		super();
	}

	public WPostType(String id){
		super(id);
	}

	@Length(min=0, max=255, message="类型名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonBackReference
	public WPostType getParent() {
		return parent;
	}

	public void setParent(WPostType parent) {
		this.parent = parent;
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

	public List<WPostType> getChild() {
		return child;
	}

	public void setChild(List<WPostType> child) {
		this.child = child;
	}
	
}