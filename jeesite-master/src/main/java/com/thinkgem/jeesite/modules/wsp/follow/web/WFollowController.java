/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.follow.web;

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
import com.thinkgem.jeesite.modules.wsp.follow.entity.WFollow;
import com.thinkgem.jeesite.modules.wsp.follow.service.WFollowService;

/**
 * 关注Controller
 * @author lyf
 * @version 2018-12-27
 */
@Controller
@RequestMapping(value = "${adminPath}/follow/wFollow")
public class WFollowController extends BaseController {

	@Autowired
	private WFollowService wFollowService;
	
	@ModelAttribute
	public WFollow get(@RequestParam(required=false) String id) {
		WFollow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wFollowService.get(id);
		}
		if (entity == null){
			entity = new WFollow();
		}
		return entity;
	}
	
	@RequiresPermissions("follow:wFollow:view")
	@RequestMapping(value = {"list", ""})
	public String list(WFollow wFollow, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WFollow> page = wFollowService.findPage(new Page<WFollow>(request, response), wFollow); 
		model.addAttribute("page", page);
		return "wsp/follow/wFollowList";
	}

	@RequiresPermissions("follow:wFollow:view")
	@RequestMapping(value = "form")
	public String form(WFollow wFollow, Model model) {
		model.addAttribute("wFollow", wFollow);
		return "wsp/follow/wFollowForm";
	}

	@RequiresPermissions("follow:wFollow:edit")
	@RequestMapping(value = "save")
	public String save(WFollow wFollow, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wFollow)){
			return form(wFollow, model);
		}
		wFollowService.save(wFollow);
		addMessage(redirectAttributes, "保存关注成功");
		return "redirect:"+Global.getAdminPath()+"/follow/wFollow/?repage";
	}
	
	@RequiresPermissions("follow:wFollow:edit")
	@RequestMapping(value = "delete")
	public String delete(WFollow wFollow, RedirectAttributes redirectAttributes) {
		wFollowService.delete(wFollow);
		addMessage(redirectAttributes, "删除关注成功");
		return "redirect:"+Global.getAdminPath()+"/follow/wFollow/?repage";
	}

}