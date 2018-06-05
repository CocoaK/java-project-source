package com.biencloud.smarthome.web.monitor.service.impl;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.monitor.service.IMonitorService;

/**
 * 远程监控Sevice实现类
 * 
 * @author jsun  
 * @since 1.0 2012-6-5
 */
public class MonitorServiceImpl extends BaseService<DeviceVO> implements IMonitorService {
	@Override
	public boolean remoteUnlock(String targetDeviceCode, String personalDeviceCode,
			String personalDevicePwd,String userType) {
		return getSmartHomeService().remoteUnlock(targetDeviceCode, personalDeviceCode,
				personalDevicePwd,userType);
	}
}
