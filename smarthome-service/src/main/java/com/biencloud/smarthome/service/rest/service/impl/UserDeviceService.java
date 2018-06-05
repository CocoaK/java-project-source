package com.biencloud.smarthome.service.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.service.rest.mapper.DeviceMapper;
import com.biencloud.smarthome.service.rest.mapper.UserDeviceMapper;
import com.biencloud.smarthome.service.rest.mapper.UserMapper;
import com.biencloud.smarthome.service.rest.model.Device;
import com.biencloud.smarthome.service.rest.model.DeviceUserVo;
import com.biencloud.smarthome.service.rest.model.User;
import com.biencloud.smarthome.service.rest.model.UserDeviceKey;
import com.biencloud.smarthome.service.rest.service.IUserDeviceService;

@Service
public class UserDeviceService extends BaseResService<UserDeviceKey> implements
		IUserDeviceService {

	@Autowired
	private UserDeviceMapper userDeviceMapper;
	
	@Autowired
	private DeviceMapper deviceMapper;
	
	@Autowired
	private ICellHouseholdInfoService cellHouseholdInfoService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public BaseMapper<UserDeviceKey> getBaseMapper() {
		return userDeviceMapper;
	}


	@Override
	public List<Device> queryList(Long userId) {
		return userDeviceMapper.getDeviceListByUserId(userId);
	}
	
	@Override
	public List<Device> queryDeviceList(Long userId) {
		List<Device> devices = userDeviceMapper.getDevicesByUserId(userId);
		if(devices!=null && devices.size()>0){
			for(Device d:devices){
				if(StringUtils.isNotBlank(d.getRoomId())){
					String roomNo = cellHouseholdInfoService.getFullHouseNo(d.getRoomId());
					d.setRoomNo(roomNo);
				}
			}
		}
		return devices;
	}

	@Override
	public ResultEntity<String> unbound(Long userId, String deviceNo) {
		ResultEntity<String> re = new ResultEntity<String>(ResultEntity.FAILD,"","");
		Device device = deviceMapper.getDeviceByDeviceNo(deviceNo);
		if(device==null){
			re.setCode(ResultEntity.NOT_EXIST);
		}else{
			UserDeviceKey key = new UserDeviceKey(userId,device.getDeviceId());
			userDeviceMapper.delete(key);
			re.setCode(ResultEntity.SUCCESS);
		}
		return re;
	}

	@Override
	public ResultEntity<String> deleteByUserId(String userId) {
		UserDeviceKey key = new UserDeviceKey();
		key.setUserId(new Long(userId));
		int i = userDeviceMapper.delete(key);
		return super.proccessResultEntity(i>0?ResultEntity.SUCCESS:ResultEntity.FAILD, "", "");
	}

	@Override
	public ResultEntity<String> bound(Long userId, String deviceNo,
			String devicePwd) {
		ResultEntity<String> re = new ResultEntity<String>(ResultEntity.FAILD,"","");
		User user = userMapper.getForOne(userId);
		if(user == null){
			re.setCode(ResultEntity.NOT_EXIST);
			re.setMessage("user not exist!");
			return re;
		}
		if(StringUtils.isNotBlank(deviceNo)){
			Device d = deviceMapper.getDeviceByDeviceNo(deviceNo);
			if(d==null){
				re.setCode(ResultEntity.NOT_EXIST);
				return re;
			}
//			if(devicePwd.equals(d.getDevicePwd())){
				UserDeviceKey uk = new UserDeviceKey();
				uk.setUserId(userId);
				uk.setDeviceId(d.getDeviceId());
				List<UserDeviceKey> keys = userDeviceMapper.getList(uk);
				if(keys!=null && keys.size()>0){
					re.setCode(ResultEntity.ALREADY_EXIST);
					return re;
				}else{
					userDeviceMapper.insert(uk);
				}
				re.setCode(ResultEntity.SUCCESS);
				re.setMessage(ResultEntity.MESSAGE_SUCCESS);
//			}else{
//				re.setCode(ResultEntity.PASSWD_ERROR);
//			}
		}
		return re;
	}
	
	@Override
	public ResultEntity<Device> boundDevice(Long userId, String deviceNo,
			String devicePwd) {
		ResultEntity<Device> re = new ResultEntity<Device>(ResultEntity.FAILD,"",null);
		User user = userMapper.getForOne(userId);
		if(user == null){
			re.setCode(ResultEntity.NOT_EXIST);
			re.setMessage("user not exist!");
			return re;
		}
		if(StringUtils.isNotBlank(deviceNo)){
			Device d = deviceMapper.getDeviceByDeviceNo(deviceNo);
			if(d==null){
				re.setCode(ResultEntity.NOT_EXIST);
				return re;
			}
			if(StringUtils.isNotBlank(d.getRoomId())){
				String roomNo = cellHouseholdInfoService.getFullHouseNo(d.getRoomId());
				d.setRoomNo(roomNo);
			}
				UserDeviceKey uk = new UserDeviceKey();
				uk.setUserId(userId);
				uk.setDeviceId(d.getDeviceId());
				List<UserDeviceKey> keys = userDeviceMapper.getList(uk);
				if(keys!=null && keys.size()>0){
					re.setCode(ResultEntity.ALREADY_EXIST);
					re.setData(d);
					return re;
				}else{
					userDeviceMapper.insert(uk);
				}
				re.setCode(ResultEntity.SUCCESS);
				re.setMessage(ResultEntity.MESSAGE_SUCCESS);
				re.setData(d);
		}
		return re;
	}


	@Override
	public ResultEntity<String> searchBound(Long userId, String deviceNo) throws InterruptedException {
		ResultEntity<String> re = new ResultEntity<String>(ResultEntity.FAILD,"","");
		User user = userMapper.getForOne(userId);
		if(user == null){
			re.setCode(ResultEntity.NOT_EXIST);
			re.setMessage("user not exist!");
			return re;
		}
		if(StringUtils.isNotBlank(deviceNo)){
			Device device = new Device();
			device.setDeviceCode(deviceNo);
			device.setDeviceStatus(Constants.DEVICE_ONLINE);
			List<Device> list = deviceMapper.getForList(device);
			if(list==null || list.size()<1){
				re.setCode(ResultEntity.NOT_EXIST);
				return re;
			}
			UserDeviceKey uk = new UserDeviceKey();
			uk.setUserId(userId);
			uk.setDeviceId(list.get(0).getDeviceId());
			List<UserDeviceKey> keys = userDeviceMapper.getList(uk);
			uk.setType(0);
			if(keys!=null && keys.size()>0){
				re.setCode(ResultEntity.ALREADY_EXIST);
				return re;
			}else {
//				//查询此设备是否被绑定过
//				UserDeviceKey userDevice = new UserDeviceKey();
//				userDevice.setDeviceId(list.get(0).getDeviceId());
//				List<UserDeviceKey> li = userDeviceMapper.getList(userDevice);
//				if(li==null || li.size()<1){
//					//如果设备没有被绑定过
//					uk.setType(1);	//管理员用户
//				}
				uk.setType(1);	//管理员用户
				userDeviceMapper.insert(uk);
			}
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
		}
		return re;
	}


	@Override
	public List<DeviceUserVo> getDeviceUserList(UserDeviceKey userDeviceKey) {
		// TODO Auto-generated method stub
		return userDeviceMapper.getDeviceUserList(userDeviceKey);
	}

	@Override
	public List<DeviceUserVo> getUserListByDeviceId(UserDeviceKey userDeviceKey) {
		// TODO Auto-generated method stub
		return userDeviceMapper.getUserListByDeviceId(userDeviceKey);
	}

	@Override
	public List<UserDeviceKey> getList(UserDeviceKey userDeviceKey) {
		// TODO Auto-generated method stub
		return userDeviceMapper.getList(userDeviceKey);
	}

}  
