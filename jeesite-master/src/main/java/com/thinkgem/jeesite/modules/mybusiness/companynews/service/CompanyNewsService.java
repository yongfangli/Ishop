/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mybusiness.companynews.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mybusiness.companynews.entity.CompanyNews;
import com.thinkgem.jeesite.modules.mybusiness.activity.entity.Activity;
import com.thinkgem.jeesite.modules.mybusiness.companynews.dao.CompanyNewsDao;

/**
 * 公司新闻Service
 * @author liyongfang
 * @version 2018-03-15
 */
@Service
@Transactional(readOnly = true)
public class CompanyNewsService extends CrudService<CompanyNewsDao, CompanyNews> {

	public CompanyNews get(String id) {
		return super.get(id);
	}
	
	public List<CompanyNews> findList(CompanyNews companyNews) {
		return super.findList(companyNews);
	}
	
	public Page<CompanyNews> findPage(Page<CompanyNews> page, CompanyNews companyNews) {
		return super.findPage(page, companyNews);
	}
	
	@Transactional(readOnly = false)
	public void save(CompanyNews companyNews) {
		super.save(companyNews);
	}
	
	@Transactional(readOnly = false)
	public void delete(CompanyNews companyNews) {
		super.delete(companyNews);
	}

	public List<CompanyNews> findRecentlyNews(int num) {
		Page<CompanyNews> aPage=new Page<CompanyNews>(1,num);
		aPage.setOrderBy("create_date desc");
		aPage=findPage(aPage, new CompanyNews());
		return aPage.getList();
	}
	
}