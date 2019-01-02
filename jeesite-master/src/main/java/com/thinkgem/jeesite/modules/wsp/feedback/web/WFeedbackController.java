/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.feedback.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.wsp.feedback.entity.WFeedback;
import com.thinkgem.jeesite.modules.wsp.feedback.service.WFeedbackService;

/**
 * 反馈Controller
 * @author lyf
 * @version 2018-12-27
 */
@Controller
@RequestMapping(value = "${adminPath}/feedback/wFeedback")
public class WFeedbackController extends BaseController {

	@Autowired
	private WFeedbackService wFeedbackService;
	
	@ModelAttribute
	public WFeedback get(@RequestParam(required=false) String id) {
		WFeedback entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wFeedbackService.get(id);
		}
		if (entity == null){
			entity = new WFeedback();
		}
		return entity;
	}
	
	@RequiresPermissions("feedback:wFeedback:view")
	@RequestMapping(value = {"list", ""})
	public String list(WFeedback wFeedback, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WFeedback> page = wFeedbackService.findPage(new Page<WFeedback>(request, response), wFeedback); 
		model.addAttribute("page", page);
		return "wsp/feedback/wFeedbackList";
	}

	@RequiresPermissions("feedback:wFeedback:view")
	@RequestMapping(value = "form")
	public String form(WFeedback wFeedback, Model model) {
		model.addAttribute("wFeedback", wFeedback);
		return "wsp/feedback/wFeedbackForm";
	}

	@RequiresPermissions("feedback:wFeedback:edit")
	@RequestMapping(value = "save")
	public String save(WFeedback wFeedback, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wFeedback)){
			return form(wFeedback, model);
		}
		wFeedbackService.save(wFeedback);
		addMessage(redirectAttributes, "保存反馈成功");
		return "redirect:"+Global.getAdminPath()+"/feedback/wFeedback/?repage";
	}
	
	@RequiresPermissions("feedback:wFeedback:edit")
	@RequestMapping(value = "delete")
	public String delete(WFeedback wFeedback, RedirectAttributes redirectAttributes) {
		wFeedbackService.delete(wFeedback);
		addMessage(redirectAttributes, "删除反馈成功");
		return "redirect:"+Global.getAdminPath()+"/feedback/wFeedback/?repage";
	}

}