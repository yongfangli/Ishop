/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.novel.web;

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
import com.thinkgem.jeesite.modules.wsp.novel.entity.WNovel;
import com.thinkgem.jeesite.modules.wsp.novel.service.WNovelService;

/**
 * 小说Controller
 * @author lyf
 * @version 2019-01-06
 */
@Controller
@RequestMapping(value = "${adminPath}/novel/wNovel")
public class WNovelController extends BaseController {

	@Autowired
	private WNovelService wNovelService;
	
	@ModelAttribute
	public WNovel get(@RequestParam(required=false) String id) {
		WNovel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wNovelService.get(id);
		}
		if (entity == null){
			entity = new WNovel();
		}
		return entity;
	}
	
	@RequiresPermissions("novel:wNovel:view")
	@RequestMapping(value = {"list", ""})
	public String list(WNovel wNovel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WNovel> page = wNovelService.findPage(new Page<WNovel>(request, response), wNovel); 
		model.addAttribute("page", page);
		return "wsp/novel/wNovelList";
	}

	@RequiresPermissions("novel:wNovel:view")
	@RequestMapping(value = "form")
	public String form(WNovel wNovel, Model model) {
		model.addAttribute("wNovel", wNovel);
		return "wsp/novel/wNovelForm";
	}

	@RequiresPermissions("novel:wNovel:edit")
	@RequestMapping(value = "save")
	public String save(WNovel wNovel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wNovel)){
			return form(wNovel, model);
		}
		wNovelService.save(wNovel);
		addMessage(redirectAttributes, "保存小说成功");
		return "redirect:"+Global.getAdminPath()+"/novel/wNovel/?repage";
	}
	
	@RequiresPermissions("novel:wNovel:edit")
	@RequestMapping(value = "delete")
	public String delete(WNovel wNovel, RedirectAttributes redirectAttributes) {
		wNovelService.delete(wNovel);
		addMessage(redirectAttributes, "删除小说成功");
		return "redirect:"+Global.getAdminPath()+"/novel/wNovel/?repage";
	}

}