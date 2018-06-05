package com.biencloud.smarthome.service.housemgr.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.housemgr.model.BuildingCellInfo;

/**
 * 单元 Service 单元测试
 * 
 * @author jsun
 * @since 1.0 2012-5-15
 */
public class BuildingCellInfoServiceTest extends BaseTest {
	@Autowired
	private IBuildingCellInfoService service;

	@Test
	public void testQueryPaging() {
		BuildingCellInfo cell = new BuildingCellInfo();
		cell.setName("c");

		Paging<BuildingCellInfo> paging = service
				.queryBuildingCellInfosForPaging(cell, 1, 10);

		logger.info("单元模糊查询分页信息：{}", paging);
	}

	@Test
	public void testGetByCode() {
		//logger.info("通过单元编码获取单元信息:{}", service.getByCode("01"));
		//logger.info("获取不存在的单元:{}", service.getByCode("99"));
	}

	@Test
	public void testSave() {
		BuildingCellInfo cell = new BuildingCellInfo();
		cell.setName("c");

		service.save(cell);
	}

	@Test
	public void testUpdate() {
		BuildingCellInfo cell = new BuildingCellInfo();
		cell.setId("1");
		cell.setName("new name");
		service.update(cell);
	}
	
	@Test
	public void testNewCodeNotRepeat() {
		logger.info("测试新增的单元编号不重复:{}", service.newCodeNotRepeat("01", "75"));
		logger.info("测试新增的单元编号不重复:{}", service.newCodeNotRepeat("99", "75"));
	}

	@Test
	public void testUpdateCodeNotRepeat() {
		logger.info("测试更新的单元编号不重复:{}", service.updateCodeNotRepeat("23", "01", "75"));
		logger.info("测试更新的单元编号不重复:{}", service.updateCodeNotRepeat("23", "02", "75"));
	}

	@Test
	public void testRemoveByIds() {
		service.removeByIds("41");
	}

	@Test
	public void testHasHouse() {
		logger.info("判断单元包含房号:{}", service.hasHouse("1"));
		logger.info("判断单元不包含房号:{}", service.hasHouse("99"));
	}
}
