package com.thinkgem.jeesite.modules.user;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.tools.corba.se.idl.StringGen;
import com.thinkgem.jeesite.modules.postManeger.controller.MyBaseController;
import com.thinkgem.jeesite.modules.postManeger.cost.AjaxReturn;
import com.thinkgem.jeesite.modules.postManeger.message.EmailMessageService;

@Controller
@RequestMapping("${webPath}/system")
public class RegisterController extends MyBaseController {
	@Autowired
	private EmailMessageService emailService;
	
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Map<String, Object> register(String phone, String email, String nickName, String validateCode,
			String password, String repassword) {
		Map<String, Object> resultMap = new HashMap<>();
		return resultMap;
	}

	@RequestMapping(value = "/goRegister")
	public String goRegister(Model model) throws UnsupportedEncodingException {
		return "modules/system/register";
	}
	@ResponseBody
	@RequestMapping(value = "/code")
	public Map<String, Object> getCode(String email) throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put(AjaxReturn.STATUS,AjaxReturn.SUCCESS);
		return resultMap;
	}
}
