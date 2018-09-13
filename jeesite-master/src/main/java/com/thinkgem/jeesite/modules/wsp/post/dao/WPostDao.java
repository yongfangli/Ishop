/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.post.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wsp.post.entity.WPost;

/**
 * 帖子DAO接口
 * @author lyf
 * @version 2018-09-12
 */
@MyBatisDao
public interface WPostDao extends CrudDao<WPost> {
	
}