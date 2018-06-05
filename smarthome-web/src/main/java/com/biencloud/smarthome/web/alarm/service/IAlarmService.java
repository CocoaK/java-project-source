package com.biencloud.smarthome.web.alarm.service;

import java.util.List;

import com.biencloud.smarthome.web.alarm.vo.AlarmVO;
import com.biencloud.smarthome.web.appdata.json.Json;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.wsclient.stub.Alarm;

/**
 * 
 * 类名称：IAlarmService 
 * 类描述： 报警app接口
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-23 下午2:52:07
 */
public interface IAlarmService {
	
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
	public PagingVO<AlarmVO> queryAlarmVOForPaging(AlarmVO paramsOb, int pageNum, int pageSize,String userType,String userId,String groupNo);
	/**
	 * 
	 * 方法的描述: 获取单个报警信息对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:43:52
	 * @param entityId：报警信息对象主键
	 * @return
	 */
	public AlarmVO getAlarmVO(String entityId);
	/**
	 * 
	 * 方法的描述: 查询报警信息
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:53:10
	 * @param alarm
	 * @return List
	 */
	public List<AlarmVO> queryAlarms(AlarmVO alarm);
	
	/**
	 * 
	 * 方法的描述: 删除报警信息对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:44:16
	 * @param entity:报警信息对象
	 */
	public AlarmVO queryAlarmById(String id);
	
	/**
	 * 
	 * 方法的描述: 保存或修改报警信息
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:54:46
	 * @param alarm
	 */
	public void saveOrUpdateAlarm(AlarmVO alarm);
	
	/**
	 * 
	 * 方法的描述: 删除报警信息
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:55:44
	 * @param alarm
	 */
	public void removeAlarm(AlarmVO alarm);
	/**
	 * 
	 * 方法的描述: 删除报警信息对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:44:16
	 * @param entity:报警信息对象
	 */
	public void delAlarm(String id) ;
	/**
	 * 
	 * 方法的描述: 更新报警信息
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:43:23
	 * @param entity:报警信息对象
	 */
	public void updateAlarmStatus(String status,String id,String userId);
	/**
	 * 
	 * 方法的描述: 返回报警信息li列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:48:25
	 * @param paramsOb:报警信息对象
	 * @param groupNo:小区ID
	 * @param path：根路径
	 * @return
	 */
	//public String queryAlarmString(AlarmVO paramsOb,String groupNo,String path);
	/**
	 * 
	 * 方法的描述: 返回地图报警相关信息
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:48:54
	 * @param alarmId：报警记录主键
	 * @param groupNo：小区ID
	 * @return
	 */
	public Object[] queryAlarmStringForMap(String alarmId,String groupNo);
	/**
	 * 
	 * 方法的描述: 物业查询今日报警
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:50:21
	 * @param loginUserType：登录用户类型
	 * @param userId：用户ID
	 * @param districtId：小区ID
	 * @return
	 */
	public List<AlarmVO> queryAlarmVOForIndex(String loginUserType,String userId,String districtId);
	/**
	 * 
	 * 方法的描述: 查询业主总报警数
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:50:43
	 * @param loginUserType：登录用户类型
	 * @param userId：用户ID
	 * @param districtId：小区ID
	 * @return
	 */
	public Long getOwnerAlarmCount(String loginUserType,String userId,String districtId);
	/**
	 * 
	 * 方法的描述: 根据设备编号获取设备对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-20 上午10:26:36
	 * @param deviceNo
	 * @return
	 */
	public DeviceVO getDeviceVO(String deviceNo);
	
	/**
	 * 
	 * 方法的描述: 保存或修改报警信息
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-25 下午6:51:02
	 * @param jsonString
	 * @return
	 */
	public Json saveOrUpdateAlarm(String jsonString);
	/**
	 * 
	 * 方法的描述: 查询报警列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-16 下午3:25:11
	 * @param ob
	 * @return
	 */
	public List<Alarm> queryAlarmsList(Alarm ob);
	/**
	 * 
	 * 方法的描述: 获取楼栋全称
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-16 下午3:27:00
	 * @param dVo
	 * @return
	 */
	public String getHomeText(DeviceVO deviceVo);
	
}
