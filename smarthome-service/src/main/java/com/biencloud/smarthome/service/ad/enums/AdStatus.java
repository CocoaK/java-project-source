package com.biencloud.smarthome.service.ad.enums;

/**
 * 广告投放状态枚举类。
 * @author kouy
 * @since 1.0 2012-5-30
 */
public enum AdStatus {

	/**已申请 */
	APPLIED("0"),
	/**等待发布*/
	PUBLISHING("1"),
	/**已发布*/
	PUBLISHED("2"),
	/**已投放*/
	RUNNING("3"),
	/**已停止*/
	STOPPED("4");
	
	private String value;
	
	private AdStatus(String value){
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
