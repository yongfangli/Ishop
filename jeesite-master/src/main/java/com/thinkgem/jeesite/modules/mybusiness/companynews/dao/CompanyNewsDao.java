/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mybusiness.companynews.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mybusiness.companynews.entity.CompanyNews;

/**
 * 公司新闻DAO接口
 * @author liyongfang
 * @version 2018-03-15
 */
@MyBatisDao
public interface CompanyNewsDao extends CrudDao<CompanyNews> {
	
}