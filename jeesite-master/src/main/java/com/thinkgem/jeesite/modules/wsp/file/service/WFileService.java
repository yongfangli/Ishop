/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.file.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wsp.file.entity.WFile;
import com.thinkgem.jeesite.modules.wsp.file.dao.WFileDao;

/**
 * fileService
 * @author lyf
 * @version 2018-09-20
 */
@Service
@Transactional(readOnly = true)
public class WFileService extends CrudService<WFileDao, WFile> {

	public WFile get(String id) {
		return super.get(id);
	}
	
	public List<WFile> findList(WFile wFile) {
		return super.findList(wFile);
	}
	
	public Page<WFile> findPage(Page<WFile> page, WFile wFile) {
		return super.findPage(page, wFile);
	}
	
	@Transactional(readOnly = false)
	public void save(WFile wFile) {
		super.save(wFile);
	}
	
	@Transactional(readOnly = false)
	public void save(String fileId,String targetId,String contentType,long size) {
		WFile wFile=new WFile();
		wFile.setContenttype(contentType);
		wFile.setTargetid(targetId);
		wFile.setFileid(fileId);
		wFile.setSize(size);
		super.save(wFile);
	}
	
	
	@Transactional(readOnly = false)
	public void delete(WFile wFile) {
		super.delete(wFile);
	}
	
}