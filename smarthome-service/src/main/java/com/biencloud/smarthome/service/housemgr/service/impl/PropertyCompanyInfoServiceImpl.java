package com.biencloud.smarthome.service.housemgr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.housemgr.model.PropertyCompanyInfo;
import com.biencloud.smarthome.service.housemgr.service.IPropertyCompanyInfoService;

/**
 * 物业公司 Service 实现类
 * 
 * @author jsun  
 * @since 1.0 2012-5-17
 */
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class PropertyCompanyInfoServiceImpl extends
		BaseService<PropertyCompanyInfo, Integer> implements
		IPropertyCompanyInfoService {
	private IDeviceService deviceService;

	@Override
	public Paging<PropertyCompanyInfo> queryPropertyCompanyInfosForPaging(PropertyCompanyInfo condition,
			int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql, condition);

		jpql.insert(0, "SELECT " + REPLACE_CHARS + " FROM PropertyCompanyInfo pc");
		String queryString = jpql.toString().replace(REPLACE_CHARS, "pc");
		String queryStringCount = jpql.toString().replace(REPLACE_CHARS,
				"COUNT(pc)");

		Paging<PropertyCompanyInfo> propertyCompanyInfo = queryByNamedParamsForPaging(pageNum, pageSize,
				queryString, queryStringCount, params);

		return propertyCompanyInfo;
	}

	/**
	 * 根据物业公司模型, 生成JPQL查询条件
	 * 
	 * @param jpql
	 * @param condition
	 * @return
	 */
	private Map<String, Object> createParams(StringBuilder jpql, PropertyCompanyInfo condition) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(condition == null)
			return params;

		// 物业公司名
		if(StringUtils.isNotBlank(condition.getName()))
			appendCondition(jpql, "pc.name LIKE :name", "name", 
					"%" + condition.getName() + "%", params);

		return params;
	}

	@Override
	public PropertyCompanyInfo getPropertyCompanyInfoByDeviceCode(String deviceCode) {
		Device device = deviceService.queryDeviceByCode(deviceCode);
		if (device!=null&&device.getHousingDistrictInfo() != null) {
			return device.getHousingDistrictInfo().getPropertyCompanyInfo();
		}
		return null;
	}

	@Override
	public List<PropertyCompanyInfo> findPropertyCompanyInfos(PropertyCompanyInfo condition) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql, condition);

		jpql.insert(0, "SELECT pc FROM PropertyCompanyInfo pc");
		String queryString = jpql.toString() + " order by name";

		return findByNamedParams(queryString, params);
	}

	public IDeviceService getDeviceService() {
		return deviceService;
	}
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}
}
