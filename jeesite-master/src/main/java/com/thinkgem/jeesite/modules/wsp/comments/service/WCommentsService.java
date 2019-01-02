/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.comments.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wsp.comments.entity.WComments;
import com.thinkgem.jeesite.modules.wsp.comments.dao.WCommentsDao;

/**
 * 帖子评论生成Service
 * @author lyf
 * @version 2018-12-25
 */
@Service
@Transactional(readOnly = true)
public class WCommentsService extends CrudService<WCommentsDao, WComments> {

	public WComments get(String id) {
		return super.get(id);
	}
	
	public List<WComments> findList(WComments wComments) {
		return super.findList(wComments);
	}
	
	public Page<WComments> findPage(Page<WComments> page, WComments wComments) {
		return super.findPage(page, wComments);
	}
	
	@Transactional(readOnly = false)
	public void save(WComments wComments) {
		super.save(wComments);
	}
	
	@Transactional(readOnly = false)
	public void delete(WComments wComments) {
		super.delete(wComments);
	}
	
}