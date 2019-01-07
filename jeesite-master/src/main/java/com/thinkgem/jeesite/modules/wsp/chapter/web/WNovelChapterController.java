/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.chapter.web;

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
import com.thinkgem.jeesite.modules.wsp.chapter.entity.WNovelChapter;
import com.thinkgem.jeesite.modules.wsp.chapter.service.WNovelChapterService;

/**
 * 章节Controller
 * @author lyf
 * @version 2019-01-06
 */
@Controller
@RequestMapping(value = "${adminPath}/chapter/wNovelChapter")
public class WNovelChapterController extends BaseController {

	@Autowired
	private WNovelChapterService wNovelChapterService;
	
	@ModelAttribute
	public WNovelChapter get(@RequestParam(required=false) String id) {
		WNovelChapter entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wNovelChapterService.get(id);
		}
		if (entity == null){
			entity = new WNovelChapter();
		}
		return entity;
	}
	
	@RequiresPermissions("chapter:wNovelChapter:view")
	@RequestMapping(value = {"list", ""})
	public String list(WNovelChapter wNovelChapter, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WNovelChapter> page = wNovelChapterService.findPage(new Page<WNovelChapter>(request, response), wNovelChapter); 
		model.addAttribute("page", page);
		return "wsp/chapter/wNovelChapterList";
	}

	@RequiresPermissions("chapter:wNovelChapter:view")
	@RequestMapping(value = "form")
	public String form(WNovelChapter wNovelChapter, Model model) {
		model.addAttribute("wNovelChapter", wNovelChapter);
		return "wsp/chapter/wNovelChapterForm";
	}

	@RequiresPermissions("chapter:wNovelChapter:edit")
	@RequestMapping(value = "save")
	public String save(WNovelChapter wNovelChapter, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wNovelChapter)){
			return form(wNovelChapter, model);
		}
		wNovelChapterService.save(wNovelChapter);
		addMessage(redirectAttributes, "保存章节成功");
		return "redirect:"+Global.getAdminPath()+"/chapter/wNovelChapter/?repage";
	}
	
	@RequiresPermissions("chapter:wNovelChapter:edit")
	@RequestMapping(value = "delete")
	public String delete(WNovelChapter wNovelChapter, RedirectAttributes redirectAttributes) {
		wNovelChapterService.delete(wNovelChapter);
		addMessage(redirectAttributes, "删除章节成功");
		return "redirect:"+Global.getAdminPath()+"/chapter/wNovelChapter/?repage";
	}

}