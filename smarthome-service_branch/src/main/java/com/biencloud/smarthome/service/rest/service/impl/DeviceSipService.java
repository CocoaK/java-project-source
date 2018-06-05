package com.biencloud.smarthome.service.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.mapper.DeviceSipMapper;
import com.biencloud.smarthome.service.rest.model.DeviceSip;
import com.biencloud.smarthome.service.rest.service.IDeviceSipService;

@Service
public class DeviceSipService extends BaseResService<DeviceSip> implements IDeviceSipService{
	
	@Autowired
	private DeviceSipMapper deviceSipMapper;
	
	@Override
	public BaseMapper<DeviceSip> getBaseMapper() {
		return deviceSipMapper;
	}

	@Override
	public List<DeviceSip> queryList(DeviceSip deviceSip) {
		
		return deviceSipMapper.getList(deviceSip);
	}

	@Override
	public ResultEntity<String> delete(DeviceSip deviceSip) {
		if(deviceSip==null){
			return null;
		}
		int i = deviceSipMapper.delete(deviceSip.getId());
		return super.proccessResultEntity(i > 0 ? ResultEntity.SUCCESS : ResultEntity.FAILD,"","");
	}
	
	@Override
	public ResultEntity<String> addForResultEntity(DeviceSip deviceSip){
		if(deviceSip==null)
			return null;

		//检查二维码是否已经存在
		DeviceSip sip = new DeviceSip();
		sip.setSipid(deviceSip.getSipid());
		sip.setDeviceId(deviceSip.getDeviceId());
		if(sip.getDeviceId()!=null){
			//查询sipuid,doorsipid,roomNo二维码
			List<DeviceSip> list = this.queryList(sip);
			//如果存在则返回
			if(list != null && list.size()>0){
				return new ResultEntity<String>(ResultEntity.FAILD,"already exist","");
			}
		}
		
		ResultEntity<String> re = proccessResultEntity(add(deviceSip) > 0 ? ResultEntity.SUCCESS
				: ResultEntity.FAILD, "", "");
		return re;
	}

	@Override
	public ResultEntity<String> deleteByDeviceId(String deviceId) {
		if(StringUtils.isBlank(deviceId)){
			return null;
		}
		int i = deviceSipMapper.deleteByDeviceId(deviceId);
		return super.proccessResultEntity(i > 0 ? ResultEntity.SUCCESS : ResultEntity.FAILD,"","");
	}
}
