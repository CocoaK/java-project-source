package com.biencloud.smarthome.service.device.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.device.dao.IDeviceDao;
import com.biencloud.smarthome.service.device.dao.IDeviceIpDao;
import com.biencloud.smarthome.service.device.model.DeviceIp;
import com.biencloud.smarthome.service.device.service.IDeviceIpService;

/**
 * 设备IP领域服务接口实现类。
 * 
 * @author Cocoa
 * @since 1.0 2012-5-15
 */
public class DeviceIpServiceImpl extends BaseService<DeviceIp, String> implements IDeviceIpService {

	private IDeviceIpDao deviceIpDao;
	private IDeviceDao deviceDao;

	public IDeviceIpDao getDeviceIpDao() {
		return deviceIpDao;
	}

	public void setDeviceIpDao(IDeviceIpDao deviceIpDao) {
		this.deviceIpDao = deviceIpDao;
	}

	public IDeviceDao getDeviceDao() {
		return deviceDao;
	}

	public void setDeviceDao(IDeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	@Override
	public Paging<DeviceIp> queryDeviceIpForPaging(DeviceIp deviceIp, int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createQueryParams(jpql, deviceIp);
		jpql.insert(0, "SELECT " + REPLACE_CHARS + " FROM DeviceIp deviceIp");
		String queryString = jpql.toString().replace(REPLACE_CHARS, "deviceIp");
		String queryStringCount = jpql.toString().replace(REPLACE_CHARS, "COUNT(deviceIp)");
		// queryString = queryString + " order by deviceIp.ipAddress";
		return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
	}

	@Override
	public DeviceIp queryDeviceIpById(String deviceIpId) {
		return get(deviceIpId);
	}

	/**
	 * 
	 * 方法的描述:新增IP段，新增的IP都是未分配的。即只有先增加了IP才能在注册设备的时候给设备分配IP.
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-7-20 下午2:42:46
	 * @see com.biencloud.smarthome.service.device.service.IDeviceIpService#saveSubnetIps(com.biencloud.smarthome.service.device.model.DeviceIp)
	 * @param deviceIp
	 * @return 1成功，0失败
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveSubnetIps(DeviceIp deviceIp) {
		int result = 0;
		if (deviceIp != null && deviceIp.getIpAddress() != null && deviceIp.getHousingDistrictInfo() != null) {
			String subip = deviceIp.getIpAddress().substring(0, deviceIp.getIpAddress().lastIndexOf("."));// 截取IP地址前三段
			String subnet = subip.substring(subip.lastIndexOf(".") + 1);	//截取网段
			//根据ip地址字符串和小区id来查询设备ip
			DeviceIp exsitIp = queryDeviceIpByIp(deviceIp.getIpAddress(), deviceIp.getHousingDistrictInfo().getId());
			//如果查询已经有此IP，则返回0
			if (exsitIp != null) {
				return result;
			}
			for (int i = 1; i < 255; i++) {
				DeviceIp ip = new DeviceIp();
				ip.setIpAddress(subip + "." + i);
				ip.setSubnet(subnet);
				ip.setStatus(Constants.IP_NOT_USED);
				ip.setHousingDistrictInfo(deviceIp.getHousingDistrictInfo());
				result = getDeviceIpDao().saveDeviceIp(ip);
				if (result == 0)
					break;
			}
		}
		return result;
	}

	@Override
	public int deleteSubnetIps(DeviceIp deviceIp) {
		return getDeviceIpDao().deleteDeviceIp(deviceIp);
	}

	// 创建属性变量名和属性值映射。
	private Map<String, Object> createQueryParams(StringBuilder jpql, DeviceIp deviceIp) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (deviceIp == null)
			return params;
		if (StringUtils.isNotBlank(deviceIp.getSubnet()))
			appendCondition(jpql, "deviceIp.subnet = :subnet", "subnet", deviceIp.getSubnet(), params);

		if (StringUtils.isNotBlank(deviceIp.getIpAddress()))
			appendCondition(jpql, "deviceIp.ipAddress like :ipAddress", "ipAddress", "%" + deviceIp.getIpAddress() + "%", params);

		if (StringUtils.isNotBlank(deviceIp.getStatus()))
			appendCondition(jpql, "deviceIp.status = :status", "status", deviceIp.getStatus(), params);

		if (deviceIp.getDevice() != null && StringUtils.isNotBlank(deviceIp.getDevice().getDeviceName()))
			appendCondition(jpql, "deviceIp.device.deviceName like :deviceName", "deviceName", "%" + deviceIp.getDevice().getDeviceName()
					+ "%", params);

		if (deviceIp.getHousingDistrictInfo() != null && StringUtils.isNotBlank(deviceIp.getHousingDistrictInfo().getName()))
			appendCondition(jpql, "deviceIp.housingDistrictInfo.name like :districtName", "districtName", "%"
					+ deviceIp.getHousingDistrictInfo().getName() + "%", params);

		if (deviceIp.getDevice() != null && deviceIp.getDevice().getHousingDistrictRegionInfo() != null
				&& StringUtils.isNotBlank(deviceIp.getDevice().getHousingDistrictRegionInfo().getName()))
			appendCondition(jpql, "deviceIp.device.housingDistrictRegionInfo.name like :areaName", "areaName", "%"
					+ deviceIp.getDevice().getHousingDistrictRegionInfo().getName() + "%", params);

		if (deviceIp.getDevice() != null && deviceIp.getDevice().getRegionBuildingInfo() != null
				&& StringUtils.isNotBlank(deviceIp.getDevice().getRegionBuildingInfo().getName()))
			appendCondition(jpql, "deviceIp.device.regionBuildingInfo.name like :buildingName", "buildingName", "%"
					+ deviceIp.getDevice().getRegionBuildingInfo().getName() + "%", params);

		if (deviceIp.getDevice() != null && deviceIp.getDevice().getBuildingCellInfo() != null
				&& StringUtils.isNotBlank(deviceIp.getDevice().getBuildingCellInfo().getName()))
			appendCondition(jpql, "deviceIp.device.buildingCellInfo.name like :unitName", "unitName", "%"
					+ deviceIp.getDevice().getBuildingCellInfo().getName() + "%", params);

		if (deviceIp.getDevice() != null && deviceIp.getDevice().getCellHouseholdInfo() != null
				&& StringUtils.isNotBlank(deviceIp.getDevice().getCellHouseholdInfo().getName()))
			appendCondition(jpql, "deviceIp.device.cellHouseholdInfo.name like :roomName", "roomName", "%"
					+ deviceIp.getDevice().getCellHouseholdInfo().getName() + "%", params);

		if (deviceIp.getHousingDistrictInfo() != null
				&& StringUtils.isNotBlank(deviceIp.getHousingDistrictInfo().getId()))
			appendCondition(jpql, "deviceIp.housingDistrictInfo.id = :districtId", "districtId", deviceIp.getHousingDistrictInfo().getId(), params);
		return params;
	}

	@Override
	public DeviceIp queryDeviceIpByIp(String ipAddress, String districtId) {
		String queryString = "SELECT DeviceIp FROM DeviceIp deviceIp WHERE deviceIp.ipAddress = ?1 and deviceIp.housingDistrictInfo.id = ?2 ";
		List<DeviceIp> list = find(queryString, ipAddress, districtId);
		if (list != null && list.size() != 0)
			return list.get(0);
		return null;
	}
	@Override
	public DeviceIp queryDeviceIpByIpAndAreaCode(String ipAddress, String areaCode) {
		DeviceIp deviceIp=null;
		String queryString = "SELECT DeviceIp FROM DeviceIp deviceIp WHERE deviceIp.ipAddress = ?1 and deviceIp.housingDistrictInfo.code = ?2 ";
		List<DeviceIp> list = find(queryString, ipAddress, areaCode);
		if (list != null && list.size() != 0)
			deviceIp= list.get(0);
		return deviceIp;
	}
	@Override
	public int updateDeviceIp(DeviceIp deviceIp) {
		return getDeviceIpDao().updateDeviceIp(deviceIp);
	}

	@Override
	public boolean isExistIP(String areaNo, String ip) {
		boolean isExist = false;
		if (areaNo != null && ip != null) {
			List<DeviceIp> list = super.find("from DeviceIp where housingDistrictInfo.code=?1 and ipAddress=?2", areaNo, ip);
			if (list != null && !list.isEmpty()) {
				isExist = true;
			}
		}
		return isExist;
	}

	@Override
	public DeviceIp isUsedIP(String areaNo, String ip, String deviceID) {
		DeviceIp deviceIp = null;
		if (areaNo != null && ip != null) {
			List<DeviceIp> list = null;
			if (deviceID == null) {
				list = super.find(
						"from DeviceIp where housingDistrictInfo.code=?1 and ipAddress=?2 and status='" + Constants.IP_USED + "'", areaNo,
						ip);
			} else {
				list = super.find("from DeviceIp where housingDistrictInfo.code=?1 and ipAddress=?2 and status='" + Constants.IP_USED
						+ "' and device.deviceId !=?3", areaNo, ip, deviceID);
			}
			if (list != null && !list.isEmpty()) {
				deviceIp = list.get(0);
			}
		}
		return deviceIp;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void clearDeviceId(String deviceId) {
		if(StringUtils.isBlank(deviceId)){
			return;
		}
		String queryString = "UPDATE DeviceIp SET device=?1 ,status=?2 where device.deviceId=?3";
		super.update(queryString, null,Constants.IP_NOT_USED,deviceId);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveDeviceIp(DeviceIp deviceIp) {
		super.save_update(deviceIp);
	}

	
}
