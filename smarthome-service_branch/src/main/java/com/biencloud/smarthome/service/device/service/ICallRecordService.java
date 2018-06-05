package com.biencloud.smarthome.service.device.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.device.model.CallRecord;

/**
 * 类名称：ICallRecordService 
 * 类描述： 设备通话记录领域服务接口。
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-15 下午1:57:02
 */
public interface ICallRecordService extends IService<CallRecord,String>{

	/**
	 * 方法的描述: 根据ID查询CallRecord
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-27 下午1:57:21
	 * @param id
	 * @return
	 */
	public CallRecord getCallRecordById(String id);
	
	/**
	 * 方法的描述: 根据条件查询留言
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-27 下午1:57:44
	 * @param callRecord
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Paging<CallRecord> queryCallRecordForPaging(CallRecord callRecord, int pageNum,int pageSize);
	
	/**
	 * 方法的描述: 保存通话记录
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-27 下午1:57:59
	 * @param callRecord
	 */
	public void saveCallRecord(CallRecord callRecord);
	
	/**
	 * 
	 * 方法的描述: 保存通话记录
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-21 下午4:06:22
	 * @param callRecord 通话记录实体对象
	 */
	public void removeCallRecord(CallRecord callRecord);

	/**
	 * 
	 * 方法的描述: 查询通话记录
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-21 下午4:06:22
	 * @param callRecord
	 * @return List<callRecord> 
	 */
	public List<CallRecord> queryCallRecords(CallRecord callRecord);
	
	/**
	 * 
	 * 方法的描述: 查询留言记录数
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-13 上午11:21:28
	 * @param callRecord
	 * @return Long
	 */
	public Long callRecordCount(CallRecord callRecord);
	
	/**
	 * 
	 * 方法的描述: 根据设备Id删除
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-6 下午3:59:56
	 * @param deviceId
	 */
	public void removeByDeviceId(String deviceId);
	
}
