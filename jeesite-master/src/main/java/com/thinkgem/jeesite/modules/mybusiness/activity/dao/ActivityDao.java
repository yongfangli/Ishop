/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mybusiness.activity.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mybusiness.activity.entity.Activity;

/**
 * 活动DAO接口
 * @author liyongfang
 * @version 2018-03-13
 */
@MyBatisDao
public interface ActivityDao extends CrudDao<Activity> {
	
}