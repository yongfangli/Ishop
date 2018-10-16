package com.thinkgem.jeesite.modules.user;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.security.Digests;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.UserAgentUtils;
import com.thinkgem.jeesite.modules.postManeger.controller.MyBaseController;
import com.thinkgem.jeesite.modules.postManeger.cost.AjaxReturn;
import com.thinkgem.jeesite.modules.postManeger.message.EmailMessageService;
import com.thinkgem.jeesite.modules.wsp.token.TokenService;
import com.thinkgem.jeesite.modules.wsp.user.entity.WUser;
import com.thinkgem.jeesite.modules.wsp.user.service.WUserService;

@Controller
@RequestMapping("${webPath}/system")
public class RegisterController extends MyBaseController {
	@Autowired
	private EmailMessageService emailService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private WUserService userService;
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Map<String, Object> register(RegisterEntity entity) {
		Map<String, Object> resultMap = new HashMap<>();
		//判断是否已经注册
		WUser users=new WUser();
		users.setEmail(entity.getEmail());
		List<WUser> users2=userService.findList(users);
		if(users2.size()>0) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG,"该用户已经注册了!");
			return resultMap;
		}
		//验证验证码
		String code=tokenService.getValidateCode(getSession().getId());
		if(StringUtils.isNotEmpty(entity.getValidateCode())) {
			if(StringUtils.isEmpty(code)||!code.equals(entity.getValidateCode())) {
				resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
				resultMap.put(AjaxReturn.MSG,"验证码出错或者超时!");
				return resultMap;
			}
		}
		//校验密码
		if(!entity.getPassword().equals(entity.getRepassword())) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG,"两次密码不相同!");
			return resultMap;
		}
		
		//注册成功
		WUser user=new WUser();
		String user_agent = UserAgentUtils.getBrowser(getRequest()).getName();
		String client = UserAgentUtils.getDeviceType(getRequest()).getName();
		user.setBrowserType(user_agent);
		user.setClient(client);
		user.setCreateDate(new Date());
		user.setEmail(entity.getEmail());
		user.setFirstIp(getRemortIP());
		user.setNickname(entity.getNickName());
		user.setPasswordMd5(Encodes.encodeBase64(Digests.md5(entity.getPassword().getBytes())));
		user.setPassword(entity.getPassword());
		user.setPhone(entity.getPhone());
		userService.save(user);
		resultMap.put(AjaxReturn.STATUS,AjaxReturn.SUCCESS);
		resultMap.put(AjaxReturn.MSG,"注册成功!");
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
	/*	int num=0;
		if(null!=getSession().getAttribute("codeNum")||(int)getSession().getAttribute("codeNum")>5) {
			resultMap.put(AjaxReturn.STATUS,AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "验证码请求次数超过限制!请30分钟之后再试");
		}*/
		emailService.sendRegister(true,email);
		emailService.getCode().setSessionId(getSession().getId());
		tokenService.save(emailService.getCode());
		resultMap.put(AjaxReturn.STATUS,AjaxReturn.SUCCESS);
		return resultMap;
	}
}
