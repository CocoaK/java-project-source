package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.common.page.Page;
import com.biencloud.smarthome.service.rest.model.User;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User>{
    int delete(Long id);

    int insert(User record);

    List<User> getForList(User user);

    User getForOne(Long id);

    int updateOnActive(User record);

    int update(User record);

    int insertGetId(User record);
    
    Page<User> getForPage(@Param(PAGE_KEY) Page<User> p, @Param(PO_KEY) Object obj);
}