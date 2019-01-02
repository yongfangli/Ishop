/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.comments.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wsp.comments.entity.WComments;

/**
 * 帖子评论生成DAO接口
 * @author lyf
 * @version 2018-12-25
 */
@MyBatisDao
public interface WCommentsDao extends CrudDao<WComments> {
	
}