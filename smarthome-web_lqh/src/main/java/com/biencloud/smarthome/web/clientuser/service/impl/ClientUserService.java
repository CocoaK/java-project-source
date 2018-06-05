package com.biencloud.smarthome.web.clientuser.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;
import com.biencloud.smarthome.web.base.service.BaseRestService;
import com.biencloud.smarthome.web.clientuser.service.IClientUserService;
import com.biencloud.smarthome.web.clientuser.vo.ClientUserVO;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.common.vo.ResultEntity;
import com.biencloud.smarthome.web.common.vo.ResultList;
import com.biencloud.smarthome.web.qrcode.vo.QrcodeVO;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

public class ClientUserService extends BaseRestService<ClientUserVO> implements IClientUserService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	@Override
	public List<ClientUserVO> queryList(ClientUserVO clientUserVO) throws Exception {
		super.setQueryUrl("/user/queryPage");
		if(clientUserVO==null){
			clientUserVO = new ClientUserVO();
		}
		List<ClientUserVO> list = super.postForObject(restServiceUrl+queryUrl, clientUserVO, new ParameterizedTypeReference<List<ClientUserVO>>(){});
		return list;
	}
	@Override
	public List<ClientUserVO> userList(ClientUserVO clientUserVO) throws Exception {
		// TODO Auto-generated method stub
		List<ClientUserVO> list = new ArrayList<ClientUserVO>();
		return list;
	}

	@Override
	public ResultEntity<ClientUserVO> get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		super.setQueryUrl("/user/get");
		ResultEntity<ClientUserVO> result = super.postForObject(restServiceUrl+queryUrl, id, new ParameterizedTypeReference<ResultEntity<ClientUserVO>>(){});
		return result;
	}

	@Override
	public ResultEntity<String> delete(Integer id) throws Exception {
		super.setDeleteUrl("/user/del");
		ResultEntity<String> result = super.postForObject(restServiceUrl+deleteUrl, id, new ParameterizedTypeReference<ResultEntity<String>>(){});
		return result;
	}

	@Override
	public ResultEntity<String> update(ClientUserVO clientUserVO)
			throws Exception {
		super.setUpdateUrl("/user/updt");
		ResultEntity<String> result = super.postForObject(restServiceUrl+updateUrl, clientUserVO, new ParameterizedTypeReference<ResultEntity<String>>(){});
		return result;
	}
	
	@Override
	public PagingVO<ClientUserVO> queryPaging(ClientUserVO clientUserVO, PagingVO<ClientUserVO> paging) {
		super.setQueryUrl("/user/queryPage");
		Map<String,Object> map = new HashMap<String,Object>();
		if(clientUserVO!=null){
			map.put("vo", clientUserVO);
		}
		if(paging!=null){
			map.put("page", paging.getPageNum());
			map.put("size", paging.getPageSize());
		}
		ResultList<List<ClientUserVO>> results = super.postForObject(restServiceUrl+queryUrl, map, new ParameterizedTypeReference<ResultList<List<ClientUserVO>>>(){});
		if(results!=null){
			paging.setResults(results.getInfo());
			paging.setTotalCount(results.getTotal());
		}
		return paging;
	}
}
