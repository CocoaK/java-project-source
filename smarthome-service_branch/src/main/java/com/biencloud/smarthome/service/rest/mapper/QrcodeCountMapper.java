package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.QrcodeCount;

import java.util.List;

public interface QrcodeCountMapper extends BaseMapper<QrcodeCount>{
    int delete(Long id);

    int insert(QrcodeCount record);

    List<QrcodeCount> getForList();

    QrcodeCount getForOne(Long id);

    int updateOnActive(QrcodeCount record);

    int update(QrcodeCount record);
    
    QrcodeCount getByHouseId(String houseId);
}