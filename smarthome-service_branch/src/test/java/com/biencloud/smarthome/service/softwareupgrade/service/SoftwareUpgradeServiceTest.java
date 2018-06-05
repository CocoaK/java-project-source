package com.biencloud.smarthome.service.softwareupgrade.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.device.model.DeviceType;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.softwareupgrade.enums.SoftwareUpgradeStatus;
import com.biencloud.smarthome.service.softwareupgrade.model.SoftwareUpgrade;
import com.biencloud.smarthome.service.softwareupgrade.model.SoftwareUpgradeTarget;


/**
 * 软件升级模块服务测试类。
 * @author Matt Weng
 * @since 1.0 2012-4-25
 */
public class SoftwareUpgradeServiceTest extends BaseTest {

	@Autowired
	private ISoftwareUpgradeService softwareUpgradeService;
	
	@Test
	public void addSoftware() {
		SoftwareUpgrade su = createEntity();
		softwareUpgradeService.save(su);
		logger.info("************新增软件：{}", su);
	}
	
	@Test
	public void upgradeSoftWare(){
		SoftwareUpgrade su = createEntity();
		softwareUpgradeService.save(su);
		SoftwareUpgrade currSu = new SoftwareUpgrade();
		currSu.setSoftwareId(su.getSoftwareId());
		currSu.setVersionName("V2.0.0");
		currSu.setSavePath("/upload/fileAction_downloadWebUploadFile.action?fileObj.fileName=DigiHomeV2.0.apk");
		softwareUpgradeService.upgradeSoftware(currSu);		
		logger.info("************升级软件：{}", currSu);
	}
	
	@Test
	public void getLatestSoftware(){
		SoftwareUpgrade su = softwareUpgradeService.getLatestSoftware("0000000019");
		logger.info("************返回最新的软件详细信息：{}", su);
	}
	
	@Test
	public void removeSoftware() {
		SoftwareUpgrade su = createEntity();
		softwareUpgradeService.save(su);		
		softwareUpgradeService.removeByIds(su.getSoftwareId());
		logger.info("************删除软件：{}", su);
	}
	
	@Test
	public void getSoftwareDetail() {
		SoftwareUpgrade su = createEntity();
		softwareUpgradeService.save(su);
		SoftwareUpgrade softwareUpgrade = softwareUpgradeService.get(su.getSoftwareId());
		logger.info("************返回软件详细信息：{}", softwareUpgrade);
	}
	
	@Test
	public void querySoftwaresForPaging() {
		Paging<SoftwareUpgrade> paging = softwareUpgradeService.querySoftwaresForPaging(
				null, "02", null, 1, 10);
		logger.info("************返回软件分页信息：{}", paging);
	}
	
	@Test
	public void approveSoftware() {
		SoftwareUpgrade su = createEntity();
		softwareUpgradeService.save(su);
		softwareUpgradeService.approveSoftware(su.getSoftwareId(), true, "admin");
		logger.info("************审核软件：{}", su);
		su = createEntity();
		softwareUpgradeService.save(su);
		softwareUpgradeService.approveSoftware(su.getSoftwareId(), false, "admin");
		logger.info("************拒绝审核软件：{}", su);
	}
	
	@Test
	public void publishSoftware() {
		SoftwareUpgrade su = createEntity();
		softwareUpgradeService.save(su);
		softwareUpgradeService.approveSoftware(su.getSoftwareId(), true, "admin");
		softwareUpgradeService.publishSoftware(su.getSoftwareId(), "admin");
		logger.info("************发布软件：{}", su);
	}
	
	@Test
	public void publishTimingSoftware() {
		SoftwareUpgrade su = createEntity();
		softwareUpgradeService.save(su);
		softwareUpgradeService.approveSoftware(su.getSoftwareId(), true, "admin");
		softwareUpgradeService.publishTimingSoftware(su.getSoftwareId(), new Date(), "admin");
		logger.info("************定时发布软件：{}", su);
	}
	
	@Test
	public void publishTimingSoftwares() {
		softwareUpgradeService.publishTimingSoftwares();
		logger.info("************处理等待发布的软件************");
	}
	
	private SoftwareUpgrade createEntity(){
		SoftwareUpgrade su = new SoftwareUpgrade();
		List<SoftwareUpgradeTarget> suTargets = new ArrayList<SoftwareUpgradeTarget>();
		DeviceType dt1 = new DeviceType();
		dt1.setDeviceType("01");
		DeviceType dt2 = new DeviceType();
		dt2.setDeviceType("02");
		HousingDistrictInfo hdi1 = new HousingDistrictInfo();
		hdi1.setId("1");
		HousingDistrictInfo hdi2 = new HousingDistrictInfo();
		hdi2.setId("2");
		SoftwareUpgradeTarget sut1 = new SoftwareUpgradeTarget();
		sut1.setDeviceType(dt1);
		sut1.setHousingDistrictInfo(hdi1);
		suTargets.add(sut1);
		SoftwareUpgradeTarget sut2 = new SoftwareUpgradeTarget();
		sut2.setDeviceType(dt2);
		sut2.setHousingDistrictInfo(hdi2);
		suTargets.add(sut2);
		su.setSuTargets(suTargets);
		su.setSavePath("/upload/fileAction_downloadWebUploadFile.action?fileObj.fileName=DigiHomeV2.0.apk");
		su.setSoftwareName("DigiHome");
		su.setStatus(SoftwareUpgradeStatus.APPLIED.getValue());
		su.setVersionName("V2.0.0");
		su.setSoftwareDesc("DigiHome.");
		su.setVersion(1);
		su.setApplyedTime(new Date());
		su.setApplyedUser("admin");
		return su;
	}
}
