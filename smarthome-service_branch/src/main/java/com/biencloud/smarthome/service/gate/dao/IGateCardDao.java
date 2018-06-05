package com.biencloud.smarthome.service.gate.dao;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.gate.model.GateCard;

/**
 * 门禁管理数据访问对象接口。
 * @author kouy
 * @since 1.0 2012-5-4
 * @see IDao
 * @throws RuntimeException 如果操作执行失败
 */
public interface IGateCardDao extends IDao<GateCard, String> {
	
	/**
	 * 删除指定设备编号列表关联的门禁权限。
	 * @param deviceId 设备编号
	 */
	public void removeGatePermissionsDevices(String deviceId);

}
