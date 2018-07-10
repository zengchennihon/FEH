package org.feh.model.functions;

import org.feh.enums.ResultCodeEnums;

public class ResultModel {

	private Integer code;

	private Object data;

	private String msg;

	private Integer curPage;

	private Long total;

	public static ResultModel result(Object data) {
		ResultModel result = new ResultModel();
		result.setCode(0);
		if (data != null) {
			result.setData(data);
		}
		return result;
	}

	public static ResultModel result(Object data, Integer curPage, Long total) {
		ResultModel result = new ResultModel();
		result.setCode(0);
		result.setCurPage(curPage);
		result.setTotal(total);
		if (data != null) {
			result.setData(data);
		}
		return result;
	}

	public static ResultModel result(Integer code, Object data) {
		ResultModel result = new ResultModel();
		result.setCode(code);
		if (data != null) {
			result.setData(data);
		}
		return result;
	}

	public static ResultModel result() {
		ResultModel result = new ResultModel();
		return result;
	}

	public ResultModel() {
		this.setCode(0);
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

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
