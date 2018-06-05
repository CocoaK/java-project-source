package com.biencloud.smarthome.service.housemgr.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictRegionInfo;

/**
 * 区域 Service 单元测试
 * 
 * @author jsun
 * @since 1.0 2012-5-15
 */
public class HousingDistrictRegionInfoServiceTest extends BaseTest {
	@Autowired
	private IHousingDistrictRegionInfoService service;

	@Test
	public void testQueryPaging() {
		HousingDistrictRegionInfo region = new HousingDistrictRegionInfo();
		region.setName("r");

		Paging<HousingDistrictRegionInfo> paging = service
				.queryHousingDistrictRegionInfosForPaging(region, 1, 10);

		logger.info("小区区域模糊查询分页信息：{}", paging);
	}

	@Test
	public void testGetRegions() {
		HousingDistrictInfo district = new HousingDistrictInfo();
		district.setId("2");
		HousingDistrictRegionInfo region = new HousingDistrictRegionInfo();
		region.setHousingDistrictInfo(district);

		List<HousingDistrictRegionInfo> districts = service.getRegions(region);

		logger.info("获得所有符合条件的区域个数：{}", districts.size());
	}

	@Test
	public void testGetByCode() {
	//	logger.info("通过区域编码获取区域信息:{}", service.getByCode("01"));
	//	logger.info("获取不存在的区域:{}", service.getByCode("99"));
	}

	@Test
	public void testSave() {
		HousingDistrictRegionInfo region = new HousingDistrictRegionInfo();
		region.setName("test");

		service.save(region);
	}

	@Test
	public void testUpdate() {
		HousingDistrictRegionInfo region = new HousingDistrictRegionInfo();
		region.setId("1");
		region.setName("test");
		service.update(region);
	}

	@Test
	public void testNewCodeNotRepeat() {
		logger.info("测试新增的区域编码不重复:{}", service.newCodeNotRepeat("01", "1"));
		logger.info("测试新增的区域编码不重复:{}", service.newCodeNotRepeat("99", "1"));
	}

	@Test
	public void testUpdateCodeNotRepeat() {
		logger.info("测试更新的区域编码不重复:{}", service.updateCodeNotRepeat("76", "24", "1"));
		logger.info("测试更新的区域编码不重复:{}", service.updateCodeNotRepeat("76", "11", "1"));
	}

	@Test
	public void testRemoveByIds() {
		service.removeByIds("86");
	}

	@Test
	public void testHasBuilding() {
		logger.info("判断区域包含楼宇:{}", service.hasBuilding("1"));
		logger.info("判断区域不包含楼宇:{}", service.hasBuilding("99"));
	}
}
