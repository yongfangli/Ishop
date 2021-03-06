/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.feedback.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wsp.feedback.entity.WFeedback;

/**
 * 反馈DAO接口
 * @author lyf
 * @version 2018-12-27
 */
@MyBatisDao
public interface WFeedbackDao extends CrudDao<WFeedback> {
	
}