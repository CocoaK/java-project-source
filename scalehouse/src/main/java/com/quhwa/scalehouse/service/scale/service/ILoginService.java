package com.quhwa.scalehouse.service.scale.service;

import java.util.Date;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.service.scale.model.Login;

/**
 * 
 * @Title:        ILoginService 
 * @Description:  登录信息接口         
 * @author        kouzhao
 * @Date          2018-5-17 下午2:03:07
 */
public interface ILoginService extends IBaseService<Login> {
	
	/**
	 * 
	 * @Title:        insertActive 
	 * @Description:  上传
	 * @param:        @param record
	 * @param:        @return    
	 * @return:       ResultEntity<String>    
	 * @throws 
	 * @author        kouzhao
	 * @Date          2018-5-17 下午2:03:37
	 */
	ResultEntity<String> insertActive(String account,String loginTimeGroup);
	
}
