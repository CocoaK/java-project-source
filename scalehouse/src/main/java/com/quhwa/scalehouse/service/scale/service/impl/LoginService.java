package com.quhwa.scalehouse.service.scale.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.common.utils.CryptoUtils;
import com.quhwa.scalehouse.service.scale.mapper.BaseMapper;
import com.quhwa.scalehouse.service.scale.mapper.LoginMapper;
import com.quhwa.scalehouse.service.scale.mapper.PersonMapper;
import com.quhwa.scalehouse.service.scale.model.Login;
import com.quhwa.scalehouse.service.scale.model.Person;
import com.quhwa.scalehouse.service.scale.model.PersonDeviceMac;
import com.quhwa.scalehouse.service.scale.service.ILoginService;
import com.quhwa.scalehouse.util.JsonUtils;

@Service
@Transactional
public class LoginService extends BaseService<Login> implements ILoginService{

	@Autowired
	private LoginMapper loginMapper;
	
	@Autowired
	private PersonMapper personMapper;
	
	@Override
	public BaseMapper<Login> getBaseMapper() {
		// TODO Auto-generated method stub
		return loginMapper;
	}

	@Override
	public ResultEntity<String> insertActive(String account,String loginTimeGroup) {
		ResultEntity<String> re=new ResultEntity<String>();
		Person p=new Person();
		p.setAccount(account);
		List<Person> list=personMapper.queryForList(p);//查询用户是否存在
		if(list==null || list.size()==0){
			re.setCode(ResultEntity.ACCOUNT_NOT_EXIST);
			re.setMessage("用户不存在");
			return re;
		}
		int personId=list.get(0).getId();
		if(loginTimeGroup==null || "".equals(loginTimeGroup)){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData("");
			return re;
		}
		List<Login> pdm=parseJson(loginTimeGroup);
		try {
			for(Login log:pdm){
				if(log.getLoginTime()==null || "".equals(log.getLoginTime())){
					re.setCode(ResultEntity.INPUT_IS_NULL);
					re.setMessage("loginTime数组中有null值");
					return re;
				}//判断接收的值中是否有空的
				log.setPersonId(personId);
				loginMapper.insertActive(log);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return re;
		}
		re.setCode(ResultEntity.SUCCESS);
		re.setMessage(ResultEntity.MESSAGE_SUCCESS);
		return re;
	}
	private List<Login> parseJson(String wagerJson) {
		List<Login> list = JsonUtils.getObjectList(wagerJson, Login.class);
		return list;
	}

}
