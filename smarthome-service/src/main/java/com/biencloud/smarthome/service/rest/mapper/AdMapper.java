package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.Ad;
import java.util.List;

public interface AdMapper extends BaseMapper<Ad>{
    int delete(Integer adId);

    int insert(Ad record);

    List<Ad> getForList();

    Ad getForOne(Integer adId);

    int updateOnActive(Ad record);

    int update(Ad record);
    
    List<Ad> getListByEntity(Ad record);
}