package com.biencloud.smarthome.service.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.mapper.DeviceConfigMapper;
import com.biencloud.smarthome.service.rest.model.DeviceConfig;
import com.biencloud.smarthome.service.rest.service.IDeviceConfigService;

@Service
public class DeviceConfigService extends BaseResService<DeviceConfig> implements
		IDeviceConfigService {

	@Autowired
	private DeviceConfigMapper deviceConfigMapper;
	
	@Override
	public BaseMapper<DeviceConfig> getBaseMapper() {
		return deviceConfigMapper;
	}

	@Override
	public ResultEntity<String> addForEntity(DeviceConfig deviceConfig) {
		ResultEntity<String> re = new ResultEntity<String>();
		if(deviceConfig==null || deviceConfig.getDeviceId()==null){
			return re;
		}
		DeviceConfig dc = deviceConfigMapper.getForOne(deviceConfig.getDeviceId());
		if(dc!=null){
			re.setCode(ResultEntity.ALREADY_EXIST);
			return re;
		}else{
			int i = deviceConfigMapper.insert(deviceConfig);
			return this.proccessResultEntity(i>0?ResultEntity.SUCCESS:ResultEntity.FAILD, "", "");
		}
	}

	@Override
	public ResultEntity<DeviceConfig> getByDeviceId(Long deviceId) {
		ResultEntity<DeviceConfig> re = new ResultEntity<DeviceConfig>();
		DeviceConfig dc = deviceConfigMapper.getForOne(deviceId);
		if(dc!=null){
			re.setData(dc);
			re.setCode(ResultEntity.SUCCESS);
		}else{
			re.setCode(ResultEntity.NOT_EXIST);
		}
		return re;
	}

	@Override
	public ResultEntity<String> updateAndSave(DeviceConfig deviceConfig) {
		ResultEntity<String> re = new ResultEntity<String>();
		if(deviceConfig==null || deviceConfig.getDeviceId()==null){
			return re;
		}
		DeviceConfig dc = deviceConfigMapper.getForOne(deviceConfig.getDeviceId());
		if(dc!=null){
			deviceConfigMapper.updateOnActive(deviceConfig);
			re.setCode(ResultEntity.SUCCESS);
			return re;
		}else{
			int i = deviceConfigMapper.insert(deviceConfig);
			return this.proccessResultEntity(i>0?ResultEntity.SUCCESS:ResultEntity.FAILD, "", "");
		}
	}

}  
