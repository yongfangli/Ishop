/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.mypicture.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wsp.mypicture.entity.Mypicture;
import com.thinkgem.jeesite.modules.wsp.mypicture.dao.MypictureDao;

/**
 * 我的图片Service
 * @author lyf
 * @version 2018-12-02
 */
@Service
@Transactional(readOnly = true)
public class MypictureService extends CrudService<MypictureDao, Mypicture> {

	public Mypicture get(String id) {
		return super.get(id);
	}
	
	public List<Mypicture> findList(Mypicture mypicture) {
		return super.findList(mypicture);
	}
	
	public Page<Mypicture> findPage(Page<Mypicture> page, Mypicture mypicture) {
		return super.findPage(page, mypicture);
	}
	
	@Transactional(readOnly = false)
	public void save(Mypicture mypicture) {
		super.save(mypicture);
	}
	
	@Transactional(readOnly = false)
	public void delete(Mypicture mypicture) {
		super.delete(mypicture);
	}
	
}