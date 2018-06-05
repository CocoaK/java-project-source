package com.biencloud.smarthome.web.appdata.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.biencloud.smarthome.web.appdata.constant.AppDataConstant;
import com.biencloud.smarthome.web.appdata.json.RoomDeviceJson;
import com.biencloud.smarthome.web.appdata.json.RoomInfoJson;
import com.biencloud.smarthome.web.appdata.json.RoomJson;
import com.biencloud.smarthome.web.appdata.json.RoomSipJson;
import com.biencloud.smarthome.web.appdata.service.IRoomService;
import com.biencloud.smarthome.web.appdata.vo.RoomDeviceVO;
import com.biencloud.smarthome.web.appdata.vo.RoomInfoVO;
import com.biencloud.smarthome.web.appdata.vo.RoomSipVO;
import com.biencloud.smarthome.web.appdata.vo.RoomVO;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.util.JsonUtil;
import com.biencloud.smarthome.web.device.service.IDeviceService;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.devicetype.vo.DeviceTypeVO;
import com.biencloud.smarthome.web.housemgr.service.IHousingDistrictInfoService;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.monitor.service.ISceneMonitorService;
import com.biencloud.smarthome.web.monitor.vo.SceneDeviceVo;
import com.biencloud.smarthome.web.wsclient.stub.Device;
import com.biencloud.smarthome.web.wsclient.stub.HousingDistrictInfo;
import com.biencloud.smarthome.web.wsclient.stub.Room;
import com.ibm.wsdl.util.StringUtils;

public class RoomServiceImpl extends BaseService<RoomVO> implements IRoomService {
	
	private IDeviceService deviceService;
	private ISceneMonitorService sceneMonitorService;
	private IHousingDistrictInfoService housingDistrictInfoService;
	
	@Override
	public RoomJson queryRooms(String deviceCode) {
		RoomJson roomJson = new RoomJson();
		try{
			Device device = getSmartHomeService().queryDeviceByCode(deviceCode);
			//查询房间设备数据集合
			List<Device> ds = getSmartHomeService().queryFamilyDevice(device);
			List<RoomVO> list = new ArrayList<RoomVO>();
			if (ds != null && ds.size() != 0) {
				for (Device d : ds) {
					RoomVO room = new RoomVO();
					if(d!=null){
						room.setDeviceNo(d.getDeviceCode());	//设备编号
						room.setDeviceName(d.getDeviceName());	//设备名称
						room.setIp(d.getDeviceIp());		//设备IP
						room.setMac(d.getDeviceMac());	//设备MAC地址
						if(d.getDeviceType()!=null)
							room.setDeviceType(d.getDeviceType().getDeviceType());	//设备类型
						if (d.getCellHouseholdInfo() != null)
							room.setRoomNo(d.getCellHouseholdInfo().getCode());	//房号
						if (d.getBuildingCellInfo() != null)
							room.setUnitNo(d.getBuildingCellInfo().getCode());	//单元号
						if (d.getRegionBuildingInfo() != null)
							room.setBuildingNo(d.getRegionBuildingInfo().getCode());	//楼栋号
						if (d.getHousingDistrictRegionInfo() != null)
							room.setAreaNo(d.getHousingDistrictRegionInfo().getCode());	//区域号
					}
					list.add(room);
				}
			}
			roomJson.setRoomList(list);
			roomJson.setCode(AppDataConstant.SUCCESS);
		}
		catch(Exception e){
			roomJson.setCode(AppDataConstant.FAILTRUE);
		}
		return roomJson;
	}

	@Override
	public RoomInfoJson queryRoomInfoByDeviceNo(String deviceNo) {
		RoomInfoJson rij = new RoomInfoJson();
		try {
			if (deviceNo != null) {
				rij.setDeviceNo(deviceNo);
				//查询出房间数据集合
				List<Room> roomList = super.getSmartHomeService().queryRoomByDeviceNo(deviceNo);
				List<RoomInfoVO> rivList = null;
				//房间数据转换
				if (roomList != null && !roomList.isEmpty()) {
					rivList = new ArrayList<RoomInfoVO>();
					for (Room rv : roomList) {
						if (rv != null) {
							RoomInfoVO riv = new RoomInfoVO(rv.getId(), rv.getName(), rv.getPlan());
							rivList.add(riv);
						}
					}
					rij.setRoomData(rivList);
				}
			}
			rij.setCode(AppDataConstant.SUCCESS);
		} catch (Exception e) {
			rij.setCode(AppDataConstant.FAILTRUE);
		}
		return rij;
	}

	@Override
	public RoomVO queryRoom(String jsonString) {
		RoomVO roomVO = new RoomVO();
		JSONObject jsonObj = JsonUtil.jsonStringToJsonObject(jsonString);
		if(jsonObj!=null){
			String regionNo = jsonObj.getString("regionNo");	//小区号
			String areaNo = jsonObj.getString("areaNo");		//区域号
			String buildingNo = jsonObj.getString("buildingNo");//楼栋号
			String unitNo = jsonObj.getString("unitNo");		//单元号
			String deviceNo = jsonObj.getString("deviceNo");	//设备编号
			String roomNo = jsonObj.getString("roomNo");		//房号
			
			Device device = super.getSmartHomeService().queryDeviceByCode(deviceNo);
			if(device!=null && device.getDeviceType()!=null && Constants.UNIT_DOOR_DEVICE.equals(device.getDeviceType().getDeviceType())){
				if(device.getHousingDistrictInfo()!=null)
					regionNo = device.getHousingDistrictInfo().getCode();	//小区号
				if(device.getHousingDistrictRegionInfo()!=null)
					areaNo = device.getHousingDistrictRegionInfo().getCode();	//区域号
				if(device.getRegionBuildingInfo()!=null)
					buildingNo = device.getRegionBuildingInfo().getCode();	//楼栋号
				if(device.getBuildingCellInfo()!=null)
					unitNo = device.getBuildingCellInfo().getCode();	//单元号
			}
			Device d= super.getSmartHomeService().queryDeviceByRoomNo(regionNo, areaNo, buildingNo, unitNo, roomNo);
			if(d != null){
				roomVO.setAreaNo(areaNo);
				roomVO.setBuildingNo(buildingNo);
				roomVO.setUnitNo(unitNo);
				roomVO.setRoomNo(roomNo);
				roomVO.setDeviceNo(d.getDeviceCode());
				roomVO.setDeviceName(d.getDeviceName());
				if(d.getDeviceType()!=null)
					roomVO.setDeviceType(d.getDeviceType().getDeviceType());
				roomVO.setIp(d.getDeviceIp());
				roomVO.setMac(d.getDeviceMac());
			}
		}
		return roomVO;
	}

	@Override
	public RoomJson queryManageDevice(String deviceNo) {
		DeviceVO deviceVO = deviceService.queryDeviceByCode(deviceNo);
		if(deviceVO == null){
			return null;
		}
		DeviceVO dvc = new DeviceVO();
		DeviceTypeVO deviceType = new DeviceTypeVO();
		//管理机
		deviceType.setDeviceType(Constants.MANAGEMENT_DEVICE);
		dvc.setHousingDistrictInfo(deviceVO.getHousingDistrictInfo());
		dvc.setDeviceType(deviceType);
		List<DeviceVO> list = deviceService.queryDevices(dvc);
		List<RoomVO> roomList = new ArrayList<RoomVO>();
		RoomJson roomJson = new RoomJson();
		if(list != null && list.size() != 0){
			for(DeviceVO device : list){
				if(device!=null){
				RoomVO roomVO = new RoomVO();
				roomVO.setDeviceNo(device.getDeviceCode());
				roomVO.setDeviceName(device.getDeviceName());
				roomVO.setIp(device.getDeviceIp());
				roomVO.setMac(device.getDeviceMac());
				roomVO.setRoomNo(device.getPosition());	//位置号
				if(device.getDeviceType()!=null)
					roomVO.setDeviceType(device.getDeviceType().getDeviceType());
				if(device.getHousingDistrictRegionInfo() != null)
					roomVO.setAreaNo(device.getHousingDistrictRegionInfo().getCode());
				if(device.getRegionBuildingInfo() != null)
					roomVO.setBuildingNo(device.getRegionBuildingInfo().getCode());
				if(device.getBuildingCellInfo() != null)
					roomVO.setUnitNo(device.getBuildingCellInfo().getCode());
				roomList.add(roomVO);
				}
			}
		}
		roomJson.setRoomList(roomList);
		return roomJson;
	}

	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@Override
	public RoomDeviceJson queryRoomDevices(String deviceNo) {
		RoomDeviceJson rdj = new RoomDeviceJson();
		try {
			if (deviceNo != null) {
				rdj.setDeviceNo(deviceNo);
				//查询出房间数据集合
				List<Room> roomList = super.getSmartHomeService().queryRoomByDeviceNo(deviceNo);
				List<RoomDeviceVO> rdvList = null;
				//房间数据转换
				if (roomList != null && !roomList.isEmpty()) {
					
					rdvList = new ArrayList<RoomDeviceVO>();
					for (Room rv : roomList) {
						if (rv != null) {
							SceneDeviceVo sdv = new SceneDeviceVo();
							sdv.setDeviceNo(deviceNo);
							sdv.setRoomNo(rv.getId());
							List<SceneDeviceVo> sceneDevices = sceneMonitorService.querySceneDevice(sdv);
							RoomDeviceVO rdv = new RoomDeviceVO(rv.getId(), rv.getName(), rv.getPlan(),sceneDevices);
							rdvList.add(rdv);
						}
					}
					rdj.setRoomDeviceData(rdvList);
				}
			}
			rdj.setCode(AppDataConstant.SUCCESS);
		} catch (Exception e) {
			rdj.setCode(AppDataConstant.FAILTRUE);
		}
		return rdj;
	}

	public ISceneMonitorService getSceneMonitorService() {
		return sceneMonitorService;
	}

	public void setSceneMonitorService(ISceneMonitorService sceneMonitorService) {
		this.sceneMonitorService = sceneMonitorService;
	}

	public IHousingDistrictInfoService getHousingDistrictInfoService() {
		return housingDistrictInfoService;
	}

	public void setHousingDistrictInfoService(
			IHousingDistrictInfoService housingDistrictInfoService) {
		this.housingDistrictInfoService = housingDistrictInfoService;
	}

	@Override
	public RoomSipJson queryManageDeviceByRoomNo(String roomNo) {
		if("".equals(roomNo) || roomNo == null){
			return null;
		}
		
		String districtCode = roomNo.substring(0,4);
		HousingDistrictInfoVo housingDistrictInfoVo = new HousingDistrictInfoVo();
		HousingDistrictInfoVo hdi = new HousingDistrictInfoVo();
		housingDistrictInfoVo.setCode(districtCode);
		List<HousingDistrictInfoVo> housingDistrictInfoVos = housingDistrictInfoService.getDistricts(housingDistrictInfoVo);
		if(housingDistrictInfoVos==null || housingDistrictInfoVos.size()<=0){
			return null;
		}
		hdi = housingDistrictInfoVos.get(0);
		DeviceVO dvc = new DeviceVO();
		DeviceTypeVO deviceType = new DeviceTypeVO();
		//管理机
		deviceType.setDeviceType(Constants.MANAGEMENT_DEVICE);
		dvc.setHousingDistrictInfo(hdi);
		dvc.setDeviceType(deviceType);
		List<DeviceVO> list = deviceService.queryDevices(dvc);
		List<RoomSipVO> roomSipList = new ArrayList<RoomSipVO>();
		RoomSipJson roomSipJson = new RoomSipJson();
		try{
			if(list != null && list.size() != 0){
				for(DeviceVO device : list){
					if(device!=null){
					RoomSipVO roomSipVO = new RoomSipVO();
					StringBuffer sb = new StringBuffer();
					roomSipVO.setDeviceNo(device.getDeviceCode());
					roomSipVO.setDeviceName(device.getDeviceName());
					roomSipVO.setIp(device.getDeviceIp());
					roomSipVO.setMac(device.getDeviceMac());
					//roomSipVO.setRoomNo(device.getPosition());	//位置号
	//				if(device.getDeviceType()!=null)
	//					roomSipVO.setDeviceType(device.getDeviceType().getDeviceType());
	//				if(device.getHousingDistrictRegionInfo() != null)
	//					roomSipVO.setAreaNo(device.getHousingDistrictRegionInfo().getCode());
	//				if(device.getRegionBuildingInfo() != null)
	//					roomSipVO.setBuildingNo(device.getRegionBuildingInfo().getCode());
	//				if(device.getBuildingCellInfo() != null)
	//					roomVO.setUnitNo(device.getBuildingCellInfo().getCode());
					if(device.getHousingDistrictInfo() != null)
						sb.append(device.getHousingDistrictInfo().getCode());
					if(device.getHousingDistrictRegionInfo() != null){
						sb.append(device.getHousingDistrictRegionInfo().getCode());
					}else{
						sb.append("00");
					}
					if(device.getRegionBuildingInfo() != null){
						device.getRegionBuildingInfo().getCode();
					}else{
						sb.append("000");
					}
					if(device.getBuildingCellInfo() != null){
						device.getBuildingCellInfo().getCode();
					}else{
						sb.append("00");
					}
					sb.append(device.getPosition());
					roomSipVO.setSipuid(sb.toString());
					roomSipList.add(roomSipVO);
					}
				}
			}
			roomSipJson.setRoomSipList(roomSipList);
			roomSipJson.setCode(AppDataConstant.SUCCESS);
		} catch (Exception e) {
			roomSipJson.setCode(AppDataConstant.FAILTRUE);
		}
		return roomSipJson;
	}
	
}
