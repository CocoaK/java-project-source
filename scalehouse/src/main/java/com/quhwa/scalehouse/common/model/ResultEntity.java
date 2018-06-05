package com.quhwa.scalehouse.common.model;

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
	public static final String MESSAGE_SUCCESS = "SUCCESS";
	public static final String MESSAGE_FAILED = "FAILED";
	/** 返回代码：密码错误 */
	public static final int PASSWD_ERROR = 5;
	/** 返回代码：不存在*/
	public static final int NOT_EXIST = 6;
	/** 返回代码：已存在*/
	public static final int ALREADY_EXIST = 7;
	/** 返回代码：用户名或者密码错误*/
	public static final int ACCOUNT_OR_PASSWD_ERROR = 8;
	/** 返回代码：结果为空*/
	public static final int RESULT_IS_NULL = 9;
	/** 返回代码：输入值为空*/
	public static final int INPUT_IS_NULL = 10;
	/** 返回代码：用户名或密码为空*/
	public static final int ACCOUNT_OR_PASSWD_IS_NULL = 11;
	/** 返回代码：用户名不存在*/
	public static final int ACCOUNT_NOT_EXIST = 12;
	/** 返回代码：手机号已存在*/
	public static final int MOBILE_ALREADY_EXIST = 13;
	
	private int code;
    private String message;
    private T data;
    private Long total;
    private T rows;
    
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
	
	public Long getTotal(){
		return total;
	}
	
	public void setTotal(Long total){
		this.total=total;
	}
	
	public T getRows(){
		return rows;
	}
	
	public void setRows(T rows){
		this.rows=rows;
	}
    
	public void setContent(int code, String message, T data){
		this.code = code;
		this.message = message;
		this.data = data;
	}
    
}