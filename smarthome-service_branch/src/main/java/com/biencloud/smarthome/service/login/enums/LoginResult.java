package com.biencloud.smarthome.service.login.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录结果枚举类。
 * @author kouy
 * @since 1.0 2012-5-24
 */
public enum LoginResult {

	/**登录成功 */
	SUCCESS("00"),
	/**帐号不存在*/
	ACC_ERROR("01"),
	/**密码错误*/
	PASS_ERROR("02"),
	/**帐号已禁用*/
	DISABLED("03"),
	/**帐号已锁定*/
	LOCKED("04"),
	/**帐号已删除*/
	REMOVED("05"),
	/**帐号已启用*/
	ENABLED("06");
	
	private static final Map<String, LoginResult> RESULT_MAPPINGS;
	static{
		RESULT_MAPPINGS = new HashMap<String, LoginResult>();
		RESULT_MAPPINGS.put("1", DISABLED);
		RESULT_MAPPINGS.put("2", LOCKED);
		RESULT_MAPPINGS.put("3", REMOVED);
		RESULT_MAPPINGS.put("0", ENABLED);	
	}
	
	private String value;
	
	private LoginResult(String value){
		this.value = value;
	}
	
	/**
	 * 获取枚举值。
	 * @return
	 */
	public String getValue(){
		return value;
	}
	
	/**
	 * 将帐号状态转换为对应的结果。
	 * @param status 帐号状态
	 * @return
	 * @throws IllegalArgumentException 当帐号状态无效时
	 */
	public static LoginResult convertToResult(String status){
		if(!RESULT_MAPPINGS.containsKey(status))
			throw new IllegalArgumentException("The status(" + status + ") is invalid!");
		
		return RESULT_MAPPINGS.get(status);
	}
}
