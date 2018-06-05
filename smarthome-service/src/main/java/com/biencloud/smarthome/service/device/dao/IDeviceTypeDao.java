package com.biencloud.smarthome.service.device.dao;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.device.model.DeviceType;

/**
 * 设备类型数据访问对象接口。
 * @author kouy
 * @since 1.0 2012-5-3
 * @see IDao
 * @throws RuntimeException 如果操作执行失败
 */
public interface IDeviceTypeDao extends IDao<DeviceType, String> {

}
