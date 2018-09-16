/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mybusiness.contactus.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mybusiness.contactus.entity.Contactus;

/**
 * 联系我们表DAO接口
 * @author liyongfang
 * @version 2018-03-16
 */
@MyBatisDao
public interface ContactusDao extends CrudDao<Contactus> {
	
}