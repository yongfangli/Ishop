/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.file.web;

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
import com.thinkgem.jeesite.modules.wsp.file.entity.WFile;
import com.thinkgem.jeesite.modules.wsp.file.service.WFileService;

/**
 * fileController
 * @author lyf
 * @version 2018-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/file/wFile")
public class WFileController extends BaseController {

	@Autowired
	private WFileService wFileService;
	
	@ModelAttribute
	public WFile get(@RequestParam(required=false) String id) {
		WFile entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wFileService.get(id);
		}
		if (entity == null){
			entity = new WFile();
		}
		return entity;
	}
	
	@RequiresPermissions("file:wFile:view")
	@RequestMapping(value = {"list", ""})
	public String list(WFile wFile, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WFile> page = wFileService.findPage(new Page<WFile>(request, response), wFile); 
		model.addAttribute("page", page);
		return "wsp/file/wFileList";
	}

	@RequiresPermissions("file:wFile:view")
	@RequestMapping(value = "form")
	public String form(WFile wFile, Model model) {
		model.addAttribute("wFile", wFile);
		return "wsp/file/wFileForm";
	}

	@RequiresPermissions("file:wFile:edit")
	@RequestMapping(value = "save")
	public String save(WFile wFile, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wFile)){
			return form(wFile, model);
		}
		wFileService.save(wFile);
		addMessage(redirectAttributes, "保存file成功");
		return "redirect:"+Global.getAdminPath()+"/file/wFile/?repage";
	}
	
	@RequiresPermissions("file:wFile:edit")
	@RequestMapping(value = "delete")
	public String delete(WFile wFile, RedirectAttributes redirectAttributes) {
		wFileService.delete(wFile);
		addMessage(redirectAttributes, "删除file成功");
		return "redirect:"+Global.getAdminPath()+"/file/wFile/?repage";
	}

}