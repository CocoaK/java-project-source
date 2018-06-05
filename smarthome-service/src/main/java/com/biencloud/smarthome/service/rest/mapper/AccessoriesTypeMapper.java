package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.AccessoriesType;
import java.util.List;

public interface AccessoriesTypeMapper extends BaseMapper<AccessoriesType>{
    int delete(Long accessoriesType);

    int insert(AccessoriesType record);

    List<AccessoriesType> getForList(AccessoriesType accessoriesType);

    AccessoriesType getForOne(Long accessoriesType);

    int updateOnActive(AccessoriesType record);

    int update(AccessoriesType record);
}