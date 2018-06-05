package com.quhwa.scalehouse.service.scale.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quhwa.scalehouse.common.page.Page;

public interface BaseMapper<T> {
	
	String PO_KEY = "po";
	String PAGE_KEY = "page";
	
	int delete(Integer id);

	int deleteById(Integer id);
	
    int insert(T record);

    T getForOne(Integer id);

    int updateOnActive(T record);

    int update(T record);
    
    List<T> getList(T record);
    
    Page<T> getPageList(@Param(PAGE_KEY) Page<T> p, @Param(PO_KEY) Object obj);
    
}
