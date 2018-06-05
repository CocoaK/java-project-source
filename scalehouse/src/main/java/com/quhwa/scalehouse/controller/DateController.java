/**
 * 
 */
package com.quhwa.scalehouse.controller;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


/** 
 * @Title:        DateController 
 * @Description:  TODO(这里用一句话描述这个方法的作用)         
 * @author        kouzhao
 * @Date          2018-5-18 下午4:28:51 
 */
public class DateController {
	@InitBinder  
    protected void initBinder(WebDataBinder binder) {  
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {  
            @Override  
            public void setAsText(String value) {  
                setValue(new Timestamp(Long.valueOf(value)));  
            }  
        });  
  
    }
}
