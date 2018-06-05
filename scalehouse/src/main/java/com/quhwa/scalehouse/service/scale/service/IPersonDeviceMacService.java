/**
 * 
 */
package com.quhwa.scalehouse.service.scale.service;

import java.util.List;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.service.scale.model.PersonDeviceMac;

/** 
 * @Title:        IPersonDeviceMac 
 * @Description:  TODO(这里用一句话描述这个方法的作用)         
 * @author        kouzhao
 * @Date          2018-5-25 下午1:35:52 
 */
public interface IPersonDeviceMacService extends IBaseService<PersonDeviceMac>{
	
	ResultEntity<String> uploadMac(String macGroup,String account);
	
	ResultEntity<List<PersonDeviceMac>> getMac(String account);
	
}
