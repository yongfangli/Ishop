/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.comments.web;

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
import com.thinkgem.jeesite.modules.wsp.comments.entity.WComments;
import com.thinkgem.jeesite.modules.wsp.comments.service.WCommentsService;

/**
 * 帖子评论生成Controller
 * @author lyf
 * @version 2018-12-25
 */
@Controller
@RequestMapping(value = "${adminPath}/comments/wComments")
public class WCommentsController extends BaseController {

	@Autowired
	private WCommentsService wCommentsService;
	
	@ModelAttribute
	public WComments get(@RequestParam(required=false) String id) {
		WComments entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wCommentsService.get(id);
		}
		if (entity == null){
			entity = new WComments();
		}
		return entity;
	}
	
	@RequiresPermissions("comments:wComments:view")
	@RequestMapping(value = {"list", ""})
	public String list(WComments wComments, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WComments> page = wCommentsService.findPage(new Page<WComments>(request, response), wComments); 
		model.addAttribute("page", page);
		return "wsp/comments/wCommentsList";
	}

	@RequiresPermissions("comments:wComments:view")
	@RequestMapping(value = "form")
	public String form(WComments wComments, Model model) {
		model.addAttribute("wComments", wComments);
		return "wsp/comments/wCommentsForm";
	}

	@RequiresPermissions("comments:wComments:edit")
	@RequestMapping(value = "save")
	public String save(WComments wComments, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wComments)){
			return form(wComments, model);
		}
		wCommentsService.save(wComments);
		addMessage(redirectAttributes, "保存帖子评论生成成功");
		return "redirect:"+Global.getAdminPath()+"/comments/wComments/?repage";
	}
	
	@RequiresPermissions("comments:wComments:edit")
	@RequestMapping(value = "delete")
	public String delete(WComments wComments, RedirectAttributes redirectAttributes) {
		wCommentsService.delete(wComments);
		addMessage(redirectAttributes, "删除帖子评论生成成功");
		return "redirect:"+Global.getAdminPath()+"/comments/wComments/?repage";
	}

}