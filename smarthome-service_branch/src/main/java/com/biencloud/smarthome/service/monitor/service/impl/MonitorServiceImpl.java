package com.biencloud.smarthome.service.monitor.service.impl;

import java.util.Date;
import java.util.List;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.constants.PushKindConstants;
import com.biencloud.smarthome.service.common.utils.CryptoUtils;
import com.biencloud.smarthome.service.device.model.DevicePassword;
import com.biencloud.smarthome.service.device.service.IDevicePasswordService;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.monitor.service.IMonitorService;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.push.service.IPushService;

/**
 * 
 * 类名称：MonitorServiceImpl 类描述：监控业务接口实现类
 * 
 * @author kouy
 * @version 0.1
 * @date 2012-6-4 下午9:57:32
 */
@SuppressWarnings("rawtypes")
public class MonitorServiceImpl extends BaseService implements IMonitorService {
	private IPushService pushService;
	private IDeviceService deviceService;
	private IDevicePasswordService devicePasswordService;

	/**
	 * 发送开锁请求需要调用Push接口(IPushService.insertPush), 将信息推送出去(pushName: 随便, pushKind: 常量, pushContent: 目标机编码, pushClientId: 个人机编码)
	 * 
	 * @author: jsun  
	 * @version: 1.0 2012-6-5
	 * @return
	 */
	@Override
	public boolean remoteUnlock(String targetDeviceCode, String personalDeviceCode,
			String personalDevicePwd,String userType) {
		// 包装推送请求
		Push push = new Push();
		push.setPushName(PushKindConstants.PUSH_OPEN_DOOR_COMMAND_KIND);
		push.setPushKind(PushKindConstants.PUSH_OPEN_DOOR_COMMAND_KIND);
		push.setPushContent("targetDeviceNo:"+targetDeviceCode);
		push.setPushClientId(targetDeviceCode);
		push.setAddTime(new Date());
		// 如果是物管用户，则验证设备本身的密码。验证设备密码是否正确, 才能授权开锁,
//		if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){
//			if (deviceService.validatePasswd(targetDeviceCode, CryptoUtils.encodeByMD5(personalDevicePwd))) {
//				// 将开锁请求推送出去
//				return pushService.insertPush(push);
//			} 
//		}
		//如果是业主用户，则验证业主设置的开锁密码。
		//if(Constants.LOGIN_USER_TYPE_OWNER.equals(userType)){
			if(devicePasswordService.validatePasswd(personalDeviceCode, personalDevicePwd)){
				// 将开锁请求推送出去
				return pushService.insertPush(push);
			}
		//}
		return false;
	}

	public IPushService getPushService() {
		return pushService;
	}
	public void setPushService(IPushService pushService) {
		this.pushService = pushService;
	}
	public IDeviceService getDeviceService() {
		return deviceService;
	}
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public IDevicePasswordService getDevicePasswordService() {
		return devicePasswordService;
	}

	public void setDevicePasswordService(IDevicePasswordService devicePasswordService) {
		this.devicePasswordService = devicePasswordService;
	}
}
