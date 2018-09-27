/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.file.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * fileEntity
 * @author lyf
 * @version 2018-09-20
 */
public class WFile extends DataEntity<WFile> {
	
	private static final long serialVersionUID = 1L;
	private String fileid;		// fileid
	private String targetid;		// targetid
	private String contenttype;		// contenttype
	private long size;		// size
	
	public WFile() {
		super();
	}

	public WFile(String id){
		super(id);
	}

	@Length(min=0, max=64, message="fileid长度必须介于 0 和 64 之间")
	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}
	
	@Length(min=0, max=64, message="targetid长度必须介于 0 和 64 之间")
	public String getTargetid() {
		return targetid;
	}

	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}
	
	@Length(min=0, max=255, message="contenttype长度必须介于 0 和 255 之间")
	public String getContenttype() {
		return contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
	
	
}