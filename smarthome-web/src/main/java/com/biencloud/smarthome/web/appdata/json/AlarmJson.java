package com.biencloud.smarthome.web.appdata.json;

import java.util.List;

import com.biencloud.smarthome.web.alarm.vo.AlarmVO;

/**
 * 
 * 类名称：AlarmJson 
 * 类描述： 报警Json
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-25 下午5:59:14
 */
public class AlarmJson extends Json{
	
	private List<AlarmVO> alarmList;

	public List<AlarmVO> getAlarmList() {
		return alarmList;
	}

	public void setAlarmList(List<AlarmVO> alarmList) {
		this.alarmList = alarmList;
	}
	
}
