/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.menu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.wsp.menu.entity.WMenu;
import com.thinkgem.jeesite.modules.wsp.menu.dao.WMenuDao;

/**
 * 前台菜单Service
 * @author lyf
 * @version 2018-10-08
 */
@Service
@Transactional(readOnly = true)
public class WMenuService extends TreeService<WMenuDao, WMenu> {

	public WMenu get(String id) {
		return super.get(id);
	}
	
	public List<WMenu> findList(WMenu wMenu) {
		if (StringUtils.isNotBlank(wMenu.getParentIds())){
			wMenu.setParentIds(","+wMenu.getParentIds()+",");
		}
		return super.findList(wMenu);
	}
	
	@Transactional(readOnly = false)
	public void save(WMenu wMenu) {
		super.save(wMenu);
	}
	
	@Transactional(readOnly = false)
	public void delete(WMenu wMenu) {
		super.delete(wMenu);
	}
	
}