package com.biencloud.smarthome.cxfservice.service.impl;

import java.util.Map;
import java.util.List;
import com.biencloud.smarthome.cxfservice.service.SmartHomeTcpService;
import com.biencloud.smarthome.service.app.service.IAppService;
import com.biencloud.smarthome.service.app.vo.AppLoginVO;
import com.biencloud.smarthome.service.app.vo.AppVO;
import com.biencloud.smarthome.service.deivceno.service.IDeviceNoService;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.log.model.ClientLog;
import com.biencloud.smarthome.service.log.service.IClientLogService;
import com.biencloud.smarthome.service.monitor.service.ISceneMonitorService;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.push.service.IPushFinishService;
import com.biencloud.smarthome.service.push.service.IPushService;


/**
 * 
 * 类名称：SmartHomeTcpServiceImpl 类描述： 为tcp socket发布的服务
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-9 下午2:08:38
 */
public class SmartHomeTcpServiceImpl implements SmartHomeTcpService {
	private IPushService pushService;
	private IPushFinishService pushFinishService;
	private IDeviceService deviceService;
	private IDeviceNoService deviceNoService;
	private IAppService appService;
	private ISceneMonitorService sceneMonitorService;
	private IClientLogService clientLogService;

	@Override
	public List<Push> listPush(String whereCondition) {
		// TODO Auto-generated method stub
		return pushService.listPush(whereCondition);
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return pushService.deleteById(id);
	}

	@Override
	public boolean deleteByEntity(Push push) {
		// TODO Auto-generated method stub
		return pushService.deleteByEntity(push);
	}

	@Override
	public boolean insertPushFinish(Long pushId) {
		// TODO Auto-generated method stub
		return pushFinishService.savePushFinish(pushId);
	}

	@Override
	public Push findById(Long id) {
		// TODO Auto-generated method stub
		return pushService.findById(id);
	}

	@Override
	public List<Push> listPushByClientId(String ids) {
		// TODO Auto-generated method stub
		return pushService.listPushByClientID(ids);
	}

	

	@Override
	public AppVO saveOrUpdateDevice(AppLoginVO loginInfo) {
		
		return appService.login(loginInfo);
	}

	@Override
	public boolean deviceOutLine(String deviceNo) {
		// TODO Auto-generated method stub
		return appService.outLine(deviceNo);
	}
	
	@Override
	public boolean saveClientLog(ClientLog clientLog) {
		
		return clientLogService.saveClientLog(clientLog);
	}
	@Override
	public void updateDeviceStatusForAll(String status) {
		// TODO Auto-generated method stub
		 appService.updateDeviceStatusForAll(status);
	}
	@Override
	public void updateDeviceStatusByMac(String status,String mac) {
		// TODO Auto-generated method stub
		 appService.updateDeviceStatusByMac(status,mac);
	}
	@Override
	public Map<String, List<Push>> listPushByClientIDForMap(String ids,String pushKinds) {
		// TODO Auto-generated method stub
		return pushService.listPushByClientIDForMap(ids,pushKinds);
	}
	
	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public IDeviceNoService getDeviceNoService() {
		return deviceNoService;
	}

	public void setDeviceNoService(IDeviceNoService deviceNoService) {
		this.deviceNoService = deviceNoService;
	}

	public IPushService getPushService() {
		return pushService;
	}

	public void setPushService(IPushService pushService) {
		this.pushService = pushService;
	}

	public IPushFinishService getPushFinishService() {
		return pushFinishService;
	}

	public void setPushFinishService(IPushFinishService pushFinishService) {
		this.pushFinishService = pushFinishService;
	}

	public IAppService getAppService() {
		return appService;
	}

	public void setAppService(IAppService appService) {
		this.appService = appService;
	}

	public ISceneMonitorService getSceneMonitorService() {
		return sceneMonitorService;
	}

	public void setSceneMonitorService(ISceneMonitorService sceneMonitorService) {
		this.sceneMonitorService = sceneMonitorService;
	}

	public IClientLogService getClientLogService() {
		return clientLogService;
	}

	public void setClientLogService(IClientLogService clientLogService) {
		this.clientLogService = clientLogService;
	}

	@Override
	public String queryCronTimeUpdate() {
		return appService.queryCronTimeUpdate();
		
	}

	@Override
	public AppVO saveOrUpdateDeviceByEasyLogin(AppLoginVO loginInfo) {
		// TODO Auto-generated method stub
		return appService.easyLogin(loginInfo);
	} 
}
