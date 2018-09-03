package com.thinkgem.jeesite.modules.postManeger.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "${webPath}/post/")
public class PostManagerController {
	@RequestMapping(value="postInput")
    public String goPostInput(Model model) {
		return "modules/postManager/postInput";
}}
