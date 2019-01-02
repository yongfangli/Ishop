/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.praise.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wsp.praise.entity.WPostPraise;

/**
 * 帖子赞DAO接口
 * @author lyf
 * @version 2018-12-27
 */
@MyBatisDao
public interface WPostPraiseDao extends CrudDao<WPostPraise> {
	
}