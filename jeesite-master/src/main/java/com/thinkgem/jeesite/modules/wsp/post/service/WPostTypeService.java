/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.post.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.wsp.post.entity.WPostType;
import com.thinkgem.jeesite.modules.wsp.post.dao.WPostTypeDao;

/**
 * 帖子类型Service
 * @author liyongfang
 * @version 2018-09-29
 */
@Service
@Transactional(readOnly = true)
public class WPostTypeService extends TreeService<WPostTypeDao, WPostType> {

	public WPostType get(String id) {
		return super.get(id);
	}
	
	public List<WPostType> findList(WPostType wPostType) {
		if (StringUtils.isNotBlank(wPostType.getParentIds())){
			wPostType.setParentIds(","+wPostType.getParentIds()+",");
		}
		return super.findList(wPostType);
	}
	
	@Transactional(readOnly = false)
	public void save(WPostType wPostType) {
		super.save(wPostType);
	}
	
	@Transactional(readOnly = false)
	public void delete(WPostType wPostType) {
		super.delete(wPostType);
	}
	
}