package com.thinkgem.jeesite.modules.postManeger.cost;

public interface TokenType {
	String BAIDU = "BAIDU";
	String REG_VALIDATE="REG_CODE";
	long REG_EXPIRED=2*60;
	long SESSION_EXPIRED=30*60;//session expired
}
