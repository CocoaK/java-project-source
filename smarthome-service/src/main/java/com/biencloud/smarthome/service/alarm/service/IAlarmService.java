package com.biencloud.smarthome.service.alarm.service;

import java.util.List;

import com.biencloud.smarthome.service.alarm.model.Alarm;
import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
/**
 * 
 * 类名称：IAlarmService 
 * 类描述： 报警领域服务接口
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-23 上午11:40:27
 */
public interface IAlarmService extends IService<Alarm,String>{
	/**
	 * 
	 * 方法的描述: 查询报警信息
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-23 上午11:43:27
	 * @param alarm
	 * @return List
	 */
	public List<Alarm> queryAlarms(Alarm alarm);
	
	/**
	 * 
	 * 方法的描述: 查询报警信息列表(分页)
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:42:51
	 * @param paramsOb:报警信息对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public Paging<Alarm> queryAlarmForPaging(Alarm paramsOb, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述: 更新报警信息
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:43:23
	 * @param entity:报警信息对象
	 */
	public void updateAlarm(Alarm entity) ;
	/**
	 * 
	 * 方法的描述: 保存报警信息
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:43:39
	 * @param entity：报警信息对象
	 */
	public void saveAlarm(Alarm entity);
	/**
	 * 
	 * 方法的描述: 获取单个报警信息对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:43:52
	 * @param entityId：报警信息对象主键
	 * @return
	 */
	public Alarm getAlarm(String entityId);
	/**
	 * 
	 * 方法的描述: 删除报警信息对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:44:16
	 * @param entity:报警信息对象
	 */
	public void DelAlarm(Alarm entity);
	/**
	 * 
	 * 方法的描述: 查询业主总报警数
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:45:53
	 * @param paramsOb:报警信息对象
	 * @return
	 */
	public Long getOwnerAlarmCount(Alarm paramsOb);

}
