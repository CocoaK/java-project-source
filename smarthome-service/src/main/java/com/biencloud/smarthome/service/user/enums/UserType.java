package com.biencloud.smarthome.service.user.enums;

/**
 * 用户类型枚举类。
 * @author kouy
 * @since 1.0 2012-5-14
 */
public enum UserType {

	/**业主 */
	OWNER("01"),
	/**物业管理员*/
	PA("02"),
	/**系统管理员*/
	SA("03"),
	/**终端管理员*/
	CA("04");
	
	
	private String value;
	
	private UserType(String value){
		this.value = value;
	}
	
	/**
	 * 获取枚举值。
	 * @return
	 */
	public String getValue(){
		return value;
	}
}
