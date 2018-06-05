package com.quhwa.scalehouse.service.scale.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.service.scale.mapper.BaseMapper;
import com.quhwa.scalehouse.service.scale.mapper.DeviceMapper;
import com.quhwa.scalehouse.service.scale.mapper.PersonMapper;
import com.quhwa.scalehouse.service.scale.mapper.PressureMapper;
import com.quhwa.scalehouse.service.scale.model.Device;
import com.quhwa.scalehouse.service.scale.model.Person;
import com.quhwa.scalehouse.service.scale.model.Pressure;
import com.quhwa.scalehouse.service.scale.service.IPressureService;
import com.quhwa.scalehouse.util.JsonUtils;

@Service
@Transactional
public class PressureService extends BaseService<Pressure> implements IPressureService{

	@Autowired
	private PressureMapper pressureMapper;
	@Autowired
	private PersonMapper personMapper;
	@Autowired
	private DeviceMapper deviceMapper;
	
	@Override
	public BaseMapper<Pressure> getBaseMapper() {
		return pressureMapper;
	}

	@Override
	public ResultEntity<String> upload(String jsonGroup, String account,
			String password) {
		ResultEntity<String> re=new ResultEntity<String>();
		//String pass=CryptoUtils.encodeByMD5(password);
		Person per=new Person();
		per.setAccount(account);
		per.setPassword(password);
		Person p = personMapper.queryByAccountAndPassword(per);
		if(p==null){
			re.setCode(ResultEntity.ACCOUNT_OR_PASSWD_ERROR);
			re.setMessage("用户名或密码错误");
			return re;
		}
		int personId=p.getId();
		pressureMapper.deleteByPersonId(personId);
		if(jsonGroup==null){
			return re;
		}
		List<Pressure> list=parseJson(jsonGroup);
		try {
			for(Pressure pressure:list){
				Device device=new Device();
				pressure.setPersonId(personId);
				device.setMac(pressure.getMac());
				device.setName(pressure.getName());
				device.setType(pressure.getType());
				device.setModel(pressure.getModel());
				device.setStatus("1");
				device.setPersonId(personId);
				if(null != pressure.getMac() && !"".equals(pressure.getMac())){
					List<Device> deviceList=deviceMapper.queryByMac(pressure.getMac());
					if(deviceList==null || deviceList.size()==0){
						deviceMapper.insertActive(device);
						pressure.setDeviceId(device.getId());
					}else{
						pressure.setDeviceId(deviceList.get(0).getId());
					}
				}
				pressureMapper.insertActive(pressure);
			}
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			return re;
		} catch (Exception e) {
			e.printStackTrace();
			return re;
		}
	}

	@Override
	public ResultEntity<List<Pressure>> download(Person per) {
		ResultEntity<List<Pressure>> re=new ResultEntity<List<Pressure>>();
		//String password=CryptoUtils.encodeByMD5(per.getPassword());
		//per.setPassword(password);
		Person p=personMapper.queryByAccountAndPassword(per);
		if(p==null){
			re.setCode(ResultEntity.ACCOUNT_OR_PASSWD_ERROR);
			re.setMessage("用户名或密码错误");
			return re;
		}
		int personId=p.getId();
		
		List<Pressure> bd=pressureMapper.getList(personId);
		if(bd!=null){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData(bd);
		}else{
			re.setMessage(ResultEntity.MESSAGE_FAILED);
		}
		return re;
	}
	
	private List<Pressure> parseJson(String wagerJson) {
		List<Pressure> list = JsonUtils.getObject(wagerJson, Pressure.class);
		return list;
	}
}
