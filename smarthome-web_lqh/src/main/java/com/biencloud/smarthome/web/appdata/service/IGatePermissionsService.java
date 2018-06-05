package com.biencloud.smarthome.web.appdata.service;

import com.biencloud.smarthome.web.appdata.json.GatePermissionsJson;

/**
 * 
 * 类名称：IGatePermissionsService 
 * 类描述： 门禁数据获取接口
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-16 下午3:33:10
 */
public interface IGatePermissionsService {

	/**
	 * 
	 * 方法的描述: 查询本终端所有的门禁数据
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-16 下午3:36:53
	 * @param deviceNo 设备编号
	 * @return
	 */
	public GatePermissionsJson queryAllGateDataByDeviceNo(String deviceNo);
	
}
