package com.biencloud.smarthome.service.rest.mapper;

import java.util.List;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.Device;
import com.biencloud.smarthome.service.rest.model.DeviceUserVo;
import com.biencloud.smarthome.service.rest.model.UserDeviceKey;

public interface UserDeviceMapper extends BaseMapper<UserDeviceKey>{
    int delete(UserDeviceKey record);

    int insert(UserDeviceKey record);
    
    List<Device> getDeviceListByUserId(Long userId);
    
    List<UserDeviceKey> getList(UserDeviceKey record);
    
    List<DeviceUserVo> getDeviceUserList(UserDeviceKey record);
    
    List<DeviceUserVo> getUserListByDeviceId(UserDeviceKey record);

	List<Device> getDevicesByUserId(Long userId);
}