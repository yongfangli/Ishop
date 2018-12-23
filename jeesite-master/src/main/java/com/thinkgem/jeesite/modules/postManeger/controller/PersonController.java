package com.thinkgem.jeesite.modules.postManeger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.modules.postManeger.controller.MyBaseController;
@Controller
@RequestMapping(value = "${webPath}/personal")
public class PersonController extends MyBaseController {
	 @RequestMapping("")
     public String index() {
		return "modules/personal/index";
     }
}
