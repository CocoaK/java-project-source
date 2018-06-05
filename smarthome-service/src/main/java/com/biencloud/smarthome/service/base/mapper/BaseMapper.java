package com.biencloud.smarthome.service.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.biencloud.smarthome.service.common.page.Page;

public interface BaseMapper<T> {
	String PO_KEY = "po";
	String PAGE_KEY = "page";
	
	int delete(Integer id);

	int deleteById(Integer id);
	
    int insert(T record);

	Page<T> getForPage(@Param(PAGE_KEY) Page<T> p, @Param(PO_KEY) Object obj);
    
    T getForOne(Integer id);

    int updateOnActive(T record);

    int update(T record);
    
    List<T> getList(T record);
    
    
}
