package com.biencloud.smarthome.web.sip.service;

import java.util.List;
import com.biencloud.smarthome.web.base.service.IBaseRestService;
import com.biencloud.smarthome.web.common.vo.ResultEntity;
import com.biencloud.smarthome.web.sip.VO.UserRoomNoVo;

public interface IUserRoomNoService extends IBaseRestService<UserRoomNoVo>{
	
	List<UserRoomNoVo> queryList(UserRoomNoVo userRoomNoVo) throws Exception;
	
	ResultEntity<UserRoomNoVo> get(Integer id) throws Exception;
	
	ResultEntity<String> delete(Integer id) throws Exception;
	
	ResultEntity<String> update(UserRoomNoVo userRoomNoVo) throws Exception;
	
	ResultEntity<String> add(UserRoomNoVo userRoomNoVo) throws Exception;
	
	ResultEntity<String> delete(UserRoomNoVo userRoomNoVo) throws Exception;
	
}
