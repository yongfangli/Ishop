/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.praise.web;

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
import com.thinkgem.jeesite.modules.wsp.praise.entity.WPostPraise;
import com.thinkgem.jeesite.modules.wsp.praise.service.WPostPraiseService;

/**
 * 帖子赞Controller
 * @author lyf
 * @version 2018-12-27
 */
@Controller
@RequestMapping(value = "${adminPath}/praise/wPostPraise")
public class WPostPraiseController extends BaseController {

	@Autowired
	private WPostPraiseService wPostPraiseService;
	
	@ModelAttribute
	public WPostPraise get(@RequestParam(required=false) String id) {
		WPostPraise entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wPostPraiseService.get(id);
		}
		if (entity == null){
			entity = new WPostPraise();
		}
		return entity;
	}
	
	@RequiresPermissions("praise:wPostPraise:view")
	@RequestMapping(value = {"list", ""})
	public String list(WPostPraise wPostPraise, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WPostPraise> page = wPostPraiseService.findPage(new Page<WPostPraise>(request, response), wPostPraise); 
		model.addAttribute("page", page);
		return "wsp/praise/wPostPraiseList";
	}

	@RequiresPermissions("praise:wPostPraise:view")
	@RequestMapping(value = "form")
	public String form(WPostPraise wPostPraise, Model model) {
		model.addAttribute("wPostPraise", wPostPraise);
		return "wsp/praise/wPostPraiseForm";
	}

	@RequiresPermissions("praise:wPostPraise:edit")
	@RequestMapping(value = "save")
	public String save(WPostPraise wPostPraise, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wPostPraise)){
			return form(wPostPraise, model);
		}
		wPostPraiseService.save(wPostPraise);
		addMessage(redirectAttributes, "保存帖子赞成功");
		return "redirect:"+Global.getAdminPath()+"/praise/wPostPraise/?repage";
	}
	
	@RequiresPermissions("praise:wPostPraise:edit")
	@RequestMapping(value = "delete")
	public String delete(WPostPraise wPostPraise, RedirectAttributes redirectAttributes) {
		wPostPraiseService.delete(wPostPraise);
		addMessage(redirectAttributes, "删除帖子赞成功");
		return "redirect:"+Global.getAdminPath()+"/praise/wPostPraise/?repage";
	}

}