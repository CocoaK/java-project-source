package com.quhwa.scalehouse.service.scale.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.service.scale.mapper.BackgroundMapper;
import com.quhwa.scalehouse.service.scale.mapper.BaseMapper;
import com.quhwa.scalehouse.service.scale.model.Background;
import com.quhwa.scalehouse.service.scale.service.IBackgroundService;

@Service
@Transactional
public class BackgroundService extends BaseService<Background> implements IBackgroundService{

	@Autowired
	private BackgroundMapper backgroundMapper;
	
	@Override
	public BaseMapper<Background> getBaseMapper() {
		return backgroundMapper;
	}

	@Override
	public ResultEntity<List<Background>> queryByUserAndPassword(Background back) {
		ResultEntity<List<Background>> re=new ResultEntity<List<Background>>();
		if(back.getUser()==null || back.getPassword()==null){
			re.setMessage("账户名或密码不能为空");
			return re;
		}
		List<Background> ba=backgroundMapper.queryByUserAndPassword(back);
		if(ba.size()!=0){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData(ba);
		}
		return re;
	}


}
