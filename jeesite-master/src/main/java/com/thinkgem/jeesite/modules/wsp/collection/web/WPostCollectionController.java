/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.collection.web;

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
import com.thinkgem.jeesite.modules.wsp.collection.entity.WPostCollection;
import com.thinkgem.jeesite.modules.wsp.collection.service.WPostCollectionService;

/**
 * 收藏Controller
 * @author lyf
 * @version 2018-12-27
 */
@Controller
@RequestMapping(value = "${adminPath}/collection/wPostCollection")
public class WPostCollectionController extends BaseController {

	@Autowired
	private WPostCollectionService wPostCollectionService;
	
	@ModelAttribute
	public WPostCollection get(@RequestParam(required=false) String id) {
		WPostCollection entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wPostCollectionService.get(id);
		}
		if (entity == null){
			entity = new WPostCollection();
		}
		return entity;
	}
	
	@RequiresPermissions("collection:wPostCollection:view")
	@RequestMapping(value = {"list", ""})
	public String list(WPostCollection wPostCollection, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WPostCollection> page = wPostCollectionService.findPage(new Page<WPostCollection>(request, response), wPostCollection); 
		model.addAttribute("page", page);
		return "wsp/collection/wPostCollectionList";
	}

	@RequiresPermissions("collection:wPostCollection:view")
	@RequestMapping(value = "form")
	public String form(WPostCollection wPostCollection, Model model) {
		model.addAttribute("wPostCollection", wPostCollection);
		return "wsp/collection/wPostCollectionForm";
	}

	@RequiresPermissions("collection:wPostCollection:edit")
	@RequestMapping(value = "save")
	public String save(WPostCollection wPostCollection, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wPostCollection)){
			return form(wPostCollection, model);
		}
		wPostCollectionService.save(wPostCollection);
		addMessage(redirectAttributes, "保存收藏成功");
		return "redirect:"+Global.getAdminPath()+"/collection/wPostCollection/?repage";
	}
	
	@RequiresPermissions("collection:wPostCollection:edit")
	@RequestMapping(value = "delete")
	public String delete(WPostCollection wPostCollection, RedirectAttributes redirectAttributes) {
		wPostCollectionService.delete(wPostCollection);
		addMessage(redirectAttributes, "删除收藏成功");
		return "redirect:"+Global.getAdminPath()+"/collection/wPostCollection/?repage";
	}

}