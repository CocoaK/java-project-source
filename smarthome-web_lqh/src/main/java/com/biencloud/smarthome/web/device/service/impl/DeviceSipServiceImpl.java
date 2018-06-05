package com.biencloud.smarthome.web.device.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;
import com.biencloud.smarthome.web.base.service.BaseRestService;
import com.biencloud.smarthome.web.common.vo.ResultEntity;
import com.biencloud.smarthome.web.device.service.IDeviceSipService;
import com.biencloud.smarthome.web.device.vo.DeviceSipVO;

/**
 * 
 * 类名称：DeviceSipServiceImpl 
 * 类描述： 设备SIPweb端实现类
 * @author: ykou  
 * @version: 0.1
 * @date: 2016-1-6 上午11:38:59
 */
public class DeviceSipServiceImpl extends BaseRestService<DeviceSipVO> implements IDeviceSipService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	@Override
	public List<DeviceSipVO> queryList(DeviceSipVO deviceSipVO)
			throws Exception {
		super.setQueryUrl("/device/sip/queryList");
		List<DeviceSipVO> list = super.postForObject(restServiceUrl+queryUrl, deviceSipVO, new ParameterizedTypeReference<List<DeviceSipVO>>(){});
		return list;
	}

	@Override
	public ResultEntity<DeviceSipVO> get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultEntity<String> delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultEntity<String> add(DeviceSipVO deviceSipVO) throws Exception {
		super.setQueryUrl("/device/sip/add");
		ResultEntity<String> re = super.postForObject(restServiceUrl+queryUrl, deviceSipVO, new ParameterizedTypeReference<ResultEntity<String>>(){});
		return re;
	}

	@Override
	public ResultEntity<String> delete(DeviceSipVO deviceSipVO) throws Exception {
		super.setQueryUrl("/device/sip/del");
		ResultEntity<String> re = super.postForObject(restServiceUrl+queryUrl, deviceSipVO, new ParameterizedTypeReference<ResultEntity<String>>(){});
		return re;
	}
	

}
