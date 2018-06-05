package com.biencloud.smarthome.service.device.dao.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.dao.impl.BaseDao;
import com.biencloud.smarthome.service.device.dao.ICallRecordDao;
import com.biencloud.smarthome.service.device.model.CallRecord;

/**
 * 类名称：CallRecordDaoJpa 
 * 类描述： 通话记录数据访问对象JPA实现
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-15 上午10:34:08
 */
@Transactional(propagation=Propagation.REQUIRED)
public class CallRecordDaoJpa extends BaseDao<CallRecord,String> implements ICallRecordDao{

	@Override
	public void removeByDeviceId(String deviceId) {
		if(deviceId== null){
			return;
		}
		String removeString = "DELETE FROM CallRecord WHERE device.deviceId=?1";
		String queryString = "SELECT COUNT(callRecord) FROM CallRecord callRecord WHERE device.deviceId=?1";
		Long count= super.getTotalCount(queryString, deviceId);
		if(count<1)
			return;
		super.removeByParams(removeString, deviceId);
		
	}
	
	
}
