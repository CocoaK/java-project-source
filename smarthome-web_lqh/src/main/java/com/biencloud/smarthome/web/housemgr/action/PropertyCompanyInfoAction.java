package com.biencloud.smarthome.web.housemgr.action;

import java.util.List;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.service.IHousingDistrictInfoService;
import com.biencloud.smarthome.web.housemgr.service.IPropertyCompanyInfoService;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.PropertyCompanyInfoVo;

/**
 * 物业公司Action
 * 
 * @author jsun
 * @since 1.0 2012-5-14
 */
@SuppressWarnings("serial")
public class PropertyCompanyInfoAction extends BaseAction<PropertyCompanyInfoVo> {
	private Integer propertyCompanyInfoId;
	private String propertyCompanyName;
	private PropertyCompanyInfoVo propertyCompanyInfo;

	private boolean successFlag;
	private List<PropertyCompanyInfoVo> results;

	private IPropertyCompanyInfoService propertyCompanyInfoService;
	private IHousingDistrictInfoService housingDistrictInfoService;

	
	/**
	 * 查看物业公司详细信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String viewDetail() throws Exception {
		// 从登陆模块取出用户归属的小区ID, 再根据小区ID获取其关联的物业公司
		if (getDistrictId() != null) {
			HousingDistrictInfoVo district = housingDistrictInfoService.getHousingDistrictInfo(getDistrictId());
			propertyCompanyInfo = district.getPropertyCompanyInfo();
		}

		return SUCCESS;
	}
	
	/**
	 * 查看物业公司详细信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String detail() throws Exception {
		// 从登陆模块取出用户归属的小区ID, 再根据小区ID获取其关联的物业公司
		if (propertyCompanyInfoId != null) {
			propertyCompanyInfo = propertyCompanyInfoService.getPropertyCompanyInfoDetail(propertyCompanyInfoId);
		}

		return SUCCESS;
	}

	/**
	 * 获取物业公司信息进行更新。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updateInput() throws Exception {
		viewDetail();
		return SUCCESS;
	}
	
	/**
	 * 获取物业公司信息进行更新。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updatePropertyCompanyInput() throws Exception {
		detail();
		return SUCCESS;
	}

	/**
	 * 更新物业公司信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String update() throws Exception {
		if (propertyCompanyInfo.getId() != 0) { // 修改物业公司信息
			propertyCompanyInfoService.update(propertyCompanyInfo);
			// 变更小区归属的物业公司为修改后的物业公司ID
			housingDistrictInfoService.updatePropertyCompanyId(getDistrictId(), propertyCompanyInfo.getId());
		} else { // 新增物业公司
			int newPropertyCompanyId = propertyCompanyInfoService.save(propertyCompanyInfo);
			// 变更小区归属的物业公司为新增的物业公司ID
			housingDistrictInfoService.updatePropertyCompanyId(getDistrictId(), newPropertyCompanyId);
		}

		setSuccessFlag(true);
		return SUCCESS;
	}

	/**
	 * 更新物业公司信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updatePropertyCompany() throws Exception {
		if (propertyCompanyInfo.getId() != 0) { // 修改物业公司信息
			propertyCompanyInfoService.update(propertyCompanyInfo);
		} else { // 新增物业公司
			propertyCompanyInfoService.save(propertyCompanyInfo);
		}

		setSuccessFlag(true);
		return SUCCESS;
	}
	
	/**
	 * 更新物业公司信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String delete() throws Exception {
		if (propertyCompanyInfo.getId() != 0) { // 修改物业公司信息
			propertyCompanyInfoService.deletePropertyCompany(propertyCompanyInfo);
		} 
		return queryList();
	}
	
	/**
	 * 查询物业公司列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryList() throws Exception {
		PagingVO<PropertyCompanyInfoVo> page = getPage();
		if(page == null) {
			page = new PagingVO<PropertyCompanyInfoVo>();
		}

		PropertyCompanyInfoVo vo = new PropertyCompanyInfoVo();
		vo.setName(propertyCompanyName);
		PagingVO<PropertyCompanyInfoVo> pagingVO = propertyCompanyInfoService
				.queryPropertyCompanyInfosForPaging(vo, page.getPageNum(), page.getPageSize());
		setPage(pagingVO);
		return SUCCESS;
	}
	
	/**
	 * 按名称模糊查询物业公司列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findList() throws Exception {
		PropertyCompanyInfoVo vo = new PropertyCompanyInfoVo();
		vo.setName(propertyCompanyName);

		results = propertyCompanyInfoService.findPropertyCompanyInfos(vo);
		return SUCCESS;
	}

	/**
	 * 新增物业公司信息输入页面。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String addInput() throws Exception {
		return SUCCESS;
	}
	
	/**
	 * 增加物业公司信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String add() throws Exception {
		propertyCompanyInfoService.save(propertyCompanyInfo);
		return SUCCESS;
	}
	
	
	public IPropertyCompanyInfoService getPropertyCompanyInfoService() {
		return propertyCompanyInfoService;
	}
	public void setPropertyCompanyInfoService(
			IPropertyCompanyInfoService propertyCompanyInfoService) {
		this.propertyCompanyInfoService = propertyCompanyInfoService;
	}
	
	public Integer getPropertyCompanyInfoId() {
		return propertyCompanyInfoId;
	}
	public void setPropertyCompanyInfoId(Integer propertyCompanyInfoId) {
		this.propertyCompanyInfoId = propertyCompanyInfoId;
	}
	
	public PropertyCompanyInfoVo getPropertyCompanyInfo() {
		return propertyCompanyInfo;
	}
	public void setPropertyCompanyInfo(PropertyCompanyInfoVo propertyCompanyInfo) {
		this.propertyCompanyInfo = propertyCompanyInfo;
	}
	
	public boolean isSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	
	public String getPropertyCompanyName() {
		return propertyCompanyName;
	}
	public void setPropertyCompanyName(String propertyCompanyName) {
		this.propertyCompanyName = propertyCompanyName;
	}
	
	public List<PropertyCompanyInfoVo> getResults() {
		return results;
	}
	public void setResults(List<PropertyCompanyInfoVo> results) {
		this.results = results;
	}
	
	public IHousingDistrictInfoService getHousingDistrictInfoService() {
		return housingDistrictInfoService;
	}
	public void setHousingDistrictInfoService(IHousingDistrictInfoService housingDistrictInfoService) {
		this.housingDistrictInfoService = housingDistrictInfoService;
	}

}
