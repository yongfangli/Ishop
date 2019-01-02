/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.praise.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wsp.praise.entity.WPostPraise;
import com.thinkgem.jeesite.modules.wsp.praise.dao.WPostPraiseDao;

/**
 * 帖子赞Service
 * @author lyf
 * @version 2018-12-27
 */
@Service
@Transactional(readOnly = true)
public class WPostPraiseService extends CrudService<WPostPraiseDao, WPostPraise> {

	public WPostPraise get(String id) {
		return super.get(id);
	}
	
	public List<WPostPraise> findList(WPostPraise wPostPraise) {
		return super.findList(wPostPraise);
	}
	
	public Page<WPostPraise> findPage(Page<WPostPraise> page, WPostPraise wPostPraise) {
		return super.findPage(page, wPostPraise);
	}
	
	@Transactional(readOnly = false)
	public void save(WPostPraise wPostPraise) {
		super.save(wPostPraise);
	}
	
	@Transactional(readOnly = false)
	public void delete(WPostPraise wPostPraise) {
		super.delete(wPostPraise);
	}
	
}