package org.wechat.feh.enums;

public enum WeChatResultCodeEnums {
	
	THE_SYSTEM_IS_BUSY(-1, "系统繁忙，此时请开发者稍候再试"),
	SUCCESS(0, "请求成功"),
	ERROR_APPSECRET(40001, "AppSecret错误或者AppSecret不属于这个公众号，请开发者确认AppSecret的正确性"),
	GRANT_TYPE_ISNOT_CLIENT_CREDENTIAL(40002, "请确保grant_type字段值为client_credential"),
	IP_NOTIN_WHITELIST(40164, "调用接口的IP地址不在白名单中，请在接口IP白名单中进行设置。（小程序及小游戏调用不要求IP地址在白名单内。）"),
	
	
	
	NOT_EXIST_RESULTCODE(-99999, "WeChat返回码未定义"),
	
	;
	
	private Integer code;
	private String msg;
	
	private WeChatResultCodeEnums(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public static WeChatResultCodeEnums findByCode(Integer code) {
		for (WeChatResultCodeEnums rce : WeChatResultCodeEnums.values()) {
			if(rce.getCode() == code) {
				return rce;
			}
		}
		return NOT_EXIST_RESULTCODE;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
	public String getName() {
		return this.name();
	}
	
}
