package com.biencloud.smarthome.service.alarm.service;

import java.util.List;

import com.biencloud.smarthome.service.alarm.model.AlarmType;
import com.biencloud.smarthome.service.base.service.IService;

/**
 * 
 * 类名称：IAlarmTypeService 
 * 类描述： 报警类型服务接口
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-23 上午11:47:38
 */
public interface IAlarmTypeService extends IService<AlarmType,String> {
	
	/**
	 * 
	 * 方法的描述: 查询报警类型
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-23 上午11:49:03
	 * @param alarmType
	 * @return List
	 */
	public List<AlarmType> queryAlarmType(AlarmType alarmType);

}
