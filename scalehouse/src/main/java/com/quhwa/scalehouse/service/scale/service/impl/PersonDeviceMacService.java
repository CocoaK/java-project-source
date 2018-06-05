/**
 * 
 */
package com.quhwa.scalehouse.service.scale.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.service.scale.mapper.BaseMapper;
import com.quhwa.scalehouse.service.scale.mapper.PersonDeviceMacMapper;
import com.quhwa.scalehouse.service.scale.mapper.PersonMapper;
import com.quhwa.scalehouse.service.scale.model.Person;
import com.quhwa.scalehouse.service.scale.model.PersonDeviceMac;
import com.quhwa.scalehouse.service.scale.service.IPersonDeviceMacService;
import com.quhwa.scalehouse.util.JsonUtils;

/** 
 * @Title:        PersonDeviceMac 
 * @Description:  TODO(这里用一句话描述这个方法的作用)         
 * @author        kouzhao
 * @Date          2018-5-25 下午1:46:23 
 */
@Service
@Transactional
public class PersonDeviceMacService extends BaseService<PersonDeviceMac> implements IPersonDeviceMacService{

	@Autowired
	private PersonDeviceMacMapper personDeviceMacMapper;

	@Autowired
	private PersonMapper personMapper;
	
	@Override
	public BaseMapper<PersonDeviceMac> getBaseMapper() {
		return personDeviceMacMapper;
	}

	@Override
	public ResultEntity<String> uploadMac(String macGroup,String account) {
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
		PersonDeviceMac per=new PersonDeviceMac();
		per.setPersonId(personId);
		personDeviceMacMapper.deleteForList(per);
		if(macGroup==null || "".equals(macGroup)){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData("");
			return re;
		}
		List<PersonDeviceMac> pdm=parseJson(macGroup);
		try {
			for(PersonDeviceMac personDeviceMac:pdm){
				if(personDeviceMac.getType()==null || "".equals(personDeviceMac.getType()) || personDeviceMac.getMac()==null || "".equals(personDeviceMac.getMac())){
					re.setCode(ResultEntity.INPUT_IS_NULL);
					re.setMessage("mac数组中有null值");
					return re;
				}//判断接收的值中是否有空的
				List<PersonDeviceMac> lp=personDeviceMacMapper.getForList(personDeviceMac);
				if(lp==null || lp.size()==0){
					personDeviceMac.setPersonId(personId);
					personDeviceMacMapper.insertActive(personDeviceMac);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return re;
		}
		re.setCode(ResultEntity.SUCCESS);
		re.setMessage(ResultEntity.MESSAGE_SUCCESS);
		return re;
	}

	@Override
	public ResultEntity<List<PersonDeviceMac>> getMac(String account) {
		ResultEntity<List<PersonDeviceMac>> re=new ResultEntity<List<PersonDeviceMac>>();
		if(account==null || "".equals(account)){
			re.setCode(ResultEntity.INPUT_IS_NULL);
			re.setMessage("账户为空");
			return re;
		}
		Person person=new Person();
		person.setAccount(account);
		List<Person> list=personMapper.getForList(person);
		if(list==null || list.size()==0){
			re.setCode(ResultEntity.ACCOUNT_NOT_EXIST);
			re.setMessage("用户不存在");
		}
		int personId=list.get(0).getId();
		PersonDeviceMac pdmac=new PersonDeviceMac();
		pdmac.setPersonId(personId);
		List<PersonDeviceMac> pd=personDeviceMacMapper.getForList(pdmac);//获取Mac地址
		re.setCode(ResultEntity.SUCCESS);
		re.setMessage(ResultEntity.MESSAGE_SUCCESS);
		re.setData(pd);
		return re;
	}
	
	private List<PersonDeviceMac> parseJson(String wagerJson) {
		List<PersonDeviceMac> list = JsonUtils.getObject(wagerJson, PersonDeviceMac.class);
		return list;
	}
}
