/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.wsp.user.entity.WUser;
import com.thinkgem.jeesite.modules.wsp.user.dao.WUserDao;

/**
 * 网站用户Service
 * @author lyf
 * @version 2018-09-12
 */
@Service
@Transactional(readOnly = true)
public class WUserService extends CrudService<WUserDao, WUser> {

	public WUser get(String id) {
		return super.get(id);
	}
	
	public List<WUser> findList(WUser wUser) {
		return super.findList(wUser);
	}
	
	public Page<WUser> findPage(Page<WUser> page, WUser wUser) {
		return super.findPage(page, wUser);
	}
	
	@Transactional(readOnly = false)
	public void save(WUser wUser) {
		super.save(wUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(WUser wUser) {
		super.delete(wUser);
	}
	
	public WUser findByIp(String ip) {
		WUser user=new WUser();
		if(StringUtils.isNoneEmpty(ip)) {
			user.setFirstIp(ip);
			List<WUser> users=this.findList(user);
			if(users.size()>0)
				return users.get(0);
		}
		return null;
	}
}