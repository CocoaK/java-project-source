package com.biencloud.smarthome.web.sip.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import com.biencloud.smarthome.web.base.service.BaseRestService;
import com.biencloud.smarthome.web.common.vo.ResultEntity;
import com.biencloud.smarthome.web.sip.VO.SipRegister;
import com.biencloud.smarthome.web.sip.service.ISipRegisterService;

public class SipRegisterService extends BaseRestService<SipRegister> implements ISipRegisterService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	@Override
	public List<SipRegister> queryList(SipRegister sipRegister) throws Exception {
		super.setQueryUrl("/sip/reg/queryList");
		List<SipRegister> list = super.postForObject(restServiceUrl+queryUrl, sipRegister, new ParameterizedTypeReference<List<SipRegister>>(){});
		return list;
	}

	@Override
	public ResultEntity<SipRegister> get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultEntity<String> delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultEntity<String> update(SipRegister sipRegister) throws Exception {
		super.setUpdateUrl("/qrcode/update");
		ResultEntity<String> re = super.postForObject(restServiceUrl+updateUrl, sipRegister, new ParameterizedTypeReference<ResultEntity<String>>(){});
		return re;
	}

	@Override
	public ResultEntity<String> add(SipRegister sipRegister) throws Exception {
		super.setQueryUrl("/sip/reg/add");
		ResultEntity<String> re = super.postForObject(restServiceUrl+queryUrl, sipRegister, new ParameterizedTypeReference<ResultEntity<String>>(){});
		return re;
	}

	@Override
	public ResultEntity<String> delete(SipRegister sipRegister) throws Exception {
		super.setQueryUrl("/qrcode/del");
		ResultEntity<String> re = super.postForObject(restServiceUrl+queryUrl, sipRegister, new ParameterizedTypeReference<ResultEntity<String>>(){});
		return re;
	}
	
	@Override
	public ResultEntity<SipRegister> create(SipRegister sipRegister) throws Exception {
		if(sipRegister==null){
			sipRegister = new SipRegister();
		}
		super.setQueryUrl("/sip/reg/create");
		return super.postForObject(restServiceUrl+queryUrl, sipRegister, new ParameterizedTypeReference<ResultEntity<SipRegister>>(){});
	}

	@Override
	public ResultEntity<SipRegister> getByUsername(String username) throws Exception {
		super.setQueryUrl("/sip/reg/get");
		Long i = Long.parseLong(username);
		ResultEntity<SipRegister> re = super.postForObject(restServiceUrl+queryUrl, i, new ParameterizedTypeReference<ResultEntity<SipRegister>>(){});
		return re;
	}
}
