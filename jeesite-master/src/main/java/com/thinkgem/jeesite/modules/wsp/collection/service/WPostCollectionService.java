/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.collection.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wsp.collection.entity.WPostCollection;
import com.thinkgem.jeesite.modules.wsp.collection.dao.WPostCollectionDao;

/**
 * 收藏Service
 * @author lyf
 * @version 2018-12-27
 */
@Service
@Transactional(readOnly = true)
public class WPostCollectionService extends CrudService<WPostCollectionDao, WPostCollection> {

	public WPostCollection get(String id) {
		return super.get(id);
	}
	
	public List<WPostCollection> findList(WPostCollection wPostCollection) {
		return super.findList(wPostCollection);
	}
	
	public Page<WPostCollection> findPage(Page<WPostCollection> page, WPostCollection wPostCollection) {
		return super.findPage(page, wPostCollection);
	}
	
	@Transactional(readOnly = false)
	public void save(WPostCollection wPostCollection) {
		super.save(wPostCollection);
	}
	
	@Transactional(readOnly = false)
	public void delete(WPostCollection wPostCollection) {
		super.delete(wPostCollection);
	}
	
}