package com.quhwa.scalehouse.service.scale.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.quhwa.scalehouse.service.scale.model.BodyInfo;

public interface BodyInfoMapper extends BaseMapper<BodyInfo>{
    int delete(Integer id);
    
    int deleteByPersonId(Integer personId);
    
    int insert(BodyInfo record);

    int insertActive(BodyInfo record);

    BodyInfo getOne(Integer id);

    int updateActive(BodyInfo record);

    int update(BodyInfo record);
    
    List<BodyInfo> getList(Integer personId);
    
    List<BodyInfo> getTodayData(Integer personId);
    
    List<BodyInfo> getSevenDaysData(Integer personId);
    
    List<BodyInfo> getCurrentMonthData(Integer personId);
    
    List<BodyInfo> getByTimesolt(Map<String, Object> map);
    
    List<BodyInfo> getByPersonId(Integer personId);
    
    List<BodyInfo> queryByWeight(Double weight);
    
    List<BodyInfo> queryByHighWeight(Double weight);
    
    List<BodyInfo> getOtherdayData(@Param(value="recordTime") Date recordTime,@Param(value="personId") Integer personId);
    
    Long getTotal(Integer personId);
    
}