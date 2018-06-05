package com.biencloud.smarthome.service.gate.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.gate.model.GateCard;
import com.biencloud.smarthome.service.gate.model.GatePermissions;

/**
 * 门卡管理服务测试类。
 * @author Matt Weng
 * @since 1.0 2012-5-4
 */

public class GateCardServiceTest extends BaseTest {
	protected final Logger logger = LoggerFactory.getLogger(getClass()); 
	@Autowired
	private IGateCardService gateCardService;
	
	@Test
	public void queryGateCardsForPaging(){
		Paging<GateCard> paging = gateCardService.queryGateCardsForPaging(
				null, 1, 10);
		logger.info("---------------------------返回无查询条件的门卡列表分页信息：{}", paging);
		GateCard gc = new GateCard();
		gc.setCardNo("00");
		gc.setOwnerName("张");
		gc.setOwnerIdCard("35");
		gc.setStatus("0");
		paging = gateCardService.queryGateCardsForPaging(gc, 1, 10);
		logger.info("---------------------------返回查询条件的门卡列表分页信息：{}", paging);
	}
	
	@Test
	public void getGateCardDetail(){
		GateCard gc = gateCardService.get("1");
		logger.info("---------------------------返回门卡详细信息：{}", gc);
	}
	
	@Test	
	public void addGateCard(){
		GateCard gc = new GateCard();
		gc.setOwnerName("张六");
		gc.setOwnerIdCard("260626197812051245");
		gc.setCardNo("10000001");
		gc.setStatus("0");
		gc.setCreatedUser("test");
		gc.setDistrictId("1");
		List<GatePermissions> list = new ArrayList<GatePermissions>();
		GatePermissions gp1 = new GatePermissions();
		Device device = new Device();
		device.setDeviceId("1");
		gp1.setDevice(device);
		Calendar beginTime = Calendar.getInstance();
		beginTime.add(Calendar.YEAR, -1);
		gp1.setBeginTime(beginTime.getTime());
		gp1.setEndTime(new Date());
		list.add(gp1);
		gc.setGatePermissions(list);
		gateCardService.save(gc);
		logger.info("---------------------------新增门卡信息：{}", gc);
	}
	
	@Test
	public void updateGateCard(){
		GateCard gc = gateCardService.get("2");
		if(gc != null){
			List<GatePermissions> list = gc.getGatePermissions();
			if(CollectionUtils.isNotEmpty(list))
				list.remove(0);
			
			if(list == null)
				list = new ArrayList<GatePermissions>();
			
//			GatePermissions gp = new GatePermissions();
//			Calendar beginTime = Calendar.getInstance();
//			beginTime.add(Calendar.YEAR, -1);
//			gp.setBeginTime(beginTime.getTime());
//			gp.setEndTime(new Date());
//			Device device = new Device();
//			device.setDeviceId("1");
//			gp.setDevice(device);
//			gp.setGateCardId(gc.getGateCardId());
//			list.add(gp);
						
			gc.setUpdatedUser("test");
			gc.setUpdatedTime(new Date());
			gateCardService.update(gc);
			logger.info("---------------------------更新门卡信息：{}", gc);
		}		
	}
	
	@Test
	public void removeGateCard(){
		String gateCardId = "1";
		gateCardService.removeByIds(gateCardId);
		logger.info("---------------------------删除门卡编号为{}的门卡信息", gateCardId);
	}
	
	@Test
	public void updateGateCardStatus(){
		String gateCardId = "1";
		gateCardService.updateGateCardStatus(gateCardId, "1", "test");
		logger.info("----------------------更新编号为{}的门卡状态", gateCardId);
	}
	
	@Test
	public void existCardNo(){
		String cardNo = "0000000001";
//		boolean existFlag = gateCardService.existCardNo(cardNo);
//		logger.info("----------------------门卡号为{}是否存在：{}", new Object[]{cardNo, existFlag});
//		cardNo = "0101012345";
//		existFlag = gateCardService.existCardNo(cardNo);
//		logger.info("----------------------门卡号为{}是否存在：{}", new Object[]{cardNo, existFlag});
	}
	
	@Test
	public void queryByDeviceCode(){
		String deviceCode = "00000001";
		List<GateCard> list = gateCardService.queryByDeviceCode(deviceCode);
		logger.info("----------------------通过设备代码获取门卡列表：{}", list);
	}
	
	@Test
	public void removeGatePermissionsDevices(){
		List<String> deviceIds = new ArrayList<String>();
		deviceIds.add("1");
		deviceIds.add("18");
		gateCardService.removeGatePermissionsDevices(deviceIds);
		logger.info("----------------------删除设备编号列表为{}关联的门禁权限", deviceIds);
	}
	
	@Test
	public void queryByCardNo(){
		String districtId = "1";
		String cardNo = "111";
		logger.info("----------------------根据卡号查询门卡{}", gateCardService.queryGateCardByCardNo(districtId, cardNo));
	}
}
