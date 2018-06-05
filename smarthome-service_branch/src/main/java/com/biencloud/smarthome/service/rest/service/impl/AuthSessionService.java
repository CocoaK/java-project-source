package com.biencloud.smarthome.service.rest.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.mapper.AuthSessionMapper;
import com.biencloud.smarthome.service.rest.model.AuthSession;
import com.biencloud.smarthome.service.rest.service.IAuthSessionService;

@Service
public class AuthSessionService extends BaseResService<AuthSession> implements
		IAuthSessionService {

	@Autowired
	private AuthSessionMapper authSessionMapper;

	@Override
	public BaseMapper<AuthSession> getBaseMapper() {
		return authSessionMapper;
	}

	@Override
	public ResultEntity<String> save(String sessionKey,Long userId) {
		ResultEntity<String> re = new ResultEntity<String>(ResultEntity.FAILD,"",null);
		if(StringUtils.isBlank(sessionKey) || userId == null){
			re.setCode(ResultEntity.FAILD);
			return re;
		}

		AuthSession auth = new AuthSession();
//		auth.setSessionKey(sessionKey);
//		auth.setUserId(userId);
		//判断用户名是否已经存在
//		List<AuthSession> list = authSessionMapper.getForList(auth);
//		if(list!=null && list.size()>0){
//			re.setCode(ResultEntity.ALREADY_EXIST);
//			return re;
//		}
		
		int i = authSessionMapper.insert(auth);
		if(i>0){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData(auth.getSessionKey());
		}
		return re;
	}

	@Override
	public ResultEntity<AuthSession> getByUserId(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthSession getBySessionKey(String sessionKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultEntity<String> deleteBySessionKey(String sessionKey) {
		// TODO Auto-generated method stub
		return null;
	}

}  
