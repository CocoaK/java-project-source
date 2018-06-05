package com.biencloud.smarthome.service.housemgr.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.housemgr.model.BuildingCellInfo;
import com.biencloud.smarthome.service.housemgr.model.CellSizeInfo;
import com.biencloud.smarthome.service.housemgr.model.Room;

/**
 * 户型 Service 单元测试
 * 
 * @author jsun  
 * @since 1.0 2012-5-24
 */
public class CellSizeInfoServiceTest extends BaseTest {
	@Autowired
	private ICellSizeInfoService cellSizeInfoService;

	@Test
	public void testSave() {
		BuildingCellInfo cell = new BuildingCellInfo();
		cell.setId("3");

		CellSizeInfo size = new CellSizeInfo();
		size.setRoom("3");
		size.setHall("2");
		size.setTHmBuildingCellInfo(cell);

		cellSizeInfoService.save(size);

		Assert.assertNotNull("保存户型信息到数据库后, ID应该有值", size.getId());
	}

	@Test
	public void testFind() {
		BuildingCellInfo cell = new BuildingCellInfo();
		cell.setId("3");
		CellSizeInfo size = new CellSizeInfo();
		size.setTHmBuildingCellInfo(cell);

		List<CellSizeInfo> sizeList = cellSizeInfoService.findCellSizeInfo(size);
		logger.info("根据单元ID查找它拥有多少个户型：{}", sizeList.size());
	}

	@Test
	public void testUpdate() {
		CellSizeInfo size = new CellSizeInfo();
		size.setId("1");
		size.setRoom("3");
		cellSizeInfoService.update(size);
	}

	@Test
	public void testSaveWithRooms() {
		BuildingCellInfo cell = new BuildingCellInfo();
		cell.setId("3");

		CellSizeInfo size = new CellSizeInfo();
		size.setRoom("3");
		size.setHall("2");
		size.setTHmBuildingCellInfo(cell);

		List<Room> rooms = new ArrayList<Room>();
		Room room = new Room();
		room.setName("客厅");
		//room.setSize(size);
		rooms.add(room);
		Set<Room> roomSet = new HashSet<Room>(rooms);
		size.setRooms(roomSet);

		cellSizeInfoService.save(size);

		Assert.assertNotNull("保存户型信息到数据库后, ID应该有值", size.getId());
		Set<Room> savedRooms = size.getRooms();
		Assert.assertNotNull("保存户型信息的房间信息到数据库后, 房间信息ID应该有值", savedRooms.iterator().next().getId());
	}

	@Test
	public void testSaveSizeAndRooms() {
		BuildingCellInfo cell = new BuildingCellInfo();
		cell.setId("3");

		List<Room> rooms = new ArrayList<Room>();
		Room room = new Room();
		room.setName("客厅");
		rooms.add(room);
		
		CellSizeInfo size = new CellSizeInfo();
		size.setRoom("3");
		size.setHall("2");
		size.setTHmBuildingCellInfo(cell);

		//Assert.assertNotNull("保存户型信息到数据库后, 返回新增的户型ID", cellSizeInfoService.saveSizeAndRooms(size, rooms));
		Set<Room> savedRooms = size.getRooms();
		Assert.assertNotNull("保存户型信息的房间信息到数据库后, 房间信息ID应该有值", savedRooms.iterator().next().getId());
	}
}
