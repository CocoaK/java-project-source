package com.biencloud.smarthome.web.sip.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;
import com.biencloud.smarthome.web.base.service.BaseRestService;
import com.biencloud.smarthome.web.common.vo.ResultEntity;
import com.biencloud.smarthome.web.sip.VO.UserRoomNoVo;
import com.biencloud.smarthome.web.sip.service.IUserRoomNoService;

public class UserRoomNoService extends BaseRestService<UserRoomNoVo> implements IUserRoomNoService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	@Override
	public List<UserRoomNoVo> queryList(UserRoomNoVo userRoomNoVo) throws Exception {
		super.setQueryUrl("/user/roomNo/queryList");
		List<UserRoomNoVo> list = super.postForObject(restServiceUrl+queryUrl, userRoomNoVo, new ParameterizedTypeReference<List<UserRoomNoVo>>(){});
		return list;
	}

	@Override
	public ResultEntity<UserRoomNoVo> get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultEntity<String> delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultEntity<String> update(UserRoomNoVo userRoomNoVo) throws Exception {
		super.setUpdateUrl("/user/roomNo/update");
		ResultEntity<String> re = super.postForObject(restServiceUrl+updateUrl, userRoomNoVo, new ParameterizedTypeReference<ResultEntity<String>>(){});
		return re;
	}

	@Override
	public ResultEntity<String> add(UserRoomNoVo userRoomNoVo) throws Exception {
		super.setQueryUrl("/user/roomNo/add");
		ResultEntity<String> re = super.postForObject(restServiceUrl+queryUrl, userRoomNoVo, new ParameterizedTypeReference<ResultEntity<String>>(){});
		return re;
	}

	@Override
	public ResultEntity<String> delete(UserRoomNoVo userRoomNoVo) throws Exception {
		super.setQueryUrl("/user/roomNo/del");
		ResultEntity<String> re = super.postForObject(restServiceUrl+queryUrl, userRoomNoVo, new ParameterizedTypeReference<ResultEntity<String>>(){});
		return re;
	}
	
}
