/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.follow.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wsp.follow.entity.WFollow;
import com.thinkgem.jeesite.modules.wsp.follow.dao.WFollowDao;

/**
 * 关注Service
 * @author lyf
 * @version 2018-12-27
 */
@Service
@Transactional(readOnly = true)
public class WFollowService extends CrudService<WFollowDao, WFollow> {

	public WFollow get(String id) {
		return super.get(id);
	}
	
	public List<WFollow> findList(WFollow wFollow) {
		return super.findList(wFollow);
	}
	
	public Page<WFollow> findPage(Page<WFollow> page, WFollow wFollow) {
		return super.findPage(page, wFollow);
	}
	
	@Transactional(readOnly = false)
	public void save(WFollow wFollow) {
		super.save(wFollow);
	}
	
	@Transactional(readOnly = false)
	public void delete(WFollow wFollow) {
		super.delete(wFollow);
	}
	
}