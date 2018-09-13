/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.user.web;

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
import com.thinkgem.jeesite.modules.wsp.user.entity.WUser;
import com.thinkgem.jeesite.modules.wsp.user.service.WUserService;

/**
 * 网站用户Controller
 * @author lyf
 * @version 2018-09-12
 */
@Controller
@RequestMapping(value = "${adminPath}/user/wUser")
public class WUserController extends BaseController {

	@Autowired
	private WUserService wUserService;
	
	@ModelAttribute
	public WUser get(@RequestParam(required=false) String id) {
		WUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wUserService.get(id);
		}
		if (entity == null){
			entity = new WUser();
		}
		return entity;
	}
	
	@RequiresPermissions("user:wUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(WUser wUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WUser> page = wUserService.findPage(new Page<WUser>(request, response), wUser); 
		model.addAttribute("page", page);
		return "wsp/user/wUserList";
	}

	@RequiresPermissions("user:wUser:view")
	@RequestMapping(value = "form")
	public String form(WUser wUser, Model model) {
		model.addAttribute("wUser", wUser);
		return "wsp/user/wUserForm";
	}

	@RequiresPermissions("user:wUser:edit")
	@RequestMapping(value = "save")
	public String save(WUser wUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wUser)){
			return form(wUser, model);
		}
		wUserService.save(wUser);
		addMessage(redirectAttributes, "保存网站用户成功");
		return "redirect:"+Global.getAdminPath()+"/user/wUser/?repage";
	}
	
	@RequiresPermissions("user:wUser:edit")
	@RequestMapping(value = "delete")
	public String delete(WUser wUser, RedirectAttributes redirectAttributes) {
		wUserService.delete(wUser);
		addMessage(redirectAttributes, "删除网站用户成功");
		return "redirect:"+Global.getAdminPath()+"/user/wUser/?repage";
	}

}