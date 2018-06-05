package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.AlarmInfo;
import java.util.List;

public interface AlarmInfoMapper extends BaseMapper<AlarmInfo>{
    int delete(Long alarmId);

    int insert(AlarmInfo record);

    List<AlarmInfo> getForList();

    AlarmInfo getForOne(Long alarmId);

    int updateOnActive(AlarmInfo record);

    int update(AlarmInfo record);
    
    int getCount(AlarmInfo record);
}