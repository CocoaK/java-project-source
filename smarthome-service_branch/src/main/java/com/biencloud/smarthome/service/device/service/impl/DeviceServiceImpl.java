package com.biencloud.smarthome.service.device.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.biencloud.smarthome.service.ad.dao.IAdDao;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.AppConstants;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.constants.PushKindConstants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.device.dao.ICallRecordDao;
import com.biencloud.smarthome.service.device.dao.IDeviceDao;
import com.biencloud.smarthome.service.device.dao.IDeviceIpDao;
import com.biencloud.smarthome.service.device.dao.IDevicePasswordDao;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.model.DeviceIp;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.gate.dao.IGateCardDao;
import com.biencloud.smarthome.service.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.push.service.IPushService;
import com.biencloud.smarthome.service.rest.model.DeviceSip;
import com.biencloud.smarthome.service.rest.service.IDeviceSipService;

/**
 * 设备管理领域服务实现类。
 * 
 * @author kouy
 * @since 1.0 2012-5-7
 * @see IDeviceService
 * @see BaseService
 */
public class DeviceServiceImpl extends BaseService<Device, String> implements IDeviceService {

	private IDeviceDao deviceDao;
	private IPushService pushService;
	private ICellHouseholdInfoService cellHouseholdInfoService;

	private ICallRecordDao callRecordDao;
	private IAdDao adDao;
	private IDeviceIpDao deviceIpDao;
	private IGateCardDao gateCardDao;
	private IDevicePasswordDao devicePasswordDao;
	private IDeviceSipService deviceSipService;

	public IDeviceDao getDeviceDao() {
		return deviceDao;
	}

	public void setDeviceDao(IDeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	public IPushService getPushService() {
		return pushService;
	}

	public void setPushService(IPushService pushService) {
		this.pushService = pushService;
	}

	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}

	public void setCellHouseholdInfoService(ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}

	public ICallRecordDao getCallRecordDao() {
		return callRecordDao;
	}

	public void setCallRecordDao(ICallRecordDao callRecordDao) {
		this.callRecordDao = callRecordDao;
	}

	public IAdDao getAdDao() {
		return adDao;
	}

	public void setAdDao(IAdDao adDao) {
		this.adDao = adDao;
	}

	public IDeviceIpDao getDeviceIpDao() {
		return deviceIpDao;
	}

	public void setDeviceIpDao(IDeviceIpDao deviceIpDao) {
		this.deviceIpDao = deviceIpDao;
	}

	public IGateCardDao getGateCardDao() {
		return gateCardDao;
	}

	public void setGateCardDao(IGateCardDao gateCardDao) {
		this.gateCardDao = gateCardDao;
	}

	public IDevicePasswordDao getDevicePasswordDao() {
		return devicePasswordDao;
	}

	public void setDevicePasswordDao(IDevicePasswordDao devicePasswordDao) {
		this.devicePasswordDao = devicePasswordDao;
	}

	@Override
	public List<Device> queryDevices(Device device) {
		StringBuilder jpql = new StringBuilder();

		Map<String, Object> params = createQueryParams(jpql, device);

		jpql.insert(0, "SELECT d FROM Device d");

		return findByNamedParams(jpql.toString(), params);
	}

	// 创建属性变量名和属性值映射。
	private Map<String, Object> createQueryParams(StringBuilder jpql, Device device) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (device == null)
			return params;

		if (device.getHousingDistrictInfo() != null && StringUtils.isNotBlank(device.getHousingDistrictInfo().getName()))
			appendCondition(jpql, "d.housingDistrictInfo.name LIKE :districtName", "districtName", "%"
					+ device.getHousingDistrictInfo().getName().trim() + "%", params);

		if (device.getHousingDistrictRegionInfo() != null && StringUtils.isNotBlank(device.getHousingDistrictRegionInfo().getName()))
			appendCondition(jpql, "d.housingDistrictRegionInfo.name LIKE :areaName", "areaName", "%"
					+ device.getHousingDistrictRegionInfo().getName().trim() + "%", params);

		if (device.getRegionBuildingInfo() != null && StringUtils.isNotBlank(device.getRegionBuildingInfo().getName()))
			appendCondition(jpql, "d.regionBuildingInfo.name LIKE :buildingName", "buildingName", "%"
					+ device.getRegionBuildingInfo().getName().trim() + "%", params);

		if (device.getBuildingCellInfo() != null && StringUtils.isNotBlank(device.getBuildingCellInfo().getName()))
			appendCondition(jpql, "d.buildingCellInfo.name LIKE :unitName", "unitName", "%" + device.getBuildingCellInfo().getName().trim() + "%",
					params);
		if (device.getBuildingCellInfo() != null && StringUtils.isNotBlank(device.getBuildingCellInfo().getId()))
			appendCondition(jpql, "d.buildingCellInfo.id = :unitCellId", "unitCellId", device.getBuildingCellInfo().getId(),
					params);
		if (device.getCellHouseholdInfo() != null && StringUtils.isNotBlank(device.getCellHouseholdInfo().getName()))
			appendCondition(jpql, "d.cellHouseholdInfo.name LIKE :roomName", "roomName", "%" + device.getCellHouseholdInfo().getName().trim()
					+ "%", params);

		if (device.getHousingDistrictInfo() != null && StringUtils.isNotBlank(device.getHousingDistrictInfo().getId()))
			appendCondition(jpql, "d.housingDistrictInfo.id = :districtId", "districtId", device.getHousingDistrictInfo().getId(), params);

		if (device.getCellHouseholdInfo() != null && StringUtils.isNotBlank(device.getCellHouseholdInfo().getId()))
			appendCondition(jpql, "d.cellHouseholdInfo.id = :roomId", "roomId", device.getCellHouseholdInfo().getId(), params);

		if (StringUtils.isNotBlank(device.getDeviceName()))
			appendCondition(jpql, "d.deviceName LIKE :deviceName", "deviceName", "%" + device.getDeviceName().trim() + "%", params);

		if (StringUtils.isNotBlank(device.getDeviceCode()))
			appendCondition(jpql, "d.deviceCode LIKE :deviceCode", "deviceCode", "%" + device.getDeviceCode().trim() + "%", params);

		if (StringUtils.isNotBlank(device.getDeviceStatus()))
			appendCondition(jpql, "d.deviceStatus = :deviceStatus", "deviceStatus", device.getDeviceStatus(), params);

		if (device.getDeviceType() != null && StringUtils.isNotBlank(device.getDeviceType().getDeviceType()))
			appendCondition(jpql, "d.deviceType.deviceType = :deviceType", "deviceType", device.getDeviceType().getDeviceType(), params);
		
		if (device.getDeviceTypeList() != null &&device.getDeviceTypeList().size()!=0)
			appendCondition(jpql, "d.deviceType.deviceType in :deviceType", "deviceType", device.getDeviceTypeList(), params);
		
		if(device.isAreaIsNull())
			jpql.append(" and d.housingDistrictRegionInfo.id is null");

		return params;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveDevice(Device device) {
		return getDeviceDao().saveDevice(device);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteDevice(Device device) {
		return getDeviceDao().deleteDevice(device);

	}

	@Override
	@Transactional(propagation = Propagation.NESTED)
	public int updateDevice(Device device, String updateType) {
		if(device==null){
			return 0;
		}
		if (Constants.DEVICE_NAME.equals(updateType)) {
			if (device.getCellHouseholdInfo() != null) { // 如果房号不为空才有以下逻辑
				Device d = new Device();
				d.setCellHouseholdInfo(device.getCellHouseholdInfo());
				List<Device> list = queryDevices(d);
				if (list != null && list.size() != 0) {
					for (Device de : list) {
						if (device.getDeviceName()!=null && de!=null && device.getDeviceName().equals(de.getDeviceName())) {
							return 2; // 同一房间有重复设备名称，返回2
						}
					}
				}
			}
			Push push = new Push();
			Push push2 = new Push();
			push.setPushClientId(device.getDeviceCode());
			push.setPushContent(device.getDeviceName());
			push.setAddTime(new Date());
			push.setPushName("device");
			push.setPushKind(updateType);
			push2.setAddTime(new Date());
			push2.setPushName("device");
			push2.setPushKind("deviceAlias");
			push2.setPushClientId(device.getDeviceCode());
			push2.setPushContent(device.getDeviceAlias());
			getPushService().insertPush(push);	//推送数据：设备名称修改
			getPushService().insertPush(push2);	//推送数据：设备别名修改
		}
		
		//如果是围墙机密码修改
		if (device.getDeviceType() != null && Constants.DEVICE_PASSWORD.equals(updateType)
				&& Constants.FENCE_DEVICE.equals(device.getDeviceType().getDeviceType())) {
			Push push = new Push();
			push.setPushClientId(device.getDeviceCode());
			push.setAddTime(new Date());
			push.setPushName("device");
			push.setPushKind(updateType);
			push.setPushContent(device.getDevicePwd());
			getPushService().insertPush(push);	//推送密码修改
		}
		
		//如果是位置坐标修改了
		if (device.getDeviceType() != null && PushKindConstants.PUSH_DEVICE_LOCATION_KIND.equals(updateType)) {
			Push push = new Push();
			push.setPushClientId(device.getDeviceCode());
			push.setAddTime(new Date());
			push.setPushName(updateType);
			push.setPushKind(updateType);
			StringBuffer content = new StringBuffer();
			content.append("downloadPath=");
			if(device.getHousingDistrictInfo()!=null)
				content.append(device.getHousingDistrictInfo().getFloorPlan());
			//拼凑coordinate=xx|xx
			content.append(",coordinate=");
			content.append(StringUtils.replace(device.getCoordinate(), ",", "|"));
			content.append(",changeTime=");
			content.append(new Date().getTime());
			push.setPushContent(content.toString());
			getPushService().insertPush(push);	//推送坐标修改
		}

		return getDeviceDao().updateDevice(device);
	}

	@Override
	public Device queryDeviceById(String deviceId) {
		return get(deviceId);
	}

	@Override
	public Paging<Device> queryDeviceForPaging(Device device, int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createQueryParams(jpql, device);
		jpql.insert(0, "SELECT " + REPLACE_CHARS + " FROM Device d");
		String queryString = jpql.toString().replace(REPLACE_CHARS, "d");
		String queryStringCount = jpql.toString().replace(REPLACE_CHARS, "COUNT(d)");
		queryString = queryString + " order by d.createdTime desc ";
		return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
	}

	@Override
	public Device queryDeviceByMac(String mac) {
		Device device = null;
		if (mac != null) {
			List<Device> list = super.find("from Device where deviceMac=?", mac);
			if (list != null && !list.isEmpty()) {
				device = list.get(0);
			}
		}
		return device;
	}

	@Override
	public Device queryDeviceByMac(String mac, String areaNo, String regionNo, String buildingNo, String unitNo, String houseNo) {
		Device device = null;
		List<Device> list = null;
		if (mac != null) {
			String hql = "from Device where deviceMac=?1";
			StringBuilder sb = new StringBuilder();
			List<String> sbParamValue = new ArrayList<String>();
			sb.append(hql);
			sbParamValue.add(mac);
			if(areaNo != null){
				sb.append(" ").append(" and ").append("housingDistrictInfo.code=?2");
				sbParamValue.add(areaNo);
				if (regionNo != null) {
					sb.append(" ").append(" and ").append("housingDistrictRegionInfo.code=?3");
					sbParamValue.add(regionNo);
					if (buildingNo != null) {
						sb.append(" ").append(" and ").append("regionBuildingInfo.code=?4");
						sbParamValue.add(buildingNo);
						if (unitNo != null) {
							sb.append(" ").append(" and ").append("buildingCellInfo.code=?5");
							sbParamValue.add(unitNo);
							if (houseNo != null) {
								sb.append(" ").append(" and ").append("cellHouseholdInfo.code=?6");
								sbParamValue.add(houseNo);
							}
						}
					}
				}
			}
			hql = sb.toString();
			Object[] value = sbParamValue.toArray();
			list = super.find(hql, value);
			if (list != null && !list.isEmpty()) {
				device = list.get(0);
			}
		}
		return device;
	}

	@Override
	public List<Device> queryOwnerUnitDevice(String unitId, String roomId) {
		List<Device> list = null;
		if (roomId != null && unitId != null) {
			list = super.find("from Device where cellHouseholdInfo.id ='" + roomId + "' or deviceType.deviceType <> '"
					+ Constants.INDOOR_DEVICE + "' and buildingCellInfo.id='" + unitId + "'");
		}
		return list;
	}

	@Override
	public List<Device> queryPropertyDevice(String distrcitId) {
		List<Device> list = null;
		if (distrcitId != null) {
			list = super.find("from Device where housingDistrictInfo.id=?1 and deviceType.deviceType in (?2,?3)",distrcitId,Constants.UNIT_DOOR_DEVICE,Constants.FENCE_DEVICE);
		}
		return list;
	}

	@Override
	public boolean validatePasswd(String deviceCode, String passwd) {
		Device device = queryDeviceByCode(deviceCode);
		if (device != null && device.getDevicePwd() != null) {
			if (device.getDevicePwd().equals(passwd))
				return true;
		}
		return false;
	}

	@Override
	public Device queryDeviceByCode(String deviceCode) {
		Device device = null;
		if (deviceCode != null) {
			List<Device> list = super.find("from Device where deviceCode='" + deviceCode + "' order by createdTime desc ");
			if (list != null && !list.isEmpty()) {
				device = list.get(0);
			}
		}
		return device;
	}

	@Override
	public List<Device> queryDevices(List<String> groupIds, List<String> deviceTypes) {
		List<Device> devices = null;
		StringBuilder jpql = new StringBuilder();
		StringBuilder conditionDistrict = new StringBuilder();
		StringBuilder conditionType = new StringBuilder();
		jpql.insert(0, "SELECT device FROM Device device WHERE 1=1 ");
		//组织条件
		if (groupIds != null && groupIds.size() != 0) {
			conditionDistrict.insert(0, "'" + groupIds.get(0) + "'");
			for (int i = 1; i < groupIds.size(); i++) {
				conditionDistrict.append(",'" + groupIds.get(i) + "'");
			}
			jpql.append(" AND device.housingDistrictInfo.groupId in ");
			jpql.append("(" + conditionDistrict + ")");
		}
		//组织条件
		if (deviceTypes != null && deviceTypes.size() != 0) {
			conditionType.insert(0, "'" + deviceTypes.get(0) + "'");
			for (int i = 1; i < deviceTypes.size(); i++) {
				conditionType.append(",'" + deviceTypes.get(i) + "'");
			}
			jpql.append(" AND device.deviceType.deviceType in ");
			jpql.append("(" + conditionType + ")");
		}
		devices = super.find(jpql.toString());
		return devices;
	}

	@Override
	public Device queryDeviceByRoomId(String roomId) {
		Device device = null;
		if (roomId != null) {
			List<Device> list = super.find("from Device where cellHouseholdInfo.id='" + roomId + "' order by createdTime desc");
			if (list != null && !list.isEmpty()) {
				device = list.get(0);
			}
		}
		return device;
	}

	@Override
	public Long deviceCount(String districtId, String status) {
		StringBuilder jpql = new StringBuilder("SELECT COUNT(device) FROM Device device WHERE 1=1");
		Map<String, Object> params = new HashMap<String, Object>();
		if (districtId != null && StringUtils.isNotBlank(districtId)) {
			jpql.append(" AND device.housingDistrictInfo.id = :districtId");
			params.put("districtId", districtId);
		}
		if (status != null && StringUtils.isNotBlank(status)) {
			jpql.append(" AND device.deviceStatus = :status");
			params.put("status", status);
		}
		return getTotalCountByNamedParams(jpql.toString(), params);
	}

	@Override
	public List<Device> queryFamilyDevice(Device device) {
		List<Device> list = new ArrayList<Device>();
		if (device != null && device.getDeviceType() != null && device.getHousingDistrictInfo() != null) {
			// 如果设备为单元门口机，则只查询本单元的室内机,门口机和管理机所有设备，否则查询本小区的室内机和门口机和管理机所有设备
			if (device.getBuildingCellInfo() != null && Constants.UNIT_DOOR_DEVICE.equals(device.getDeviceType().getDeviceType())) {
				list = super
						.find("from Device where (buildingCellInfo.id=?1 and deviceType.deviceType=?2 ) or (housingDistrictInfo.id=?3 and deviceType.deviceType in( ?4,?5 ) ) order by createdTime desc ",
								device.getBuildingCellInfo().getId(), Constants.INDOOR_DEVICE, device.getHousingDistrictInfo().getId(),
								Constants.MANAGEMENT_DEVICE, Constants.UNIT_DOOR_DEVICE);
			} else {
				list = super
						.find("from Device where housingDistrictInfo.id=?1 and deviceType.deviceType in( ?2, ?3, ?4 ) order by createdTime desc ",
								device.getHousingDistrictInfo().getId(), Constants.INDOOR_DEVICE, Constants.MANAGEMENT_DEVICE,
								Constants.UNIT_DOOR_DEVICE);
			}
		}
		// 同一房号只取最新的设备
		removeDuplicate(list);
		return list;
	}

	@Override
	public List<Device> queryMonitorDevice(Device device) {
		List<Device> list = new ArrayList<Device>();
		StringBuffer queryString = new StringBuffer();
		if (device != null) {
			// 查询设备类型为单元门口机和围墙机的设备
			queryString.append("from Device where 1=1 ");
			if(device.getDeviceType()!=null && Constants.INDOOR_DEVICE.equals(device.getDeviceType().getDeviceType())){
				queryString.append("and buildingCellInfo.id='" + device.getBuildingCellInfo().getId()+"'");
			}
			queryString.append(" and deviceType.deviceType ='" + Constants.UNIT_DOOR_DEVICE+"'");
			queryString.append(" or (deviceType.deviceType='" + Constants.FENCE_DEVICE+"'");
			queryString.append(" and housingDistrictInfo.id = '" + device.getHousingDistrictInfo().getId() + "')");
			list = super.find(queryString.toString());
		}
		return list;
	}

	@Override
	public String getDeviceCodeById(String deviceId) {
		String jpql = "SELECT device.deviceCode FROM Device device WHERE device.deviceId = ?1";
		List<Object> deviceCodes = getDao().findObjects(jpql, deviceId);
		if (CollectionUtils.isEmpty(deviceCodes))
			return "";

		return (String) deviceCodes.get(0);
	}

	@Override
	public boolean isHaveDevice(String roomId) {
		String jpql = "SELECT COUNT(device) FROM Device device WHERE device.cellHouseholdInfo.id = ?1";
		List<Object> devices = getDao().findObjects(jpql, roomId);
		Long count = (Long) devices.get(0);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Device> queryAllDeviceExcludeCurrentDevice(String areaNo, String currentMac) {
		List<Device> list = null;
		if (areaNo != null && currentMac != null) {
			String hql = "from Device where deviceMac !=?1 and housingDistrictInfo.code=?2";
			list = super.find(hql, currentMac, areaNo);
		}
		return list;
	}

	@Override
	public boolean isRepeatDeviceName(String areaNo, String regionNo, String buildingNo, String unitNo, String houseNo, String deviceName,
			String existDeviceId) {
		boolean isRepeat = false;

		StringBuilder sb = new StringBuilder();
		if (areaNo != null && deviceName != null) {
			sb.append("from Device where housingDistrictInfo.code='" + areaNo + "' and deviceName='" + deviceName + "'");
			if (existDeviceId != null) {
				sb.append(" ");
				sb.append(" and deviceId !='" + existDeviceId + "'");
			}
			if (regionNo != null) {
				sb.append(" ");
				sb.append(" and housingDistrictRegionInfo.code='" + regionNo + "'");
				if (buildingNo != null) {
					sb.append(" ");
					sb.append(" and regionBuildingInfo.code='" + buildingNo + "'");
					if (unitNo != null) {
						sb.append(" ");
						sb.append(" and buildingCellInfo.code='" + unitNo + "'");
						if (houseNo != null) {
							sb.append(" ");
							sb.append(" and cellHouseholdInfo.code='" + houseNo + "'");
						}
					}
				}
			}
		}
		if (sb.length() > 0) {
			String hql = sb.toString();
			List<Device> list = super.find(hql);
			if (list != null && !list.isEmpty()) {
				isRepeat = true;
			}
		}
		return isRepeat;
	}

	@Override
	public boolean isRepeatDevicePosition(String areaNo, String regionNo, String buildingNo, String unitNo, String deviceType,
			String position, String existDeviceId) {
		boolean isRepeat = false;

		StringBuilder sb = new StringBuilder();
		if (areaNo != null && position != null && !AppConstants.DEVICE_TYPE_INDOOR.equals(deviceType)) {
			sb.append("from Device where housingDistrictInfo.code='" + areaNo + "' and position='" + position
					+ "' and deviceType.deviceType !='" + AppConstants.DEVICE_TYPE_INDOOR + "'");
			if (existDeviceId != null) {
				sb.append(" ");
				sb.append(" and deviceId !='" + existDeviceId + "'");
			}
			if (regionNo != null) {
				sb.append(" ");
				sb.append(" and housingDistrictRegionInfo.code='" + regionNo + "'");
				if (buildingNo != null) {
					sb.append(" ");
					sb.append(" and regionBuildingInfo.code='" + buildingNo + "'");
					if (unitNo != null) {
						sb.append(" ");
						sb.append(" and buildingCellInfo.code='" + unitNo + "'");

					}
				}
			}
		}
		if (sb.length() > 0) {
			String hql = sb.toString();
			List<Device> list = super.find(hql);
			if (list != null && !list.isEmpty()) {
				isRepeat = true;
			}
		}
		return isRepeat;
	}

	@Override
	public String queryIndoorDeviceNo(String areaNo, String regionNo, String buildingNo, String unitNo, String houseNo) {
		String deviceNo = null;
		if (areaNo != null && regionNo != null && buildingNo != null && unitNo != null && houseNo != null) {
			List<Device> list = super
					.find("from Device where housingDistrictInfo.code=?1 and housingDistrictRegionInfo.code=?2 and regionBuildingInfo.code=?3 and buildingCellInfo.code=?4 and cellHouseholdInfo.code=?5 order by createdTime desc",
							areaNo, regionNo, buildingNo, unitNo, houseNo);
			if (list != null && !list.isEmpty()) {
				Device device = list.get(0);
				if (device != null) {
					deviceNo = device.getDeviceCode();
				}
			}
		}
		return deviceNo;
	}

	@Override
	public List<Device> queryIndoorDevice(String areaNo, String regionNo, String buildingNo, String unitNo, String houseNo) {
		List<Device> list = null;
		if (areaNo != null && regionNo != null && buildingNo != null && unitNo != null && houseNo != null) {
			list = super
					.find("from Device where housingDistrictInfo.code=?1 and housingDistrictRegionInfo.code=?2 and regionBuildingInfo.code=?3 and buildingCellInfo.code=?4 and cellHouseholdInfo.code=?5 order by createdTime desc",
							areaNo, regionNo, buildingNo, unitNo, houseNo);

		}
		return list;
	}
	@Override
	public List<Device> queryNotIndoorDevice(String areaNo, String regionNo, String buildingNo, String unitNo,String position) {
		StringBuilder sb = new StringBuilder();
		List<Device> list = null;
		if (areaNo != null&&position!=null) {
			sb.append("from Device where housingDistrictInfo.code='" + areaNo + "'");
			if (regionNo != null) 
			{
				sb.append(" ");
				sb.append(" and housingDistrictRegionInfo.code='" + regionNo + "'");
				if (buildingNo != null) 
				{
					sb.append(" ");
					sb.append(" and regionBuildingInfo.code='" + buildingNo + "'");
					if (unitNo != null) 
					{
						sb.append(" ");
						sb.append(" and buildingCellInfo.code='" + unitNo + "'");

					}
				}
			}
			sb.append(" ");
			sb.append("and position='" + position + "'");
			sb.append(" ");
			sb.append(" and deviceType.deviceType!='" + AppConstants.DEVICE_TYPE_INDOOR + "'");
			
		}
		if (sb.length() > 0) {
			String hql = sb.toString();
			list = super.find(hql);

		}
		return list;
	}
	@Override
	public List<Device> findAll() {
		List<Device> list = super.find("from Device");
		return list;
	}

	@Override
	public List<Device> findAll(String areaNo, String regionNo, String buildingNo, String unitNo, String deviceType, String currentDeviceId) {
		StringBuilder sb = new StringBuilder();
		List<Device> list = null;
		if (areaNo != null) {
			sb.append("from Device where housingDistrictInfo.code='" + areaNo + "'");
			if (regionNo != null) {
				sb.append(" ");
				sb.append(" and housingDistrictRegionInfo.code='" + regionNo + "'");
				if (buildingNo != null) {
					sb.append(" ");
					sb.append(" and regionBuildingInfo.code='" + buildingNo + "'");
					if (unitNo != null) {
						sb.append(" ");
						sb.append(" and buildingCellInfo.code='" + unitNo + "'");

					}
				}
			}
			if (deviceType != null) {
				sb.append(" ");
				sb.append(" and deviceType.deviceType='" + deviceType + "'");
			}
			if (currentDeviceId != null) {
				sb.append(" ");
				sb.append(" and deviceId !='" + currentDeviceId + "'");
			}
		}
		if (sb.length() > 0) {
			String hql = sb.toString();
			list = super.find(hql);

		}
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteDeviceById(String deviceId) {
		boolean flag = false;
		if (deviceId != null) {
			Device d = super.get(deviceId);
			if (d != null) {
				callRecordDao.removeByDeviceId(deviceId); // 删除通话记录
				adDao.removeAdTargetDevices(deviceId); // 删除广告目标设备
				gateCardDao.removeGatePermissionsDevices(deviceId);// 删除权限
				deviceIpDao.deleteDeviceIpByDevice(deviceId); // 删除设备ip
				devicePasswordDao.removePassword(deviceId);// 删除跟设备有关联的设备密码
				DeviceSip ds = new DeviceSip();
				ds.setDeviceId(Long.parseLong(deviceId));
				deviceSipService.delete(ds);
				this.remove(d);
			}
			flag = true;
		}
		return flag;
	}

	@Override
	public Device queryDeviceByDeviceNo(String deviceNo, String excludeMac) {
		Device d = null;
		if (deviceNo != null && excludeMac != null) {
			List<Device> list = super.find("from Device where deviceCode=?1 and deviceMac !=?2", deviceNo, excludeMac);
			if (list != null && !list.isEmpty()) {
				d = list.get(0);
			}
		}
		return d;
	}

	@Override
	public List<Device> queryOwnerUnlockDevice(String unitId, String districtId) {
		List<Device> list = null;
		if (districtId != null && unitId != null) {
			list = super
					.find("from Device WHERE (deviceType.deviceType =?1 and buildingCellInfo.id=?2) or (deviceType.deviceType =?3 and housingDistrictInfo.id=?4 ) ",
							Constants.UNIT_DOOR_DEVICE, unitId, Constants.FENCE_DEVICE, districtId);
		}
		return list;
	}

	public List<Device> removeDuplicate(List<Device> list) {
		if (list == null || list.size() == 0)
			return null;
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).getCellHouseholdInfo() != null && list.get(i).getCellHouseholdInfo() != null
						&& list.get(j).getCellHouseholdInfo().getId().equals(list.get(i).getCellHouseholdInfo().getId())) {
					list.remove(j);
				}
			}
		}
		return list;
	}

	@Override
	public List<Device> queryGateCardDevices(Device device) {
		StringBuilder jpql = new StringBuilder();

		Map<String, Object> params = createQueryParams(jpql, device);

		jpql.insert(0, "SELECT d FROM Device d");

		String queryCondition = " deviceType.deviceType IN( " + Constants.FENCE_DEVICE + ", " + Constants.UNIT_DOOR_DEVICE + " )";
		if (StringUtils.contains(jpql, " WHERE ")) {
			jpql.append(" AND ").append(queryCondition);
		} else {
			jpql.append(" WHERE ").append(queryCondition);
		}

		return findByNamedParams(jpql.toString(), params);
	}

	@Override
	public boolean isHaveDistrict(String districtId) {
		Long count = super.getTotalCount("SELECT COUNT(d) FROM Device d WHERE d.housingDistrictInfo.id =?1 ", districtId);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isHaveArea(String areaId) {
		Long count = super.getTotalCount("SELECT COUNT(d) FROM Device d WHERE d.housingDistrictRegionInfo.id =?1 ", areaId);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isHaveBuilding(String buildingId) {
		Long count = super.getTotalCount("SELECT COUNT(d) FROM Device d WHERE d.regionBuildingInfo.id =?1 ", buildingId);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isHaveUnit(String unitId) {
		Long count = super.getTotalCount("SELECT COUNT(d) FROM Device d WHERE d.buildingCellInfo.id =?1 ", unitId);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isHaveRoomId(String roomId) {
		Long count = super.getTotalCount("SELECT COUNT(d) FROM Device d WHERE d.cellHouseholdInfo.id =?1 ", roomId);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Device updateDeviceForIPConflict(Device device, DeviceIp deviceIp) {
		Device d = null;
		if (device != null && deviceIp != null) {
			//device.setDeviceIp(null);
			deviceIp.setDevice(null);
			deviceIp.setStatus(Constants.IP_USED);
			deviceIpDao.update(deviceIp);
			//d = super.save_update(device);

		}
		return d;
	}

	@Override
	public List<Device> queryUnitDoorDeviceByUnitId(String unitId) {
		return super.find("FROM Device WHERE buildingCellInfo.id=?1 AND deviceType.deviceType=?2 ", unitId, Constants.UNIT_DOOR_DEVICE);
	}

	@Override
	public Device queryDeviceByRoomNo(String districtNo, String regionNo, String buildingNo, String unitNo, String roomNo) {
		List<Device> list = null;
		String queryString = "FROM Device WHERE housingDistrictInfo.code=?1 AND housingDistrictRegionInfo.code=?2 AND regionBuildingInfo.code=?3 AND buildingCellInfo.code=?4 AND cellHouseholdInfo.code=?5 ORDER BY createdTime DESC ";
		list = super.find(queryString, districtNo, regionNo, buildingNo, unitNo, roomNo);
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public boolean isDeviceOnline(String deviceId){
		String queryString = "SELECT COUNT(d) FROM Device d WHERE d.deviceId=?1 AND d.deviceStatus=?2";
		Long count = super.getTotalCount(queryString, deviceId,Constants.DEVICE_ONLINE);
		if(count>0){
			return true;
		}
		return false;
	}

	@Override
	public List<Device> findDeviceByStatus(String status) {
		List<Device> list=null;
		if(status!=null)
		{
			String hql="from Device where deviceStatus=?";
			list=super.find(hql, status);
			
		}
		return list;
	}

	public IDeviceSipService getDeviceSipService() {
		return deviceSipService;
	}

	public void setDeviceSipService(IDeviceSipService deviceSipService) {
		this.deviceSipService = deviceSipService;
	}
	
}
