package com.biencloud.smarthome.service.rest.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.mapper.DeviceMapper;
import com.biencloud.smarthome.service.rest.mapper.UserRoomNoMapper;
import com.biencloud.smarthome.service.rest.model.Device;
import com.biencloud.smarthome.service.rest.model.UserRoomNo;
import com.biencloud.smarthome.service.rest.model.UserRoomNoVo;
import com.biencloud.smarthome.service.rest.service.IUserRoomNoService;

@Service
public class UserRoomNoService extends BaseResService<UserRoomNo> implements
		IUserRoomNoService {

	@Autowired
	private UserRoomNoMapper userRoomNoMapper;
	
	@Autowired
	private DeviceMapper deviceMapper;
	
	@Override
	public BaseMapper<UserRoomNo> getBaseMapper() {
		return userRoomNoMapper;
	}


	@Override
	public ResultEntity<String> remove(UserRoomNo userRoomNo) {
		int i = userRoomNoMapper.remove(userRoomNo);
		return proccessResultEntity(i>0?ResultEntity.SUCCESS:ResultEntity.FAILD, "", "");
	}


	@Override
	public ResultEntity<String> save(UserRoomNo lfUserDevice) {
		ResultEntity<String> re = new ResultEntity<String>();
		List<UserRoomNo> list = userRoomNoMapper.getList(lfUserDevice);
		if(list!=null && list.size()>0){
			re.setCode(ResultEntity.ALREADY_EXIST);
			re.setMessage("alreay exist");
			return re;
		}else{
			userRoomNoMapper.insert(lfUserDevice);
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
		}
		return re;
	}


	@Override
	public List<UserRoomNo> getList(UserRoomNo userRoomNo) {
		return userRoomNoMapper.getList(userRoomNo);
	}


	@Override
	public List<UserRoomNo> getListByDeviceNo(String deviceNo) {
		List<UserRoomNo> list = new ArrayList<UserRoomNo>();
		Device device = deviceMapper.getDeviceByDeviceNo(deviceNo);
		if(device==null){
			return list;
		}
		if(Constants.UNIT_DOOR_DEVICE.equals(device.getDeviceType())){
			UserRoomNo userRoomNo = new UserRoomNo();
			userRoomNo.setUnitId(device.getUnitId());
			list = userRoomNoMapper.getList(userRoomNo);
		}
		if(Constants.FENCE_DEVICE.equals(device.getDeviceType())){
			UserRoomNo userRoomNo = new UserRoomNo();
			userRoomNo.setDistrictId(device.getDistrictId());
			list = userRoomNoMapper.getList(userRoomNo);
		}
		return list;
	}


	@Override
	public List<Object> getListMap(UserRoomNo record) {
		// TODO Auto-generated method stub
		return userRoomNoMapper.getListMap(record);
	}


	@Override
	public ResultEntity<String> updateStatusBySipid(UserRoomNo record) {
		int i = userRoomNoMapper.updateStatusBySipid(record);
		return proccessResultEntity(i>0?ResultEntity.SUCCESS:ResultEntity.FAILD, "", "");
	}


	@Override
	public void deleteByDeviceNo(String deviceNo) {
		UserRoomNo userRoomNo = new UserRoomNo();
		userRoomNo.setDeviceNo(deviceNo);
		userRoomNoMapper.remove(userRoomNo);
	}


	@Override
	public UserRoomNo getForOne(Long id) {
		// TODO Auto-generated method stub
		return userRoomNoMapper.getForOne(id);
	}


	@Override
	public UserRoomNo queryBySipid(String sipid,String roomNo) {
		// TODO Auto-generated method stub
		return userRoomNoMapper.queryBySipid(sipid,roomNo);
	}

	@Override
	public List<UserRoomNo> showBoundSip(String roomNo) {
		// TODO Auto-generated method stub
		return userRoomNoMapper.showBoundSip(roomNo);
	}


	@Override
	public List<UserRoomNo> showBoundSipByHouseId(Long houseId) {
		// TODO Auto-generated method stub
		return userRoomNoMapper.showBoundSipByHouseId(houseId);
	}


	@Override
	public List<UserRoomNo> queryByHouseId(UserRoomNo record) {
		// TODO Auto-generated method stub
		return userRoomNoMapper.queryByHouseId(record);
	}
	
	@Override
	public List<UserRoomNo> queryByHouseIdAndSipId(UserRoomNo record) {
		List<UserRoomNo> list = new ArrayList<UserRoomNo>();
		List<UserRoomNo> userRoomNos = userRoomNoMapper.getList(record);
		if(userRoomNos==null || userRoomNos.size()==0){
			return list;
		}
		for(UserRoomNo u : userRoomNos){
			Device device = deviceMapper.getDeviceByDeviceNo(u.getDeviceNo());
			if(device==null){
				return list;
			}
			UserRoomNo urm = new UserRoomNo();
			urm.setUnitId(device.getUnitId());
			urm.setSipid(record.getSipid());
			List<UserRoomNo> tempList = userRoomNoMapper.getList(record);
			list.addAll(tempList);
		}
		Set<UserRoomNo> set = new LinkedHashSet<UserRoomNo>(list);
		list.clear();
		list.addAll(set);
		return list;
	}

	@Override
	public List<UserRoomNoVo> getListMapBySip(UserRoomNo record) {
		List<UserRoomNoVo> list = new ArrayList<UserRoomNoVo>();
		if(record==null){
			return list;
		}
		List<UserRoomNo> userRoomNos = userRoomNoMapper.getList(record);
		if(userRoomNos==null || userRoomNos.size()==0){
			return list;
		}
		for(UserRoomNo u : userRoomNos){
			Device device = deviceMapper.getDeviceByDeviceNo(u.getDeviceNo());
			if(device==null){
				return list;
			}
			record.setUnitId(device.getUnitId());
			record.setDistrictId(device.getDistrictId());
			List<UserRoomNoVo> tempList = userRoomNoMapper.getListMapByEntity(record);
			list.addAll(tempList);
		}
		 Set<UserRoomNoVo> set = new LinkedHashSet<UserRoomNoVo>(list);
		list.clear();
		list.addAll(set);
		return list;
	}
}  
