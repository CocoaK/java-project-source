package com.smarthome.socket.common.constant;

public class Constant {
	/**配件设备状态 ：无效*/
	public final static int ACCESSORIES_STATUS_INVALID = 0;
	
	/**配件设备状态 ：有效*/
	public final static int ACCESSORIES_STATUS_VALID = 1;
	
	/**配件设备状态 ：新增*/
	public final static int ACCESSORIES_STATUS_NEW = 2;
	
	/**控制命令开头：配件添加返回 */
	public final static String CMD_ACCESSORIES_ADD_PREFIX = "5b0400";
	
	/**控制命令开头：上传报警 */
	public final static String CMD_ALARM_ADD_PREFIX = "5b0410";
	
	/**控制命令开头：刷新配件 */
	public final static String CMD_ACCESSORIES_REFRESH_PREFIX = "5c0455";
	
	/**控制命令开头：长按清除配件 */
	public final static String CMD_ACCESSORIES_CLEAR_PREFIX = "5c0400";
	
	/**控制命令结尾 */
	public final static String CMD_ACCESSORIES_ADD_END = "88";
	
	/**报警类型 ：普通*/
	public final static String ALARM_TYPE_NORMAL = "1";
	
	/**报警类型 ：防拆*/
	public final static String ALARM_TYPE_REMOVE = "2";
	
	/**设防状态 ：设防*/
	public final static String DEFENCE_STATUS_SHEFANG = "0";
	
	/**设防状态 ：撤防*/
	public final static String DEFENCE_STATUS_CHEFANG = "1";
	
	/**设防状态 ：在家设防*/
	public final static String DEFENCE_STATUS_ZAIJIASHEFANG = "2";

	/**控制命令帧头：WIFI插座电源开关 */
	public final static String CMD_OUTLET_POWER_PREFIX = "6b0100";
	/**控制命令帧头：WIFI插座电源开关 */
	public final static String CMD_OUTLET_LIGHT_PREFIX = "6b0101";
	/**控制命令帧头：WIFI插座全开开关 */
	public final static String CMD_OUTLET_ALL_PREFIX = "6b0102";
	/**控制命令帧头：WIFI插座按键设置 */
	public final static String CMD_OUTLET_BUTTON_PREFIX = "6b0103";
	/**控制命令帧头：WIFI插座灯延时 */
	public final static String CMD_OUTLET_LIGHT_DELAY_PREFIX = "6b0104";
	/**控制命令帧头：WIFI插座定时预约 */
	public final static String CMD_OUTLET_TIMER_PREFIX = "6b0105";
}
