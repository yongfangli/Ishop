/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.post.web;

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
import com.thinkgem.jeesite.modules.wsp.post.entity.WPost;
import com.thinkgem.jeesite.modules.wsp.post.service.WPostService;

/**
 * 帖子Controller
 * @author lyf
 * @version 2018-09-12
 */
@Controller
@RequestMapping(value = "${adminPath}/post/wPost")
public class WPostController extends BaseController {

	@Autowired
	private WPostService wPostService;
	
	@ModelAttribute
	public WPost get(@RequestParam(required=false) String id) {
		WPost entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wPostService.get(id);
		}
		if (entity == null){
			entity = new WPost();
		}
		return entity;
	}
	
	@RequiresPermissions("post:wPost:view")
	@RequestMapping(value = {"list", ""})
	public String list(WPost wPost, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WPost> page = wPostService.findPage(new Page<WPost>(request, response), wPost); 
		model.addAttribute("page", page);
		return "wsp/post/wPostList";
	}

	@RequiresPermissions("post:wPost:view")
	@RequestMapping(value = "form")
	public String form(WPost wPost, Model model) {
		model.addAttribute("wPost", wPost);
		return "wsp/post/wPostForm";
	}

	@RequiresPermissions("post:wPost:edit")
	@RequestMapping(value = "save")
	public String save(WPost wPost, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wPost)){
			return form(wPost, model);
		}
		wPostService.save(wPost);
		addMessage(redirectAttributes, "保存帖子成功");
		return "redirect:"+Global.getAdminPath()+"/post/wPost/?repage";
	}
	
	@RequiresPermissions("post:wPost:edit")
	@RequestMapping(value = "delete")
	public String delete(WPost wPost, RedirectAttributes redirectAttributes) {
		wPostService.delete(wPost);
		addMessage(redirectAttributes, "删除帖子成功");
		return "redirect:"+Global.getAdminPath()+"/post/wPost/?repage";
	}

}