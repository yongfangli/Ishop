/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.session.web;

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
import com.thinkgem.jeesite.modules.wsp.session.entity.WSession;
import com.thinkgem.jeesite.modules.wsp.session.service.WSessionService;

/**
 * session管理Controller
 * @author lyf
 * @version 2018-10-04
 */
@Controller
@RequestMapping(value = "${adminPath}/session/wSession")
public class WSessionController extends BaseController {

	@Autowired
	private WSessionService wSessionService;
	
	@ModelAttribute
	public WSession get(@RequestParam(required=false) String id) {
		WSession entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wSessionService.get(id);
		}
		if (entity == null){
			entity = new WSession();
		}
		return entity;
	}
	
	@RequiresPermissions("session:wSession:view")
	@RequestMapping(value = {"list", ""})
	public String list(WSession wSession, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WSession> page = wSessionService.findPage(new Page<WSession>(request, response), wSession); 
		model.addAttribute("page", page);
		return "wsp/session/wSessionList";
	}

	@RequiresPermissions("session:wSession:view")
	@RequestMapping(value = "form")
	public String form(WSession wSession, Model model) {
		model.addAttribute("wSession", wSession);
		return "wsp/session/wSessionForm";
	}

	@RequiresPermissions("session:wSession:edit")
	@RequestMapping(value = "save")
	public String save(WSession wSession, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wSession)){
			return form(wSession, model);
		}
		wSessionService.save(wSession);
		addMessage(redirectAttributes, "保存session管理成功");
		return "redirect:"+Global.getAdminPath()+"/session/wSession/?repage";
	}
	
	@RequiresPermissions("session:wSession:edit")
	@RequestMapping(value = "delete")
	public String delete(WSession wSession, RedirectAttributes redirectAttributes) {
		wSessionService.delete(wSession);
		addMessage(redirectAttributes, "删除session管理成功");
		return "redirect:"+Global.getAdminPath()+"/session/wSession/?repage";
	}

}