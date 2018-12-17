/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.mypicture.web;

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
import com.thinkgem.jeesite.modules.wsp.mypicture.entity.Mypicture;
import com.thinkgem.jeesite.modules.wsp.mypicture.service.MypictureService;

/**
 * 我的图片Controller
 * @author lyf
 * @version 2018-12-02
 */
@Controller
@RequestMapping(value = "${adminPath}/mypicture/mypicture")
public class MypictureController extends BaseController {

	@Autowired
	private MypictureService mypictureService;
	
	@ModelAttribute
	public Mypicture get(@RequestParam(required=false) String id) {
		Mypicture entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mypictureService.get(id);
		}
		if (entity == null){
			entity = new Mypicture();
		}
		return entity;
	}
	
	@RequiresPermissions("mypicture:mypicture:view")
	@RequestMapping(value = {"list", ""})
	public String list(Mypicture mypicture, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Mypicture> page = mypictureService.findPage(new Page<Mypicture>(request, response), mypicture); 
		model.addAttribute("page", page);
		return "wsp/mypicture/mypictureList";
	}

	@RequiresPermissions("mypicture:mypicture:view")
	@RequestMapping(value = "form")
	public String form(Mypicture mypicture, Model model) {
		model.addAttribute("mypicture", mypicture);
		return "wsp/mypicture/mypictureForm";
	}

	@RequiresPermissions("mypicture:mypicture:edit")
	@RequestMapping(value = "save")
	public String save(Mypicture mypicture, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mypicture)){
			return form(mypicture, model);
		}
		mypictureService.save(mypicture);
		addMessage(redirectAttributes, "保存我的图片成功");
		return "redirect:"+Global.getAdminPath()+"/mypicture/mypicture/?repage";
	}
	
	@RequiresPermissions("mypicture:mypicture:edit")
	@RequestMapping(value = "delete")
	public String delete(Mypicture mypicture, RedirectAttributes redirectAttributes) {
		mypictureService.delete(mypicture);
		addMessage(redirectAttributes, "删除我的图片成功");
		return "redirect:"+Global.getAdminPath()+"/mypicture/mypicture/?repage";
	}

}