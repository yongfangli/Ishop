/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mybusiness.companyprogress.web;

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
import com.thinkgem.jeesite.modules.mybusiness.companyprogress.entity.CompanyProgress;
import com.thinkgem.jeesite.modules.mybusiness.companyprogress.service.CompanyProgressService;

/**
 * 公司进展Controller
 * @author liyongfang
 * @version 2018-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/companyprogress/companyProgress")
public class CompanyProgressController extends BaseController {

	@Autowired
	private CompanyProgressService companyProgressService;
	
	@ModelAttribute
	public CompanyProgress get(@RequestParam(required=false) String id) {
		CompanyProgress entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = companyProgressService.get(id);
		}
		if (entity == null){
			entity = new CompanyProgress();
		}
		return entity;
	}
	
	@RequiresPermissions("companyprogress:companyProgress:view")
	@RequestMapping(value = {"list", ""})
	public String list(CompanyProgress companyProgress, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CompanyProgress> page = companyProgressService.findPage(new Page<CompanyProgress>(request, response), companyProgress); 
		model.addAttribute("page", page);
		return "mybusiness/companyprogress/companyProgressList";
	}

	@RequiresPermissions("companyprogress:companyProgress:view")
	@RequestMapping(value = "form")
	public String form(CompanyProgress companyProgress, Model model) {
		model.addAttribute("companyProgress", companyProgress);
		return "mybusiness/companyprogress/companyProgressForm";
	}

	@RequiresPermissions("companyprogress:companyProgress:edit")
	@RequestMapping(value = "save")
	public String save(CompanyProgress companyProgress, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, companyProgress)){
			return form(companyProgress, model);
		}
		companyProgressService.save(companyProgress);
		addMessage(redirectAttributes, "保存公司进展成功");
		return "redirect:"+Global.getAdminPath()+"/companyprogress/companyProgress/?repage";
	}
	
	@RequiresPermissions("companyprogress:companyProgress:edit")
	@RequestMapping(value = "delete")
	public String delete(CompanyProgress companyProgress, RedirectAttributes redirectAttributes) {
		companyProgressService.delete(companyProgress);
		addMessage(redirectAttributes, "删除公司进展成功");
		return "redirect:"+Global.getAdminPath()+"/companyprogress/companyProgress/?repage";
	}

}