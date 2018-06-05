package com.biencloud.smarthome.service.housemgr.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.housemgr.model.PropertyCompanyInfo;

/**
 * 物业公司Service
 * 
 * @author jsun
 * @since 1.0 2012-5-14
 */
public interface IPropertyCompanyInfoService extends
		IService<PropertyCompanyInfo, Integer> {
	/**
	 * 分页查询物业公司信息
	 * 
	 * @param condition 以物业公司实体类作为模版来生成查询条件
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Paging<PropertyCompanyInfo> queryPropertyCompanyInfosForPaging(
			PropertyCompanyInfo condition, int pageNum, int pageSize);

	/**
	 * 查询物业公司
	 * 
	 * @param condition
	 * @return
	 */
	public List<PropertyCompanyInfo> findPropertyCompanyInfos(PropertyCompanyInfo condition);

	/**
	 * 根据设备编号查询物业公司信息
	 * 
	 * @param deviceCode
	 * @return
	 */
	public PropertyCompanyInfo getPropertyCompanyInfoByDeviceCode(String deviceCode);
}
