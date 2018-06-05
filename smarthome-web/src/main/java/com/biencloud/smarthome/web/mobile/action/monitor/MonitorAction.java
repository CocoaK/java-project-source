package com.biencloud.smarthome.web.mobile.action.monitor;

import java.util.ArrayList;
import java.util.List;
import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.device.service.IDeviceService;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.web.housemgr.service.IHousingDistrictInfoService;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.monitor.service.IMonitorService;
import com.biencloud.smarthome.web.monitor.service.ISceneMonitorService;
import com.biencloud.smarthome.web.monitor.vo.SceneDeviceVo;
import com.biencloud.smarthome.web.monitor.vo.SceneVo;
import com.biencloud.smarthome.web.user.service.IOwnerUserService;
import com.biencloud.smarthome.web.user.vo.OwnerUserVO;

/**
 * 类名称：MonitorAction 
 * 类描述： web移动版远程控制动作类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-11-7 上午10:00:54
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class MonitorAction extends BaseAction {
	//场景
	private SceneVo scene;
	//开锁目标设备编号
	private String targetDeviceCode;
	//本地设备编号
	private String personalDeviceCode;
	//开锁密码
	private String personalDevicePwd;
	private HousingDistrictInfoVo district;
	private CellHouseholdInfoVo house;
	//目标设备list
	private List<DeviceVO> targetDeviceList;
	//成功标志
	private boolean successFlag;
	//是否提交开锁
	private boolean submitConfirmUnlock;
	//登录用户类型 1业主，2物管，3管理员
	private String loginUserType;
	//房号id
	private String houseId;
	//设备编号
	private String deviceCode;
	//场景List
	private List<SceneVo> sceneList;
	//场景设备list
	private List<SceneDeviceVo> sceneDeviceList;
	//场景Id
	private Long sceneId;
	//场景设备id串
	private String sceneDeviceIds;
	//场景设备状态
	private String sceneDeviceStatus;
	//场景类型：0为安防，1为家电控制
	private String sceneKind;

	// 布防/撤防(0: 撤防, 1: 布防)
	private String actionWay;
	
	private IMonitorService monitorService;
	private ISceneMonitorService sceneMonitorService;
	private IDeviceService deviceService;
	private IOwnerUserService ownerUserService;
	private ICellHouseholdInfoService cellHouseholdInfoService;
	private IHousingDistrictInfoService housingDistrictInfoService;
	/** 布防撤防模式 ：1:布防**/
	public static final String DEPLOYMENT_MODEL = "1";
	
	/**
	 * 方法的描述: 远程控制主页
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-12 下午2:48:51
	 * @return
	 */
	public String remoteIndex(){
		return "remote_index";
	}

	/**
	 * 方法的描述: 远程开锁
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-28 上午11:04:04
	 * @return
	 */
	public String remoteUnlockInput() {
		String userType = getUserType();
		loginUserType = userType;
		if (Constants.LOGIN_USER_TYPE_OWNER.equals(userType)) {
			// 业主房间ID
			String ownerUserHouseId = getOwnerUserHouseId();
			// 业主单元ID
			String ownerUserCellId = getOwnerUserCellId(ownerUserHouseId);
			
			DeviceVO device = deviceService.queryDeviceByRoomId(ownerUserHouseId);
			if(device != null){
				personalDeviceCode = device.getDeviceCode();
			}else{
				//该房间没有设备
			}

			// 业主只获得单元的设备和室内机设备
			targetDeviceList = deviceService.queryOwnerUnlockDevice(ownerUserCellId, getDistrictId());
		} else if (Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)) {
			//物管的开锁密码为设备本机的密码
			personalDeviceCode = targetDeviceCode;
			// 物业管理员则获得其归属小区下所有的设备(除了业主的室内设备)
			targetDeviceList = deviceService.queryPropertyDevice(getDistrictId());
			// 物业管理远程开锁时, 显示位置信息为小区名称
			district = housingDistrictInfoService.getHousingDistrictInfo(getDistrictId());
		}
		
		extractPersonalDeviceCode(userType);
		return "remote_unlock_input";
	}
	
	/**
	 * 方法的描述: 提交远程开锁请求
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-28 上午11:04:26
	 * @return
	 */
	public String remoteUnlock() {
			successFlag = monitorService.remoteUnlock(targetDeviceCode, personalDeviceCode, personalDevicePwd,super.getUserType());
		if (successFlag == false) {
			// 已经提交开锁请求, 页面显示操作是否成功
			submitConfirmUnlock = true;
		}
		return remoteUnlockInput();
	}

	/**
	 * 方法的描述: 远程家电控制
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-28 上午11:04:36
	 * @return
	 */
	public String remoteApplianceControl() {
		houseId = getOwnerUserHouseId();
		house = getOwnerUserHouse(houseId);
		DeviceVO device = deviceService.queryDeviceByRoomId(houseId);
		if (device != null) {
			deviceCode = device.getDeviceCode();
			sceneMonitorService.sendSceneMonitorCommand(deviceCode);
		}
		return "remote_appliance_control";
	}
	
	/**
	 * 方法的描述: 远程家电场景控制
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-16 下午3:55:44
	 * @return
	 */
	public String remoteApplianceSceneControl() {
		houseId = getOwnerUserHouseId();
		house = getOwnerUserHouse(houseId);

		DeviceVO device = deviceService.queryDeviceByRoomId(houseId);
		if (device != null) {
			deviceCode = device.getDeviceCode();
			sceneMonitorService.sendGetSceneCommand(deviceCode, Constants.FAMILY_CONTROL);
		}
		return "remote_appliance_scene_control";
	}
	
	/**
	 * 方法的描述: 远程布防设防控制
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-25 上午10:49:02
	 * @return
	 */
	public String remoteSceneControl() {
		houseId = getOwnerUserHouseId();
		house = getOwnerUserHouse(houseId);
		DeviceVO device = deviceService.queryDeviceByRoomId(houseId);
		if (device != null) {
			deviceCode = device.getDeviceCode();
			sceneMonitorService.sendGetSceneCommand(deviceCode, Constants.SECURITY);
		}
		return "remote_scene_control";
	}

	/**
	 * 获取场景信息
	 * 
	 * @return
	 */
	public String getSceneByDeviceNo() {
		sceneList = sceneMonitorService.getScenes(deviceCode, sceneKind);
		return SUCCESS;
	}

	/**
	 * 获取场景设备信息
	 * 
	 * @return
	 */
	public String querySceneDeviceByDeviceNo() {
		sceneDeviceList = sceneMonitorService.querySceneDeviceByDeviceNo(deviceCode,sceneId);
		return SUCCESS;
	}

	/**
	 * 发送场景设备控制命令
	 * 
	 * @return
	 */
	public String sendSceneMonitorCommand() {
		List<SceneDeviceVo> list = new ArrayList<SceneDeviceVo>();
		if (sceneDeviceIds != null) {
			String[] ids = sceneDeviceIds.split(",");
			String[] status = sceneDeviceStatus.split(",");
			if(ids!=null){
				for (int i = 0, length = ids.length; i < length; i++) {
					SceneDeviceVo sceneDeviceVo = new SceneDeviceVo();
					sceneDeviceVo.setDeviceId(ids[i]);
					sceneDeviceVo.setStatus(status[i]);
					list.add(sceneDeviceVo);
				}
			}

			successFlag = sceneMonitorService.sendSceneMonitorCommand(deviceCode, list, sceneId);
		}
		return SUCCESS;
	}

	/**
	 * 发送布防或撤防命令
	 * 
	 * @return
	 */
	public String sendProtectionAndRemovalMonitorCommand() {
		successFlag = sceneMonitorService.sendProtectionAndRemovalMonitorCommand(deviceCode,
				sceneId, DEPLOYMENT_MODEL,Constants.SECURITY);
		return "send_protection_command";
	}

	private String getOwnerUserHouseId() {
		String houseId = "";

		OwnerUserVO ownerUser = ownerUserService.getOwnerUserDetail(getUserId());
		if (ownerUser != null) {
			houseId = ownerUser.getHouseId();
		}

		return houseId;
	}

	private String getOwnerUserCellId(String houseId) {
		String cellId = "";

		// 业主远程开锁时, 显示完整位置信息为小区名称 区域名称 楼宇名称 单元名称 房号名称
		house = getOwnerUserHouse(houseId);
		if (house != null && house.getTHmBuildingCellInfo() != null) {
			cellId = house.getTHmBuildingCellInfo().getId();
		}

		return cellId;
	}
	
	private CellHouseholdInfoVo getOwnerUserHouse(String houseId) {
		return cellHouseholdInfoService.get(houseId);
	}

	/**
	 * 从设备列表中提取个人设备编号
	 * 
	 * @param userType
	 */
	private void extractPersonalDeviceCode(String userType) {
		if(targetDeviceList == null){
			targetDeviceList = new ArrayList<DeviceVO>();
		}
		for (int i = 0; i < targetDeviceList.size(); i++) {
			DeviceVO device = targetDeviceList.get(i);
			if(device!=null){
				String deviceType = device.getDeviceType() != null ? device.getDeviceType().getDeviceType() : "";
	
				if (Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)) {
					if (Constants.DEVICE_TYPE_MANAGEMENT.equals(deviceType)) {
						// 物业的个人设备是管理机
						personalDeviceCode = device.getDeviceCode();
	
						// 从设备列表中删除个人设备(管理机)
						//targetDeviceList.remove(device);
						//i--;
					}
				} else if (Constants.LOGIN_USER_TYPE_OWNER.equals(userType)) {
					if (Constants.DEVICE_TYPE_INDOOR.equals(deviceType)) {
						// 业主的个人设备是室内机
						personalDeviceCode = device.getDeviceCode();
	
						// 从设备列表中删除个人设备(室内机)
						targetDeviceList.remove(device);
						i--;
					} else if (Constants.DEVICE_TYPE_MANAGEMENT.equals(deviceType)) {
						// 从设备列表中删除个人设备(管理机)
						targetDeviceList.remove(device);
						i--;
					}
				}
			}
		}
	}

	public String getTargetDeviceCode() {
		return targetDeviceCode;
	}
	public void setTargetDeviceCode(String targetDeviceCode) {
		this.targetDeviceCode = targetDeviceCode;
	}
	public String getPersonalDeviceCode() {
		return personalDeviceCode;
	}
	public void setPersonalDeviceCode(String personalDeviceCode) {
		this.personalDeviceCode = personalDeviceCode;
	}
	public String getPersonalDevicePwd() {
		return personalDevicePwd;
	}
	public void setPersonalDevicePwd(String personalDevicePwd) {
		this.personalDevicePwd = personalDevicePwd;
	}
	public boolean isSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	public IMonitorService getMonitorService() {
		return monitorService;
	}
	public void setMonitorService(IMonitorService monitorService) {
		this.monitorService = monitorService;
	}
	public IDeviceService getDeviceService() {
		return deviceService;
	}
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	public List<DeviceVO> getTargetDeviceList() {
		return targetDeviceList;
	}
	public void setTargetDeviceList(List<DeviceVO> targetDeviceList) {
		this.targetDeviceList = targetDeviceList;
	}
	public IOwnerUserService getOwnerUserService() {
		return ownerUserService;
	}
	public void setOwnerUserService(IOwnerUserService ownerUserService) {
		this.ownerUserService = ownerUserService;
	}
	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}
	public void setCellHouseholdInfoService(ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}
	public boolean isSubmitConfirmUnlock() {
		return submitConfirmUnlock;
	}
	public void setSubmitConfirmUnlock(boolean submitConfirmUnlock) {
		this.submitConfirmUnlock = submitConfirmUnlock;
	}
	public IHousingDistrictInfoService getHousingDistrictInfoService() {
		return housingDistrictInfoService;
	}
	public void setHousingDistrictInfoService(IHousingDistrictInfoService housingDistrictInfoService) {
		this.housingDistrictInfoService = housingDistrictInfoService;
	}
	public HousingDistrictInfoVo getDistrict() {
		return district;
	}
	public void setDistrict(HousingDistrictInfoVo district) {
		this.district = district;
	}
	public CellHouseholdInfoVo getHouse() {
		return house;
	}
	public void setHouse(CellHouseholdInfoVo house) {
		this.house = house;
	}
	public String getLoginUserType() {
		return loginUserType;
	}
	public void setLoginUserType(String loginUserType) {
		this.loginUserType = loginUserType;
	}
	public ISceneMonitorService getSceneMonitorService() {
		return sceneMonitorService;
	}
	public void setSceneMonitorService(ISceneMonitorService sceneMonitorService) {
		this.sceneMonitorService = sceneMonitorService;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public List<SceneVo> getSceneList() {
		return sceneList;
	}
	public void setSceneList(List<SceneVo> sceneList) {
		this.sceneList = sceneList;
	}
	public List<SceneDeviceVo> getSceneDeviceList() {
		return sceneDeviceList;
	}
	public void setSceneDeviceList(List<SceneDeviceVo> sceneDeviceList) {
		this.sceneDeviceList = sceneDeviceList;
	}
	public String getSceneDeviceIds() {
		return sceneDeviceIds;
	}
	public void setSceneDeviceIds(String sceneDeviceIds) {
		this.sceneDeviceIds = sceneDeviceIds;
	}
	public String getSceneDeviceStatus() {
		return sceneDeviceStatus;
	}
	public void setSceneDeviceStatus(String sceneDeviceStatus) {
		this.sceneDeviceStatus = sceneDeviceStatus;
	}
	public Long getSceneId() {
		return sceneId;
	}
	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}
	public String getActionWay() {
		return actionWay;
	}
	public void setActionWay(String actionWay) {
		this.actionWay = actionWay;
	}

	public String getSceneKind() {
		return sceneKind;
	}

	public void setSceneKind(String sceneKind) {
		this.sceneKind = sceneKind;
	}

	public SceneVo getScene() {
		return scene;
	}

	public void setScene(SceneVo scene) {
		this.scene = scene;
	}
}

