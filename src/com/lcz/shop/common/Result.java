package com.lcz.shop.common;

public class Result extends BaseResult {

	private static final long serialVersionUID = 1L;

	public Result(ReturnCode returnCode) {
        super(returnCode.getCode(), returnCode.getMessage());
	}
	
	public Result(ReturnCode returnCode, Object data) {
		super(returnCode.getCode(), returnCode.getMessage(), data);
	}
	
	public Result(Integer code, String message) {
		super(code, message);
	}
}
