package com.biencloud.smarthome.web.housemgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.service.IHousingDistrictRegionInfoService;
import com.biencloud.smarthome.web.housemgr.util.HouseManagementModelVoConvert;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictRegionInfoVo;
import com.biencloud.smarthome.web.wsclient.stub.HousingDistrictInfo;
import com.biencloud.smarthome.web.wsclient.stub.HousingDistrictRegionInfo;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 区域 Service 实现类
 * 
 * @author jsun  
 * @since 1.0 2012-5-17 上午9:21:58
 */
public class HousingDistrictRegionInfoServiceImpl extends
		BaseService<HousingDistrictRegionInfoVo> implements
		IHousingDistrictRegionInfoService {
	private static final String CREATE_TIME = "createTime";
	private static final String HOUSING_DISTRICT_INFO = "housingDistrictInfo";
	private static final String PROPERTY_COMPANY_INFO = "propertyCompanyInfo";

	@Override
	public PagingVO<HousingDistrictRegionInfoVo> queryHousingDistrictRegionInfosForPaging(
			HousingDistrictRegionInfoVo condition, int pageNum, int pageSize) {
		Paging paging = getSmartHomeService().queryHousingDistrictRegionInfosForPaging(convert2Model(condition),
				pageNum, pageSize);

		return HouseManagementModelVoConvert.pagingRegion2Vo(paging,
				convertToVO(paging, new String[] {CREATE_TIME, HOUSING_DISTRICT_INFO}));
	}

	@Override
	public void update(HousingDistrictRegionInfoVo region) {
		getSmartHomeService().updateHousingDistrictRegionInfo(convert2Model(region));
	}

	@Override
	public HousingDistrictRegionInfoVo get(String id) {
		HousingDistrictRegionInfoVo regionVo = new HousingDistrictRegionInfoVo();
		HousingDistrictRegionInfo region = getSmartHomeService().getHousingDistrictRegionInfo(id);
		copyProperties(region, regionVo, CREATE_TIME, HOUSING_DISTRICT_INFO);

		HousingDistrictInfoVo district = new HousingDistrictInfoVo();
		copyProperties(region.getHousingDistrictInfo(), district, PROPERTY_COMPANY_INFO);
		regionVo.setHousingDistrictInfo(district);
		return regionVo;
	}

	@Override
	public void save(HousingDistrictRegionInfoVo region) {
		getSmartHomeService().saveHousingDistrictRegionInfo(convert2Model(region));
	}

	private HousingDistrictRegionInfo convert2Model(HousingDistrictRegionInfoVo region) {
		HousingDistrictRegionInfo _region = new HousingDistrictRegionInfo();		
		copyProperties(region, _region, HOUSING_DISTRICT_INFO);

		HousingDistrictInfo _district = new HousingDistrictInfo();
		copyProperties(region.getHousingDistrictInfo(), _district);
		_region.setHousingDistrictInfo(_district);

		return _region;
	}

	@Override
	public int remove(String id) {
		return getSmartHomeService().removeRegionById(id);
	}

	@Override
	public List<HousingDistrictRegionInfoVo> getRegions(HousingDistrictRegionInfoVo condition) {
		List<HousingDistrictRegionInfo> regions = getSmartHomeService().getRegions(convert2Model(condition));

		List<HousingDistrictRegionInfoVo> regionVos = new ArrayList<HousingDistrictRegionInfoVo>();
		for (int i = 0, length = regions.size(); i < length; i++) {
			HousingDistrictRegionInfoVo regionVo = new HousingDistrictRegionInfoVo();
			copyProperties(regions.get(i), regionVo, CREATE_TIME, HOUSING_DISTRICT_INFO);

			regionVos.add(regionVo);
		}

		return regionVos;
	}

	@Override
	public HousingDistrictRegionInfoVo saveOrUpdate(HousingDistrictRegionInfoVo region) {
		HousingDistrictRegionInfoVo regionVo = null;
		HousingDistrictRegionInfo model = getSmartHomeService().saveOrUpdateRegion(convert2Model(region));

		if (model != null) { // 新增/更新后返回model不为null则表示成功
			regionVo = new HousingDistrictRegionInfoVo();
			copyProperties(model, regionVo, CREATE_TIME, HOUSING_DISTRICT_INFO);

			HousingDistrictInfoVo district = new HousingDistrictInfoVo();
			copyProperties(model.getHousingDistrictInfo(), district, PROPERTY_COMPANY_INFO);
			regionVo.setHousingDistrictInfo(district);
		}

		return regionVo;
	}

	@Override
	public boolean newCodeNotRepeat(String regionCode, String districtId) {
		return getSmartHomeService().newRegionCodeNotRepeat(regionCode, districtId);
	}

	@Override
	public boolean updateCodeNotRepeat(String regionId, String regionCode, String districtId) {
		return getSmartHomeService().updateRegionCodeNotRepeat(regionId, regionCode, districtId);
	}

	@Override
	public boolean hasBuilding(String regionId) {
		return getSmartHomeService().hasBuilding(regionId);
	}

	@Override
	public boolean existRegionName(String districtId, String regionId,
			String regionName) {
		return getSmartHomeService().existRegionName(
				districtId, regionId, regionName);
	}
}
