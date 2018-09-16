/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mybusiness.contactus.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mybusiness.contactus.entity.Contactus;
import com.thinkgem.jeesite.modules.mybusiness.contactus.dao.ContactusDao;

/**
 * 联系我们表Service
 * @author liyongfang
 * @version 2018-03-16
 */
@Service
@Transactional(readOnly = true)
public class ContactusService extends CrudService<ContactusDao, Contactus> {

	public Contactus get(String id) {
		return super.get(id);
	}
	
	public List<Contactus> findList(Contactus contactus) {
		return super.findList(contactus);
	}
	
	public Page<Contactus> findPage(Page<Contactus> page, Contactus contactus) {
		return super.findPage(page, contactus);
	}
	
	@Transactional(readOnly = false)
	public void save(Contactus contactus) {
		super.save(contactus);
	}
	
	@Transactional(readOnly = false)
	public void delete(Contactus contactus) {
		super.delete(contactus);
	}
	
}