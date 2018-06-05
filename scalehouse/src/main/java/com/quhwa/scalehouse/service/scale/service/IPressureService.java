package com.quhwa.scalehouse.service.scale.service;

import java.util.List;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.service.scale.model.Person;
import com.quhwa.scalehouse.service.scale.model.Pressure;

/**
 * 
 * @Title:        IPressureService 
 * @Description:  血压计
 * @author        kouzhao
 * @Date          2018-5-17 下午2:08:55
 */
public interface IPressureService extends IBaseService<Pressure>{
	
	/**
	 * 
	 * @Title:        upload 
	 * @Description:  上传数据接口 
	 * @param:        @param jsonGroup
	 * @param:        @param account
	 * @param:        @param password
	 * @param:        @return    
	 * @return:       ResultEntity<String>    
	 * @throws 
	 * @author        kouzhao
	 * @Date          2018-5-17 下午2:09:27
	 */
	ResultEntity<String> upload(String jsonGroup,String account,String password);
	
	/**
	 * 
	 * @Title:        download 
	 * @Description:  下载数据接口 
	 * @param:        @param per
	 * @param:        @return    
	 * @return:       ResultEntity<List<Pressure>>    
	 * @throws 
	 * @author        kouzhao
	 * @Date          2018-5-17 下午2:09:51
	 */
	ResultEntity<List<Pressure>> download(Person per);
}
