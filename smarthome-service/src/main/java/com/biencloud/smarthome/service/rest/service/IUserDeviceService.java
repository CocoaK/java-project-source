package com.biencloud.smarthome.service.rest.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.Device;
import com.biencloud.smarthome.service.rest.model.DeviceUserVo;
import com.biencloud.smarthome.service.rest.model.UserDeviceKey;

public interface IUserDeviceService extends IBaseResService<UserDeviceKey>{
	
	List<Device> queryList(Long userId);
	
	ResultEntity<String> unbound(Long userId,String deviceNo);
	
	ResultEntity<String> deleteByUserId(String userId);
	
	ResultEntity<String> bound(Long userId,String deviceNo,String devicePwd);
	
	ResultEntity<String> searchBound(Long userId,String deviceNo) throws InterruptedException;
	
	List<UserDeviceKey> getList(UserDeviceKey userDeviceKey);

	List<DeviceUserVo> getDeviceUserList(UserDeviceKey userDeviceKey);

	List<Device> queryDeviceList(Long userId);

	ResultEntity<Device> boundDevice(Long userId, String deviceNo,
			String devicePwd);

	/**
	 * 查询设备绑定的用户
	 * @param userDeviceKey
	 * @return
	 */
	List<DeviceUserVo> getUserListByDeviceId(UserDeviceKey userDeviceKey);
	
}
