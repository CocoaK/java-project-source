package com.biencloud.smarthome.service.device.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.PushKindConstants;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.model.DevicePassword;
import com.biencloud.smarthome.service.device.service.IDevicePasswordService;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.housemgr.model.CellHouseholdInfo;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.push.service.IPushService;
/**
 * 
 * 类名称：DevicePasswordServiceImpl 
 * 类描述： 设备开锁密码服务实现类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-8-3 下午3:28:13
 */

public class DevicePasswordServiceImpl extends BaseService<DevicePassword,Long> implements IDevicePasswordService{
	
	private IDeviceService deviceService;
	private IPushService pushService;

	@Override
	public List<DevicePassword> queryDevicePassword(String deviceNo) {
		List<DevicePassword> list = super.find("FROM DevicePassword WHERE device.deviceCode=?1",deviceNo);
		return list;
	}
	
	@Override
	@Transactional(propagation = Propagation.NESTED)
	public void saveAndPushDevicePassword(DevicePassword devicePassword){
		List<Device> targetDevices = null;	//目标设备（本单元单元门口机）
		List<Device> roomDevices = null;	//上传开锁密码的设备所在房间内可能有多个设备
		//如果devicePassword为空，后面的代码不执行
		if(devicePassword==null){
			return;
		}
		if(devicePassword != null && devicePassword.getDevice()!=null){
			if(devicePassword.getDevice().getBuildingCellInfo()!=null){
				//目标设备（仅为本房号所属单元的所有门口机）
				targetDevices = deviceService.queryUnitDoorDeviceByUnitId(devicePassword.getDevice().getBuildingCellInfo().getId());
			}
			if(devicePassword.getDevice().getCellHouseholdInfo() != null){
				Device d = new Device();
				CellHouseholdInfo chi = devicePassword.getDevice().getCellHouseholdInfo();
				d.setCellHouseholdInfo(chi);
				roomDevices = deviceService.queryDevices(d);
			}
		}
		
		//将设备的开锁密码推送到目标设备（门口机）
		if(targetDevices != null && targetDevices.size() != 0){
			for(Device targetDevice : targetDevices){
				String deviceNo = null;
				String roomNo = null;
				String password = null;
				//上传开锁密码的设备所在房间内可能有多个设备，将这些设备的开锁密码都保存为一样的
				if(roomDevices != null && roomDevices.size() != 0 && targetDevice!=null){
					for(Device roomDevice : roomDevices){
						DevicePassword dp = new DevicePassword();
						DevicePassword dpassword = null;
						if(roomDevice!=null){
						//如果在这个房号下有设备且设置过密码，则更新此密码
							dpassword = getDevicePasswordByDeviceId(roomDevice.getDeviceId(),targetDevice.getDeviceId());
						}
						if(dpassword != null){
							dp = dpassword;
						}
						dp.setDevice(roomDevice);
						dp.setPassword(devicePassword.getPassword());
						dp.setTargetDevice(targetDevice);
						super.save_update(dp);
						
						Push push = new Push();
						push.setPushClientId(targetDevice.getDeviceCode());
						push.setAddTime(new Date());
						push.setPushName("deviceUnlockPassword");
						push.setPushKind(PushKindConstants.PUSH_UNLOCK_PASSWORD);
						if(devicePassword.getDevice()!=null)
							deviceNo = roomDevice.getDeviceCode();
						if(devicePassword.getDevice().getCellHouseholdInfo()!=null)
							roomNo = roomDevice.getCellHouseholdInfo().getCode();
						password = devicePassword.getPassword();
						push.setPushContent("deviceNo:"+deviceNo+",roomNo:"+roomNo+",password:"+password);
						//将本设备的设备密码推送给本设备所属单元的所有门口机
						pushService.insertPush(push);
					}
				}
			}
		}
	}

	public void updatePassword(){
		
	}
	
	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public IPushService getPushService() {
		return pushService;
	}

	public void setPushService(IPushService pushService) {
		this.pushService = pushService;
	}

	@Override
	public DevicePassword getDevicePasswordByDeviceId(String deviceId,String targetDeviceId) {
		List<DevicePassword> list = super.find("FROM DevicePassword WHERE device.deviceId=?1 AND targetDevice.deviceId=?2", deviceId,targetDeviceId);
		if(list != null && list.size() != 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean validatePasswd(String deviceNo, String password) {
		List<DevicePassword> list = queryDevicePassword(deviceNo);
		DevicePassword devicePassword = null;
		if(list != null && list.size() != 0){
			devicePassword = list.get(0);
		}
		if(devicePassword != null && devicePassword.getPassword()!=null){
			return devicePassword.getPassword().equals(password);
		}
		return false;
	}

	@Override
	public List<DevicePassword> queryDevicePasswordByTargetDeviceNo(String targetDeviceNo) {
		List<DevicePassword> list = super.find("FROM DevicePassword WHERE targetDevice.deviceCode=?1",targetDeviceNo);
		return list;
	}

}
