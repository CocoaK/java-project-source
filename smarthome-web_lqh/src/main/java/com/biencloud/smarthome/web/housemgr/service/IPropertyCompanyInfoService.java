package com.biencloud.smarthome.web.housemgr.service;

import java.util.List;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.vo.PropertyCompanyInfoVo;

/**
 * 物业公司Service
 * 
 * @author jsun
 * @since 1.0 2012-5-14
 */
public interface IPropertyCompanyInfoService {

	/**
	 * 获取物业公司详细信息。
	 * @param id 物业公司编号
	 * @return
	 */
	public PropertyCompanyInfoVo getPropertyCompanyInfoDetail(Integer id);
	
	/**
	 * 更新物业公司信息。
	 * @param propertyCompany 物业公司信息
	 */
	public void update(PropertyCompanyInfoVo propertyCompany);

	/**
	 * 新增物业公司信息。
	 * @param propertyCompany 物业公司信息
	 * @return
	 */
	public int save(PropertyCompanyInfoVo propertyCompany);

	/**
	 * 查询物业公司列表，分页。
	 * @param condition 物业公司信息
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public PagingVO<PropertyCompanyInfoVo> queryPropertyCompanyInfosForPaging(
			PropertyCompanyInfoVo condition, int pageNum, int pageSize);

	/**
	 * 查询物业公司列表。
	 * @param condition 物业公司信息
	 * @return
	 */
	public List<PropertyCompanyInfoVo> findPropertyCompanyInfos(PropertyCompanyInfoVo condition);
	
	/**
	 * 查询物业公司列表。
	 * @param condition 物业公司信息
	 * @return
	 */
	public void deletePropertyCompany(PropertyCompanyInfoVo vo);
}
