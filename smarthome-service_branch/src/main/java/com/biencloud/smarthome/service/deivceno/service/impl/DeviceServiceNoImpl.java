package com.biencloud.smarthome.service.deivceno.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.common.utils.Utils;
import com.biencloud.smarthome.service.deivceno.model.DeviceNo;
import com.biencloud.smarthome.service.deivceno.service.IDeviceNoService;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.service.IDeviceService;

/**
 * 
 * 项目名称：smarthome-service-new 类名称：DeviceServiceNoImpl 类描述： 设备编号管理领域服务实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-6-12 上午11:58:30
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DeviceServiceNoImpl extends BaseService<DeviceNo, String> implements IDeviceNoService {

	@Override
	public Paging<DeviceNo> queryDeviceForPaging(DeviceNo paramsOb, int pageNum, int pageSize) {
		try {
			StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM DeviceNo dn");
			Map<String, Object> params = new HashMap<String, Object>();
			if (paramsOb.getDevice() != null && StringUtils.isNotBlank(paramsOb.getDevice().getDeviceName()))
				appendCondition(hql, "dn.device.deviceName LIKE :deviceName", "deviceName", "%" + paramsOb.getDevice().getDeviceName()
						+ "%", params);
			if (StringUtils.isNotBlank(paramsOb.getAreaId()))
				appendCondition(hql, "dn.device.housingDistrictInfo.groupId = :areaId", "areaId", paramsOb.getAreaId(), params);
			if (StringUtils.isNotBlank(paramsOb.getDeviceNo()))
				appendCondition(hql, "dn.deviceNo LIKE :deviceNo", "deviceNo", "%" + paramsOb.getDeviceNo() + "%", params);
			hql.append(" order by device.createdTime desc");
			String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
			String queryStringCount = hql.toString().replace(REPLACE_CHARS, "COUNT(*)");
			// return queryForPaging(pageNum, pageSize, queryString,
			// queryStringCount, values);
			return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String generatorDeviceNo() {
		String deviceNo = "";
		try {
			java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
			deviceNo = Utils.generatorChar() + Utils.covertTimeToInt(now.toString()) + Utils.generatorChar();
			String fromHql = "from DeviceNo where deviceNo=";
			String hql = "select count(*) " + fromHql + "'" + deviceNo + "'";
			long result = this.getTotalCount(hql);
			if (result > 0) {
				while (result != 0) {
					deviceNo = Utils.generatorChar() + Utils.covertTimeToInt(now.toString()) + Utils.generatorChar();
					hql = "select count(*) " + fromHql + "'" + deviceNo + "'";
					result = this.getTotalCount(hql);
				}
			}
			if (result == 0) {
				DeviceNo ob = new DeviceNo();
				ob.setDeviceNo(deviceNo);
				this.save(ob);
			}
			// DeviceNo resultOb=this.find(fromHql+"'"+deviceNo+"'").get(0);
			// return resultOb.getId();
			return deviceNo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		long result = 1;
		if (result > 0) {
			while (result == 0) {
				System.out.println("sss");
			}
		}
	}

	@Override
	public DeviceNo getDeviceNoByNo(String diveceNo) {
		DeviceNo ob = null;
		try {
			StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM DeviceNo dn");
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(diveceNo))
				appendCondition(hql, "dn.deviceNo LIKE :deviceNo", "deviceNo", "%" + diveceNo + "%", params);
			String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
			List<DeviceNo> result = findByNamedParams(queryString, params);
			if (!result.isEmpty()) {
				ob = result.get(0);
			}
			return ob;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateDeviceId(Device device, String deviceNo) {
		boolean flag = false;
		try {
			if (device != null && deviceNo != null) {
				List<DeviceNo> result = this.find("from DeviceNo where deviceNo='" + deviceNo + "'");
				if (!result.isEmpty() && !result.isEmpty()) {
					DeviceNo ob = result.get(0);
					ob.setDevice(device);
					this.update(ob);
					flag = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String generatorDeviceNoByFullHouseNo(String houseNo) {
		String deviceNo = this.queryDeviceNoByFullHouseNo(houseNo);

		if (deviceNo == null) {
			deviceNo=this.generatorNewDeviceNo();
			DeviceNo ob = new DeviceNo();
			ob.setDeviceNo(deviceNo);
			ob.setRemark(houseNo);
			this.save(ob);
		}

		return deviceNo;
	}

	/**
	 * 
	 * 方法的描述: 生成新的设备编号
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-10-8 下午5:10:42
	 * @return
	 */
	private String generatorNewDeviceNo() {
		java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
		String deviceNo = Utils.generatorChar() + Utils.covertTimeToInt(now.toString()) + Utils.generatorChar();
		String hql = "select count(*) from DeviceNo where deviceNo='" + deviceNo + "'";
		long result = this.getTotalCount(hql);
		if (result == 0) {
			return deviceNo;
		} else {
			generatorNewDeviceNo();
		}
		return null;

	}

	@Override
	public String queryDeviceNoByFullHouseNo(String houseNo) {
		String deviceNo = null;
		if (houseNo != null && !"".equals(houseNo.trim())) {
			List<DeviceNo> list = super.find("from DeviceNo where remark=?", houseNo);
			if (list != null && !list.isEmpty()) {
				deviceNo = list.get(0).getDeviceNo();
			}
		}
		return deviceNo;
	}

}
