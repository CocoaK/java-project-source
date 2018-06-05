package com.quhwa.scalehouse.service.scale.mapper;

import java.util.List;

import com.quhwa.scalehouse.service.scale.model.Background;

public interface BackgroundMapper extends BaseMapper<Background>{
    int delete(Integer id);

    int insert(Background record);

    int insertActive(Background record);

    Background getOne(Integer id);

    int updateActive(Background record);

    int update(Background record);
    
    List<Background> queryByUserAndPassword(Background record);
}