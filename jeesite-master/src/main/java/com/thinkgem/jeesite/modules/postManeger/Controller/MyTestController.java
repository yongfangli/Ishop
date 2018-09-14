package com.thinkgem.jeesite.modules.postManeger.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.modules.postManeger.baiduApi.service.TextService;
import com.thinkgem.jeesite.modules.postManeger.cost.TokenType;
import com.thinkgem.jeesite.modules.wsp.token.TokenService;

@Controller
@RequestMapping("/test")
public class MyTestController{
	@Autowired
	private TokenService tokenService;
	@Autowired
	private TextService textService;
	@ResponseBody
	@RequestMapping("/test")
	public String test() {
		boolean a=textService.validateText("操你妈");
		return "test";
	}
}
