package com.thinkgem.jeesite.modules.mybusiness.activity.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.front.JsonResult;
import com.thinkgem.jeesite.modules.mybusiness.activity.entity.Activity;
import com.thinkgem.jeesite.modules.mybusiness.activity.service.ActivityService;

@Controller
@ResponseBody
@RequestMapping(value = "${ajaxPath}/activity/")
public class ActivityFront {
	@Autowired
	private ActivityService activityService;

	@RequestMapping(value = "list")
	public JsonResult<List> list() {
		JsonResult<List> jsonResult =JsonResult.getDefault();
        List<Activity> activities=activityService.findList(new Activity());
        jsonResult.setBody(activities);
		return jsonResult;
	}
	@RequestMapping(value = "detail/{id}")
	public JsonResult<Activity> detail(@PathVariable String id) {
		JsonResult<Activity> jsonResult =JsonResult.getDefault();
		Activity activity=activityService.get(id);
        jsonResult.setBody(activity);
		return jsonResult;
	}
}
