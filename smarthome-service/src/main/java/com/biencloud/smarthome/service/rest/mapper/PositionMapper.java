package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.Position;
import java.util.List;

public interface PositionMapper extends BaseMapper<Position>{
    int delete(Long id);

    int insert(Position record);

    List<Position> getForList();

    Position getForOne(Long id);

    int updateOnActive(Position record);

    int update(Position record);
}