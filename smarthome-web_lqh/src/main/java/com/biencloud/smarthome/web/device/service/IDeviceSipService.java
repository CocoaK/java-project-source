package com.biencloud.smarthome.web.device.service;

import java.util.List;
import com.biencloud.smarthome.web.common.vo.ResultEntity;
import com.biencloud.smarthome.web.device.vo.DeviceSipVO;

/**
 * 类名称：IDeviceIpService 
 * 类描述： 设备SIP账号web端接口
 * @author: ykou  
 * @version: 0.1
 * @date: 2016-1-6 上午11:15:34
 */
public interface IDeviceSipService {
	
List<DeviceSipVO> queryList(DeviceSipVO deviceSipVO) throws Exception;
	
	ResultEntity<DeviceSipVO> get(Integer id) throws Exception;
	
	ResultEntity<String> delete(Integer id) throws Exception;
	
	ResultEntity<String> add(DeviceSipVO qrcodeVO) throws Exception;
	
	ResultEntity<String> delete(DeviceSipVO qrcodeVO) throws Exception;
}
