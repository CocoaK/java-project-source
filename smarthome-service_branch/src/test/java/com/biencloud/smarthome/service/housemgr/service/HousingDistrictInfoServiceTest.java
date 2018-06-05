/*
 * Copyright 
 */

package com.biencloud.smarthome.service.housemgr.service;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;

/**
 * 小区 Service 单元测试
 * 
 * @author jsun
 * @since 1.0 2012-5-12 9:44:07
 */
public class HousingDistrictInfoServiceTest extends BaseTest {
	@Autowired
	private IHousingDistrictInfoService service;

	/**
	 * 测试保存小区信息
	 */
	@Test
	public void testSaveHousingDistrict() {
		HousingDistrictInfo district = new HousingDistrictInfo();
		district.setName("test");
		service.save(district);

		Assert.assertNotNull("新增小区到数据库后, 小区ID应该有值", district.getId());
		Assert.assertNotNull("新增小区到数据库后, 小区没有对应的物业公司, 则新增一个空信息的物业公司与之对应", district.getPropertyCompanyInfo().getId());
	}

	/**
	 * 测试模糊查询小区名称, 获得分页的结果
	 */
	@Test
	public void testQueryPaging() {
		HousingDistrictInfo district = new HousingDistrictInfo();
		district.setName("a");

		Paging<HousingDistrictInfo> paging = service
				.queryHousingDistrictInfosForPaging(district, 1, 10);

		logger.info("小区模糊查询分页信息：{}", paging);
	}

	@Test
	public void testGetDistricts() {
		HousingDistrictInfo district = new HousingDistrictInfo();
		district.setName("a");

		List<HousingDistrictInfo> districts = service.getDistricts(district);

		logger.info("获得所有符合条件的小区个数：{}", districts.size());
	}

	@Test
	public void testAddDistrictByGroup() {
		HousingDistrictInfo district = new HousingDistrictInfo();
		district.setName("xx小区");
		district.setGroupId("12022114");

		service.save(district);
		logger.info("新增小区后自动生成4位的小区编码:{}", district.getCode());
	}

	@Test
	public void testGetByCode() {
		logger.info("通过小区编码获取小区信息:{}", service.getByCode("0001"));
		logger.info("获取不存在的小区:{}", service.getByCode("9999"));
	}

	@Test
	public void testUpateHousingDistrictInfoName() {
		service.upateHousingDistrictInfoName(11011700L, "new");
		logger.info("通过小区编码获取小区信息:{}", service.getByCode("0001"));
	}

	@Test
	public void testUpdate() {
		HousingDistrictInfo district = new HousingDistrictInfo();
		district.setId("1");
		district.setName("new name");
		service.update(district);
	}

	@Test
	public void testGetDistrictCount() {
		logger.info("查询小区总数: {}", service.getDistrictCount());
	}

	@Test
	public void testUpdateDistrictPropertyCompanyId() {
		service.updateDistrictPropertyCompanyId("1", 1);
		logger.info("修改物业公司ID后的小区信息: {}", service.get("1"));
	}

	@Test
	public void testGetDistrictIdByGroupNo() {
		logger.info("根据组织编号查询小区ID: {}", service.getDistrictIdByGroupNo(12022114L));
	}

	@Test
	public void testHasRegion() {
		logger.info("判断小区包含区域:{}", service.hasRegion("1"));
		logger.info("判断小区不包含区域:{}", service.hasRegion("99"));
	}
}
