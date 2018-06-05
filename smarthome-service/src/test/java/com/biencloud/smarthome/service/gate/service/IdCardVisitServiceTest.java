package com.biencloud.smarthome.service.gate.service;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.gate.model.IdCardVisit;

/**
 * 访客身份证刷卡管理服务测试类。
 * @author kouy
 * @since 1.0 2012-5-5
 */
public class IdCardVisitServiceTest extends BaseTest {

	@Autowired
	private IIdCardVisitService idCardVisitService;
	
	@Test
	public void queryIdCardVisitsForPaging(){
		Paging<IdCardVisit> paging = idCardVisitService.queryIdCardVisitsForPaging(
				null, 1, 10);
		logger.info("---------------------------返回无查询条件的访客身份证刷卡记录列表分页信息：{}", paging);
		
		IdCardVisit icv = new IdCardVisit();
		icv.setIdCard("35");
		icv.setVisitorName("张");
		icv.setGender("0");
		Calendar beginTime = Calendar.getInstance();
		beginTime.add(Calendar.YEAR, -1);
		icv.setBeginTime(beginTime.getTime());
		icv.setEndTime(new Date());		
		paging = idCardVisitService.queryIdCardVisitsForPaging(icv, 1, 10);
		logger.info("---------------------------返回查询条件的访客身份证刷卡记录列表分页信息：{}", paging);
	}
	
	@Test
	public void getIdCardVisitDetail(){
		IdCardVisit icv = idCardVisitService.get("1");
		logger.info("---------------------------返回访客身份证刷卡记录详细信息：{}", icv);
	}
}
