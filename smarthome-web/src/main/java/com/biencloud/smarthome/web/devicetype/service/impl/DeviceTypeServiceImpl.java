package com.biencloud.smarthome.web.devicetype.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.devicetype.service.IDeviceTypeService;
import com.biencloud.smarthome.web.devicetype.vo.DeviceTypeVO;
import com.biencloud.smarthome.web.wsclient.stub.DeviceType;

/**
 * 设备类型调用服务实现类。
 * @author kouy
 * @since 1.0 2012-5-3
 */
public class DeviceTypeServiceImpl extends BaseService<DeviceTypeVO> implements IDeviceTypeService {

	@Override
	public List<DeviceTypeVO> queryAllDeviceTypes() {
		List<DeviceType> list = getSmartHomeService().queryAllDeviceTypes();
		List<DeviceTypeVO> deviceTypes = new ArrayList<DeviceTypeVO>();
		if(list != null && list.size() > 0){
			for (int index = 0, size = list.size(); index < size; index++) {
				deviceTypes.add(new DeviceTypeVO());
			}
		}
		copyList(list, deviceTypes, true);
		
		return deviceTypes;
	}

}
