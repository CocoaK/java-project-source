package com.biencloud.smarthome.web.device.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.device.service.IDeviceService;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.devicetype.vo.DeviceTypeVO;
import com.biencloud.smarthome.web.housemgr.util.HouseManagementModelVoConvert;
import com.biencloud.smarthome.web.wsclient.stub.Device;
import com.biencloud.smarthome.web.wsclient.stub.DeviceType;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

public class DeviceServiceImpl extends BaseService<DeviceVO> implements IDeviceService{
	
	private static final String CREATED_TIME = "createdTime";
	private static final String UPDATED_TIME = "updatedTime";
	private static final String DEVICE_TYPE = "deviceType";
	private static final String HOUSING_DISTRICT_INFO = "housingDistrictInfo";
	private static final String CELL_HOUSE_HOLD_INFO = "cellHouseholdInfo";
	private static final String BUILDING_CELL_INFO = "buildingCellInfo";
	private static final String HOUSING_DISTRICT_REGION_INFO = "housingDistrictRegionInfo";
	private static final String REGION_BUILDING_INFO = "regionBuildingInfo";
	private static final String[] STR = new String[]{
			DEVICE_TYPE,HOUSING_DISTRICT_INFO,CELL_HOUSE_HOLD_INFO,
			BUILDING_CELL_INFO,HOUSING_DISTRICT_REGION_INFO,REGION_BUILDING_INFO};
	
	@Override
	public List<DeviceVO> queryDevices(DeviceVO deviceVO) {
		Device device = new Device();
		device = voToDevice(deviceVO);
		List<DeviceVO> list = new ArrayList<DeviceVO>();
		
		List<Device> devices = getSmartHomeService().queryDevices(device);
		if(devices != null && devices.size() > 0){
			for (Device d : devices) {
				DeviceVO vo = new DeviceVO();
				vo = deviceToVO(d);
				list.add(vo);
			}
		}	
		return list;
	}
	
	@Override
	public List<DeviceVO> queryOwnerUnitDevice(String unitId, String roomId) {
		List<DeviceVO> list = new ArrayList<DeviceVO>();
		List<Device> devices = getSmartHomeService().queryOwnerUnitDevice(unitId, roomId);
		if(devices != null && devices.size() > 0){
			for (Device d : devices) {
				DeviceVO vo = new DeviceVO();
				vo = deviceToVO(d);
				list.add(vo);
			}
		}
		return list;
	}

	@Override
	public List<DeviceVO> queryPropertyDevice(String distrcitId) {
		List<DeviceVO> list = new ArrayList<DeviceVO>();
		List<Device> devices = getSmartHomeService().queryPropertyDevice(distrcitId);
		if(devices != null && devices.size() > 0){
			for (Device d : devices) {
				DeviceVO vo = new DeviceVO();
				vo = deviceToVO(d);
				list.add(vo);
			}
		}
		return list;
	}

	@Override
	public int update(DeviceVO deviceVO,String updateType) {
		Device device = voToDevice(deviceVO);	//转换
		return getSmartHomeService().updateDevice(device,updateType);
		
	}

	@Override
	public DeviceVO queryDeviceVOById(String deviceId) {
		Device device = getSmartHomeService().queryDeviceById(deviceId);
		DeviceVO deviceVO = deviceToVO(device);	//转换
		return deviceVO;
	}

	@Override
	public PagingVO<DeviceVO> queryDeviceForPaging(DeviceVO deviceVO,
			int pageNum, int pageSize) {
		Device device = new Device();
		PagingVO<DeviceVO> paging;
		if(deviceVO != null){
			device = voToDevice(deviceVO);
		}
		Paging _paging = getSmartHomeService().queryDeviceForPaging(device, pageNum, pageSize);
		//将Paging对象转换成PagingVO对象
		paging = convertToPagingVO(_paging,STR,CREATED_TIME,UPDATED_TIME);
		return paging;
	}
	
	@Override
	public List<DeviceVO> queryDevices(List<String> districtIds, List<String> deviceTypes) {
		List<DeviceVO> list = new ArrayList<DeviceVO>();
		List<Device> devices = getSmartHomeService().queryDevicesList(districtIds, deviceTypes);
		if(devices != null && devices.size() > 0){
			for (Device d : devices) {
				DeviceVO vo = new DeviceVO();
				vo = deviceToVO(d);
				list.add(vo);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * 方法的描述: paging中的results中的对象有对象属性例如本方法中的DeviceVO中有deviceTypeVO类型的属性，需要进行转换。
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-30 下午1:20:08
	 * @param paging
	 * @param ignoreProperties	忽略的属性集
	 * @param xmlDateProperties	日期属性集
	 * @return
	 */
	private PagingVO<DeviceVO> convertToPagingVO(Paging paging,String[] ignoreProperties,
			String... xmlDateProperties){
		PagingVO<DeviceVO> pagingVO = new PagingVO<DeviceVO>();
		if(paging == null)
			return pagingVO;					
		copyProperties(paging, pagingVO);
		List<DeviceVO> voResults = new ArrayList<DeviceVO>();
		List<Object> results = paging.getResults();
		if(!CollectionUtils.isEmpty(results)){
			for (int index = 0, size = results.size(); index < size; index++) {
				DeviceVO vo = new DeviceVO();
				Device device = (Device) results.get(index);
				vo = deviceToVO(device);
				voResults.add(vo);
			}
		}
		pagingVO.setResults(voResults);
		
		return pagingVO;
	}
	
	/**
	 * 
	 * 方法的描述: 将Device转换成DeviceVO
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-31 下午4:01:28
	 * @param device
	 * @return DeviceVO
	 */
	@Override
	public DeviceVO deviceToVO(Device device){
		DeviceVO deviceVO = new DeviceVO();
		if(device == null){
			return null;
		}
		DeviceTypeVO deviceTypeVO = new DeviceTypeVO();
		copyProperties(device, deviceVO,STR,true, CREATED_TIME,UPDATED_TIME); 
		copyProperties(device.getDeviceType(),deviceTypeVO);
		deviceVO.setDeviceType(deviceTypeVO);
		if(device.getHousingDistrictInfo()!=null)
			deviceVO.setHousingDistrictInfo(HouseManagementModelVoConvert.housingDistrict2Vo(device.getHousingDistrictInfo()));
		if(device.getCellHouseholdInfo()!=null)
			deviceVO.setCellHouseholdInfo(HouseManagementModelVoConvert.house2Vo(device.getCellHouseholdInfo()));
		if(device.getBuildingCellInfo()!=null)
			deviceVO.setBuildingCellInfo(HouseManagementModelVoConvert.cell2Vo(device.getBuildingCellInfo()));
		if(device.getHousingDistrictRegionInfo()!=null)
			deviceVO.setHousingDistrictRegionInfo(HouseManagementModelVoConvert.region2Vo(device.getHousingDistrictRegionInfo()));
		if(device.getRegionBuildingInfo()!=null)
			deviceVO.setRegionBuildingInfo(HouseManagementModelVoConvert.regionBuildingInfo2Vo(device.getRegionBuildingInfo()));
		return deviceVO;
	}
	/**
	 * 
	 * 方法的描述: DeviceVO转换成Device
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-31 下午4:02:13
	 * @param deviceVO
	 * @return Device
	 */
	
	public Device voToDevice(DeviceVO deviceVO){
		Device device = new Device();
		if(deviceVO == null){
			return null;
		}
		DeviceType deviceType = new DeviceType();
		copyProperties(deviceVO, device,STR,false, CREATED_TIME,UPDATED_TIME); 
		copyProperties(deviceVO.getDeviceType(),deviceType);
		device.setDeviceType(deviceType);
		if(deviceVO.getHousingDistrictInfo()!=null)
			device.setHousingDistrictInfo(HouseManagementModelVoConvert.district2Model(deviceVO.getHousingDistrictInfo()));
		if(deviceVO.getCellHouseholdInfo()!=null)
			device.setCellHouseholdInfo(HouseManagementModelVoConvert.house2Model(deviceVO.getCellHouseholdInfo()));
		if(deviceVO.getBuildingCellInfo()!=null)
			device.setBuildingCellInfo(HouseManagementModelVoConvert.cell2Model(deviceVO.getBuildingCellInfo()));
		if(deviceVO.getHousingDistrictRegionInfo()!=null)
			device.setHousingDistrictRegionInfo(HouseManagementModelVoConvert.hdRegion2Model(deviceVO.getHousingDistrictRegionInfo()));
		if(deviceVO.getRegionBuildingInfo()!=null)
			device.setRegionBuildingInfo(HouseManagementModelVoConvert.regionBuildingInfo2Model(deviceVO.getRegionBuildingInfo()));
		return device;
	}

	@Override
	public DeviceVO queryDeviceByRoomId(String roomId) {
		Device device = getSmartHomeService().queryDeviceByRoomId(roomId);
		return deviceToVO(device);
	}

	@Override
	public Long deviceCount(String districtId, String status) {
		return getSmartHomeService().deviceCount(districtId, status);
	}

	@Override
	public DeviceVO queryDeviceByCode(String deviceCode) {
		Device device = getSmartHomeService().queryDeviceByCode(deviceCode);
		return deviceToVO(device);
	}

	@Override
	public List<DeviceVO> queryFamilyDevice(DeviceVO deviceVO) {
		Device device = new Device();
		device = voToDevice(deviceVO);
		List<DeviceVO> list = new ArrayList<DeviceVO>();
		List<Device> devices = getSmartHomeService().queryFamilyDevice(device);
		if(devices == null || devices.size() == 0)
			return list;
		for (Device d : devices) {
			DeviceVO vo = new DeviceVO();
			vo = deviceToVO(d);
			list.add(vo);
		}	
		return list;
	}

	@Override
	public boolean isHaveDevice(String roomId) {
		return getSmartHomeService().isHaveDevice(roomId);
	}

	@Override
	public List<DeviceVO> queryOwnerUnlockDevice(String unitId, String districtId) {
		List<DeviceVO> list = new ArrayList<DeviceVO>();
		List<Device> devices = getSmartHomeService().queryOwnerUnlockDevice(unitId, districtId);
		if(devices == null || devices.size() == 0)
			return list;
		for (Device d : devices) {
			DeviceVO vo = new DeviceVO();
			vo = deviceToVO(d);
			list.add(vo);
		}	
		return list;
	}

	@Override
	public List<DeviceVO> queryGateCardDevices(DeviceVO deviceVO) {
		List<DeviceVO> list = new ArrayList<DeviceVO>();
		Device device = voToDevice(deviceVO);
		List<Device> devices = getSmartHomeService().queryGateCardDevices(device);
		if(devices == null || devices.size() == 0)
			return list;
		for (Device d : devices) {
			DeviceVO vo = new DeviceVO();
			vo = deviceToVO(d);
			list.add(vo);
		}	
		return list;
	}

	@Override
	public boolean removeDeviceById(String deviceId) {
		return getSmartHomeService().removeDeviceById(deviceId);
	}

	@Override
	public boolean isDeviceOnline(String deviceId) {
		return getSmartHomeService().isDeviceOnline(deviceId);
	}

}
