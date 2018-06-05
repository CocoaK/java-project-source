package com.biencloud.smarthome.service.permissions.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 默认角色代码枚举类。
 * @author kouy
 * @since 1.0 2012-5-22
 */
public enum RoleCode {

	/**超级管理员 */
	SUPER_ADMIN("R000000001"),
	/**系统管理员*/
	SYS_ADMIN("R000000002"),
	/**物业管理员*/
	PROP_ADMIN("R000000003"),
	/**业主*/
	OWNER("R000000004");
	
	
	private String value;
	
	private RoleCode(String value){
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
	 * 获取所有枚举值。
	 * @return
	 */
	public static final List<String> getAllValues(){
		List<String> list = new ArrayList<String>();
		RoleCode[] roleCodes = values();
		if(roleCodes == null)
			return list;
		for (RoleCode rc : roleCodes)
			list.add(rc.getValue());

		return list;
	}
}
