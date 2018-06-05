package com.smarthome.socket.service.business.service.device.impl;

import com.smarthome.socket.common.constant.PubConstant;
import com.smarthome.socket.common.util.StringUtil;
import com.smarthome.socket.service.business.adapater.RegistReq;
import com.smarthome.socket.service.business.service.BaseService;
import com.smarthome.socket.service.business.service.device.IDeviceService;
import com.smarthome.socket.wsservice.stub.AppLoginVO;
import com.smarthome.socket.wsservice.stub.AppVO;


/**
 * 
 * 类名称：DeviceServiceImpl 类描述： 设备业务处理接口实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-4 下午10:14:03
 */
public class DeviceServiceImpl extends BaseService implements IDeviceService {

	
	@Override
	public String [] login(RegistReq req) throws Exception {
		String [] obj=new String[6];
		obj[0]=req.getDeviceNo();
		obj[1]=PubConstant.CLIENT_REGEIST_FAILURE;
		if (req.getDeviceMac() != null) {			
			AppLoginVO alv=new AppLoginVO();
			alv.setAreaNo(StringUtil.coverEmptyToNull(req.getAreaNo()));
			alv.setBuildingNo(StringUtil.coverEmptyToNull(req.getRidgepole()));
			alv.setDeviceName(StringUtil.coverEmptyToNull(req.getDeviceName()));
			alv.setDeviceNo(StringUtil.coverEmptyToNull(req.getDeviceNo()));
			alv.setDevicePassword(StringUtil.coverEmptyToNull(req.getDevicePassword()));
			alv.setDeviceType(StringUtil.coverEmptyToNull(req.getDeviceType()));
			alv.setHouseNo(StringUtil.coverEmptyToNull(req.getHouseNo()));
			alv.setIp(StringUtil.coverEmptyToNull(req.getDeviceIp()));
			alv.setMac(StringUtil.coverEmptyToNull(req.getDeviceMac()));
			alv.setRegionNo(StringUtil.coverEmptyToNull(req.getEstateNo()));
			alv.setUnitNo(StringUtil.coverEmptyToNull(req.getUnitNo()));
			alv.setIpState(StringUtil.coverEmptyToNull(req.getIpState()));
			alv.setHouseState(StringUtil.coverEmptyToNull(req.getHouseState()));
			alv.setVersion(StringUtil.coverEmptyToNull(req.getVersion()));
			alv.setPosition(StringUtil.coverEmptyToNull(req.getPosition()));
			alv.setSipid(StringUtil.coverEmptyToNull(req.getSipid()));
			
			AppVO appVO = this.getSmartHomeTcpService().saveOrUpdateDevice(alv);
			if(appVO!=null)
			{
				obj[0]=appVO.getDeviceNo();
				obj[1]=appVO.getResult();
				obj[2]=appVO.getDataServerIP();
				obj[3]=appVO.getFileServerIP();
				obj[4]=appVO.getSocketServerIP();
				obj[5]=appVO.getConflictMac();
			}
			
		}
		return obj;
	}

	@Override
	public void exit(String mac) throws Exception {
		if (mac != null) {
			this.getSmartHomeTcpService().deviceOutLine(mac);
			
		}

	}

	@Override
	public void updateDeviceStatusForAll(String status) {
		this.getSmartHomeTcpService().updateDeviceStatusForAll(status);
		
	}

	@Override
	public void updateDeviceStatusByMac(String status, String mac) {		
		this.getSmartHomeTcpService().updateDeviceStatusByMac(status, mac);
	}

	@Override
	public String[] easyLogin(RegistReq req) throws Exception {
		String [] obj=new String[6];
		obj[0]=req.getDeviceNo();
		obj[1]=PubConstant.CLIENT_REGEIST_FAILURE;
		if (req.getDeviceMac() != null) {			
			AppLoginVO alv=new AppLoginVO();
			alv.setAreaNo(StringUtil.coverEmptyToNull(req.getAreaNo()));
			alv.setBuildingNo(StringUtil.coverEmptyToNull(req.getRidgepole()));
			alv.setDeviceName(StringUtil.coverEmptyToNull(req.getDeviceName()));
			alv.setDeviceNo(StringUtil.coverEmptyToNull(req.getDeviceNo()));
			alv.setDevicePassword(StringUtil.coverEmptyToNull(req.getDevicePassword()));
			alv.setDeviceType(StringUtil.coverEmptyToNull(req.getDeviceType()));
			alv.setHouseNo(StringUtil.coverEmptyToNull(req.getHouseNo()));
			alv.setIp(StringUtil.coverEmptyToNull(req.getDeviceIp()));
			alv.setMac(StringUtil.coverEmptyToNull(req.getDeviceMac()));
			alv.setRegionNo(StringUtil.coverEmptyToNull(req.getEstateNo()));
			alv.setUnitNo(StringUtil.coverEmptyToNull(req.getUnitNo()));
			alv.setIpState(StringUtil.coverEmptyToNull(req.getIpState()));
			alv.setHouseState(StringUtil.coverEmptyToNull(req.getHouseState()));
			alv.setVersion(StringUtil.coverEmptyToNull(req.getVersion()));
			AppVO appVO = this.getSmartHomeTcpService().saveOrUpdateDeviceByEasyLogin(alv);
			if(appVO!=null)
			{
				obj[0]=appVO.getDeviceNo();
				obj[1]=appVO.getResult();
				obj[2]=appVO.getDataServerIP();
				obj[3]=appVO.getFileServerIP();
				obj[4]=appVO.getSocketServerIP();
				obj[5]=appVO.getConflictMac();
			}
			
		}
		return obj;
	}

	

}
