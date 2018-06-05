package com.biencloud.smarthome.web.alarm.service;

import java.util.List;

import com.biencloud.smarthome.web.alarm.vo.AlarmTypeVO;

/**
 * 
 * 类名称：IAlarmTypeService 
 * 类描述：报警类型APP调用服务接口 
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-23 下午2:58:27
 */
public interface IAlarmTypeService {
	
	/**
	 * 
	 * 方法的描述: 查询报警类型
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-23 下午3:08:29
	 * @param alarmType
	 * @return List
	 */
	public List<AlarmTypeVO> queryAlarmTypes(AlarmTypeVO alarmTypeVO);
	
	/**
	 * 
	 * 方法的描述: 查询报警类型
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-23 下午3:12:36
	 * @param String alarmType
	 * @return AlarmTypeVO
	 */
	public AlarmTypeVO queryAlarmTypeById(String alarmType);
	
	/**
	 * 
	 * 方法的描述: 保存或者修改报警类型
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-23 下午3:13:05
	 * @param alarmTypeVO
	 */
	public void saveOrUpdateAlarmType(AlarmTypeVO alarmTypeVO);
	
	/**
	 * 
	 * 方法的描述: 删除报警类型
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-23 下午3:13:45
	 * @param alarmTypeVO
	 */
	public void removeAlarmType(AlarmTypeVO alarmTypeVO);
}
