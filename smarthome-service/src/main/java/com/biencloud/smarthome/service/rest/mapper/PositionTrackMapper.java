package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.PositionTrack;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PositionTrackMapper extends BaseMapper<PositionTrack>{
    int delete(Long id);

    int insert(PositionTrack record);

    List<PositionTrack> getForList();

    PositionTrack getForOne(Long id);

    int updateOnActive(PositionTrack record);

    int update(PositionTrack record);
    
    List<PositionTrack> getList(@Param("pos") PositionTrack record,@Param("beginTime") Date beginTime,@Param("endTime") Date endTime);
}