package com.biencloud.smarthome.service.housemgr.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictRegionInfo;
import com.biencloud.smarthome.service.housemgr.model.RegionBuildingInfo;

/**
 * 楼宇 Service 单元测试
 * 
 * @author jsun
 * @since 1.0 2012-5-15
 */
public class RegionBuildingInfoServiceTest extends BaseTest {
	@Autowired
	private IRegionBuildingInfoService service;

	@Test
	public void testQueryPaging() {
		HousingDistrictRegionInfo region = new HousingDistrictRegionInfo();
		region.setId("1");

		RegionBuildingInfo building = new RegionBuildingInfo();
		building.setTHmHousingDistrictRegionInfo(region);
		building.setName("1");

		Paging<RegionBuildingInfo> paging = service
				.queryRegionBuildingInfosForPaging(building, 1, 10);

		logger.info("樓宇模糊查询分页信息：{}", paging);
	}

	@Test
	public void testGetBuildings() {
		HousingDistrictRegionInfo region = new HousingDistrictRegionInfo();
		region.setId("2");
		RegionBuildingInfo building = new RegionBuildingInfo();
		building.setTHmHousingDistrictRegionInfo(region);

		List<RegionBuildingInfo> buildings = service.getBuildings(building);

		logger.info("获得所有符合条件的楼宇个数：{}", buildings.size());
	}

	@Test
	public void testGetByCode() {
		//logger.info("通过楼宇编码获取楼宇信息:{}", service.getByCode("001"));
		//logger.info("获取不存在的楼宇:{}", service.getByCode("999"));
	}

	@Test
	public void testSave() {
		RegionBuildingInfo building = new RegionBuildingInfo();
		building.setName("test");

		service.save(building);
	}

	@Test
	public void testUpdate() {
		RegionBuildingInfo building = new RegionBuildingInfo();
		building.setId("1");
		building.setName("test");
		service.update(building);
	}

	@Test
	public void testSaveOrUpdateRepeatBuildingCode() {
		HousingDistrictRegionInfo region = new HousingDistrictRegionInfo();
		region.setId("1");

		RegionBuildingInfo building = new RegionBuildingInfo();
		building.setCode("001");
		building.setTHmHousingDistrictRegionInfo(region);

		RegionBuildingInfo newBuilding = service.saveOrUpdateRegionBuilding(building);
		logger.info("新增后的楼宇信息: {}", newBuilding);
		RegionBuildingInfo updateBuilding = service.saveOrUpdateRegionBuilding(building);
		logger.info("修改后的楼宇信息: {}", updateBuilding);
	}

	@Test
	public void testNewBuildingCodeNotRepeat() {
		logger.info("测试新增的楼宇编号不重复:{}", service.newBuildingCodeNotRepeat("001", "1"));
		logger.info("测试新增的楼宇编号不重复:{}", service.newBuildingCodeNotRepeat("999", "1"));
	}

	@Test
	public void testUpdateBuildingCodeNotRepeat() {
		logger.info("测试更新的楼宇编号不重复:{}", service.updateBuildingCodeNotRepeat("94", "021", "59"));
		logger.info("测试更新的楼宇编号不重复:{}", service.updateBuildingCodeNotRepeat("94", "022", "59"));
	}

	@Test
	public void testRemoveByIds() {
		service.removeByIds("118");
	}
	
	@Test
	public void testHasCell() {
		logger.info("判断楼宇包含单元:{}", service.hasCell("1"));
		logger.info("判断楼宇不包含单元:{}", service.hasCell("99"));
	}
}
