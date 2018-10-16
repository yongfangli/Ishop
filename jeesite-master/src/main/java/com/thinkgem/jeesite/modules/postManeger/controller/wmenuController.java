package com.thinkgem.jeesite.modules.postManeger.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.modules.postManeger.cost.AjaxReturn;
import com.thinkgem.jeesite.modules.wsp.menu.entity.WMenu;
import com.thinkgem.jeesite.modules.wsp.menu.service.WMenuService;
import com.thinkgem.jeesite.modules.wsp.post.entity.WPostType;

@Controller
@RequestMapping(value = "${webPath}/menu/")
public class wmenuController extends MyBaseController {
	@Autowired
	private WMenuService menuService;

	@ResponseBody
	@RequestMapping(value = "menuJson")
	public Map<String, Object> typeListJson() {
		Map<String, Object> resultMap = new HashMap<>();
		WMenu type=new WMenu();
		type.setParent(new WMenu("0"));
		List<WMenu> types=menuService.findList(type);
		resultMap.put(AjaxReturn.STATUS,AjaxReturn.SUCCESS);
		resultMap.put(AjaxReturn.DATA, types);
		return resultMap;
	}
}
