package com.biencloud.smarthome.web.alarm.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 
 * 类名称：AlarmTypeVO 
 * 类描述： 报警类型VO
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-23 下午3:06:08
 */
public class AlarmTypeVO extends BaseVO{

	private static final long serialVersionUID = 8680421771917645904L;
	private String alarmType;//报警类型
	private String alarmName;//报警名称
	private String alarmDesc;//报警描述
	public String getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public String getAlarmName() {
		return alarmName;
	}
	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}
	public String getAlarmDesc() {
		return alarmDesc;
	}
	public void setAlarmDesc(String alarmDesc) {
		this.alarmDesc = alarmDesc;
	}
	
	
}
