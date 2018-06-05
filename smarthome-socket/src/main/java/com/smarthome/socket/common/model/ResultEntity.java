package com.smarthome.socket.common.model;

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
    @Override
	public String toString(){
    	StringBuilder sb = new StringBuilder();
    	sb.append("[code:");
    	sb.append(code+",");
    	sb.append("message:");
    	sb.append(message+",");
    	sb.append("data:");
    	sb.append(data+"]");
		return sb.toString();
	}
	
}