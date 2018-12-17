package com.thinkgem.jeesite.modules.three;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.modules.postManeger.controller.MyBaseController;

@Controller
@RequestMapping(value = "${webPath}/three/")
public class ThreeController extends MyBaseController {
	@RequestMapping("three")
	public String three() {
         return "modules/three/three";
	}
	
}
