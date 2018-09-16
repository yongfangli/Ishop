/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mybusiness.activity.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.mybusiness.activity.entity.Activity;
import com.thinkgem.jeesite.modules.mybusiness.activity.service.ActivityService;

/**
 * 活动Controller
 * @author liyongfang
 * @version 2018-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/activity/activity")
public class ActivityController extends BaseController {

	@Autowired
	private ActivityService activityService;
	
	@ModelAttribute
	public Activity get(@RequestParam(required=false) String id) {
		Activity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = activityService.get(id);
		}
		if (entity == null){
			entity = new Activity();
		}
		return entity;
	}
	
	@RequiresPermissions("activity:activity:view")
	@RequestMapping(value = {"list", ""})
	public String list(Activity activity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Activity> page = activityService.findPage(new Page<Activity>(request, response), activity); 
		model.addAttribute("page", page);
		return "mybusiness/activity/activityList";
	}

	@RequiresPermissions("activity:activity:view")
	@RequestMapping(value = "form")
	public String form(Activity activity, Model model) {
		model.addAttribute("activity", activity);
		return "mybusiness/activity/activityForm";
	}

	@RequiresPermissions("activity:activity:edit")
	@RequestMapping(value = "save")
	public String save(Activity activity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, activity)){
			return form(activity, model);
		}
		activity.setContent(StringEscapeUtils.unescapeHtml4(activity.getContent()));
		activityService.save(activity);
		addMessage(redirectAttributes, "保存活动成功");
		return "redirect:"+Global.getAdminPath()+"/activity/activity/?repage";
	}
	
	@RequiresPermissions("activity:activity:edit")
	@RequestMapping(value = "delete")
	public String delete(Activity activity, RedirectAttributes redirectAttributes) {
		activityService.delete(activity);
		addMessage(redirectAttributes, "删除活动成功");
		return "redirect:"+Global.getAdminPath()+"/activity/activity/?repage";
	}

}