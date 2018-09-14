/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.post.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wsp.post.dao.WPostDao;
import com.thinkgem.jeesite.modules.wsp.post.entity.WPost;

/**
 * 帖子Service
 * @author lyf
 * @version 2018-09-12
 */
@Service
@Transactional(readOnly = true)
public class WPostService extends CrudService<WPostDao, WPost> {

	public WPost get(String id) {
		return super.get(id);
	}
	
	public List<WPost> findList(WPost wPost) {
		return super.findList(wPost);
	}
	
	public Page<WPost> findPage(Page<WPost> page, WPost wPost) {
		return super.findPage(page, wPost);
	}
	
	@Transactional(readOnly = false)
	public void save(WPost wPost) {
		super.save(wPost);
	}
	
	@Transactional(readOnly = false)
	public void delete(WPost wPost) {
		super.delete(wPost);
	}
	
}