/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.feedback.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wsp.feedback.entity.WFeedback;
import com.thinkgem.jeesite.modules.wsp.feedback.dao.WFeedbackDao;

/**
 * 反馈Service
 * @author lyf
 * @version 2018-12-27
 */
@Service
@Transactional(readOnly = true)
public class WFeedbackService extends CrudService<WFeedbackDao, WFeedback> {

	public WFeedback get(String id) {
		return super.get(id);
	}
	
	public List<WFeedback> findList(WFeedback wFeedback) {
		return super.findList(wFeedback);
	}
	
	public Page<WFeedback> findPage(Page<WFeedback> page, WFeedback wFeedback) {
		return super.findPage(page, wFeedback);
	}
	
	@Transactional(readOnly = false)
	public void save(WFeedback wFeedback) {
		super.save(wFeedback);
	}
	
	@Transactional(readOnly = false)
	public void delete(WFeedback wFeedback) {
		super.delete(wFeedback);
	}
	
}