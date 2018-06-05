package com.biencloud.smarthome.service.ad.dao;

import com.biencloud.smarthome.service.ad.model.Advertisement;
import com.biencloud.smarthome.service.base.dao.IDao;

/**
 * 广告投放管理数据访问对象接口。
 * @author kouy
 * @since 1.0 2012-5-29
 * @see IDao
 * @throws RuntimeException 如果操作执行失败
 */
public interface IAdDao extends IDao<Advertisement, String> {
	
	/**
	 * 删除指定设备编号列表关联的广告投放目标。
	 * @param deviceIds 设备编号列表
	 */
	public void removeAdTargetDevices(String deviceId);

}
