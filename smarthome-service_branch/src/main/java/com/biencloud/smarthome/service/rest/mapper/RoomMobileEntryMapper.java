package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.RoomMobileEntry;

import java.util.List;

public interface RoomMobileEntryMapper extends BaseMapper<RoomMobileEntry>{
    int delete(Long id);

    int insert(RoomMobileEntry record);

    List<RoomMobileEntry> getForList();

    RoomMobileEntry getForOne(Long id);

    int updateOnActive(RoomMobileEntry record);

    int update(RoomMobileEntry record);
    
    List<RoomMobileEntry> getList(RoomMobileEntry record);

	List<RoomMobileEntry> getListForEntity(RoomMobileEntry obj);

}