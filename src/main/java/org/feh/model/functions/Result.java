package org.feh.model.functions;

import org.feh.enums.ResultCodeEnums;

public class Result {

	private Integer code;

	private Object data;

	private String msg;
	
	public static Result result;
	
	static {
		if (result == null) {
			result = new Result();
			result.setCode(0);
		}
	}
	
	public static Result result(Object data) {
		result.setCode(0);
		if(data != null) {
			result.setData(data);
		}
		return result;
	}
	
	public static Result result(Integer code, Object data) {
		result.setCode(code);
		if(data != null) {
			result.setData(data);
		}
		return result;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
		this.msg = ResultCodeEnums.findMsgByCode(code);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
