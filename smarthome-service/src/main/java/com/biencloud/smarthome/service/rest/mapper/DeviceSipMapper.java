package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.DeviceSip;
import java.util.List;

public interface DeviceSipMapper extends BaseMapper<DeviceSip>{
    int delete(Long id);

    int insert(DeviceSip record);

    List<DeviceSip> getForList();
    
    List<DeviceSip> getList(DeviceSip record);

    DeviceSip getForOne(Long id);

    int updateOnActive(DeviceSip record);

    int update(DeviceSip record);
    
    int deleteByDeviceId(String deviceId);
}