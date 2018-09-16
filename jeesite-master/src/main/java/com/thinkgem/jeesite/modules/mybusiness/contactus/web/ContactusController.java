/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mybusiness.contactus.web;

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
import com.thinkgem.jeesite.modules.mybusiness.contactus.entity.Contactus;
import com.thinkgem.jeesite.modules.mybusiness.contactus.service.ContactusService;

/**
 * 联系我们表Controller
 * @author liyongfang
 * @version 2018-03-16
 */
@Controller
@RequestMapping(value = "${adminPath}/contactus/contactus")
public class ContactusController extends BaseController {

	@Autowired
	private ContactusService contactusService;
	
	@ModelAttribute
	public Contactus get(@RequestParam(required=false) String id) {
		Contactus entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = contactusService.get(id);
		}
		if (entity == null){
			entity = new Contactus();
		}
		return entity;
	}
	
	@RequiresPermissions("contactus:contactus:view")
	@RequestMapping(value = {"list", ""})
	public String list(Contactus contactus, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Contactus> page = contactusService.findPage(new Page<Contactus>(request, response), contactus); 
		model.addAttribute("page", page);
		return "mybusiness/contactus/contactusList";
	}

	@RequiresPermissions("contactus:contactus:view")
	@RequestMapping(value = "form")
	public String form(Contactus contactus, Model model) {
		model.addAttribute("contactus", contactus);
		return "mybusiness/contactus/contactusForm";
	}

	@RequiresPermissions("contactus:contactus:edit")
	@RequestMapping(value = "save")
	public String save(Contactus contactus, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, contactus)){
			return form(contactus, model);
		}
		contactusService.save(contactus);
		addMessage(redirectAttributes, "保存联系我们表成功");
		return "redirect:"+Global.getAdminPath()+"/contactus/contactus/?repage";
	}
	
	@RequiresPermissions("contactus:contactus:edit")
	@RequestMapping(value = "delete")
	public String delete(Contactus contactus, RedirectAttributes redirectAttributes) {
		contactusService.delete(contactus);
		addMessage(redirectAttributes, "删除联系我们表成功");
		return "redirect:"+Global.getAdminPath()+"/contactus/contactus/?repage";
	}

}