package com.thinkgem.jeesite.modules.postManeger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "${webPath}/")
public class IndexController {
	@RequestMapping("index.html")
	public String index() {
		return "modules/home/index";
	}
}
