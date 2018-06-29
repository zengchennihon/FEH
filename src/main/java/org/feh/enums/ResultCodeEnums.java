package org.feh.enums;

public enum ResultCodeEnums {
	
	SUCCESS(0, "成功"),
	
	
	UNKNOW_ERROR(-999, "未知错误")
	
	;
	
	private Integer code;
	private String msg;
	
	ResultCodeEnums(Integer code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public static String findMsgByCode(Integer code) {
		for (ResultCodeEnums rce : ResultCodeEnums.values()) {
			if(rce.getCode() == code)
				return rce.getMsg();
		}
		return UNKNOW_ERROR.getMsg();
	}
	
	public static ResultCodeEnums findByCode(Integer code) {
		for (ResultCodeEnums rce : ResultCodeEnums.values()) {
			if(rce.getCode() == code)
				return rce;
		}
		return UNKNOW_ERROR;
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
