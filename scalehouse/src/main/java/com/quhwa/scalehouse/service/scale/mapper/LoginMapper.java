package com.quhwa.scalehouse.service.scale.mapper;

import com.quhwa.scalehouse.service.scale.model.Login;

public interface LoginMapper extends BaseMapper<Login>{
    int delete(Integer id);

    int insert(Login record);

    int insertActive(Login record);

    Login getOne(Integer id);

    int updateActive(Login record);

    int update(Login record);
}