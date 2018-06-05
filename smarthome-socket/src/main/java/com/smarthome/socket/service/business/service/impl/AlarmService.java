package com.smarthome.socket.service.business.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;
import com.smarthome.socket.common.constant.Constant;
import com.smarthome.socket.common.model.ResultEntity;
import com.smarthome.socket.service.business.service.BaseRestService;
import com.smarthome.socket.service.business.service.IAlarmService;
import com.smarthome.socket.service.vo.AlarmInfo;
import com.smarthome.socket.service.vo.DeviceAccessories;

public class AlarmService extends BaseRestService<AlarmInfo> implements IAlarmService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	@Override
	public ResultEntity<String> save(String data,String mac) {
		ResultEntity<String> re = new ResultEntity<String>();
		AlarmInfo ai = new AlarmInfo();
		try{
			if(StringUtils.isNotBlank(data) && data.startsWith(Constant.CMD_ALARM_ADD_PREFIX)){
				String code = data.substring(data.length()-10,data.length()-4);
				ResultEntity<Long> deviceIdResult = getDeviceIdByDeviceNo(mac.replace(":",""));
				if(deviceIdResult == null || deviceIdResult.getData()==null){
					return re;
				}
				Long deviceId = deviceIdResult.getData();
				DeviceAccessories da = new DeviceAccessories();
				da.setCode(code);
				da.setDeviceId(deviceId);
				DeviceAccessories result = getAccessoriesIdByCode(da);
				if(result == null || result.getId() == null){
					return re;
				}
				Long accessoriesId = result.getId();
				ai.setDeviceCode(accessoriesId.toString());
				ai.setAlarmType(Constant.ALARM_TYPE_NORMAL);
				ai.setStatus(AlarmInfo.HANLDER_STATUS_NO.toString());
			}
			super.setCreateUrl("/alarm/info/create");
			re = super.postForObject(restServiceUrl+createUrl, ai, new ParameterizedTypeReference<ResultEntity<String>>(){});
		}catch(Exception e){
			System.out.println("save alarm exception:"+e.getMessage());
			return new ResultEntity<String>();
		}
		return re;
	}

	public DeviceAccessories getAccessoriesIdByCode(DeviceAccessories da){
		super.setQueryUrl("/device/accessories/get");
		DeviceAccessories de = super.postForObject(restServiceUrl+queryUrl, da, new ParameterizedTypeReference<DeviceAccessories>(){});
		return de;
	}
	
	@Override
	public ResultEntity<Long> getDeviceIdByDeviceNo(String deviceNo) throws Exception {
		super.setQueryUrl("/device/getId");
		Map<String,String> map = new HashMap<String,String>();
		map.put("deviceNo", deviceNo);
		ResultEntity<Long> re = super.postForObject(restServiceUrl+queryUrl, map, new ParameterizedTypeReference<ResultEntity<Long>>(){});
		return re;
	}
}
