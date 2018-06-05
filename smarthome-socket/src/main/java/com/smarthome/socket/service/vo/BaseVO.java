package com.smarthome.socket.service.vo;

/**
 * 
 * 类名称：BaseVO 类描述：基类值对象
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-9 下午9:53:18
 */
public class BaseVO {
	
	/**成功*/
	public static final String SUCCESS = "1";
	
	/**失败*/
	public static final String FAILED = "0";
	
	// json数据类型
	protected String jsonType;	
	//返回结果
	protected String code;	

	public String getJsonType() {
		return jsonType;
	}

	public void setJsonType(String jsonType) {
		this.jsonType = jsonType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}	

}
