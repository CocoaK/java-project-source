package com.biencloud.smarthome.web.appdata.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.biencloud.smarthome.web.appdata.constant.AppDataConstant;
import com.biencloud.smarthome.web.appdata.json.Json;
import com.biencloud.smarthome.web.appdata.json.PasswordJson;
import com.biencloud.smarthome.web.appdata.service.IDevicePasswordService;
import com.biencloud.smarthome.web.appdata.vo.DevicePasswordVO;
import com.biencloud.smarthome.web.appdata.vo.PasswordVO;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.util.JsonUtil;
import com.biencloud.smarthome.web.device.service.IDeviceService;
import com.biencloud.smarthome.web.wsclient.stub.Device;
import com.biencloud.smarthome.web.wsclient.stub.DevicePassword;

/**
 * 类名称：DevicePasswordServiceImpl 
 * 类描述： 设备开锁密码业务实现类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-11-27 上午11:00:05
 */
public class DevicePasswordServiceImpl extends BaseService<DevicePasswordVO> implements IDevicePasswordService{

	private IDeviceService deviceService;
	
	@Override
	public Json save(String jsonString) {
		Json json = new Json();
		json.setCode(AppDataConstant.FAILTRUE);
		JSONObject jsonObj = JsonUtil.jsonStringToJsonObject(jsonString);
			if(jsonObj!=null){
			String password = jsonObj.getString("password");//密码
			String deviceNo = jsonObj.getString("deviceNo");//本设备的设备编号
			Device device = getSmartHomeService().queryDeviceByCode(deviceNo);
			if(device == null){
				return json;
			}
			DevicePassword dp = new DevicePassword();
			dp.setPassword(password);
			dp.setDevice(device);
			json.setDeviceNo(deviceNo);
			getSmartHomeService().saveAndPushDevicePassword(dp);	//推送
			json.setCode(AppDataConstant.SUCCESS);	//成功
		}
		return json;
	}


	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}


	@Override
	public PasswordJson queryPasswordByTargetDeviceNo(String deviceNo) {
		PasswordJson pj = new PasswordJson();
		List<PasswordVO> list = new ArrayList<PasswordVO>();
		List<DevicePassword> passwordList = getSmartHomeService().queryPasswordByTargetDeviceNo(deviceNo);
		if(passwordList !=null && passwordList.size()!=0){
			for(DevicePassword devicePassword : passwordList){
				PasswordVO passwordVO = new PasswordVO();
				if(devicePassword.getDevice()!=null && devicePassword.getDevice().getHousingDistrictInfo()!=null)	//小区
					passwordVO.setDistrictNo(devicePassword.getDevice().getHousingDistrictInfo().getCode());
				if(devicePassword.getDevice()!=null && devicePassword.getDevice().getHousingDistrictRegionInfo()!=null)	//区域
					passwordVO.setAreaNo(devicePassword.getDevice().getHousingDistrictRegionInfo().getCode());	
				if(devicePassword.getDevice()!=null && devicePassword.getDevice().getRegionBuildingInfo()!=null)	//楼栋
					passwordVO.setBuildingNo(devicePassword.getDevice().getRegionBuildingInfo().getCode());
				if(devicePassword.getDevice()!=null && devicePassword.getDevice().getBuildingCellInfo()!=null)	//单元
					passwordVO.setUnitNo(devicePassword.getDevice().getBuildingCellInfo().getCode());
				if(devicePassword.getDevice()!=null && devicePassword.getDevice().getCellHouseholdInfo()!=null)	//房号
					passwordVO.setRoomNo(devicePassword.getDevice().getCellHouseholdInfo().getCode());
				passwordVO.setPassword(devicePassword.getPassword());
				list.add(passwordVO);
			}
		}
		pj.setPasswordList(list);
		pj.setDeviceNo(deviceNo);
		return pj;
	}

}
