package com.biencloud.smarthome.service.housemgr.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.charge.model.ChargeType;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.housemgr.model.BuildingCellInfo;
import com.biencloud.smarthome.service.housemgr.model.CellHouseholdInfo;
import com.biencloud.smarthome.service.housemgr.model.CellSizeInfo;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictRegionInfo;
import com.biencloud.smarthome.service.housemgr.model.RegionBuildingInfo;
import com.biencloud.smarthome.service.housemgr.model.Room;

/**
 * 房号 Service 单元测试
 * 
 * @author jsun  
 * @since 1.0 2012-5-18
 */
public class CellHouseholdInfoServiceTest extends BaseTest {
	@Autowired
	private ICellHouseholdInfoService service;

	@Test
	public void testGet() {
		CellHouseholdInfo house = service.get("1");
		logger.info("房号(户)信息：{}", house);
	}

	@Test
	public void testQueryPaging() {
		BuildingCellInfo cell = new BuildingCellInfo();
		cell.setId("3");
		CellHouseholdInfo house = new CellHouseholdInfo();
		//house.setHousingPurpose("1");
		house.setTHmBuildingCellInfo(cell);

		Paging<CellHouseholdInfo> paging = service.queryCellHouseholdInfosForPaging(house, 1, 10);
		logger.info("通过单元ID, 房屋用途 分页查询房号(户)数据：{}", paging);
	}

	@Test
	public void testQueryPagingByCellName() {
		BuildingCellInfo cell = new BuildingCellInfo();
		cell.setName("2");
		CellHouseholdInfo house = new CellHouseholdInfo();
		house.setTHmBuildingCellInfo(cell);

		Paging<CellHouseholdInfo> paging = service.queryCellHouseholdInfosForPaging(house, 1, 10);
		logger.info("通过单元名分页模糊查询查询房号(户)数据：{}", paging);
	}

	@Test
	public void testUpdateSomeProperty() {
		CellSizeInfo size = new CellSizeInfo();
		size.setId("24");

		List<ChargeType> chargeTypes = new ArrayList<ChargeType>();
		ChargeType chargeType = new ChargeType();
		chargeType.setId(Long.valueOf(8));
		chargeTypes.add(chargeType);

		service.updateSomeProperty("1", "0001", size, "123.12", chargeTypes);
		logger.info("修改户型后的房号(户)数据：{}", service.get("1"));
	}

	@Test
	public void testGetHouseholds() {
		HousingDistrictRegionInfo region = new HousingDistrictRegionInfo();
//		region.setName("1");
		
		RegionBuildingInfo building = new RegionBuildingInfo();
		building.setName("1");
		building.setTHmHousingDistrictRegionInfo(region);
		
		BuildingCellInfo cell = new BuildingCellInfo();
		cell.setName("1");
		cell.setTHmRegionBuildingInfo(building);
		
		CellHouseholdInfo house = new CellHouseholdInfo();
		house.setName("1");
		house.setTHmBuildingCellInfo(cell);
		
		logger.info("通过多个模糊条件查询出所有符合条件的房号(户)数据：{}", service.findHouseholds(house));
	}

	@Test
	public void testGetByCode() {
		//logger.info("通过房号编码获取房号信息:{}", service.getByCode("0001"));
		//logger.info("获取不存在的房号:{}", service.getByCode("9999"));
	}

	@Test
	public void testSave() {
		CellHouseholdInfo house = new CellHouseholdInfo();
		house.setName("house");

		service.save(house);
	}

	@Test
	public void testUpdate() {
		CellHouseholdInfo house = new CellHouseholdInfo();
		house.setId("1");
		house.setName("new name");
		service.update(house);
	}

	@Test
	public void testUpdateHouseSizeId() {
		service.updateHouseSizeId("1", "1");
		logger.info("修改户型ID后的房号信息: {}", service.get("1"));
	}
	
	@Test
	public void testQueryRoomByDeviceNo() {
		List<Room> rooms = service.queryRoomByDeviceNo("MC00021");
		logger.info("根据设备编号查询房号的房间信息: {}", rooms);
	}
	

	@Test
	public void testNewCodeNotRepeat() {
		logger.info("测试新增的房号不重复:{}", service.newCodeNotRepeat("4001", "1"));
		logger.info("测试新增的房号不重复:{}", service.newCodeNotRepeat("4002", "1"));
	}

	@Test
	public void testUpdateCodeNotRepeat() {
		logger.info("测试更新的房号不重复:{}", service.updateCodeNotRepeat("70", "0001", "28"));
		logger.info("测试更新的房号不重复:{}", service.updateCodeNotRepeat("70", "0002", "28"));
	}
}
