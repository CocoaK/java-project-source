package com.quhwa.scalehouse.service.scale.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quhwa.scalehouse.service.scale.model.Device;
import com.quhwa.scalehouse.service.scale.model.Person;


public interface PersonMapper extends BaseMapper<Person>{
    int delete(Integer id);

    int insert(Person record);
    
    Long getTotal(Person record);
    
    Long getNewTotal(@Param(value="countryCode") String countryCode,@Param(value="type") String type);

    int insertActive(Person record);

    Person getOne(Integer id);

    int updateActive(Person record);

    int update(Person record);
    
    List<Person> getAll(@Param(value="countryCode") String countryCode,@Param(value="type") String type);
    
    Person queryByAccount(String account);
    
    Person queryByAccountAndPassword(Person record);
    
    List<Person> getForList(Person record);
    
    List<Person> queryForList(Person record);
    
    int updateForPerson(Person record);
    
    Long getSum(@Param(value="time") String time,@Param(value="countryCode") String countryCode,@Param(value="type") String type);
    
    List<Device> getCountry();
    
    Long todayActivePerN(@Param(value="countryCode") String countryCode,@Param(value="type") String type);
    
    Long getPersons(@Param(value="time") String time,@Param(value="countryCode") String countryCode,@Param(value="type") String type);
}