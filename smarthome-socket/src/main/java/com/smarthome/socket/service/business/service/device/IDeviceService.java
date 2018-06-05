package com.smarthome.socket.service.business.service.device;

import com.smarthome.socket.service.business.adapater.RegistReq;


/**
 * 
 * 类名称：IDeviceService 
 * 类描述： 设备业务处理接口
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-4 下午10:12:34
 */
public interface IDeviceService {
	
   /**
    * 
    * 方法的描述: 没有房号信息的WIFI模块设备保存到数据库
    * @author: kouy  
    * @version: 0.1
    * @date: 2016-5-12 下午10:38:19
    * @param req
    * @return
    * @throws Exception
    */
   public String [] easyLogin(RegistReq req)throws Exception;
   
   /**
    * 
    * 方法的描述: 保存到数据库
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-5-4 下午10:38:19
    * @param req
    * @return
    * @throws Exception
    */
   public String [] login(RegistReq req)throws Exception;
   
   /**
    * 
    * 方法的描述: 终端短开
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-5-15 下午9:42:00
    * @param mac
    * @throws Exception
    */
   public void exit(String mac)throws Exception;
   /**
    * 
    * 方法的描述: 更新全部设备状态
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-8-2 下午4:56:29
    * @param status
    */
   public void updateDeviceStatusForAll(String status);
   /**
    * 
    * 方法的描述: 更新设备状态
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-9-5 上午10:06:20
    * @param status
    * @param mac
    */
   public void updateDeviceStatusByMac(String status,String mac);
  
} 
