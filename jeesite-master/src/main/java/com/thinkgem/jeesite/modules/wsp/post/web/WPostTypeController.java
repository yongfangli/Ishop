/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.post.web;

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
import com.thinkgem.jeesite.modules.wsp.post.entity.WPostType;
import com.thinkgem.jeesite.modules.wsp.post.service.WPostTypeService;

/**
 * 帖子类型Controller
 * @author liyongfang
 * @version 2018-09-29
 */
@Controller
@RequestMapping(value = "${adminPath}/post/wPostType")
public class WPostTypeController extends BaseController {

	@Autowired
	private WPostTypeService wPostTypeService;
	
	@ModelAttribute
	public WPostType get(@RequestParam(required=false) String id) {
		WPostType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wPostTypeService.get(id);
		}
		if (entity == null){
			entity = new WPostType();
		}
		return entity;
	}
	
	@RequiresPermissions("post:wPostType:view")
	@RequestMapping(value = {"list", ""})
	public String list(WPostType wPostType, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<WPostType> list = wPostTypeService.findList(wPostType); 
		model.addAttribute("list", list);
		return "wsp/post/wPostTypeList";
	}

	@RequiresPermissions("post:wPostType:view")
	@RequestMapping(value = "form")
	public String form(WPostType wPostType, Model model) {
		if (wPostType.getParent()!=null && StringUtils.isNotBlank(wPostType.getParent().getId())){
			wPostType.setParent(wPostTypeService.get(wPostType.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(wPostType.getId())){
				WPostType wPostTypeChild = new WPostType();
				wPostTypeChild.setParent(new WPostType(wPostType.getParent().getId()));
				List<WPostType> list = wPostTypeService.findList(wPostType); 
				if (list.size() > 0){
					wPostType.setSort(list.get(list.size()-1).getSort());
					if (wPostType.getSort() != null){
						wPostType.setSort(wPostType.getSort() + 30);
					}
				}
			}
		}
		if (wPostType.getSort() == null){
			wPostType.setSort(30);
		}
		model.addAttribute("wPostType", wPostType);
		return "wsp/post/wPostTypeForm";
	}

	@RequiresPermissions("post:wPostType:edit")
	@RequestMapping(value = "save")
	public String save(WPostType wPostType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wPostType)){
			return form(wPostType, model);
		}
		wPostTypeService.save(wPostType);
		addMessage(redirectAttributes, "保存帖子类型成功");
		return "redirect:"+Global.getAdminPath()+"/post/wPostType/?repage";
	}
	
	@RequiresPermissions("post:wPostType:edit")
	@RequestMapping(value = "delete")
	public String delete(WPostType wPostType, RedirectAttributes redirectAttributes) {
		wPostTypeService.delete(wPostType);
		addMessage(redirectAttributes, "删除帖子类型成功");
		return "redirect:"+Global.getAdminPath()+"/post/wPostType/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<WPostType> list = wPostTypeService.findList(new WPostType());
		for (int i=0; i<list.size(); i++){
			WPostType e = list.get(i);
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