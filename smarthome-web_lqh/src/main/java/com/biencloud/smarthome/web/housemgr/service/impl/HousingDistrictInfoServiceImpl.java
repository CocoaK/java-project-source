package com.biencloud.smarthome.web.housemgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.service.IHousingDistrictInfoService;
import com.biencloud.smarthome.web.housemgr.util.HouseManagementModelVoConvert;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.PropertyCompanyInfoVo;
import com.biencloud.smarthome.web.wsclient.stub.HousingDistrictInfo;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
import com.biencloud.smarthome.web.wsclient.stub.PropertyCompanyInfo;

/**
 * 小区 Service 实现类
 * 
 * @author jsun  
 * @since 1.0 2012-5-17 上午9:21:54
 */
public class HousingDistrictInfoServiceImpl extends
		BaseService<HousingDistrictInfoVo> implements
		IHousingDistrictInfoService {
	private static final String CREATE_TIME = "createTime";
	private static final String PROPERTY_COMPANY_INFO = "propertyCompanyInfo";

	@Override
	public HousingDistrictInfoVo getHousingDistrictInfo(String id) {
		HousingDistrictInfoVo districtVo = new HousingDistrictInfoVo();
		HousingDistrictInfo district = getSmartHomeService().getHousingDistrictInfo(id);

		copyProperties(district, districtVo, PROPERTY_COMPANY_INFO);
		convertPropertyCompanyToVO(district, districtVo);
		return districtVo;
	}

	@Override
	public PagingVO<HousingDistrictInfoVo> queryHousingDistrictInfosForPaging(
			HousingDistrictInfoVo condition, int pageNum, int pageSize) {
		// Web层VO(Web层依赖这些模型) -> service层Model(service层依赖这些模型)
		HousingDistrictInfo hd = new HousingDistrictInfo();		
		copyProperties(condition, hd);

		Paging paging = getSmartHomeService()
				.queryHousingDistrictInfosForPaging(hd, pageNum,
						pageSize);

		return convertPropertyCompanyToPagingVO(paging);
	}

	@Override
	public void update(HousingDistrictInfoVo district) {
		getSmartHomeService().updateHousingDistrictInfo(
				HouseManagementModelVoConvert.district2Model(district));
	}

	/**
	 * 由于从service层中取得的Model必须转成web层的VO对象, 减少依赖.
	 * 但是小区Model中嵌套了物业公司, 不能一次性转换为VO对象, 需要分步转换.
	 * 
	 * @param paging
	 * @return
	 */
	private PagingVO<HousingDistrictInfoVo> convertPropertyCompanyToPagingVO(Paging paging) {
		// 先忽略物业公司属性, 将Paging -> PagingVO
		PagingVO<HousingDistrictInfoVo> pagingVO = convertToVO(paging,
				new String[] { PROPERTY_COMPANY_INFO });

		if(paging == null || paging.getResults() == null 
				|| paging.getResults().isEmpty())
			return pagingVO;
		
		for (int i = 0, length = paging.getResults().size(); i < length; i++) {
			HousingDistrictInfo district = (HousingDistrictInfo) paging.getResults().get(i);
			HousingDistrictInfoVo districtVo = pagingVO.getResults().get(i);

			convertPropertyCompanyToVO(district, districtVo);
		}

		return pagingVO;
	}

	/**
	 * 将小区Model中物业公司Model转换为物业公司VO.
	 * 先取出小区Model中物业公司属性copy给新建的物业公司VO, 然后还给小区VO
	 * 
	 * @param district
	 * @param districtVo
	 */
	private void convertPropertyCompanyToVO(HousingDistrictInfo district,
			HousingDistrictInfoVo districtVo) {
		PropertyCompanyInfo propertyCompany = district.getPropertyCompanyInfo();

		PropertyCompanyInfoVo vo = new PropertyCompanyInfoVo();
		copyProperties(propertyCompany, vo, CREATE_TIME);

		districtVo.setPropertyCompanyInfo(vo);
	}

	@Override
	public List<HousingDistrictInfoVo> getDistricts(HousingDistrictInfoVo condition) {
		// Web层VO(Web层依赖这些模型) -> service层Model(service层依赖这些模型)
		HousingDistrictInfo hd = new HousingDistrictInfo();		
		copyProperties(condition, hd);
		List<HousingDistrictInfo> districts = getSmartHomeService().getDistricts(hd);

		List<HousingDistrictInfoVo> districtVos = new ArrayList<HousingDistrictInfoVo>();
		
		if(districts == null || districts.isEmpty())
			return districtVos;
		
		for (int i = 0, length = districts.size(); i < length; i++) {
			HousingDistrictInfoVo districtVo = new HousingDistrictInfoVo();
			copyProperties(districts.get(i), districtVo, PROPERTY_COMPANY_INFO);

			districtVos.add(districtVo);
		}

		return districtVos;
	}

	@Override
	public void save(HousingDistrictInfoVo district) {
		getSmartHomeService().saveHousingDistrictInfo(
				HouseManagementModelVoConvert.district2Model(district));
	}

	@Override
	public long getDistrictCount() {
		return getSmartHomeService().getDistrictCount();
	}

	@Override
	public void updatePropertyCompanyId(String districtId, int propertyCompanyId) {
		getSmartHomeService().updateDistrictPropertyCompanyId(districtId, propertyCompanyId);
	}

	@Override
	public String getDistrictIdByGroupNo(Long groupNo) {
		return getSmartHomeService().getDistrictIdByGroupNo(groupNo);
	}

	@Override
	public boolean hasRegion(String districtId) {
		return getSmartHomeService().hasRegion(districtId);
	}

	@Override
	public List<String> queryDistrictIds(List<String> groupIds) {
		return getSmartHomeService().queryDistrictIds(groupIds);
	}
}
