/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.chapter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wsp.chapter.entity.WNovelChapter;
import com.thinkgem.jeesite.modules.wsp.novel.entity.WNovel;
import com.thinkgem.jeesite.modules.wsp.chapter.dao.WNovelChapterDao;

/**
 * 章节Service
 * @author lyf
 * @version 2019-01-06
 */
@Service
@Transactional(readOnly = true)
public class WNovelChapterService extends CrudService<WNovelChapterDao, WNovelChapter> {

	public WNovelChapter get(String id) {
		return super.get(id);
	}
	
	public List<WNovelChapter> findList(WNovelChapter wNovelChapter) {
		return super.findList(wNovelChapter);
	}
	
	public Page<WNovelChapter> findPage(Page<WNovelChapter> page, WNovelChapter wNovelChapter) {
		return super.findPage(page, wNovelChapter);
	}
	
	@Transactional(readOnly = false)
	public void save(WNovelChapter wNovelChapter) {
		super.save(wNovelChapter);
	}
	
	@Transactional(readOnly = false)
	public void delete(WNovelChapter wNovelChapter) {
		super.delete(wNovelChapter);
	}
	public void fetch(List<WNovelChapter> re,WNovelChapter chapter){
		List<WNovelChapter> lChapters=super.findList(chapter);
		if(lChapters.size()>0) {
			re.add(lChapters.get(0));
			chapter.setPre(lChapters.get(0).getId());
			fetch(re,chapter);
		}
	}
	public List<WNovelChapter> getChapterByNovel(String nid){
		List<WNovelChapter> re=new ArrayList<>();
		WNovelChapter wChapter=new WNovelChapter();
		wChapter.setPre("");
		wChapter.setNovel(new WNovel(nid));
		fetch(re,wChapter);
		return re;
	}
}