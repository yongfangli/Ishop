/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mybusiness.companyprogress.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mybusiness.companyprogress.entity.CompanyProgress;

/**
 * 公司进展DAO接口
 * @author liyongfang
 * @version 2018-03-23
 */
@MyBatisDao
public interface CompanyProgressDao extends CrudDao<CompanyProgress> {
	
}