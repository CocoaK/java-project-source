package com.biencloud.smarthome.web.softwareupgrade.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.devicetype.vo.DeviceTypeVO;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.softwareupgrade.service.ISoftwareUpgradeService;
import com.biencloud.smarthome.web.softwareupgrade.vo.SoftwareUpgradeTargetVO;
import com.biencloud.smarthome.web.softwareupgrade.vo.SoftwareUpgradeVO;
import com.biencloud.smarthome.web.wsclient.stub.DeviceType;
import com.biencloud.smarthome.web.wsclient.stub.HousingDistrictInfo;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
import com.biencloud.smarthome.web.wsclient.stub.SoftwareUpgrade;
import com.biencloud.smarthome.web.wsclient.stub.SoftwareUpgradeTarget;

/**
 * 软件升级管理调用服务实现。
 * @author kouy
 * @since 1.0 2012-5-3
 * @see ISoftwareUpgradeService
 */
public class SoftwareUpgradeServiceImpl extends BaseService<SoftwareUpgradeVO> implements ISoftwareUpgradeService {

	private static final String SU_TARGETS = "suTargets";
	private static final String APPLYED_TIME = "applyedTime";
	private static final String APPROVED_TIME = "approvedTime";
	private static final String PUBLISHED_TIME = "publishedTime";
	private static final String UPGRADED_TIME = "upgradedTime";
	private static final String HOUSING_DISTRICT_INFO = "housingDistrictInfo";
	private static final String DEVICE_TYPE = "deviceType";
	
	
	@Override
	public PagingVO<SoftwareUpgradeVO> querySoftwaresForPaging(
			String softwareName, String deviceType, String status, int pageNum,
			int pageSize) {
		Paging paging = getSmartHomeService().querySoftwaresForPaging(
				softwareName, deviceType, status, pageNum, pageSize);
		PagingVO<SoftwareUpgradeVO> pagingVO = convertToVO(
				paging, new String[]{SU_TARGETS}, APPLYED_TIME, APPROVED_TIME, PUBLISHED_TIME, UPGRADED_TIME);
		return pagingVO;
	}

	@Override
	public void approveSoftware(String softwareId, boolean isApproved,
			String approvedUser) {
		getSmartHomeService().approveSoftware(
				softwareId, isApproved, approvedUser);
	}

	@Override
	public void publishSoftware(String softwareId, String publishedUser) {
		getSmartHomeService().publishSoftware(softwareId, publishedUser);
	}

	@Override
	public void publishTimingSoftware(String softwareId, Date publishedTime,
			String publishedUser) {
		getSmartHomeService().publishTimingSoftware(softwareId, 
				convertToXMLGregorianCalendar(publishedTime), publishedUser);
	}

	@Override
	public void addSoftware(SoftwareUpgradeVO softwareUpgradeVO) {
		getSmartHomeService().addSoftware(convertToEntity(softwareUpgradeVO));
	}

	@Override
	public SoftwareUpgradeVO getSoftwareDetail(String softwareId) {
		return convertToVO(getSmartHomeService().getSoftwareDetail(softwareId));
	}

	@Override
	public void removeSoftware(String softwareId) {
		getSmartHomeService().removeSoftware(softwareId);
	}

	@Override
	public void upgradeSoftware(SoftwareUpgradeVO softwareUpgradeVO) {
		getSmartHomeService().upgradeSoftware(convertToEntity(softwareUpgradeVO));
	}

	@Override
	public SoftwareUpgradeVO getLatestSoftware(String softwareCode) {
		return convertToVO(getSmartHomeService().getLatestSoftware(softwareCode));
	}
	
	@Override
	public void notifyUpgrade(String softwareId, Date upgradedTime,
			String upgradedUser) {
		getSmartHomeService().notifyUpgradeSoftware(softwareId, 
				convertToXMLGregorianCalendar(upgradedTime), upgradedUser);	
	}
	
	
	//实体转换为值对象
	private SoftwareUpgradeVO convertToVO(SoftwareUpgrade su){
		SoftwareUpgradeVO suVO = new SoftwareUpgradeVO();
		copyProperties(su, suVO, new String[]{SU_TARGETS}, 
				true, APPLYED_TIME, APPROVED_TIME, PUBLISHED_TIME, UPGRADED_TIME);
		
		List<SoftwareUpgradeTarget> suTargets = su.getSuTargets();
		if(suTargets != null){
			List<SoftwareUpgradeTargetVO> list = new ArrayList<SoftwareUpgradeTargetVO>(suTargets.size());
			for (SoftwareUpgradeTarget sut : suTargets) {
				SoftwareUpgradeTargetVO sutVO = new SoftwareUpgradeTargetVO();
				copyProperties(sut, sutVO, HOUSING_DISTRICT_INFO, DEVICE_TYPE);
				
				DeviceTypeVO deviceTypeVO = new DeviceTypeVO();
				copyProperties(sut.getDeviceType(), deviceTypeVO);
				sutVO.setDeviceType(deviceTypeVO);
				
				HousingDistrictInfo hdi = sut.getHousingDistrictInfo();
				if(hdi != null){
					HousingDistrictInfoVo hdiVO = new HousingDistrictInfoVo();
					hdiVO.setId(hdi.getId());
					hdiVO.setCode(hdi.getCode());
					hdiVO.setName(hdi.getName());
					hdiVO.setGroupId(hdi.getGroupId());
					sutVO.setHousingDistrictInfo(hdiVO);
				}
				list.add(sutVO);
			}
			suVO.setSuTargets(list);
		}
		return suVO;
	}
	
	//值对象转换为实体
	private SoftwareUpgrade convertToEntity(SoftwareUpgradeVO suVO){
		SoftwareUpgrade su = new SoftwareUpgrade();		
		copyProperties(suVO, su, new String[]{SU_TARGETS}, 
				false, APPLYED_TIME, APPROVED_TIME, PUBLISHED_TIME, UPGRADED_TIME);
		
		List<SoftwareUpgradeTargetVO> suTargets = suVO.getSuTargets();
		if(suTargets != null){
			List<SoftwareUpgradeTarget> list = su.getSuTargets();
			for (SoftwareUpgradeTargetVO sutVO : suTargets) {
				SoftwareUpgradeTarget sut = new SoftwareUpgradeTarget();
				copyProperties(sutVO, sut, HOUSING_DISTRICT_INFO, DEVICE_TYPE);
				
				DeviceType deviceType = new DeviceType();
				copyProperties(sutVO.getDeviceType(), deviceType);
				sut.setDeviceType(deviceType);
				
				HousingDistrictInfoVo hdiVO = sutVO.getHousingDistrictInfo();
				if(hdiVO != null){
					HousingDistrictInfo hdi = new HousingDistrictInfo();
					hdi.setId(hdiVO.getId());
					sut.setHousingDistrictInfo(hdi);
				}
				list.add(sut);
			}
		}
		return su;
	}
}
