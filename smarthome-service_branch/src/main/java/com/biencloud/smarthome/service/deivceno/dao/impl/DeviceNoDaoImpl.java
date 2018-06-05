package com.biencloud.smarthome.service.deivceno.dao.impl;

import java.util.List;

import com.biencloud.smarthome.service.base.dao.impl.BaseDao;
import com.biencloud.smarthome.service.deivceno.dao.IDeviceNoDao;
import com.biencloud.smarthome.service.deivceno.model.DeviceNo;

/**
 * 设备管理数据访问对象，基于JPA实现。
 * @author dehua ye
 * @since 1.0 2012-5-11
 * @see IDeviceNoDao
 */
public class DeviceNoDaoImpl extends BaseDao<DeviceNo, String> implements IDeviceNoDao {

	@Override
	public DeviceNo getDeviceNoByNo(String deviceNo) {
		if(deviceNo!=null)
		{
			List<DeviceNo> list=super.find("from DeviceNo where deviceNo=?1", deviceNo);
			
			if(list!=null&&!list.isEmpty())
			{
				return list.get(0);
			}
		}
		return null;
	}

}
