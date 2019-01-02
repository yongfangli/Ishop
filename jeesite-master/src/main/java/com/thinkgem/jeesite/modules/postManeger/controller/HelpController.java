package com.thinkgem.jeesite.modules.postManeger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "${webPath}/help/")
public class HelpController extends MyBaseController{
	@RequestMapping("index")
	public String index() {
		return "modules/help/help";
	}
}
