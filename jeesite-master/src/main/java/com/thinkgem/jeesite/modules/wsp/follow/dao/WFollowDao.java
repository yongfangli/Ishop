/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.follow.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wsp.follow.entity.WFollow;

/**
 * 关注DAO接口
 * @author lyf
 * @version 2018-12-27
 */
@MyBatisDao
public interface WFollowDao extends CrudDao<WFollow> {
	
}