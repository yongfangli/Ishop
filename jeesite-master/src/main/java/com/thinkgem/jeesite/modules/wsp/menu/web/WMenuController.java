/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.menu.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.wsp.menu.entity.WMenu;
import com.thinkgem.jeesite.modules.wsp.menu.service.WMenuService;

/**
 * 前台菜单Controller
 * @author lyf
 * @version 2018-10-08
 */
@Controller
@RequestMapping(value = "${adminPath}/menu/wMenu")
public class WMenuController extends BaseController {

	@Autowired
	private WMenuService wMenuService;
	
	@ModelAttribute
	public WMenu get(@RequestParam(required=false) String id) {
		WMenu entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wMenuService.get(id);
		}
		if (entity == null){
			entity = new WMenu();
		}
		return entity;
	}
	
	@RequiresPermissions("menu:wMenu:view")
	@RequestMapping(value = {"list", ""})
	public String list(WMenu wMenu, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<WMenu> list = wMenuService.findList(wMenu); 
		model.addAttribute("list", list);
		return "wsp/menu/wMenuList";
	}

	@RequiresPermissions("menu:wMenu:view")
	@RequestMapping(value = "form")
	public String form(WMenu wMenu, Model model) {
		if (wMenu.getParent()!=null && StringUtils.isNotBlank(wMenu.getParent().getId())){
			wMenu.setParent(wMenuService.get(wMenu.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(wMenu.getId())){
				WMenu wMenuChild = new WMenu();
				wMenuChild.setParent(new WMenu(wMenu.getParent().getId()));
				List<WMenu> list = wMenuService.findList(wMenu); 
				if (list.size() > 0){
					wMenu.setSort(list.get(list.size()-1).getSort());
					if (wMenu.getSort() != null){
						wMenu.setSort(wMenu.getSort() + 30);
					}
				}
			}
		}
		if (wMenu.getSort() == null){
			wMenu.setSort(30);
		}
		model.addAttribute("wMenu", wMenu);
		return "wsp/menu/wMenuForm";
	}

	@RequiresPermissions("menu:wMenu:edit")
	@RequestMapping(value = "save")
	public String save(WMenu wMenu, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wMenu)){
			return form(wMenu, model);
		}
		wMenuService.save(wMenu);
		addMessage(redirectAttributes, "保存前台菜单成功");
		return "redirect:"+Global.getAdminPath()+"/menu/wMenu/?repage";
	}
	
	@RequiresPermissions("menu:wMenu:edit")
	@RequestMapping(value = "delete")
	public String delete(WMenu wMenu, RedirectAttributes redirectAttributes) {
		wMenuService.delete(wMenu);
		addMessage(redirectAttributes, "删除前台菜单成功");
		return "redirect:"+Global.getAdminPath()+"/menu/wMenu/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<WMenu> list = wMenuService.findList(new WMenu());
		for (int i=0; i<list.size(); i++){
			WMenu e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}