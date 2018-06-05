package com.smarthome.socket.service.business.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;
import com.smarthome.socket.common.model.ResultEntity;
import com.smarthome.socket.service.business.service.BaseRestService;
import com.smarthome.socket.service.business.service.IDevicesService;
import com.smarthome.socket.service.vo.Device;

public class DevicesService extends BaseRestService<Device> implements IDevicesService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	@Override
	public ResultEntity<String> updateAllDeviceStatus(String status) {
		ResultEntity<String> re = new ResultEntity<String>();
		super.setUpdateUrl("/device/updateAllDeviceStatus");
		Map<String,String> map = new HashMap<String,String>();
		map.put("status", status);
		re = super.postForObject(restServiceUrl+updateUrl, map, new ParameterizedTypeReference<ResultEntity<String>>(){});
		return re;
	}

	@Override
	public ResultEntity<Device> getById(String deviceId) {
		super.setUpdateUrl("/device/getById");
		Map<String,String> map = new HashMap<String,String>();
		map.put("deviceId", deviceId);
		ResultEntity<Device> re = super.postForObject(restServiceUrl+updateUrl, map, new ParameterizedTypeReference<ResultEntity<Device>>(){});
		return re;
	}
	
	@Override
	public ResultEntity<Device> getByDeviceNo(String deviceNo) {
		super.setUpdateUrl("/device/getEntityByDeviceNo");
		Map<String,String> map = new HashMap<String,String>();
		map.put("deviceNo", deviceNo);
		ResultEntity<Device> re = super.postForObject(restServiceUrl+updateUrl, map, new ParameterizedTypeReference<ResultEntity<Device>>(){});
		return re;
	}
}
