package com.thinkgem.jeesite.modules.postManeger.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.thinkgem.jeesite.modules.wsp.user.entity.WUser;

public class MyBaseController {
	/**
	 * 管理基础路径
	 */
	@Value("${webPath}")
	protected String webPath;
	
	public static final String WEB_USER="WEB_USER";
	
	  public  HttpServletRequest getRequest(){
	        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	        return req;
	    }
	  /**
	   * 方法暂时出错
	   * @return
	   */
	    public HttpServletResponse getResponse(){
	        HttpServletResponse resp = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
	        return resp;

	    }
	    public HttpSession getSession() {
	    	return getRequest().getSession();
	    }
	    public WUser getCurrentUser() {
	    	return (WUser) getRequest().getSession().getAttribute(WEB_USER);
	    }
	    public String getCurrentUserId() {
	         return	getSession().getId();
	    }
	    public String getRemortIP() {
	    	  if (getRequest().getHeader("x-forwarded-for") == null) {
	    	   return getRequest().getRemoteAddr();
	    	  }
	    	  return getRequest().getHeader("x-forwarded-for");
	    	}
}
