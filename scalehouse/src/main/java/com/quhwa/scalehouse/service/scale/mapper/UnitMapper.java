package com.quhwa.scalehouse.service.scale.mapper;

import org.apache.ibatis.annotations.Param;

import com.quhwa.scalehouse.service.scale.model.Unit;

public interface UnitMapper {
    int delete(@Param("value") String value, @Param("type") String type);

    int insert(Unit record);

    int insertActive(Unit record);

    Unit getOne(@Param("value") String value, @Param("type") String type);

    int updateActive(Unit record);

    int update(Unit record);
}