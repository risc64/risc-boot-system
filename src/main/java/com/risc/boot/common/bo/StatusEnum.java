package com.risc.boot.common.bo;

public enum StatusEnum {
	
	OK("成功", 0),
	ERROR("失败", 1),
	NOT_DATA("没有数据", 2),
	EXCEPTION("异常", 3),
	PERMISSION_DENIED("没有权限", 4),
	PARAM_ERROR("参数错误", 5),
	CAPTCHA_CODE_ERROR("验证码错误", 5),
	OVERDUEL("未登录或登录已过期", 7);

	private String msg;
	private int key;
	
	StatusEnum(String msg, int key) {
		this.msg = msg;
		this.key = key;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public int getKey() {
		return key;
	}
}
