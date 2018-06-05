package com.biencloud.smarthome.service.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biencloud.smarthome.service.app.service.IAppService;
import com.biencloud.smarthome.service.app.vo.AppLoginVO;
import com.biencloud.smarthome.service.app.vo.AppVO;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.constants.AppConstants;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.rest.mapper.DeviceMapper;
import com.biencloud.smarthome.service.rest.model.Device;
import com.biencloud.smarthome.service.rest.model.DeviceLoginInfo;
import com.biencloud.smarthome.service.rest.model.DeviceSip;
import com.biencloud.smarthome.service.rest.service.IDeviceSipService;
import com.biencloud.smarthome.service.rest.service.IDevicesService;
import com.biencloud.smarthome.service.sip.mapper.SipRegisterMapper;
import com.biencloud.smarthome.service.sip.model.SipRegister;
import com.biencloud.smarthome.service.sip.service.ISipRegisterService;

@Service
public class DevicesService extends BaseResService<Device> implements
		IDevicesService {

	@Autowired
	private DeviceMapper deviceMapper;
	
	@Autowired
	private IDeviceSipService deviceSipService;
	
	@Autowired
	private ISipRegisterService sipRegisterService;
	
	private IAppService appService;
	
	private IDeviceService deviceServiceImpl;

	@Override
	public BaseMapper<Device> getBaseMapper() {
		return deviceMapper;
	}

	@Override
	public ResultEntity<String> verifyDevice(Long deviceId, String password) {
		ResultEntity<String> re = new ResultEntity<String>(ResultEntity.FAILD,"","");
		if(deviceId!=null && StringUtils.isNotBlank(password)){
			Device d = deviceMapper.getForOne(deviceId);
			if(d==null){
				re.setCode(ResultEntity.NOT_EXIST);
				return re;
			}
			if("password".equals(d.getDevicePwd())){
				re.setCode(ResultEntity.SUCCESS);
				re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			}else{
				re.setCode(ResultEntity.PASSWD_ERROR);
			}
		}
		return re;
	}

	@Override
	public ResultEntity<Long> getIdByDeviceNo(String deviceNo) {
		ResultEntity<Long> re = new ResultEntity<Long>();
		Device device = deviceMapper.getDeviceByDeviceNo(deviceNo);
		if(device==null){
			return re;
		}
		re.setCode(ResultEntity.SUCCESS);
		re.setData(device.getDeviceId());
		return re;
	}

	@Override
	public ResultEntity<Device> addForEntity(Device device) {
		// TODO Auto-generated method stub
		return null;
	}

	public IAppService getAppService() {
		return appService;
	}

	public void setAppService(IAppService appService) {
		this.appService = appService;
	}

	public IDeviceService getDeviceServiceImpl() {
		return deviceServiceImpl;
	}

	public void setDeviceServiceImpl(IDeviceService deviceServiceImpl) {
		this.deviceServiceImpl = deviceServiceImpl;
	}

	@Override
	public ResultEntity<AppLoginVO> login(AppLoginVO appLoginVO) {
		ResultEntity<AppLoginVO> re = new ResultEntity<AppLoginVO>();
		AppVO appVO = appService.login(appLoginVO);
		if(appVO!=null && AppConstants.SUCCESSFULL.equals(appVO.getResult())){
			String deviceNo = appVO.getDeviceNo();
			String sipid = null;
			String sipPwd = null;
			SipRegister sipRegister = null;
			Device device = deviceMapper.getDeviceByDeviceNo(deviceNo);
			if(device.getSipid()!=null){
				ResultEntity<SipRegister> sipResult = sipRegisterService.getByUsername(device.getSipid());
				if(sipResult!=null){
					sipRegister = sipResult.getData();
					sipid = sipRegister.getUsername();
					sipPwd = sipRegister.getPassword();
				}
			}else{
				ResultEntity<SipRegister> result = sipRegisterService.register(new SipRegister());
				if(ResultEntity.SUCCESS == result.getCode()){
					sipRegister = result.getData();
					sipid = sipRegister.getUsername();
					sipPwd = sipRegister.getPassword();
					device.setSipid(sipid);
					deviceMapper.update(device);
				}else{
					re.setMessage("sip registration failed");
				}
			}
			if(sipRegister==null){
				sipRegister = new SipRegister();
			}
			appLoginVO.setSipId(sipid);
			appLoginVO.setSipPwd(sipPwd);
			appLoginVO.setDeviceNo(appVO.getDeviceNo());
			re.setCode(ResultEntity.SUCCESS);
			re.setData(appLoginVO);
		}else{
			re.setMessage(appVO.getResult());
		}
		return re;
	}

}  
