package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.PositionLine;
import java.util.List;

public interface PositionLineMapper extends BaseMapper<PositionLine>{
    int delete(Long id);

    int insert(PositionLine record);

    List<PositionLine> getForList();

    PositionLine getForOne(Long id);

    int updateOnActive(PositionLine record);

    int update(PositionLine record);
    
    List<PositionLine> getList(PositionLine record);
    
    int deleteBySipUid(String sipUid);
}