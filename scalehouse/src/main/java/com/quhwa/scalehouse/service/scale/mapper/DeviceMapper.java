package com.quhwa.scalehouse.service.scale.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quhwa.scalehouse.service.scale.model.Device;
import com.quhwa.scalehouse.service.scale.model.Product;

public interface DeviceMapper extends BaseMapper<Device>{
    int delete(Integer id);

    int insert(Device record);

    int insertActive(Device record);

    Device getOne(Integer id);

    int updateActive(Device record);

    int update(Device record);
    
    List<Device> queryForList(Device record);
    
    List<Device> queryByMac(String mac);
    
    Long getTotal(Device record);
    
    Long getNewTotal(@Param(value="countryCode") String countryCode,@Param(value="type") String type);
    
    List<Device> getAll(@Param(value="countryCode") String countryCode,@Param(value="type") String type);
    
    List<Product> getProduct();
    
    Long todayActiveDevN(@Param(value="countryCode") String countryCode,@Param(value="type") String type);
    
    Long getSum(@Param(value="time") String time,@Param(value="countryCode") String countryCode,@Param(value="type") String type);
    
    Long getDevices(@Param(value="time") String time,@Param(value="countryCode") String countryCode,@Param(value="type") String type);
}