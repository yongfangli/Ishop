/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mybusiness.companynews.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.util.StringUtil;
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
import com.thinkgem.jeesite.modules.mybusiness.companynews.entity.CompanyNews;
import com.thinkgem.jeesite.modules.mybusiness.companynews.service.CompanyNewsService;

/**
 * 公司新闻Controller
 * 
 * @author liyongfang
 * @version 2018-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/companynews/companyNews")
public class CompanyNewsController extends BaseController {

	@Autowired
	private CompanyNewsService companyNewsService;

	@ModelAttribute
	public CompanyNews get(@RequestParam(required = false) String id) {
		CompanyNews entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = companyNewsService.get(id);
		}
		if (entity == null) {
			entity = new CompanyNews();
		}
		return entity;
	}

	@RequiresPermissions("companynews:companyNews:view")
	@RequestMapping(value = { "list", "" })
	public String list(CompanyNews companyNews, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CompanyNews> page = companyNewsService.findPage(new Page<CompanyNews>(request, response), companyNews);
		model.addAttribute("page", page);
		return "mybusiness/companynews/companyNewsList";
	}

	@RequiresPermissions("companynews:companyNews:view")
	@RequestMapping(value = "form")
	public String form(CompanyNews companyNews, Model model) {
		model.addAttribute("companyNews", companyNews);
		return "mybusiness/companynews/companyNewsForm";
	}

	@RequiresPermissions("companynews:companyNews:edit")
	@RequestMapping(value = "save")
	public String save(CompanyNews companyNews, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, companyNews)) {
			return form(companyNews, model);
		}
		companyNews.setContent(StringEscapeUtils.unescapeHtml4(companyNews.getContent()));
		companyNewsService.save(companyNews);
		addMessage(redirectAttributes, "保存公司新闻成功");
		return "redirect:" + Global.getAdminPath() + "/companynews/companyNews/?repage";
	}

	@RequiresPermissions("companynews:companyNews:edit")
	@RequestMapping(value = "delete")
	public String delete(CompanyNews companyNews, RedirectAttributes redirectAttributes) {
		companyNewsService.delete(companyNews);
		addMessage(redirectAttributes, "删除公司新闻成功");
		return "redirect:" + Global.getAdminPath() + "/companynews/companyNews/?repage";
	}

}