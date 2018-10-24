/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.interceptor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.support.json.JSONUtils;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.UserAgentUtils;
import com.thinkgem.jeesite.modules.postManeger.cost.AjaxReturn;
import com.thinkgem.jeesite.modules.wsp.session.entity.WSession;
import com.thinkgem.jeesite.modules.wsp.session.service.WSessionService;

import oracle.jdbc.driver.DatabaseError;

/**
 * 手机端视图拦截器
 * 
 * @author ThinkGem
 * @version 2014-9-1
 */
public class SessionInterceptor extends BaseService implements HandlerInterceptor {
	@Autowired
	private WSessionService sessionService;

	@Transactional(readOnly = false)
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();
		if (url.indexOf("login") > 0 || url.indexOf("menuJson") > 0 || url.indexOf("goRegister") > 0
				|| url.indexOf("tryLogin") > 0 || url.indexOf("isLogin") > 0) {
			return true;
		} else {
			Map<String, Object> resultMap = new HashMap<>();
			// 检测session
			WSession session = sessionService.getCurrentUser(request.getSession().getId());
			if (null == session || null == session.getUser()) {
				Class handlerc = handler.getClass();
				String requestType = request.getHeader("X-Requested-With");
				if ("XMLHttpRequest".equals(requestType)) {
					resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
					resultMap.put(AjaxReturn.MSG, "请先登入");
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().write(JSONUtils.toJSONString(resultMap));
					response.getWriter().flush();
					return false;
				} else {
					response.sendRedirect(request.getContextPath() + Global.getWebBasePath() + "/system/login");
				}
			} else {
				session.setCreateDate(new Date(System.currentTimeMillis()));
				sessionService.save(session);
				return true;
			}
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
