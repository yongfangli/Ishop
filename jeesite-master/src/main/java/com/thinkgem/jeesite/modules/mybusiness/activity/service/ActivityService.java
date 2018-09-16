/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mybusiness.activity.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mybusiness.activity.entity.Activity;
import com.thinkgem.jeesite.modules.mybusiness.activity.dao.ActivityDao;

/**
 * 活动Service
 * @author liyongfang
 * @version 2018-03-13
 */
@Service
@Transactional(readOnly = true)
public class ActivityService extends CrudService<ActivityDao, Activity> {

	public Activity get(String id) {
		return super.get(id);
	}
	
	public List<Activity> findList(Activity activity) {
		return super.findList(activity);
	}
	
	public Page<Activity> findPage(Page<Activity> page, Activity activity) {
		return super.findPage(page, activity);
	}
	
	@Transactional(readOnly = false)
	public void save(Activity activity) {
		super.save(activity);
	}
	
	@Transactional(readOnly = false)
	public void delete(Activity activity) {
		super.delete(activity);
	}
	/**
	 * 前几条
	 * @param num
	 * @return
	 */
	public List<Activity> findRecentlyActivity(int num){
		Page<Activity> aPage=new Page<Activity>(1,num);
		aPage.setOrderBy("start_time desc");
		aPage=findPage(aPage, new Activity());
		return aPage.getList();
	}
}