package com.biencloud.smarthome.service.housemgr.enums;

/**
 * 房产管理操作结果枚举类。
 * @author kouy
 * @since 1.0 2012-8-13
 */
public enum Result {

	/**成功 */
	SUCCESS(0),
	/**失败：存在关系数据不允许删除 */
	FAIL_REMOVE(1),
	/**失败：系统异常 */
	SYS_EX(-1);
	
	private int value;
	
	private Result(int value){
		this.value = value;
	}
	
	/**
	 * 获取枚举值。
	 * @return
	 */
	public int getValue(){
		return value;
	}
}
