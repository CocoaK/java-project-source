package com.smarthome.socket.service.business.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import com.smarthome.socket.common.constant.Constant;
import com.smarthome.socket.common.model.ResultEntity;
import com.smarthome.socket.service.business.service.BaseRestService;
import com.smarthome.socket.service.business.service.IPushClientService;
import com.smarthome.socket.service.vo.PushClient;

public class PushClientService extends BaseRestService<PushClient> implements IPushClientService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	@Override
	public ResultEntity<Long> getDeviceIdByDeviceNo(String deviceNo) throws Exception {
		super.setQueryUrl("/device/getId");
		Map<String,String> map = new HashMap<String,String>();
		map.put("deviceNo", deviceNo);
		ResultEntity<Long> re = super.postForObject(restServiceUrl+queryUrl, map, new ParameterizedTypeReference<ResultEntity<Long>>(){});
		return re;
	}

	@Override
	public ResultEntity<String> addForResultEntity(PushClient record)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PushClient getEntity(PushClient record) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultEntity<String> updateForResultEntity(PushClient record)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultEntity<String> pushDefenceStatusChanged(String data,String mac) throws Exception {
		ResultEntity<String> re = new ResultEntity<String>();
		ResultEntity<Long> deviceIdResult = getDeviceIdByDeviceNo(mac.replace(":",""));
		if(deviceIdResult == null || deviceIdResult.getData()==null){
			return re;
		}
		Long deviceId = deviceIdResult.getData();
		PushClient pushClient = new PushClient();
		pushClient.setDeviceId(deviceId);
		pushClient.setStatus(data);
		
		super.setCreateUrl("/push/client/pushStatusChanged");
		re = super.postForObject(restServiceUrl+createUrl, pushClient, new ParameterizedTypeReference<ResultEntity<String>>(){});
		return re;
	}
	
	@Override
	public ResultEntity<String> pushDefenceStatusChanged(String data,String mac,String excludeUserId) throws Exception {
		ResultEntity<String> re = new ResultEntity<String>();
		ResultEntity<Long> deviceIdResult = getDeviceIdByDeviceNo(mac.replace(":",""));
		if(deviceIdResult == null || deviceIdResult.getData()==null){
			return re;
		}
		Long deviceId = deviceIdResult.getData();
		PushClient pushClient = new PushClient();
		pushClient.setDeviceId(deviceId);
		pushClient.setExcludeUserId(excludeUserId);
		pushClient.setStatus(data);
		
		super.setCreateUrl("/push/client/pushStatusChanged");
		re = super.postForObject(restServiceUrl+createUrl, pushClient, new ParameterizedTypeReference<ResultEntity<String>>(){});
		return re;
	}
}
