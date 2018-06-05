package com.quhwa.scalehouse.service.scale.mapper;

import java.util.List;

import com.quhwa.scalehouse.service.scale.model.Pressure;

public interface PressureMapper extends BaseMapper<Pressure>{
	int delete(Integer id);

    int insert(Pressure record);

    int insertActive(Pressure record);

    Pressure getOne(Integer id);

    int updateActive(Pressure record);

    int update(Pressure record);
    
    int deleteByPersonId(Integer personId);
    
    List<Pressure> getList(Integer personId);
    
}