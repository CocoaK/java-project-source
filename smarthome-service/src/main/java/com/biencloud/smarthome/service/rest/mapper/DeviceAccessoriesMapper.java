package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.DeviceAccessories;

public interface DeviceAccessoriesMapper extends BaseMapper<DeviceAccessories>{
    int delete(Long id);

    int insert(DeviceAccessories record);

    //List<DeviceAccessories> getForList(DeviceAccessories record);

    DeviceAccessories getForOne(Long id);

    int updateOnActive(DeviceAccessories record);

    int update(DeviceAccessories record);
    
    int deleteByEntity(DeviceAccessories record);
    
    /**
     * 根据主设备编号，将状态为2的值修改为1.
     * @return
     */
    int updateNewStatus(Long deviceId);
}