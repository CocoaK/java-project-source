package com.quhwa.scalehouse.service.scale.service;

import java.util.List;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.service.scale.model.Device;
import com.quhwa.scalehouse.service.scale.model.Product;

/**
 * 
 * 类名称：IDeviceService 
 * 类描述： 设备服务接口
 * @author: kouzhao  
 * @version: 0.1
 * @date: 2018-5-16 上午11:47:38
 */
public interface IDeviceService extends IBaseService<Device>{
	
	/**
	 * 
	 * 方法的描述: 设备信息上传接口
	 * @author: kouzhao  
	 * @version: 0.1
	 * @date: 2018-5-16 上午11:49:03
	 * @param Device
	 * @return int
	 */
	ResultEntity<String> insertActive(Device record);
	
	ResultEntity<List<Device>> getAll(String countryCode,String type);
	
	ResultEntity<List<Product>> getProduct();
	
	ResultEntity<String> deviceNum(String countryCode,String type);
	
	ResultEntity<String> todayActiveDevN(String countryCode,String type);
	
	ResultEntity<List<Long>> getChartsData(int intervals,String countryCode,String type);
    
    ResultEntity<List<Long>> getDevices(int intervals,String countryCode,String type);
}
