package com.biencloud.smarthome.web.device.action;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.vo.ResultEntity;
import com.biencloud.smarthome.web.device.service.IDeviceSipService;
import com.biencloud.smarthome.web.device.vo.DeviceSipVO;

@SuppressWarnings("serial")
public class DeviceSipAction extends BaseAction<DeviceSipAction>{
	
	private DeviceSipVO deviceSipVO;
	private IDeviceSipService deviceSipService;

	public String addInput() throws Exception{
		return SUCCESS;
	}

	
	//添加二维码

	public String add() throws Exception{
		//sipRegister = new SipRegister();
		if(deviceSipVO!=null){
			ResultEntity<String> sip = deviceSipService.add(deviceSipVO);
		}
		return SUCCESS;
	}
	
	//删除二维码
	public String deleteSip() throws Exception{
		deviceSipService.delete(deviceSipVO);
		if(deviceSipVO!=null){
			deviceSipVO.setSipid(null);
		}
		return SUCCESS;
	}


	public DeviceSipVO getDeviceSipVO() {
		return deviceSipVO;
	}


	public void setDeviceSipVO(DeviceSipVO deviceSipVO) {
		this.deviceSipVO = deviceSipVO;
	}


	public IDeviceSipService getDeviceSipService() {
		return deviceSipService;
	}


	public void setDeviceSipService(IDeviceSipService deviceSipService) {
		this.deviceSipService = deviceSipService;
	}

}
