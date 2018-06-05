package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.DeviceConfig;
import java.util.List;

public interface DeviceConfigMapper extends BaseMapper<DeviceConfig>{
    int delete(Long id);

    int insert(DeviceConfig record);

    List<DeviceConfig> getForList();

    DeviceConfig getForOne(Long id);

    int updateOnActive(DeviceConfig record);

    int update(DeviceConfig record);
}