package com.biencloud.smarthome.web.clientuser.service;

import java.util.List;

import com.biencloud.smarthome.web.base.service.IBaseRestService;
import com.biencloud.smarthome.web.clientuser.vo.ClientUserVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.common.vo.ResultEntity;

public interface IClientUserService extends IBaseRestService<ClientUserVO>{
	
	List<ClientUserVO> queryList(ClientUserVO clientUserVO) throws Exception;
	List<ClientUserVO> userList(ClientUserVO clientUserVO) throws Exception;
//	ClientUserVO userDetail(String id) throws Exception;
	

	
	ResultEntity<ClientUserVO> get(Integer id) throws Exception;
	
	ResultEntity<String> delete(Integer id) throws Exception;
		
	ResultEntity<String> update(ClientUserVO clientUserVO) throws Exception;
	
	PagingVO<ClientUserVO> queryPaging(ClientUserVO clientUserVO,PagingVO<ClientUserVO> paging) throws Exception;
	
}