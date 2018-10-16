/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.post.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wsp.post.entity.WPostType;

/**
 * 帖子类型DAO接口
 * @author liyongfang
 * @version 2018-09-29
 */
@MyBatisDao
public interface WPostTypeDao extends TreeDao<WPostType> {
	
}