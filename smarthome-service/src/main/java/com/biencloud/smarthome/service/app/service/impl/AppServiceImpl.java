package com.biencloud.smarthome.service.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bsh.StringUtil;

import com.biencloud.smarthome.service.app.service.IAppService;
import com.biencloud.smarthome.service.app.vo.AppLoginVO;
import com.biencloud.smarthome.service.app.vo.AppVO;
import com.biencloud.smarthome.service.common.constants.AppConstants;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.constants.PushKindConstants;
import com.biencloud.smarthome.service.common.utils.CryptoUtils;
import com.biencloud.smarthome.service.common.utils.StringUtils;
import com.biencloud.smarthome.service.deivceno.service.IDeviceNoService;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.model.DeviceIp;
import com.biencloud.smarthome.service.device.model.DeviceType;
import com.biencloud.smarthome.service.device.service.IDeviceIpService;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.device.service.IDeviceTypeService;
import com.biencloud.smarthome.service.housemgr.model.BuildingCellInfo;
import com.biencloud.smarthome.service.housemgr.model.CellHouseholdInfo;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictRegionInfo;
import com.biencloud.smarthome.service.housemgr.model.RegionBuildingInfo;
import com.biencloud.smarthome.service.housemgr.service.IBuildingCellInfoService;
import com.biencloud.smarthome.service.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.service.housemgr.service.IHousingDistrictInfoService;
import com.biencloud.smarthome.service.housemgr.service.IHousingDistrictRegionInfoService;
import com.biencloud.smarthome.service.housemgr.service.IRegionBuildingInfoService;
import com.biencloud.smarthome.service.log.model.DiviceRegeditLog;
import com.biencloud.smarthome.service.log.service.IDiviceRegeditLogService;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.push.service.IPushService;
import com.biencloud.smarthome.service.sysparam.service.ISysParamService;

/**
 * 
 * 类名称：AppServiceImpl 类描述： app专用业务处理接口实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-31 上午9:06:49
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AppServiceImpl implements IAppService {
	private IRegionBuildingInfoService regionBuildingInfoService;
	private IHousingDistrictRegionInfoService housingDistrictRegionInfoService;
	private IHousingDistrictInfoService housingDistrictInfoService;
	private IBuildingCellInfoService buildingCellInfoService;
	private ICellHouseholdInfoService cellHouseholdInfoService;
	private IDeviceService deviceService;
	private IDeviceNoService deviceNoService;
	private IDeviceTypeService deviceTypeService;
	private IPushService pushService;
	private ISysParamService sysParamService;
	private IDeviceIpService deviceIpService;
	private IDiviceRegeditLogService appRegistLogService;

	/**
	 * 
	 * 方法的描述: 判断小区是否存在
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-12 下午9:45:16
	 * @param areaNo
	 *            小区编号
	 * @return
	 */
	private Object[] isExistArea(String areaNo) {
		Object[] o = new Object[2];
		String backString = null;
		HousingDistrictInfo area = null;
		area = housingDistrictInfoService.getByCode(areaNo);
		// 小区不存在
		if (area == null) {
			backString = AppConstants.AREA_NOT_EXIST;

		}
		o[0] = backString;
		o[1] = area;
		return o;
	}

	/**
	 * 
	 * 方法的描述: 判断小区区域是否存在
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-12 下午9:44:48
	 * @param areaNo
	 *            小区编号
	 * @param regionNo
	 *            区域编号
	 * @return
	 */
	private Object[] isExistAreaRegion(String areaNo, String regionNo) {
		Object[] o = new Object[2];
		String backString = null;
		HousingDistrictRegionInfo region = null;
		// 区域编号是否存在
		if (regionNo != null && !"".equals(regionNo)) {
			// 获得区域对象
			region = housingDistrictRegionInfoService.getByCode(areaNo, regionNo);
			if (region == null) {
				backString = AppConstants.AREA_REGION_NOT_EXIST;
			}
		}
		o[0] = backString;
		o[1] = region;
		return o;
	}

	/**
	 * 
	 * 方法的描述: 判断小区楼宇是否存在
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-12 下午9:45:59
	 * @param areaNo
	 *            小区编号
	 * @param regionNo
	 *            区域编号
	 * @param buildingNo
	 *            搂宇编号
	 * @return
	 */
	private Object[] isExistBuilding(String areaNo, String regionNo, String buildingNo) {
		Object[] o = new Object[2];
		String backString = null;
		RegionBuildingInfo building = null;
		// 判断栋号是否存在
		if (buildingNo != null && !"".equals(buildingNo)) {
			// 获得栋对象
			building = regionBuildingInfoService.getByCode(areaNo, regionNo, buildingNo);
			if (building == null) {
				backString = AppConstants.AREA_BUILDING_NOT_EXIST;
			}
		}
		o[0] = backString;
		o[1] = building;
		return o;
	}

	/**
	 * 
	 * 方法的描述: 判断小区单元是否存在
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-12 下午9:48:02
	 * @param areaNo
	 *            小区编号
	 * @param regionNo
	 *            区域编号
	 * @param buildingNo
	 *            搂宇编号
	 * @param unitNo
	 *            单元编号
	 * @return
	 */
	private Object[] isExistUnit(String areaNo, String regionNo, String buildingNo, String unitNo) {
		Object[] o = new Object[2];
		String backString = null;
		BuildingCellInfo unit = null;
		// 判断单元是否存在
		if (unitNo != null && !"".equals(unitNo)) {
			// 单元对象
			unit = buildingCellInfoService.getByCode(areaNo, regionNo, buildingNo, unitNo);
			if (unit == null) {
				backString = AppConstants.AREA_UNIT_NOT_EXIST;
			}
		}
		o[0] = backString;
		o[1] = unit;
		return o;
	}

	/**
	 * 
	 * 方法的描述: 判断房屋是否存在
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-12 下午9:50:38
	 * @param areaNo
	 *            小区编号
	 * @param regionNo
	 *            区域编号
	 * @param buildingNo
	 *            搂宇编号
	 * @param unitNo
	 *            单元编号
	 * @param houseNo
	 *            房号
	 * @return
	 */
	private Object[] isExistHouse(String areaNo, String regionNo, String buildingNo, String unitNo, String houseNo) {
		Object[] o = new Object[2];
		String backString = null;
		CellHouseholdInfo house = null;
		// 判断房号是否存在
		if (houseNo != null && !"".equals(houseNo)) {
			// 房号对象
			house = cellHouseholdInfoService.getByCode(areaNo, regionNo, buildingNo, unitNo, houseNo);
			if (house == null) {
				backString = AppConstants.AREA_ROOM_NOT_EXIST;
			}
		}
		o[0] = backString;
		o[1] = house;
		return o;
	}

	@Override
	@Transactional(propagation = Propagation.NESTED)
	public AppVO login(AppLoginVO loginInfo) {
		AppVO av = new AppVO();
		String deviceNo = null;
		String dataServerIP = "";
		String socketServerIP = "";
		String fileServerIP = "";
		String conflictMac = null;
		boolean newFlag = false;
		String result = AppConstants.FAILURE;
		if (loginInfo != null) {
			// 设备编号
			deviceNo = loginInfo.getDeviceNo();
			// 小区编号
			String areaNo = loginInfo.getAreaNo();
			// 区域编号
			String regionNo = loginInfo.getRegionNo();
			// 楼宇编号
			String buildingNo = loginInfo.getBuildingNo();
			// 单元编号
			String unitNo = loginInfo.getUnitNo();
			// 房屋编号
			String houseNo = loginInfo.getHouseNo();
			// mac地址
			String mac = loginInfo.getMac();
			// ip
			String ip = loginInfo.getIp();
			// 设备名称
			String deviceName = loginInfo.getDeviceName();
			// 设备类型
			String deviceType = loginInfo.getDeviceType();
			// 设备密码
			String devicePassword = loginInfo.getDevicePassword();
			// ip在冲突情况下，是否强制切换
			String ipState = loginInfo.getIpState();
			// 房号在冲突情况下，是否强制切换
			String houseState = loginInfo.getHouseState();
			// 位置
			String position = loginInfo.getPosition();
			// 版本
			String version = loginInfo.getVersion();
			String unitDoorNo = loginInfo.getUnitDoorNo();
			String sipid = loginInfo.getSipid();
			//完整房号
			String houseCode = this.constructHouseCodeInfo(areaNo, regionNo, buildingNo, unitNo, houseNo, position,deviceType);

			if (mac != null && deviceType != null) {

				if (devicePassword != null) {
					// 密码加密
					devicePassword = CryptoUtils.encodeByMD5(devicePassword);
				}
				// 判断小区是否存在
				Object[] o = this.isExistArea(areaNo);
				if (o[0] != null) {
					result = (String) o[0];
				} else {
					// 小区对象
					HousingDistrictInfo area = (HousingDistrictInfo) o[1];

					if (area != null) {
						//注释掉此处，因为之前的业务逻辑不适合现在的逻辑，现在可以有房号和位置号一起，位置号即为分机号
						// 设置位置
						//position = this.setPosition(houseNo, deviceType);
//						if (position != null) {
//							houseNo = null;
//						}
						// 判断该设备是否已经注册过
						Device existDevice = deviceService.queryDeviceByMac(loginInfo.getMac(), areaNo, regionNo, buildingNo, unitNo,
								houseNo);
						if (existDevice != null) {
							// 如果房号信息属于这种情形，由区，区域，栋，单元，房号变成区，区域或栋或单元，视为第一次注册
							String existHouseCode = this.constructHouseCodeInfo(existDevice);
							
//							String houseCode = this.constructHouseCodeInfo(areaNo, regionNo, buildingNo, unitNo, houseNo, position,deviceType);
							// 房号信息是否发生变化
							boolean isHouseInfoChange = this.isHouseInfoChange(existHouseCode, houseCode);
							if (isHouseInfoChange) {
								existDevice = null;
								newFlag = true;
							}
						}else{
							newFlag = true;
						}
						DeviceType dt = deviceTypeService.get(deviceType);
						// 判断ip是否存在
						// boolean isExistIP = deviceIpService.isExistIP(areaNo,
						// ip);
						// if (!isExistIP) {// ip不存在
						// result = AppConstants.DEVICE_IP_NOT_EXIST;
						// } else// ip存在
						// {
						if (dt != null) {
							// 如果是室内机，必须有房号
							if (AppConstants.DEVICE_TYPE_INDOOR.equals(deviceType)) {
								if (houseNo == null) {
									result = AppConstants.DEVICE_HOUSE_NUM_NOT_EXIST;
								}
							} else {// 非室内机，必须有位置，
								if (position == null) {
//									result = AppConstants.DEVICE_POSITION_NOT_EXIST;

								}
							}
							// 登陆处理
							if (AppConstants.FAILURE.equals(result)) {

								String[] obj = this.registLogin(existDevice, area, av, areaNo, regionNo, buildingNo, unitNo, houseNo,
										deviceNo, deviceName, devicePassword, ip, mac, dt, position, ipState, houseState,unitDoorNo,version,sipid);
								if (obj != null) {
									result = obj[0];// 登陆结果
									deviceNo = obj[1];// 设备编号
									conflictMac = obj[2];// 冲突的mac地址
									if (AppConstants.SUCCESSFULL.equals(result)) {
										// 数据服务器地址
										dataServerIP = sysParamService.queryParamValueByParamCode(AppConstants.DATA_SERVER_IP);
										// 文件服务器地址
										fileServerIP = sysParamService.queryParamValueByParamCode(AppConstants.FILE_SERVER_IP);
										// socket服务器地址
										socketServerIP = sysParamService.queryParamValueByParamCode(AppConstants.SOCKET_SERVER_IP);
									} else {
										deviceNo = loginInfo.getDeviceNo();// 返回原来设备编号
									}
								}
							}
						}
					}
					// }
				}

			}

		}
		// 封装数据
		av.setDeviceNo(deviceNo);// 设备编号
		av.setResult(result);// 登陆结果
		av.setDataServerIP(dataServerIP);// 数据服务器地址
		av.setFileServerIP(fileServerIP);// 文件服务器 地址
		av.setSocketServerIP(socketServerIP);// socket服务器地址
		av.setConflictMac(conflictMac);// 冲突mac地址
		av.setNewFlag(newFlag);			//是否是新注册
		return av;
	}

	/**
	 * 
	 * 方法的描述: 设置非室内机位置，位置以房号形式传过来
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-1 下午5:59:05
	 * @return
	 */
	private String setPosition(String houseNo, String deviceType) {
		String position = null;
		if (!AppConstants.DEVICE_TYPE_INDOOR.equals(deviceType)) {
			position = houseNo;
		}
		return position;
	}

	/**
	 * 
	 * 方法的描述: 判断完成的房间信息是否变化
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-21 下午4:02:35
	 * @param existDevice
	 * @param device
	 * @return
	 */
	private boolean isHouseInfoChange(String existHouseCode, String houseCode) {
		boolean isChange = false;
		if (existHouseCode != null && !existHouseCode.equals(houseCode)) {
			isChange = true;
		}

		return isChange;
	}

	/**
	 * 
	 * 方法的描述: 判断设备类型是否变化
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-21 下午4:39:44
	 * @param existDeviceType
	 * @param deviceType
	 * @return
	 */
	private boolean isDeviceTypeChange(String existDeviceType, String deviceType) {
		boolean isChange = false;
		if (existDeviceType != null && !existDeviceType.equals(deviceType)) {
			isChange = true;
		}

		return isChange;
	}

	/**
	 * 
	 * 方法的描述: 构建完整的房号信息
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-21 下午3:59:56
	 * @param device
	 * @return
	 */
	private String constructHouseCodeInfo(Device device) {
		String code = "";
		if (device != null) {
			StringBuilder sb = new StringBuilder();
			// 小区编号
			String areaNo = device.getHousingDistrictInfo() == null ? "0000" : device.getHousingDistrictInfo().getCode();
			// 区域编号
			String regionNo = device.getHousingDistrictRegionInfo() == null ? "00" : device.getHousingDistrictRegionInfo().getCode();
			// 楼栋编号
			String buildingNo = device.getRegionBuildingInfo() == null ? "00" : device.getRegionBuildingInfo().getCode();
			// 单元编号
			String unitNo = device.getBuildingCellInfo() == null ? "00" : device.getBuildingCellInfo().getCode();
			// 房号
			String houseNo = device.getCellHouseholdInfo() == null ? "0000" : device.getCellHouseholdInfo().getCode();
			// 位置号
			String position = device.getPosition() == null ? "00" : device.getPosition();
//			if (device.getDeviceType() != null && !AppConstants.DEVICE_TYPE_INDOOR.equals(device.getDeviceType().getDeviceType())) {
//				houseNo = device.getPosition();// 将位置号赋给房号变量
//				if (houseNo == null) {
//					houseNo = "0000";
//				}
//
//			}
			sb.append(areaNo).append(regionNo).append(buildingNo).append(unitNo).append(houseNo).append(position);
			code = sb.toString();
		}
		return code;
	}

	/**
	 * 
	 * 方法的描述: 构建完整的房号信息
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-21 下午3:59:56
	 * @param device
	 * @return
	 */
	private String constructHouseCodeInfo(String _areaNo, String _regionNo, String _buildingNo, String _unitNo, String _houseNo,
			String _position, String deviceType) {
		String code = "";
		StringBuilder sb = new StringBuilder();
		String areaNo = _areaNo == null ? "0000" : _areaNo;
		String regionNo = _regionNo == null ? "00" : _regionNo;
		String buildingNo = _buildingNo == null ? "00" : _buildingNo;
		String unitNo = _unitNo == null ? "01" : _unitNo;
		String houseNo = _houseNo == null ? "0000" : _houseNo;
		String position = _position == null ? "00" : _position;
//		if (!AppConstants.DEVICE_TYPE_INDOOR.equals(deviceType)) {
//			houseNo = position;
//			if (houseNo == null) {
//				houseNo = "0000";
//			}
//
//		}
		sb.append(areaNo).append(regionNo).append(buildingNo).append(unitNo).append(houseNo).append(position);
		code = sb.toString();

		return code;
	}

	/**
	 * 
	 * 方法的描述: 注册登陆
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-13 下午5:56:35
	 * @param existDevice
	 *            存在的设备对象
	 * @param area
	 *            小区
	 * @param region区域
	 * @param building楼宇
	 * @param unit单元
	 * @param house房号
	 * @param av结果处理对象
	 * @param areaNo小区编号
	 * @param regionNo区域编号
	 * @param buildingNo楼宇编号
	 * @param unitNo单元编号
	 * @param houseNo房号
	 * @param deviceNo设备编号
	 * @param deviceName设备名称
	 * @param devicePassword设备密码
	 * @param ip
	 * @param mac
	 * @param dt设备类型
	 * @return
	 */
	private String[] registLogin(Device existDevice, HousingDistrictInfo area, AppVO av, String areaNo, String regionNo, String buildingNo,
			String unitNo, String houseNo, String deviceNo, String deviceName, String devicePassword, String ip, String mac, DeviceType dt,
			String position, String ipState, String houseState,String unitDoorNo,String version,String sipid) {
		String result = AppConstants.FAILURE;
		String _deviceNo = deviceNo;
		String[] obj = new String[3];
		// String existHouseCode = this.constructHouseCodeInfo(existDevice);
		// String houseCode = this.constructHouseCodeInfo(areaNo, regionNo,
		// buildingNo, unitNo, houseNo);
		// 被冲突设备的MAC
		String conflictDeviceMac = null;
		Map<String, Object> map = validBaseAreaInfo(av, areaNo, regionNo, buildingNo, unitNo, houseNo);
		result = av.getResult();
		HousingDistrictRegionInfo region = map.get(AppConstants.REGION) == null ? null : (HousingDistrictRegionInfo) map
				.get(AppConstants.REGION);
		RegionBuildingInfo building = map.get(AppConstants.BUILDING) == null ? null : (RegionBuildingInfo) map.get(AppConstants.BUILDING);
		BuildingCellInfo unit = map.get(AppConstants.UNIT) == null ? null : (BuildingCellInfo) map.get(AppConstants.UNIT);
		CellHouseholdInfo house = map.get(AppConstants.HOUSE) == null ? null : (CellHouseholdInfo) map.get(AppConstants.HOUSE);

		// 第一次注册登陆
		if (existDevice == null) {

			if (av.getResult() == null) {// 如果传过来的区域编号或楼宇编号或单元编号或房号存在
				String isAllowManyDeviceInHouse = sysParamService.queryParamValueByParamCode(Constants.IsAllowManyDeviceInHouse);
				// 产生设备编号
				_deviceNo = createDeviceNo(areaNo, regionNo, buildingNo, unitNo, houseNo, position, dt, isAllowManyDeviceInHouse);

				// 判断设备是否在别的地方注册过
				deleteRegistedDevice(mac);

				// 判断设备名称是否重复
				boolean isRepeatDeviceName = deviceService.isRepeatDeviceName(areaNo, regionNo, buildingNo, unitNo, houseNo, deviceName,
						null);
				// 如果是室内机
				if (AppConstants.DEVICE_TYPE_INDOOR.equals(dt.getDeviceType())) {

					// 一个房号下只允许存在一个设备
					if (isAllowManyDeviceInHouse != null && AppConstants.notAllowManyDevicesInHouse.equals(isAllowManyDeviceInHouse)) {
						isRepeatDeviceName = false;
					}
				}
				// 设备名称不重复
				if (!isRepeatDeviceName) {
					// 判断设备IP是否被使用
					//DeviceIp usedDeviceIp = deviceIpService.isUsedIP(areaNo, ip, null);
					List<Device> existDeviceList = new ArrayList<Device>();
					// 室内机
					if (AppConstants.DEVICE_TYPE_INDOOR.equals(dt.getDeviceType())) {
						// 判断该房号下是否存在设备
						existDeviceList = deviceService.queryIndoorDevice(areaNo, regionNo, buildingNo, unitNo, houseNo);
					} else {// 非室内机
						// 判断该位置下是否存在设备
						existDeviceList = deviceService.queryNotIndoorDevice(areaNo, regionNo, buildingNo, unitNo, houseNo, position,dt.getDeviceType());
					}
					// ip和房号同时冲突
//					if (usedDeviceIp != null && !existDeviceList.isEmpty() && !(AppConstants.FORCE_TAKE_IP.equals(ipState))
//							&& !(AppConstants.HOUSE_BE_TAKED.equals(houseState))) {
//						// ip和房号同时冲突
//						result = AppConstants.HOUSE_AND_IP_BE_TAKED;
//					} else {
						// ip没有被使用
//						if (usedDeviceIp == null || (usedDeviceIp != null && AppConstants.FORCE_TAKE_IP.equals(ipState))) {
							// 非室内机
							if (!AppConstants.DEVICE_TYPE_INDOOR.equals(dt.getDeviceType())) {
								// 判断位置是否重复
								boolean isRepeatPosition = deviceService.isRepeatDevicePosition(areaNo, regionNo, buildingNo, unitNo,houseNo,
										dt.getDeviceType(), position, null);
								if (isRepeatPosition) {
									// 强占位置
									if (AppConstants.FORCE_TAKE_HOUSENO.equals(houseState)) {
										String[] saveResult = saveDevice(area, deviceName, devicePassword, ip, mac, dt, position, result,
												_deviceNo, region, building, unit, house, ipState, houseState,unitDoorNo,version,sipid);
										result = saveResult[0];
										conflictDeviceMac = saveResult[1];
									} else {
										// 位置重复
										result = AppConstants.DEVICE_POSITION_REPEAT;
									}

								} else {
									String[] saveResult = saveDevice(area, deviceName, devicePassword, ip, mac, dt, position, result,
											_deviceNo, region, building, unit, house, ipState, houseState,unitDoorNo,version,sipid);
									result = saveResult[0];
									conflictDeviceMac = saveResult[1];
								}
							} else {
								Object[] o = isExistHouse(areaNo, regionNo, buildingNo, unitNo, houseNo);
								if(o[1]==null){
									saveHouse(house);
								}
								String[] saveResult = saveDevice(area, deviceName, devicePassword, ip, mac, dt, position, result,
										_deviceNo, region, building, unit, house, ipState, houseState,unitDoorNo,version,sipid);
								result = saveResult[0];
								conflictDeviceMac = saveResult[1];
							}

//						} else {
//							// ip被使用
//							result = AppConstants.DEVICE_IP_REPEAT;
//
//						}
//					}
				} else {// 设备名称重复
					result = AppConstants.DEVICE_NAME_REPEAT;
				}

			}

		} else// 非第1次注册登陆
		{

			_deviceNo = existDevice.getDeviceCode();
			String existIP = existDevice.getDeviceIp();
			String existDeviceName = existDevice.getDeviceName();
			// 封装属性
			existDevice.setBuildingCellInfo(unit);
			existDevice.setCellHouseholdInfo(house);
			existDevice.setRegionBuildingInfo(building);
			existDevice.setDeviceType(dt);
			existDevice.setDeviceAlias(deviceName);
			existDevice.setDeviceName(deviceName);
			existDevice.setDeviceIp(ip);
			existDevice.setUpdatedTime(new Date());
			existDevice.setDeviceStatus(AppConstants.ONLINE);
			existDevice.setUnitDoorNo(unitDoorNo);
			existDevice.setPosition(position);
			existDevice.setVersion(version);
			existDevice.setSipid(sipid);
			// 判断设备名称是否重复
			boolean isRepeatDeviceName = deviceService.isRepeatDeviceName(areaNo, regionNo, buildingNo, unitNo, houseNo, deviceName,
					existDevice.getDeviceId());
			// 设备名称不重复
			if (!isRepeatDeviceName) {
				// 判断设备IP是否被使用
				DeviceIp usedDeviceIp = deviceIpService.isUsedIP(areaNo, ip, existDevice.getDeviceId());
				// ip没有被使用
				if (usedDeviceIp == null || (usedDeviceIp != null && AppConstants.FORCE_TAKE_IP.equals(ipState))) {

					// 非室内机
					if (!AppConstants.DEVICE_TYPE_INDOOR.equals(dt.getDeviceType())) {
						// 判断位置是否重复
						boolean isRepeatPosition = deviceService.isRepeatDevicePosition(areaNo, regionNo, buildingNo, unitNo,houseNo,
								dt.getDeviceType(), position, existDevice.getDeviceId());
						if (isRepeatPosition) {
							// 位置重复
							result = AppConstants.DEVICE_POSITION_REPEAT;
						} else {
							String[] updateResult = this.updateDevice(existDevice, _deviceNo, dt.getDeviceType(), areaNo, existIP,
									existDeviceName, ipState);
							result = updateResult[0];
							conflictDeviceMac = updateResult[1];
						}
					} else {
						String[] updateResult = this.updateDevice(existDevice, _deviceNo, dt.getDeviceType(), areaNo, existIP,
								existDeviceName, ipState);
						result = updateResult[0];
						conflictDeviceMac = updateResult[1];
					}
				} else {
					// ip被使用
					result = AppConstants.DEVICE_IP_REPEAT;
				}
			} else {
				// 设备名称重复
				result = AppConstants.DEVICE_NAME_REPEAT;
			}

		}
		obj[0] = result;
		obj[1] = _deviceNo;
		obj[2] = conflictDeviceMac;
		return obj;
	}

	private String[] dealHouseNoConfict(String areaNo, String regionNo, String buildingNo, String unitNo, String houseNo, String position,
			DeviceType dt, String houseState) {
		String obj[] = new String[2];
		// String result = null;
		// String conflictDeviceMac = null;
		// 如果是室内机
		if (AppConstants.DEVICE_TYPE_INDOOR.equals(dt.getDeviceType())) {
			String isAllowManyDeviceInHouse = sysParamService.queryParamValueByParamCode(Constants.IsAllowManyDeviceInHouse);
			// 一个房号下只允许存在一个设备
			if (isAllowManyDeviceInHouse != null && AppConstants.notAllowManyDevicesInHouse.equals(isAllowManyDeviceInHouse)) {
				// 判断该房号下是否存在设备
				List<Device> existDeviceList = deviceService.queryIndoorDevice(areaNo, regionNo, buildingNo, unitNo, houseNo);
				obj = this.dealConfict(existDeviceList, houseState);
			}
		} else {
			// 判断该位置号下是否存在设备
			List<Device> existDeviceList = deviceService.queryNotIndoorDevice(areaNo, regionNo, buildingNo, unitNo,houseNo, position,dt.getDeviceType());
			obj = this.dealConfict(existDeviceList, houseState);
		}
		// obj[0] = result;
		// obj[1] = conflictDeviceMac;
		return obj;
	}

	/**
	 * 
	 * 方法的描述: 处理冲突
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-7 下午6:15:25
	 * @param existDeviceList
	 * @param houseState
	 * @return
	 */
	private String[] dealConfict(List<Device> existDeviceList, String houseState) {
		String obj[] = new String[2];
		String result = null;
		String conflictDeviceMac = null;
		if (existDeviceList != null && !existDeviceList.isEmpty()) {
			if (!AppConstants.FORCE_TAKE_HOUSENO.equals(houseState)) {
				result = AppConstants.HOUSE_BE_TAKED;

			} else// 强制占用该房号
			{
				Device d = existDeviceList.get(0);
				if (d != null) {
					conflictDeviceMac = d.getDeviceMac();
					// 删除该房号下设备
					this.deleteRegistedDevice(conflictDeviceMac);
					// d.setCellHouseholdInfo(null);// 房号设置为空
					// this.pushClientToOtherClients(d);
				}
			}

		}
		obj[0] = result;
		obj[1] = conflictDeviceMac;
		return obj;
	}

	/**
	 * 
	 * 方法的描述: 删除在别处注册过的设备
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-6 下午3:23:50
	 * @param mac
	 */
	private void deleteRegistedDevice(String mac) {
		Device registedDevice = deviceService.queryDeviceByMac(mac);
		if (registedDevice != null) {
			String deviceId = registedDevice.getDeviceId();
			String deviceNo_ = registedDevice.getDeviceCode();
			String deviceIp = registedDevice.getDeviceIp();
			String deviceMac = registedDevice.getDeviceMac();
			if (deviceId != null && registedDevice.getHousingDistrictInfo()!=null) {
				// 更新设备ip为未使用状态
				DeviceIp deviceIp_ = deviceIpService.queryDeviceIpByIp(deviceIp, registedDevice.getHousingDistrictInfo().getCode());
				if (deviceIp_ != null) {
					deviceIp_.setDevice(null);
					deviceIp_.setStatus(Constants.IP_NOT_USED);
					deviceIpService.updateDeviceIp(deviceIp_);

				}
				/*
				 * if (deviceNo_ != null) { Device oth_device =
				 * deviceService.queryDeviceByDeviceNo(deviceNo_, deviceMac); //
				 * 重新绑定设备编号与设备的关系 DeviceNo deviceNoObj =
				 * deviceNoService.getDeviceNoByNo(deviceNo_); if (deviceNoObj
				 * != null) { deviceNoObj.setDevice(oth_device);
				 * deviceNoService.save_update(deviceNoObj); }
				 * 
				 * }
				 */
				deviceService.deleteDeviceById(deviceId);
			}
		}
	}

	private String[] updateDevice(Device existDevice, String _deviceNo, String deviceType, String areaNo, String existIP,
			String existDeviceName, String ipState) {
		String result = null;
		String obj[] = new String[2];
		// 被冲突设备的MAC
		String conflictDeviceMac = null;
		Device d = deviceService.save_update(existDevice);
		if (d != null) {

			_deviceNo = d.getDeviceCode();
			// 编号
			String siteNo = createSiteNo(d.getHousingDistrictInfo(), d.getHousingDistrictRegionInfo(), d.getRegionBuildingInfo(),
					d.getBuildingCellInfo(), d.getCellHouseholdInfo());
			// 写日志
//			this.writeAppRegistLog(new Date(), d.getDeviceMac(), d.getDeviceIp(), d.getDeviceName(), d.getDeviceCode(), d
//					.getHousingDistrictInfo() == null ? null : d.getHousingDistrictInfo().getName(),
//					d.getHousingDistrictRegionInfo() == null ? null : d.getHousingDistrictRegionInfo().getName(),
//					d.getRegionBuildingInfo() == null ? null : d.getRegionBuildingInfo().getName(), d.getBuildingCellInfo() == null ? null
//							: d.getBuildingCellInfo().getName(), d.getCellHouseholdInfo() == null ? null : d.getCellHouseholdInfo()
//							.getName(), d.getPosition(), siteNo, d.getDeviceType().getDeviceName(), AppConstants.APP_NOT_FIRST_REGIST);

			List<Device> list = null;
			// 将新IP设置为使用状态
			DeviceIp dip = deviceIpService.queryDeviceIpByIpAndAreaCode(existDevice.getDeviceIp(), areaNo);
			if (dip != null) {
				dip.setDevice(d);
				dip.setHousingDistrictInfo(d.getHousingDistrictInfo());
				dip.setSubnet(StringUtils.getIPSection(d.getDeviceIp()));
				dip.setStatus(Constants.IP_USED);
				deviceIpService.save_update(dip);

			}
			// 判断别名是否修改
			boolean isDeviceNameChange = this.isDeviceNameChange(existDeviceName, existDevice.getDeviceName());

			// 判断ip是否修改
			boolean isIPChange = this.isIPChange(existIP, existDevice.getDeviceIp());

			// 如果设备类型或房号信息变了
			if (isIPChange || isDeviceNameChange) {
				StringBuilder sb = new StringBuilder();
				// 类型
				String type = d.getDeviceType().getDeviceType();
				// String areaNo = device.getHousingDistrictInfo() == null ?
				// null : device.getHousingDistrictInfo().getCode();
				// 区域编号
				String regionNo = d.getHousingDistrictRegionInfo() == null ? null : d.getHousingDistrictRegionInfo().getCode();
				// 栋号
				String buildingNo = d.getRegionBuildingInfo() == null ? null : d.getRegionBuildingInfo().getCode();
				// 单元编号
				String unitNo = d.getBuildingCellInfo() == null ? null : d.getBuildingCellInfo().getCode();
				// 房号
				String houseNo = d.getCellHouseholdInfo() == null ? null : d.getCellHouseholdInfo().getCode();
				// 设备记录id
				String deviceId = d.getDeviceId();
				// ip
				String ip = d.getDeviceIp();
				// 设备编号
				String deviceNo = d.getDeviceCode();
				String mac = d.getDeviceMac();
				sb.append("deviceName:").append(covertNullToString(d.getDeviceName())).append(",areaNo:")
						.append(covertNullToString(areaNo)).append(",regionNo:").append(covertNullToString(regionNo))
						.append(",buildingNo:").append(covertNullToString(buildingNo)).append(",unitNo:")
						.append(covertNullToString(unitNo)).append(",roomNo:").append(covertNullToString(houseNo)).append(",deviceNo:")
						.append(covertNullToString(deviceNo)).append(",deviceType:").append(covertNullToString(type)).append(",ip:")
						.append(covertNullToString(ip)).append(",mac:").append(covertNullToString(mac));
				// 推送内容
				String pushContent = sb.toString();
				if (isDeviceNameChange) {

					// 非室内机别名修改通知该设备之外的所有小区内设备
					if (!AppConstants.DEVICE_TYPE_INDOOR.equals(deviceType)) {
						list = deviceService.queryAllDeviceExcludeCurrentDevice(areaNo, existDevice.getDeviceCode());

					}
				}
				if (isIPChange) {
					List<DeviceIp> ipList = new ArrayList<DeviceIp>();
					// 将原来ip改为未使用状态
					DeviceIp oldDeviceIp = deviceIpService.queryDeviceIpByIpAndAreaCode(existIP, areaNo);
					if (oldDeviceIp != null) {
						oldDeviceIp.setDevice(null);
						oldDeviceIp.setStatus(Constants.IP_NOT_USED);
						ipList.add(oldDeviceIp);
					}
					// 将新IP设置为使用状态
					DeviceIp newDeviceIp = deviceIpService.queryDeviceIpByIpAndAreaCode(existDevice.getDeviceIp(), areaNo);
					if (newDeviceIp != null) {
						// 处理ip冲突情况
						conflictDeviceMac = doIPConflict(ipState, conflictDeviceMac, newDeviceIp);
						newDeviceIp.setDevice(d);
						newDeviceIp.setHousingDistrictInfo(d.getHousingDistrictInfo());
						newDeviceIp.setSubnet(StringUtils.getIPSection(d.getDeviceIp()));
						newDeviceIp.setStatus(Constants.IP_USED);
						ipList.add(newDeviceIp);
					} else {
						newDeviceIp = new DeviceIp();
						newDeviceIp.setIpAddress(d.getDeviceIp());
						newDeviceIp.setHousingDistrictInfo(d.getHousingDistrictInfo());
						newDeviceIp.setSubnet(StringUtils.getIPSection(d.getDeviceIp()));
						newDeviceIp.setDevice(d);
						newDeviceIp.setStatus(Constants.IP_USED);
						ipList.add(newDeviceIp);
					}
					if (ipList != null && !ipList.isEmpty()) {
						deviceIpService.saveCollection(ipList);
					}
					// 通知该设备之外的所有小区内设备
					if (list == null) {
						list = deviceService.queryAllDeviceExcludeCurrentDevice(areaNo, existDevice.getDeviceMac());

					}

				}
				//this.createPushData(list, pushContent, "deviceInfo", PushKindConstants.PUSH_DEVICE_INFO_KIND);
			}
			result = AppConstants.SUCCESSFULL;
		}
		obj[0] = result;
		obj[1] = conflictDeviceMac;
		return obj;

	}

	/**
	 * 
	 * 方法的描述: 判断ip是否变化
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-21 下午6:48:17
	 * @param existIP
	 * @param ip
	 * @return
	 */
	private boolean isIPChange(String existIP, String ip) {
		boolean isChange = false;
		if (existIP != null && !existIP.equals(ip)) {
			isChange = true;
		}
		return isChange;
	}

	/**
	 * 
	 * 方法的描述: 判断设备名称是否变化
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-21 下午6:52:29
	 * @param existDeviceName
	 * @param deviceName
	 * @return
	 */
	private boolean isDeviceNameChange(String existDeviceName, String deviceName) {
		boolean isChange = false;
		if (existDeviceName != null && existDeviceName.equals(deviceName)) {
			isChange = true;
		}
		return isChange;
	}

	/**
	 * 
	 * 方法的描述: 产生设备编号
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-1 下午5:00:47
	 * @param areaNo
	 * @param regionNo
	 * @param buildingNo
	 * @param unitNo
	 * @param houseNo
	 * @param dt
	 * @return
	 */
	private String createDeviceNo(String areaNo, String regionNo, String buildingNo, String unitNo, String houseNo, String position,
			DeviceType dt, String isAllowManyDeviceInHouse) {
		String _deviceNo = null;

		// 如果是室内机
		if (AppConstants.DEVICE_TYPE_INDOOR.equals(dt.getDeviceType())) {
			// 一个房号下只允许存在一个设备
			if (isAllowManyDeviceInHouse != null && AppConstants.notAllowManyDevicesInHouse.equals(isAllowManyDeviceInHouse)) {

			} else {// 一个房号下允许存在多个设备
				// 从房号下获得存在的设备编号
				_deviceNo = deviceService.queryIndoorDeviceNo(areaNo, regionNo, buildingNo, unitNo, houseNo);
			}
			// 该房号下没有设备，则生成一个设备编号
			if (_deviceNo == null) {
				String fullHouseNo = areaNo.concat(StringUtils.covertNullToEmpty(regionNo))
						.concat(StringUtils.covertNullToEmpty(buildingNo)).concat(StringUtils.covertNullToEmpty(unitNo))
						.concat(StringUtils.covertNullToEmpty(houseNo)).trim();
				_deviceNo = deviceNoService.generatorDeviceNoByFullHouseNo(fullHouseNo);
			}
		} else// 非室内机直接生成一个设备编号
		{
			String fullHouseNo = areaNo.concat(regionNo == null ? "00" : regionNo).concat(buildingNo == null ? "00" : buildingNo)
					.concat(unitNo == null ? "00" : unitNo).concat(houseNo == null ? "0000" : houseNo)
					.concat(position == null ? "00" : position).trim();
			_deviceNo = deviceNoService.generatorDeviceNoByFullHouseNo(fullHouseNo);
		}
		return _deviceNo;
	}

	private String[] saveDevice(HousingDistrictInfo area, String deviceName, String devicePassword, String ip, String mac, DeviceType dt,
			String position, String result, String _deviceNo, HousingDistrictRegionInfo region, RegionBuildingInfo building,
			BuildingCellInfo unit, CellHouseholdInfo house, String ipState, String houseState,String unitDoorNo,String version,String sipid) {
		String obj[] = new String[2];
		String _result = result;
		// 被冲突设备的MAC
		String conflictDeviceMac = null;
		// 判断室内机是否房号已使用
		String dealResult[] = dealHouseNoConfict(area == null ? null : area.getCode(), region == null ? null : region.getCode(),
				building == null ? null : building.getCode(), unit == null ? null : unit.getCode(), house == null ? null : house.getCode(),
				position, dt, houseState);
		_result = dealResult[0];
		// 冲突的mac
		conflictDeviceMac = dealResult[1];
		if (_result == null) {
			// 封装
			Device existDevice = new Device(region, building, house, new Date(), deviceName, _deviceNo, deviceName, devicePassword,
					AppConstants.ONLINE, dt, area, mac, unit, new Date(), ip, position);
			existDevice.setVersion(version);
			existDevice.setSipid(sipid);
			existDevice.setDeviceStatus(AppConstants.ONLINE);
			existDevice.setUnitDoorNo(unitDoorNo);
			// 保持或更新
			Device d = deviceService.save_update(existDevice);
			// 设备ip
			DeviceIp deviceIp = deviceIpService.queryDeviceIpByIpAndAreaCode(ip, area.getCode());
			if (deviceIp != null) {
				// 处理ip冲突情况
				String conflictDeviceMacForIp = doIPConflict(ipState, conflictDeviceMac, deviceIp);
				if (conflictDeviceMac != null) {
					conflictDeviceMac = conflictDeviceMac + "_" + conflictDeviceMacForIp;
				}
				deviceIp.setDevice(d);
				deviceIp.setHousingDistrictInfo(d.getHousingDistrictInfo());
				deviceIp.setSubnet(StringUtils.getIPSection(d.getDeviceIp()));
				deviceIp.setStatus(Constants.IP_USED);// 设置为已使用
				deviceIpService.updateDeviceIp(deviceIp);
			} else {
				deviceIp = new DeviceIp();
				deviceIp.setIpAddress(d.getDeviceIp());
				deviceIp.setHousingDistrictInfo(d.getHousingDistrictInfo());
				deviceIp.setDevice(d);
				deviceIp.setSubnet(StringUtils.getIPSection(d.getDeviceIp()));
				deviceIp.setStatus(Constants.IP_USED);// 设置为已使用
				deviceIpService.saveDeviceIp(deviceIp);
			}
			// boolean flag = deviceNoService.updateDeviceId(d, _deviceNo);

			// 推送信息给小区内其他设备
			//pushClientToOtherClients(d);

			String siteNo = createSiteNo(area, region, building, unit, house);
			// 写日志
			this.writeAppRegistLog(new Date(), mac, ip, deviceName, _deviceNo, area == null ? null : area.getName(), region == null ? null
					: region.getName(), building == null ? null : building.getName(), unit == null ? null : unit.getName(),
					house == null ? null : house.getName(), position, siteNo, dt.getDeviceName(), AppConstants.APP_FIRST_REGIST);
			_result = AppConstants.SUCCESSFULL;
		}
		obj[0] = _result;
		obj[1] = conflictDeviceMac;
		return obj;
	}

	/**
	 * 
	 * 方法的描述: 处理IP冲突情况
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-21 下午8:55:04
	 * @param ipState
	 * @param conflictDeviceMac
	 * @param deviceIp
	 * @return
	 */
	private String doIPConflict(String ipState, String conflictDeviceMac, DeviceIp deviceIp) {
		if (Constants.IP_USED.equals(deviceIp.getStatus()) && AppConstants.FORCE_TAKE_IP.equals(ipState)) {
			Device conflicIPDevice = deviceIp.getDevice();
			if (conflicIPDevice != null) {
				conflictDeviceMac = conflicIPDevice.getDeviceMac();
				Device updatedDevice = deviceService.updateDeviceForIPConflict(conflicIPDevice, deviceIp);
				// if (updatedDevice != null) {
				// 冲突的设备ip被设置为空，推送信息给小区内其他设备
				// pushClientToOtherClients(updatedDevice);
				// }
			}
		}
		return conflictDeviceMac;
	}

	/**
	 * 
	 * 方法的描述: 组装成完整房号
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-13 下午4:44:06
	 * @param area
	 * @param region
	 * @param building
	 * @param unit
	 * @param house
	 * @return
	 */
	private String createSiteNo(HousingDistrictInfo area, HousingDistrictRegionInfo region, RegionBuildingInfo building,
			BuildingCellInfo unit, CellHouseholdInfo house) {
		StringBuilder sb = new StringBuilder();

		sb.append(area.getCode());
		if (region != null) {
			sb.append(" ");
			sb.append(region.getCode());

		}
		if (building != null) {
			sb.append(" ");
			sb.append(building.getCode());
		}
		if (unit != null) {
			sb.append(" ");
			sb.append(unit.getCode());

		}
		if (house != null) {
			sb.append(" ");
			sb.append(house.getCode());

		}
		return sb.toString();
	}

	private void pushClientToOtherClients(Device device) {
		if (device != null) {
			DeviceType deviceType = device.getDeviceType();
			if (deviceType != null) {
				String deviceName = device.getDeviceName();// 设备名称
				String type = deviceType.getDeviceType();// 设备类型
				// 小区编号
				String areaNo = device.getHousingDistrictInfo() == null ? null : device.getHousingDistrictInfo().getCode();
				// 区域编号
				String regionNo = device.getHousingDistrictRegionInfo() == null ? null : device.getHousingDistrictRegionInfo().getCode();
				// 楼栋编号
				String buildingNo = device.getRegionBuildingInfo() == null ? null : device.getRegionBuildingInfo().getCode();
				// 单元编号
				String unitNo = device.getBuildingCellInfo() == null ? null : device.getBuildingCellInfo().getCode();
				// 房号
				String houseNo = device.getCellHouseholdInfo() == null ? null : device.getCellHouseholdInfo().getCode();
				String deviceId = device.getDeviceId();
				// ip
				String ip = device.getDeviceIp();
				// 设备编号
				String deviceNo = device.getDeviceCode();
				String mac = device.getDeviceMac();

				List<Device> deviceList = null;
				// 单元机,只推送该单元下室内机
				if (AppConstants.DEVICE_TYPE_UNIT.equals(type)) {
					deviceList = deviceService.findAll(areaNo, regionNo, buildingNo, unitNo, AppConstants.DEVICE_TYPE_INDOOR, deviceId);

				} else // 推送给所有机器除本机之外
				{
					deviceList = deviceService.findAll(areaNo, regionNo, buildingNo, unitNo, null, deviceId);
				}
				// 非室内机
				if (!AppConstants.DEVICE_TYPE_INDOOR.equals(type)) {
					// 将位置号赋给房号
					houseNo = device.getPosition();
				}
				if (deviceList != null && !deviceList.isEmpty()) {
					StringBuilder sb = new StringBuilder();
					sb.append("deviceName:").append(covertNullToString(deviceName)).append(",areaNo:").append(covertNullToString(areaNo))
							.append(",regionNo:").append(covertNullToString(regionNo)).append(",buildingNo:")
							.append(covertNullToString(buildingNo)).append(",unitNo:").append(covertNullToString(unitNo))
							.append(",roomNo:").append(covertNullToString(houseNo)).append(",deviceNo:")
							.append(covertNullToString(deviceNo)).append(",deviceType:").append(covertNullToString(type)).append(",ip:")
							.append(covertNullToString(ip)).append(",mac:").append(covertNullToString(mac));
					// 推送内容
					String pushContent = sb.toString();
					// 保存到推送表
					pushService.pushToClients("deviceInfo", pushContent, PushKindConstants.PUSH_DEVICE_INFO_KIND, null, null, null,
							deviceList);

				}
			}
		}
	}

	/**
	 * 
	 * 方法的描述: 将null转空值
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-2 下午6:21:26
	 * @param s
	 * @return
	 */
	private String covertNullToString(String s) {
		if (s == null) {
			return "";
		} else {
			return s;
		}
	}

	/**
	 * 
	 * 方法的描述: 产生推送数据
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-13 下午7:10:38
	 * @param list
	 *            推送设备列表
	 * @param pushContent
	 *            推送内容
	 * @param pushName
	 *            推送名称
	 * @param pushKind
	 *            推送类型
	 * @return
	 */
	private boolean createPushData(List<Device> list, String pushContent, String pushName, String pushKind) {
		boolean flag = false;
		List<Push> pushList = null;
		if (list != null && !list.isEmpty()) {
			pushList = new ArrayList<Push>();
			for (Device d : list) {
				if (d != null) {
					String deviceNo = d.getDeviceCode();
					if (deviceNo != null) {
						Push p = new Push(pushName, pushKind, pushContent, new Date(), deviceNo);
						pushList.add(p);
					}
				}
			}
		}
		if (pushList != null && !pushList.isEmpty()) {
			flag = pushService.inserPushCollection(pushList);
		}
		return flag;
	}

	/**
	 * 
	 * 方法的描述: 验证小区基本信息
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-13 下午3:01:06
	 * @param av
	 * @param areaNo
	 *            小区编号
	 * @param regionNo
	 *            区域编号
	 * @param buildingNo
	 *            楼宇编号
	 * @param unitNo
	 *            单元编号
	 * @param houseNo
	 *            房子编号
	 */
	private Map<String, Object> validBaseAreaInfo(AppVO av, String areaNo, String regionNo, String buildingNo, String unitNo, String houseNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (regionNo != null) {
			// 判断区域是否存在
			Object[] o = this.isExistAreaRegion(areaNo, regionNo);
			if (o[0] != null) {
				av.setResult((String) o[0]);
			} else {
				HousingDistrictRegionInfo region = (HousingDistrictRegionInfo) o[1];
				map.put(AppConstants.REGION, region);
				if (region != null && buildingNo != null) {
					// 判断楼宇是否存在
					o = this.isExistBuilding(areaNo, regionNo, buildingNo);
					if (o[0] != null) {
						av.setResult((String) o[0]);
					} else {
						RegionBuildingInfo building = (RegionBuildingInfo) o[1];
						map.put(AppConstants.BUILDING, building);
						if (building != null && unitNo != null) {
							// 判断单元是否存在
							o = this.isExistUnit(areaNo, regionNo, buildingNo, unitNo);
							if (o[0] != null) {
								av.setResult((String) o[0]);
							} else {
								BuildingCellInfo unit = (BuildingCellInfo) o[1];
								map.put(AppConstants.UNIT, unit);
								if (unit != null && houseNo != null) {
									// 判断房号是否存在
									o = this.isExistHouse(areaNo, regionNo, buildingNo, unitNo, houseNo);
									if (o[0] != null) {
										av.setResult((String) o[0]);
									} else {
										CellHouseholdInfo house = (CellHouseholdInfo) o[1];
										map.put(AppConstants.HOUSE, house);
									}
//									o = this.isExistHouse(areaNo, regionNo, buildingNo, unitNo, houseNo);
//									CellHouseholdInfo house = new CellHouseholdInfo();
//									if (o[0] != null) {
//										house.setCode(houseNo);
//										house.setName(houseNo);
//										if(unit.getId()!=null && !"".equals(unit.getId())){
//											BuildingCellInfo bc = buildingCellInfoService.get(unit.getId());
//											house.setTHmBuildingCellInfo(bc);
//										}
//									} else {
//										house = (CellHouseholdInfo) o[1];
//									}
//									map.put(AppConstants.HOUSE, house);
								}
							}
						}
					}
				}
			}
		}
		return map;
	}

	@Override
	public boolean outLine(String mac) {
		boolean flag = false;
		if (mac != null) {
			Device d = deviceService.queryDeviceByMac(mac);
			if (d != null) {
				d.setDeviceStatus(AppConstants.OUTLINE);
				d.setUpdatedTime(new Date());
				deviceService.save_update(d);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public void updateDeviceStatusForAll(String status) {
		if (status != null) {
			List<Device> list = null;
			// 如果是参数是在线状态
			if (status.equals(AppConstants.ONLINE)) {
				// 获得离线设备列表
				list = deviceService.findDeviceByStatus(AppConstants.OUTLINE);
			} else {// 如果参数是离线状态
				// 获得在线设备列表
				list = deviceService.findDeviceByStatus(AppConstants.ONLINE);
			}
			if (list != null && !list.isEmpty()) {
				List<Device> updateList = new ArrayList<Device>();
				for (Device d : list) {
					if (d != null) {
						if (!status.equals(d.getDeviceStatus())) {
							d.setDeviceStatus(status);
							d.setUpdatedTime(new Date());
							updateList.add(d);
						}
					}
				}
				if (updateList != null && !updateList.isEmpty()) {
					deviceService.saveOrUpdateCollection(updateList);
				}
			}
		}

	}

	@Override
	public void updateDeviceStatusByMac(String status, String mac) {
		Device d = deviceService.queryDeviceByMac(mac);
		if (d != null) {
			if (status != null) {
				if (!status.equals(d.getDeviceStatus())) {
					d.setDeviceStatus(status);
					deviceService.update(d);
				}
			}
		}

	}

	/**
	 * 
	 * 方法的描述:
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-13 下午3:33:22
	 */
	private void writeAppRegistLog(Date addTime, String macAddr, String userIp, String deviceName, String deviceNo, String districtName,
			String regionName, String buildingName, String cellName, String hourseNo, String location, String siteNo, String diveceType,
			String isFirstRegist) {
		DiviceRegeditLog registLog = new DiviceRegeditLog(addTime, macAddr, userIp, deviceName, deviceNo, districtName, regionName,
				buildingName, cellName, hourseNo, location, siteNo, diveceType, isFirstRegist);
		appRegistLogService.saveDiviceRegeditLog(registLog);

	}

	@Override
	public String queryCronTimeUpdate() {
		StringBuffer sb = new StringBuffer();
		String instancy_cron_time = sysParamService.queryParamValueByParamCode(Constants.CRON_TIME_INSTANCY);
		String secondary_cron_time = sysParamService.queryParamValueByParamCode(Constants.CRON_TIME_SECONDARY);
		String normal_cron_time = sysParamService.queryParamValueByParamCode(Constants.CRON_TIME_NORMAL);
		String heartBeat_cron_time = sysParamService.queryParamValueByParamCode(Constants.CRON_TIME_HEARTBEAT);
		sb.append(Constants.CRON_TIME_INSTANCY).append(":").append(instancy_cron_time).append(",").append(Constants.CRON_TIME_SECONDARY)
				.append(":").append(secondary_cron_time).append(",").append(Constants.CRON_TIME_NORMAL).append(":")
				.append(normal_cron_time).append(",").append(Constants.CRON_TIME_HEARTBEAT).append(":").append(heartBeat_cron_time);
        return sb.toString();
	}

	private void saveHouse(CellHouseholdInfo house){
//		BuildingCellInfo cell = buildingCellInfoService.get(house.getTHmBuildingCellInfo().getId());
//		isExistHouse(String areaNo, String regionNo, String buildingNo, String unitNo, String houseNo);
//		if(cell)
		cellHouseholdInfoService.save(house);
	}
	
	@Override
	@Transactional(propagation = Propagation.NESTED)
	public AppVO easyLogin(AppLoginVO loginInfo) {
		AppVO av = new AppVO();
		String deviceNo = null;
		String dataServerIP = "";
		String socketServerIP = "";
		String fileServerIP = "";
		String conflictMac = null;
		String result = AppConstants.FAILURE;
		if (loginInfo != null) {
			// 设备编号
			deviceNo = loginInfo.getDeviceNo();
			// 小区编号
			String areaNo = loginInfo.getAreaNo();
			// 区域编号
			String regionNo = loginInfo.getRegionNo();
			// 楼宇编号
			String buildingNo = loginInfo.getBuildingNo();
			// 单元编号
			String unitNo = loginInfo.getUnitNo();
			// 房屋编号
			String houseNo = loginInfo.getHouseNo();
			// mac地址
			String mac = loginInfo.getMac();
			// ip
			String ip = loginInfo.getIp();
			// 设备名称
			String deviceName = loginInfo.getDeviceName();
			// 设备类型
			String deviceType = loginInfo.getDeviceType();
			// 设备密码
			String devicePassword = loginInfo.getDevicePassword();
			// ip在冲突情况下，是否强制切换
			String ipState = loginInfo.getIpState();
			// 房号在冲突情况下，是否强制切换
			String houseState = loginInfo.getHouseState();
			// 位置
			String position = null;
			//版本
			String version = loginInfo.getVersion();

			if (mac != null && deviceType != null) {

				if (devicePassword != null) {
					// 密码加密
					//devicePassword = CryptoUtils.encodeByMD5(devicePassword);
				}

				// 判断该设备是否已经注册过
				Device existDevice = deviceService.queryDeviceByMac(
						loginInfo.getMac(), areaNo, regionNo, buildingNo,
						unitNo, houseNo);
				DeviceType dt = deviceTypeService.get(deviceType);
				if (dt != null) {
					// 登陆处理
					if (AppConstants.FAILURE.equals(result)) {

						String[] obj = this.easyLoginDo(existDevice, av,deviceNo, deviceName, devicePassword, ip, mac,dt,version);
						if (obj != null) {
							result = obj[0];// 登陆结果
							deviceNo = obj[1];// 设备编号
							conflictMac = obj[2];// 冲突的mac地址
							if (AppConstants.SUCCESSFULL.equals(result)) {
								// 数据服务器地址
								dataServerIP = sysParamService
										.queryParamValueByParamCode(AppConstants.DATA_SERVER_IP);
								// 文件服务器地址
								fileServerIP = sysParamService
										.queryParamValueByParamCode(AppConstants.FILE_SERVER_IP);
								// socket服务器地址
								socketServerIP = sysParamService
										.queryParamValueByParamCode(AppConstants.SOCKET_SERVER_IP);
							} else {
								deviceNo = loginInfo.getDeviceNo();// 返回原来设备编号
							}
						}
					}
				}
			}
		}
		// 封装数据
		av.setDeviceNo(deviceNo);// 设备编号
		av.setResult(result);// 登陆结果
		av.setDataServerIP(dataServerIP);// 数据服务器地址
		av.setFileServerIP(fileServerIP);// 文件服务器 地址
		av.setSocketServerIP(socketServerIP);// socket服务器地址
		av.setConflictMac(conflictMac);// 冲突mac地址
		return av;
	}
	
	private String[] easyLoginDo(Device existDevice, AppVO av,String deviceNo, String deviceName,
			String devicePassword, String ip, String mac, DeviceType dt, String version) {
		String result = AppConstants.FAILURE;
		String _deviceNo = deviceNo;
		String[] obj = new String[3];

		String conflictDeviceMac = null;
		result = av.getResult();

		// 第一次注册登陆
		if (existDevice == null) {
			if (av.getResult() == null) {
				// 产生设备编号
				_deviceNo = StringUtils.coverMacToString(mac);
				Device device = new Device(null, null, null, new Date(), deviceName, _deviceNo, deviceName, devicePassword,
						AppConstants.ONLINE, dt, null, mac, null, new Date(), ip, null);
				// 保存或更新
				Device d = deviceService.save_update(device);

				result = AppConstants.SUCCESSFULL;
				conflictDeviceMac = d.getDeviceMac();
			}

		} else// 非第1次注册登陆
		{
			_deviceNo = existDevice.getDeviceCode();

			existDevice.setDeviceType(dt);
			existDevice.setDeviceIp(ip);
			existDevice.setUpdatedTime(new Date());
			existDevice.setDeviceStatus(AppConstants.ONLINE);
			existDevice.setVersion(version);
			Device d = deviceService.save_update(existDevice);
			result = AppConstants.SUCCESSFULL;
			conflictDeviceMac = d.getDeviceMac();

		}
		obj[0] = result;
		obj[1] = _deviceNo;
		obj[2] = conflictDeviceMac;
		return obj;
	}
	
	public IRegionBuildingInfoService getRegionBuildingInfoService() {
		return regionBuildingInfoService;
	}

	public void setRegionBuildingInfoService(IRegionBuildingInfoService regionBuildingInfoService) {
		this.regionBuildingInfoService = regionBuildingInfoService;
	}

	public IHousingDistrictRegionInfoService getHousingDistrictRegionInfoService() {
		return housingDistrictRegionInfoService;
	}

	public void setHousingDistrictRegionInfoService(IHousingDistrictRegionInfoService housingDistrictRegionInfoService) {
		this.housingDistrictRegionInfoService = housingDistrictRegionInfoService;
	}

	public IHousingDistrictInfoService getHousingDistrictInfoService() {
		return housingDistrictInfoService;
	}

	public void setHousingDistrictInfoService(IHousingDistrictInfoService housingDistrictInfoService) {
		this.housingDistrictInfoService = housingDistrictInfoService;
	}

	public IBuildingCellInfoService getBuildingCellInfoService() {
		return buildingCellInfoService;
	}

	public void setBuildingCellInfoService(IBuildingCellInfoService buildingCellInfoService) {
		this.buildingCellInfoService = buildingCellInfoService;
	}

	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}

	public void setCellHouseholdInfoService(ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
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

	public IDeviceTypeService getDeviceTypeService() {
		return deviceTypeService;
	}

	public void setDeviceTypeService(IDeviceTypeService deviceTypeService) {
		this.deviceTypeService = deviceTypeService;
	}

	public IPushService getPushService() {
		return pushService;
	}

	public void setPushService(IPushService pushService) {
		this.pushService = pushService;
	}

	public ISysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	public IDeviceIpService getDeviceIpService() {
		return deviceIpService;
	}

	public void setDeviceIpService(IDeviceIpService deviceIpService) {
		this.deviceIpService = deviceIpService;
	}

	public IDiviceRegeditLogService getAppRegistLogService() {
		return appRegistLogService;
	}

	public void setAppRegistLogService(IDiviceRegeditLogService appRegistLogService) {
		this.appRegistLogService = appRegistLogService;
	}

}
