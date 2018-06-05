package com.biencloud.smarthome.service.rest.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.mapper.MsgTokenMapper;
import com.biencloud.smarthome.service.rest.model.MsgToken;
import com.biencloud.smarthome.service.rest.service.IMsgTokenService;

@Service
public class MsgTokenService extends BaseResService<MsgToken> implements
		IMsgTokenService {

	@Autowired
	private MsgTokenMapper msgTokenMapper;

	@Override
	public ResultEntity<String> saveOrUpdate(MsgToken msgToken) {
		ResultEntity<String> re = new ResultEntity<String>(ResultEntity.FAILD,"",null);
		if (msgToken==null){
			return re;
		}
		if(msgToken.getUserId()==null || msgToken.getUserId().equals(0)){
			re.setMessage("userId is null");
			return re;
		}
		if(StringUtils.isBlank(msgToken.getToken()) || StringUtils.isBlank(msgToken.getType()) || "null".equals(msgToken.getToken())){
			return re;
		}
		MsgToken mt = msgTokenMapper.getEntityByToken(msgToken.getToken());
		if(mt==null){
			msgTokenMapper.insert(msgToken);
		}else{
			msgToken.setId(mt.getId());
			msgTokenMapper.updateOnActive(msgToken);
		}
		re.setCode(ResultEntity.SUCCESS);
		return re;
	}

	@Override
	public BaseMapper<MsgToken> getBaseMapper() {
		// TODO Auto-generated method stub
		return msgTokenMapper;
	}
	
	@Override
	public List<MsgToken> getListByDeviceId(Long deviceId) {
		return msgTokenMapper.getListByDeviceId(deviceId);
	}

	@Override
	public ResultEntity<String> delete(MsgToken msgToken) {
		ResultEntity<String> re = new ResultEntity<String>();
		if(msgToken == null || "".equals(msgToken.getToken())){
			re.setCode(ResultEntity.INPUT_IS_NULL);
			re.setData("input is null");
			return re;
		}
		MsgToken token = msgTokenMapper.getEntityByToken(msgToken.getToken());
		if(token == null){
			re.setCode(ResultEntity.NOT_EXIST);
			re.setData("not exist");
			return re;
		}
		return super.proccessResultEntity(msgTokenMapper.deleteByEntity(msgToken)>0?ResultEntity.SUCCESS:ResultEntity.FAILD, "","");
	}

	@Override
	public List<MsgToken> getListByEntity(MsgToken msgToken) {
		return msgTokenMapper.getListByEntity(msgToken);
	}


}  
