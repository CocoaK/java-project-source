package com.quhwa.scalehouse.service.scale.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.common.utils.CryptoUtils;
import com.quhwa.scalehouse.service.scale.mapper.BaseMapper;
import com.quhwa.scalehouse.service.scale.mapper.BodyInfoMapper;
import com.quhwa.scalehouse.service.scale.mapper.DeviceMapper;
import com.quhwa.scalehouse.service.scale.mapper.PersonMapper;
import com.quhwa.scalehouse.service.scale.model.BodyInfo;
import com.quhwa.scalehouse.service.scale.model.Device;
import com.quhwa.scalehouse.service.scale.model.Person;
import com.quhwa.scalehouse.service.scale.service.IBodyInfoService;
import com.quhwa.scalehouse.util.JsonUtils;

@Service
@Transactional
public class BodyInfoService extends BaseService<BodyInfo> implements IBodyInfoService{
	@Autowired
	private BodyInfoMapper bodyInfoMapper;
	@Autowired
	private PersonMapper personMapper;
	@Autowired
	private DeviceMapper deviceMapper;
	
	@Override
	public BaseMapper<BodyInfo> getBaseMapper() {
		return bodyInfoMapper;
	}
	
	@Override
	public ResultEntity<String> delete(Integer id) {
		ResultEntity<String> re=new ResultEntity<String>();
		int per=bodyInfoMapper.delete(id);
		if(per>0){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData("");
		}else{
			re.setMessage(ResultEntity.MESSAGE_FAILED);
			re.setData("");
		}
		return re;
	}

	@Override
	public ResultEntity<String> insert(BodyInfo record) {
		ResultEntity<String> re = new ResultEntity<String>();
		if(record!=null && !"".equals(record)){
			int u=bodyInfoMapper.insert(record);
			if(u>0){
				re.setCode(ResultEntity.SUCCESS);
				re.setMessage(ResultEntity.MESSAGE_SUCCESS);
				re.setData("");
			}else{
				re.setMessage(ResultEntity.MESSAGE_FAILED);
				re.setData("");
			}
		}
		return re;
	}

	@Override
	public ResultEntity<String> insertActive(BodyInfo record) {
		ResultEntity<String> re = new ResultEntity<String>();
		if(record!=null && !"".equals(record)){
			int u=bodyInfoMapper.insertActive(record);
			if(u>0){
				re.setCode(ResultEntity.SUCCESS);
				re.setMessage(ResultEntity.MESSAGE_SUCCESS);
				re.setData("");
			}else{
				re.setMessage(ResultEntity.MESSAGE_FAILED);
				re.setData("");
			}
		}else{
			re.setMessage(ResultEntity.MESSAGE_FAILED);
			re.setData("");
		}
		return re;
	}

	@Override
	public ResultEntity<BodyInfo> getOne(Integer id) {
		ResultEntity<BodyInfo> re = new ResultEntity<BodyInfo>();
		BodyInfo u=bodyInfoMapper.getOne(id);
		if(u!=null){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData(u);
		}else{
			re.setMessage(ResultEntity.MESSAGE_FAILED);
		}
		return re;
	}

	@Override
	public ResultEntity<String> update(BodyInfo record) {
		ResultEntity<String> re = new ResultEntity<String>();
		int u=bodyInfoMapper.update(record);
		if(u>0){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData("");
		}else{
			re.setMessage(ResultEntity.MESSAGE_FAILED);
			re.setData("");
		}
		return re;
	}

	@Override
	public ResultEntity<String> updateActive(BodyInfo record) {
		ResultEntity<String> re = new ResultEntity<String>();
		int u=bodyInfoMapper.updateActive(record);
		if(u>0){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData("");
		}else{
			re.setMessage(ResultEntity.MESSAGE_FAILED);
			re.setData("");
		}
		return re;
	}

	@Override
	public ResultEntity<List<BodyInfo>> getList(Person per) {
		ResultEntity<List<BodyInfo>> re=new ResultEntity<List<BodyInfo>>();
//		String password=CryptoUtils.encodeByMD5(per.getPassword());
//		per.setPassword(password);
		Person p=personMapper.queryByAccountAndPassword(per);
		if(p==null){
			re.setCode(ResultEntity.ACCOUNT_OR_PASSWD_ERROR);
			re.setMessage("用户名或密码错误");
			return re;
		}
		int personId=p.getId();
		List<BodyInfo> bd=bodyInfoMapper.getList(personId);
		if(bd!=null){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData(bd);
		}else{
			re.setMessage(ResultEntity.MESSAGE_FAILED);
		}
		return re;
	}

	@Override
	public ResultEntity<List<BodyInfo>> getTodayData(Person per) {
		ResultEntity<List<BodyInfo>> re=new ResultEntity<List<BodyInfo>>();
		Person p = personMapper.queryByAccountAndPassword(per);
		int personId=p.getId();
		Long total=bodyInfoMapper.getTotal(personId);
		List<BodyInfo> bd=bodyInfoMapper.getTodayData(personId);
		if(bd!=null && personId>0){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData(bd);
			re.setTotal(total);
			re.setRows(bd);
		}else{
			re.setMessage(ResultEntity.MESSAGE_FAILED);
		}
		return re;
	}

	@Override
	public ResultEntity<List<BodyInfo>> getCurrentMonthData(Person per) {
		ResultEntity<List<BodyInfo>> re=new ResultEntity<List<BodyInfo>>();
		Person p = personMapper.queryByAccountAndPassword(per);
		int personId=p.getId();
		Long total=bodyInfoMapper.getTotal(personId);
		List<BodyInfo> bd=bodyInfoMapper.getCurrentMonthData(personId);
		if(bd!=null && personId>0){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData(bd);
			re.setTotal(total);
			re.setRows(bd);
		}else{
			re.setMessage(ResultEntity.MESSAGE_FAILED);
		}
		return re;
	}

	@Override
	public ResultEntity<List<BodyInfo>> getSevenDaysData(Person per) {
		ResultEntity<List<BodyInfo>> re=new ResultEntity<List<BodyInfo>>();
		Person p = personMapper.queryByAccountAndPassword(per);
		int personId=p.getId();
		Long total=bodyInfoMapper.getTotal(personId);
		List<BodyInfo> bd=bodyInfoMapper.getSevenDaysData(personId);
		if(bd!=null && personId>0){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData(bd);
			re.setTotal(total);
			re.setRows(bd);
		}else{
			re.setMessage(ResultEntity.MESSAGE_FAILED);
		}
		return re;
	}

	@Override
	public ResultEntity<List<BodyInfo>> getByTimesolt(Date startTime,Date endTime,String account,String password) {
		//接收两个参数放到Map集合里
		Person per=new Person();
		per.setAccount(account);
		per.setPassword(password);
		Person p = personMapper.queryByAccountAndPassword(per);
		int personId=p.getId();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startTime",startTime);  
        map.put("endTime",endTime);
        map.put("personId",personId);
        ResultEntity<List<BodyInfo>> re=new ResultEntity<List<BodyInfo>>();
        Long total=bodyInfoMapper.getTotal(personId);
        List<BodyInfo> bd=bodyInfoMapper.getByTimesolt(map);
        System.out.println(bd);
        if(bd!=null && personId>0 && startTime!=null && endTime!=null){
        	re.setCode(ResultEntity.SUCCESS);
        	re.setMessage(ResultEntity.MESSAGE_SUCCESS);
        	re.setData(bd);
        	re.setTotal(total);
        	re.setRows(bd);
        }else{
			re.setMessage(ResultEntity.MESSAGE_FAILED);
		}
		return re;
	}

	@Override
	public ResultEntity<List<BodyInfo>> getByPersonId(Person per) {
		ResultEntity<List<BodyInfo>> re = new ResultEntity<List<BodyInfo>>();
		Person p = personMapper.queryByAccountAndPassword(per);
		int personId=p.getId();
		if(personId>0 && !"".equals(personId)){
			List<BodyInfo> u=bodyInfoMapper.getByPersonId(personId);
			if(u!=null && personId>0){
				re.setCode(ResultEntity.SUCCESS);
				re.setMessage(ResultEntity.MESSAGE_SUCCESS);
				re.setData(u);
			}else{
				re.setMessage(ResultEntity.MESSAGE_FAILED);
			}
		}
		return re;
	}

	@Override
	public ResultEntity<List<BodyInfo>> queryByWeight(Double weight) {
		ResultEntity<List<BodyInfo>> re=new ResultEntity<List<BodyInfo>>();
		if(weight!=null && !"".equals(weight)){
			List<BodyInfo> u=bodyInfoMapper.queryByWeight(weight);
			if(u!=null){
				re.setCode(ResultEntity.SUCCESS);
				re.setMessage(ResultEntity.MESSAGE_SUCCESS);
				re.setData(u);
			}else{
				re.setMessage(ResultEntity.MESSAGE_FAILED);
			}
		}
		return re;
	}

	@Override
	public ResultEntity<List<BodyInfo>> queryByHighWeight(Double weight) {
		ResultEntity<List<BodyInfo>> re=new ResultEntity<List<BodyInfo>>();
		if(weight!=null && !"".equals(weight)){
			List<BodyInfo> u=bodyInfoMapper.queryByHighWeight(weight);
			if(u!=null){
				re.setCode(ResultEntity.SUCCESS);
				re.setMessage(ResultEntity.MESSAGE_SUCCESS);
				re.setData(u);
			}else{
				re.setMessage(ResultEntity.MESSAGE_FAILED);
			}
		}
		return re;
	}

	@Override
	public ResultEntity<List<BodyInfo>> getOtherdayData(Date recordTime,String account,String password) {
		ResultEntity<List<BodyInfo>> re=new ResultEntity<List<BodyInfo>>();
		Person per=new Person();
		per.setAccount(account);
		per.setPassword(password);
		Person p = personMapper.queryByAccountAndPassword(per);
		int personId=p.getId();
		if(recordTime!=null && !"".equals(recordTime)){
			Long total=bodyInfoMapper.getTotal(personId);
			List<BodyInfo> bd=bodyInfoMapper.getOtherdayData(recordTime,personId);
			if(bd!=null && personId>0){
				re.setCode(ResultEntity.SUCCESS);
				re.setMessage(ResultEntity.MESSAGE_SUCCESS);
				re.setData(bd);
				re.setTotal(total);
				re.setRows(bd);
			}else{
				re.setMessage(ResultEntity.MESSAGE_FAILED);
			}
		}
		return re;
	}

	@Override
	public ResultEntity<List<BodyInfo>> queryByPersonId(Person per) {
		ResultEntity<List<BodyInfo>> re=new ResultEntity<List<BodyInfo>>();
		Person p = personMapper.queryByAccountAndPassword(per);
		int personId=p.getId();
		if(personId>0){
			Long total=bodyInfoMapper.getTotal(personId);
			List<BodyInfo> rows=bodyInfoMapper.getByPersonId(personId);
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData(rows);
			re.setTotal(total);
			re.setRows(rows);
		}
		return re;
	}

	@Override
	public ResultEntity<String> deleteGroup(String[] ids) {
		ResultEntity<String> re=new ResultEntity<String>();
		if (ids==null || ids.length == 0){
			return re;
        }else{
        	for (String idStr : ids){
        		int id = new Integer(idStr);
        		bodyInfoMapper.delete(id);
        	}
        	re.setCode(ResultEntity.SUCCESS);
        	re.setMessage(ResultEntity.MESSAGE_SUCCESS);
        	re.setData("");
        }
		return re;
	}

	@Override
	public ResultEntity<String> insertJsonGroup(String jsonGroup,String account,String password) {
		ResultEntity<String> re=new ResultEntity<String>();
		Person per=new Person();
		
//		String pass=CryptoUtils.encodeByMD5(password);
		per.setAccount(account);
		per.setPassword(password);
		Person p = personMapper.queryByAccountAndPassword(per);
		if(p==null){
			re.setCode(ResultEntity.ACCOUNT_OR_PASSWD_ERROR);
			re.setMessage("用户名或密码错误");
			return re;
		}
		int personId=p.getId();
		bodyInfoMapper.deleteByPersonId(personId);
		if(jsonGroup==null){
			return re;
		}
		List<BodyInfo> list=parseJson(jsonGroup);
		//System.out.println("-----------------------------------------------"+list);
		try {
			for(BodyInfo bodyInfo:list){
				Device device=new Device();
				bodyInfo.setPersonId(personId+"");
				bodyInfo.setScaleType("CF");
				bodyInfo.setUnitType("00");
				device.setMac(bodyInfo.getMac());
				device.setName(bodyInfo.getName());
				device.setStatus("1");
				device.setType(bodyInfo.getType());
				device.setModel(bodyInfo.getModel());
				device.setPersonId(personId);
				if(null != bodyInfo.getMac() && !"".equals(bodyInfo.getMac())){
					List<Device> deviceList=deviceMapper.queryByMac(bodyInfo.getMac());
					if(deviceList==null || deviceList.size()==0){
						deviceMapper.insertActive(device);
						bodyInfo.setDeviceId(device.getId());
					}else{
						bodyInfo.setDeviceId(deviceList.get(0).getId());
					}
				}
				bodyInfoMapper.insertActive(bodyInfo);
			}
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			return re;
		} catch (Exception e) {
			e.printStackTrace();
			return re;
		}
	}
	
	private List<BodyInfo> parseJson(String wagerJson) {
		List<BodyInfo> list = JsonUtils.getObject(wagerJson, BodyInfo.class);
		return list;
	}

}
