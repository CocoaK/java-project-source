package com.biencloud.smarthome.web.common.vo;

import java.io.Serializable;


/**
 * 结果封装实体
 */
 
public class ResultEntity<T> implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2598279955673063081L;
	public static final int SUCCESS = 1;
	public static final int FAILD = 0;
	public static final String MESSAGE_SUCCESS = "SUCESS";
	/** 返回代码：密码错误 */
	public static final int PASSWD_ERROR = 5;
	/** 返回代码：不存在*/
	public static final int NOT_EXIST = 6;
	/** 返回代码：已存在*/
	public static final int ALREADY_EXIST = 7;
	/** 返回代码：手机号已存在*/
	public static final int MOBILE_ALREADY_EXIST = 13;
	
	private int code;
    private String message;
    private T data;
    
    public ResultEntity(){
    }

	public ResultEntity(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
    
	public void setContent(int code, String message, T data){
		this.code = code;
		this.message = message;
		this.data = data;
	}
    
	
}