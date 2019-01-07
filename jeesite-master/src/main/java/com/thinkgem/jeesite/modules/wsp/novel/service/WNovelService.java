/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.novel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wsp.novel.entity.WNovel;
import com.thinkgem.jeesite.modules.wsp.novel.dao.WNovelDao;

/**
 * 小说Service
 * @author lyf
 * @version 2019-01-06
 */
@Service
@Transactional(readOnly = true)
public class WNovelService extends CrudService<WNovelDao, WNovel> {

	public WNovel get(String id) {
		return super.get(id);
	}
	
	public List<WNovel> findList(WNovel wNovel) {
		return super.findList(wNovel);
	}
	
	public Page<WNovel> findPage(Page<WNovel> page, WNovel wNovel) {
		return super.findPage(page, wNovel);
	}
	
	@Transactional(readOnly = false)
	public void save(WNovel wNovel) {
		super.save(wNovel);
	}
	
	@Transactional(readOnly = false)
	public void delete(WNovel wNovel) {
		super.delete(wNovel);
	}
	
}