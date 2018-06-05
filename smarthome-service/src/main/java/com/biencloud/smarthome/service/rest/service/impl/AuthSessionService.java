package com.biencloud.smarthome.service.rest.service.impl;

import java.util.List;

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
		try{
			if(StringUtils.isBlank(sessionKey) || userId == null){
				re.setCode(ResultEntity.FAILD);
				return re;
			}
	
			AuthSession auth = new AuthSession();
			//auth.setSessionKey(sessionKey);
			auth.setUserId(userId);
			//判断用户名是否已经存在，如果存在则更新sessionKey，否则插入新数据
			List<AuthSession> list = authSessionMapper.getList(auth);
			if(list!=null && list.size()>0){
				AuthSession au = list.get(0);
				au.setSessionKey(sessionKey);
				authSessionMapper.updateOnActive(au);
				re.setCode(ResultEntity.SUCCESS);
			}else{
				auth.setSessionKey(sessionKey);
				int i = authSessionMapper.insert(auth);
				if(i>0){
					re.setCode(ResultEntity.SUCCESS);
					re.setMessage(ResultEntity.MESSAGE_SUCCESS);
					re.setData(auth.getSessionKey());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
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
		return authSessionMapper.getBySessionKey(sessionKey);
	}

	@Override
	public ResultEntity<String> deleteBySessionKey(String sessionKey) {
		// TODO Auto-generated method stub
		return null;
	}

}  
