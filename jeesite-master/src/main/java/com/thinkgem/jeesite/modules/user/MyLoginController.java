package com.thinkgem.jeesite.modules.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.security.Digests;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.modules.postManeger.controller.MyBaseController;
import com.thinkgem.jeesite.modules.postManeger.cost.AjaxReturn;
import com.thinkgem.jeesite.modules.postManeger.cost.TokenType;
import com.thinkgem.jeesite.modules.postManeger.cost.UserType;
import com.thinkgem.jeesite.modules.wsp.session.entity.WSession;
import com.thinkgem.jeesite.modules.wsp.session.service.WSessionService;
import com.thinkgem.jeesite.modules.wsp.user.entity.WUser;
import com.thinkgem.jeesite.modules.wsp.user.service.WUserService;
@Controller
@RequestMapping("${webPath}/system")
public class MyLoginController extends MyBaseController{
	@Autowired
	private WUserService userService;
	
	@Autowired
	private WSessionService sessionService;
	
   @RequestMapping("/login")
   public String login() {
	   return "modules/system/login";
   }
   @ResponseBody
   @RequestMapping("/tryLogin")
   public Map<String,Object> tryLogin(String username,String password){
		Map<String, Object> resultMap = new HashMap<>();
		if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)) {
			resultMap.put(AjaxReturn.STATUS,AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG,"请输入用户名或者密码！");
			return resultMap;
		}
		WUser user=userService.findByEmail(username);
		if(null!=user) {
			String pass=Encodes.encodeBase64(Digests.md5(password.getBytes()));
			//checkpassword
			if(user.getPasswordMd5().equals(pass)){
				WSession session=new WSession();
				session.setCreateDate(new Date(System.currentTimeMillis()));
				session.setExpired(TokenType.SESSION_EXPIRED);
				session.setSessionId(getSession().getId());
				session.setUser(user);
				sessionService.save(session);
				resultMap.put(AjaxReturn.STATUS,AjaxReturn.SUCCESS);
				resultMap.put(AjaxReturn.MSG,"登录成功!");
				return resultMap;
			}else {
				resultMap.put(AjaxReturn.STATUS,AjaxReturn.ERROR);
				resultMap.put(AjaxReturn.MSG,"密码出错!");
				return resultMap;
			}
		}else {
			resultMap.put(AjaxReturn.STATUS,AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG,"用户不存在!");
			return resultMap;
		}
   }
   @ResponseBody
   @RequestMapping("/isLogin")
   public Map<String,Object> isLogin(){
		Map<String, Object> resultMap = new HashMap<>();
		WSession session=sessionService.getCurrentUser(getSession().getId());
		if(null==session||null==session.getUser()) {
			resultMap.put(AjaxReturn.STATUS,AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG,"请先登入");
		}else {
			resultMap.put(AjaxReturn.STATUS,AjaxReturn.SUCCESS);
		}
		return resultMap;
   }
}
