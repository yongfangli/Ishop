/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.chapter.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wsp.chapter.entity.WNovelChapter;

/**
 * 章节DAO接口
 * @author lyf
 * @version 2019-01-06
 */
@MyBatisDao
public interface WNovelChapterDao extends CrudDao<WNovelChapter> {
	
}