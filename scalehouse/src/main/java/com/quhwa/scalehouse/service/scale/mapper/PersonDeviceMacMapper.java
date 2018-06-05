package com.quhwa.scalehouse.service.scale.mapper;

import java.util.List;

import com.quhwa.scalehouse.service.scale.model.PersonDeviceMac;

public interface PersonDeviceMacMapper extends BaseMapper<PersonDeviceMac>{
    int delete(Integer id);

    int insert(PersonDeviceMac record);

    int insertActive(PersonDeviceMac record);

    PersonDeviceMac getOne(Integer id);

    int updateActive(PersonDeviceMac record);

    int update(PersonDeviceMac record);
    
    List<PersonDeviceMac> getForList(PersonDeviceMac personDeviceMac);
    
    int deleteForList(PersonDeviceMac p);
}