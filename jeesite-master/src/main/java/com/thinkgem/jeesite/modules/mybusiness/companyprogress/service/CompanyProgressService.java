/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mybusiness.companyprogress.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mybusiness.companyprogress.entity.CompanyProgress;
import com.thinkgem.jeesite.modules.mybusiness.companyprogress.dao.CompanyProgressDao;

/**
 * 公司进展Service
 * @author liyongfang
 * @version 2018-03-23
 */
@Service
@Transactional(readOnly = true)
public class CompanyProgressService extends CrudService<CompanyProgressDao, CompanyProgress> {

	public CompanyProgress get(String id) {
		return super.get(id);
	}
	
	public List<CompanyProgress> findList(CompanyProgress companyProgress) {
		return super.findList(companyProgress);
	}
	
	public Page<CompanyProgress> findPage(Page<CompanyProgress> page, CompanyProgress companyProgress) {
		return super.findPage(page, companyProgress);
	}
	
	@Transactional(readOnly = false)
	public void save(CompanyProgress companyProgress) {
		super.save(companyProgress);
	}
	
	@Transactional(readOnly = false)
	public void delete(CompanyProgress companyProgress) {
		super.delete(companyProgress);
	}
	
}