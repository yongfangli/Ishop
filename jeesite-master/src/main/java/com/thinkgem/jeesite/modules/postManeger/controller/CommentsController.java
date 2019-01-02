package com.thinkgem.jeesite.modules.postManeger.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.modules.cms.utils.RelativeDateFormat;
import com.thinkgem.jeesite.modules.postManeger.cost.AjaxReturn;
import com.thinkgem.jeesite.modules.wsp.comments.entity.WComments;
import com.thinkgem.jeesite.modules.wsp.comments.service.WCommentsService;
import com.thinkgem.jeesite.modules.wsp.post.entity.WPost;
import com.thinkgem.jeesite.modules.wsp.post.entity.WPostType;

@Controller
@RequestMapping(value = "${webPath}/comments/")
public class CommentsController extends MyBaseController{
	@Autowired
	private WCommentsService commentsService;
	@ResponseBody
	@RequestMapping(value = "page")
	public Map<String, Object> page(@RequestParam(defaultValue = "1") Integer pageNo, String postId) {
		Map<String, Object> resultMap = new HashMap<>();
		WComments wComments = new WComments();
		if (StringUtils.isNotEmpty(postId)) {
			wComments.setPost(new WPost(postId));
		}
		Page<WComments> page = new Page(pageNo, 8);
		page = commentsService.findPage(page, wComments);
		resultMap.put("total", page.getTotalPage());
		resultMap.put("count",page.getCount());
		if (pageNo >= page.getLast()) {
			if (page.getList().size() > 0) {
				for (WComments o : page.getList()) {
					o.setCreateStr(RelativeDateFormat.format(o.getCreateDate()));
				}
				resultMap.put(AjaxReturn.DATA, page.getList());
			} else {
				resultMap.put(AjaxReturn.DATA, new ArrayList<>());
			}
			resultMap.put("last", true);
		} else {
			for (WComments o : page.getList()) {
				o.setCreateStr(RelativeDateFormat.format(o.getCreateDate()));
			}
			resultMap.put(AjaxReturn.DATA, page.getList());
			resultMap.put("last", false);
		}
		
		resultMap.put(AjaxReturn.STATUS, AjaxReturn.SUCCESS);
		return resultMap;
	}
}
