/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.menu.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wsp.menu.entity.WMenu;

/**
 * 前台菜单DAO接口
 * @author lyf
 * @version 2018-10-08
 */
@MyBatisDao
public interface WMenuDao extends TreeDao<WMenu> {
	
}