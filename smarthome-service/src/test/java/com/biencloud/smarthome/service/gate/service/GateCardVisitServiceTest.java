package com.biencloud.smarthome.service.gate.service;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.gate.model.GateCardVisit;

/**
 * 门卡刷卡管理服务测试类。
 * @author kouy
 * @since 1.0 2012-5-5
 */
public class GateCardVisitServiceTest extends BaseTest {

	@Autowired
	private IGateCardVisitService gateCardVisitService;
	
	@Test
	public void queryGateCardVisitsForPaging(){
		Paging<GateCardVisit> paging = gateCardVisitService.queryGateCardVisitsForPaging(
				null, 1, 10);
		logger.info("---------------------------返回无查询条件的门卡刷卡记录列表分页信息：{}", paging);
		
		GateCardVisit gcv = new GateCardVisit();
		gcv.setCardNo("00");
		gcv.setOwnerName("张");
		Calendar beginTime = Calendar.getInstance();
		beginTime.add(Calendar.YEAR, -1);
		gcv.setBeginTime(beginTime.getTime());
		gcv.setEndTime(new Date());		
		paging = gateCardVisitService.queryGateCardVisitsForPaging(gcv, 1, 10);
		logger.info("---------------------------返回查询条件的门卡刷卡记录列表分页信息：{}", paging);
	}
	
	@Test
	public void getGateCardVisitDetail(){
		GateCardVisit gcv = gateCardVisitService.get("1");
		logger.info("---------------------------返回门卡刷卡记录详细信息：{}", gcv);
	}
}
