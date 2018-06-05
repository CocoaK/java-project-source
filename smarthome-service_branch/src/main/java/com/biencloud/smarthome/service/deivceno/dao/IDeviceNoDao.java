package com.biencloud.smarthome.service.deivceno.dao;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.deivceno.model.DeviceNo;

/**
 * 设备管理数据访问对象接口。
 * @author dehua ye
 * @since 1.0 2012-5-11
 * @see IDao
 * @throws RuntimeException 如果操作执行失败
 */
public interface IDeviceNoDao extends IDao<DeviceNo, String> {
	public DeviceNo getDeviceNoByNo(String diveceNo);
}
