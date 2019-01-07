/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.novel.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wsp.novel.entity.WNovel;

/**
 * 小说DAO接口
 * @author lyf
 * @version 2019-01-06
 */
@MyBatisDao
public interface WNovelDao extends CrudDao<WNovel> {
	
}