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
@RequestMapping(value = "${webPath}/feedback")
public class FeedBackController extends MyBaseController{
	@Autowired
	private WCommentsService commentsService;
	@RequestMapping(value = "")
	public String index() {
		return "modules/feedback/feedback";
	}
}
