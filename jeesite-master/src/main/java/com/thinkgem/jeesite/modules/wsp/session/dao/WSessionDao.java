/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.session.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wsp.session.entity.WSession;

/**
 * session管理DAO接口
 * @author lyf
 * @version 2018-10-04
 */
@MyBatisDao
public interface WSessionDao extends CrudDao<WSession> {

	WSession getBySessionId(String sessionId);
	
}