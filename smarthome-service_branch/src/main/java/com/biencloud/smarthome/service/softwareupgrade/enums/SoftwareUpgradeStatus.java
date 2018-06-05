package com.biencloud.smarthome.service.softwareupgrade.enums;

/**
 * 软件升级状态枚举类。
 * @author kouy
 * @since 1.0 2012-4-23
 */
public enum SoftwareUpgradeStatus {
	/**已申请未审核 */
	APPLIED("0"),
	/**已审核未发布*/
	APPROVED("1"),
	/**审核未通过*/
	REFUSED("2"),
	/**等待发布*/
	PUBLISHING("3"),
	/**已发布*/
	PUBLISHED("4"),
	/**已禁用*/
	DISABLED("5");
	
	private String value;
	
	private SoftwareUpgradeStatus(String value){
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
