/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.file.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wsp.file.entity.WFile;

/**
 * fileDAO接口
 * @author lyf
 * @version 2018-09-20
 */
@MyBatisDao
public interface WFileDao extends CrudDao<WFile> {
	
}