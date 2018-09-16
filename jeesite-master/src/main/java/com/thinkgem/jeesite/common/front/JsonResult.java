package com.thinkgem.jeesite.common.front;

import org.apache.poi.ss.formula.functions.T;

public class JsonResult<T> {
	private Long code;
	private T body;
	private String msg;

	public static <T> JsonResult<T> getDefault() {
		JsonResult<T> result = new JsonResult<T>();
		result.setCode(CodeConstant.NOMARL);
		result.setMsg("");
		return result;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
