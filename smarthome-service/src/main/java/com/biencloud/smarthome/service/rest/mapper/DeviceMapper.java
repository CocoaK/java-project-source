package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.Device;
import com.biencloud.smarthome.service.rest.model.DeviceJson;

import java.util.List;

public interface DeviceMapper extends BaseMapper<Device>{
    int delete(Long deviceId);

    int insert(Device record);

    List<Device> getForList(Device record);

    Device getForOne(Long deviceId);
    
    Device getDeviceByDeviceNo(String deviceNo);

    int updateOnActive(Device record);

    int update(Device record);

	int updateAllDeviceStatus(String status);
	
	List<DeviceJson> getSimpleList(Device record);
}