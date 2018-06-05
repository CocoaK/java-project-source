package com.biencloud.smarthome.service.softwareupgrade.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.enums.LockType;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.constants.PushKindConstants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.model.DeviceType;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.login.service.ILoginService;
import com.biencloud.smarthome.service.push.dao.IPushDao;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.softwareupgrade.enums.SoftwareUpgradeStatus;
import com.biencloud.smarthome.service.softwareupgrade.model.SoftwareUpgrade;
import com.biencloud.smarthome.service.softwareupgrade.model.SoftwareUpgradeTarget;
import com.biencloud.smarthome.service.softwareupgrade.service.ISoftwareUpgradeService;
import com.biencloud.smarthome.service.sysparam.service.ISysParamService;

/**
 * 软件升级模块领域服务实现类。
 * @author kouy
 * @since 1.0 2012-4-23
 * @see BaseService
 * @see ISoftwareUpgradeService
 */
public class SoftwareUpgradeServiceImpl extends BaseService<SoftwareUpgrade, String> implements
		ISoftwareUpgradeService {
	
	private ISysParamService sysParamService;
	private IPushDao pushDao;
	private IDeviceService deviceService;
	
	public ISysParamService getSysParamService() {
		return sysParamService;
	}
	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}
	
	public IPushDao getPushDao() {
		return pushDao;
	}
	public void setPushDao(IPushDao pushDao) {
		this.pushDao = pushDao;
	}
	
	public IDeviceService getDeviceService() {
		return deviceService;
	}
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	
	@Override
	public Paging<SoftwareUpgrade> querySoftwaresForPaging(String softwareName,
			String deviceType, String status, int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();
		
		Map<String, Object> params = createParams(
				jpql, softwareName, deviceType, status);
		jpql.append(" ORDER BY su.applyedTime desc");
		jpql.insert(0, "SELECT " + REPLACE_CHARS +" FROM SoftwareUpgrade su");
		
		String queryString = jpql.toString().replace(REPLACE_CHARS, "DISTINCT su");
		String queryStringCount = jpql.toString().replace(REPLACE_CHARS, "COUNT(DISTINCT su)");
		
		return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
	}
		
	@Override
	public SoftwareUpgrade get(String entityId) {
		SoftwareUpgrade su = getDao().get(entityId);
		if(su != null){
			su.setApplyedUser(getLoginService().getUserNameByLoginName(
					su.getApplyedUser()));
			su.setApprovedUser(getLoginService().getUserNameByLoginName(
					su.getApprovedUser()));
			su.setPublishedUser(getLoginService().getUserNameByLoginName(
					su.getPublishedUser()));
			su.setSize(getSoftwareSize(su.getSize()));
		}
		return su;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(SoftwareUpgrade su) {
		su.setStatus(SoftwareUpgradeStatus.APPLIED.getValue());
		su.setVersion(1);
		getDao().save(su);
		updateSoftwareCode(su);
		
		saveSuTargets(su);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void upgradeSoftware(SoftwareUpgrade su) {
		SoftwareUpgrade currSoftware = getDao().get(su.getSoftwareId());
		if(currSoftware == null)
			throw new IllegalArgumentException("软件编号" + su.getSoftwareId() + "不存在！");
		SoftwareUpgrade latestSoftware = getLatestSoftware(currSoftware.getSoftwareCode());
		su.setSoftwareId(null);
		su.setSoftwareCode(latestSoftware.getSoftwareCode());
		su.setSoftwareName(latestSoftware.getSoftwareName());
		su.setSoftwareDesc(latestSoftware.getSoftwareDesc());
		su.setVersion(latestSoftware.getVersion() + 1);
		su.setStatus(SoftwareUpgradeStatus.APPLIED.getValue());
		su.setSuTargets(null);
		getDao().save(su);
		
		su.setSuTargets(copySuTargets(latestSoftware.getSuTargets()));
		saveSuTargets(su);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public SoftwareUpgrade getLatestSoftware(String softwareCode) {
		StringBuilder jpql = new StringBuilder("SELECT su FROM SoftwareUpgrade su");
		jpql.append(" WHERE su.softwareCode = ?1");
		jpql.append("  AND su.version = (");
		jpql.append("SELECT MAX(su1.version) FROM SoftwareUpgrade su1");
		jpql.append(" WHERE su1.softwareCode = ?2)");
		
		Object[] values = { softwareCode, softwareCode };
		List<SoftwareUpgrade> list = getDao().find(LockType.PESSIMISTIC_WRITE, jpql.toString(), values);
		if(CollectionUtils.isEmpty(list))
			return null;
		
		return list.get(0);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void approveSoftware(String softwareId, boolean isApproved, String approvedUser) {
		StringBuilder jpql = new StringBuilder(
				"UPDATE SoftwareUpgrade su SET su.status = ?1, su.approvedUser= ?2, " +
				"su.approvedTime= ?3 WHERE su.softwareId = ?4 AND su.status = ?5");
		String status = isApproved ? SoftwareUpgradeStatus.APPROVED.getValue() 
				: SoftwareUpgradeStatus.REFUSED.getValue();
		Object[] values = {status, approvedUser, new Date(), 
				softwareId, SoftwareUpgradeStatus.APPLIED.getValue()};
		update(jpql.toString(), values);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void publishSoftware(String softwareId, String publishedUser) {
		publish(softwareId, SoftwareUpgradeStatus.PUBLISHED.getValue(), 
				new Date(), publishedUser);
		
		savePushData(softwareId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void publishTimingSoftware(String softwareId, Date publishedTime,
			String publishedUser) {		
		publish(softwareId, SoftwareUpgradeStatus.PUBLISHING.getValue(), 
				publishedTime, publishedUser);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void publishTimingSoftwares() {
		String jpql = "SELECT su.softwareId FROM SoftwareUpgrade su " + 
			"WHERE su.status = ?1 AND su.publishedTime <= ?2";
		Object[] values = {SoftwareUpgradeStatus.PUBLISHING.getValue(), new Date()};
		List<String> ids = findIds(jpql, values);
		if(CollectionUtils.isEmpty(ids)){
			logger.info("************没有等待发布的软件升级包************");
			return;
		}
		
		for(String id : ids){
			updateSoftwareStatus(id, SoftwareUpgradeStatus.PUBLISHED.getValue());
			savePushData(id);
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void notifyUpgradeSoftware(String softwareId, Date upgradedTime,
			String upgradedUser) {
		String jpql = "UPDATE SoftwareUpgrade su SET su.upgradedUser= ?3, " + 
				"su.upgradedTime= ?4 WHERE su.softwareId = ?1 AND su.status = ?2";
		Object[] values = {softwareId, SoftwareUpgradeStatus.PUBLISHED.getValue(), 
				upgradedUser, upgradedTime};
		update(jpql, values);
		
		savePushData(Constants.PUSH_OP_TYPE_UPGRADE, getDao().get(softwareId));
	}
	
	
	//计算软件大小，单位M
	private String getSoftwareSize(String softwareSize){
		if(StringUtils.isEmpty(softwareSize))
			return softwareSize;
		BigDecimal size = new BigDecimal(softwareSize);
		size = size.divide(BigDecimal.valueOf(1024L * 1024L), 2, RoundingMode.HALF_UP);
		return (size.toString() + "M");
	}
	
	//保存软件发布目标
	private void saveSuTargets(SoftwareUpgrade su){
		List<SoftwareUpgradeTarget> suTargets = su.getSuTargets();
		if(CollectionUtils.isEmpty(suTargets))
			return;
		
		for (SoftwareUpgradeTarget sut : suTargets) {
			sut.setSoftwareId(su.getSoftwareId());
			getDao().saveObject(sut);
		}
	}
	
	//初始化软件发布目标的编号为Null
	private List<SoftwareUpgradeTarget> copySuTargets(List<SoftwareUpgradeTarget> suTargets){
		List<SoftwareUpgradeTarget> retSuTargets = null;
		if(CollectionUtils.isNotEmpty(suTargets)){		
			retSuTargets = new ArrayList<SoftwareUpgradeTarget>(suTargets.size());
			for (SoftwareUpgradeTarget sut : suTargets){
				SoftwareUpgradeTarget suTarget = new SoftwareUpgradeTarget();
				suTarget.setDeviceType(sut.getDeviceType());
				suTarget.setHousingDistrictInfo(sut.getHousingDistrictInfo());
				retSuTargets.add(suTarget);
			}
		}
		return retSuTargets;
	}
	
	//更新软件状态。
	private void updateSoftwareStatus(String softwareId, String status){
		String jpql = "UPDATE SoftwareUpgrade su SET su.status = ?1 WHERE su.softwareId = ?2";
		Object[] values = {status, softwareId};
		update(jpql, values);
	}
	
	//支持即时和定时发布软件。
	private void publish(String softwareId, String status, 
			Date publishedTime, String publishedUser){
		String jpql = "UPDATE SoftwareUpgrade su SET su.status = ?1, su.publishedUser= ?2, " + 
			"su.publishedTime= ?3 WHERE su.softwareId = ?4 AND su.status = ?5";
		Object[] values = {status, publishedUser, publishedTime, 
				softwareId, SoftwareUpgradeStatus.APPROVED.getValue()};
		update(jpql, values);
	}
	
	
	//创建属性变量名和属性值映射。
	private Map<String, Object> createParams(StringBuilder jpql, 
			String softwareName, String deviceType, String status) {
		Map<String, Object> params = new HashMap<String, Object>();		

		if(StringUtils.isNotBlank(deviceType)){
			jpql.append(" JOIN su.suTargets sut");
			DeviceType dt = new DeviceType();
			dt.setDeviceType(deviceType);
			appendCondition(jpql, " sut.deviceType = :deviceType", 
					"deviceType", dt, params);
		}
		
		if(StringUtils.isNotBlank(softwareName))
			appendCondition(jpql, " su.softwareName LIKE :softwareName", 
					"softwareName", "%" + softwareName + "%", params);
		
		appendCondition(jpql, " su.status = :status", 
				"status", status, params);

		return params;
	}
	
	//更新软件代码
	private void updateSoftwareCode(SoftwareUpgrade su) {
		String jpql = "UPDATE SoftwareUpgrade su SET su.softwareCode = ?1 WHERE su.softwareId = ?2";
		String softwareCode = StringUtils.leftPad(su.getSoftwareId(), 10, "0");
		Object[] values = { softwareCode, su.getSoftwareId() };
		update(jpql, values);
		su.setSoftwareCode(softwareCode);
	}
	
	//根据软件编号保存推送的软件信息
	private void savePushData(String softwareId) {
		SoftwareUpgrade su = getDao().get(softwareId);
		String operationType = Constants.PUSH_OP_TYPE_ADD;
		if(su.getVersion() > 1)
			operationType = Constants.PUSH_OP_TYPE_UPDATE;
		savePushData(operationType, su);
	}
	
	//保存推送数据
	private void savePushData(String operationType, SoftwareUpgrade su){
		List<SoftwareUpgradeTarget> suTargets = su.getSuTargets();
		if(CollectionUtils.isEmpty(suTargets))
			return;
		
		List<String> groupIds = new ArrayList<String>();
		List<String> deviceTypes = new ArrayList<String>();		
		for (SoftwareUpgradeTarget sut : suTargets) {
			addGroupId(sut, groupIds);
			addDeviceType(sut, deviceTypes);
		}
		
		if(CollectionUtils.isEmpty(groupIds) 
				&& CollectionUtils.isEmpty(deviceTypes))
			return;
		
		List<Device> devices = getDeviceService().queryDevices(groupIds, deviceTypes);
		if(CollectionUtils.isNotEmpty(devices)){
			for (Device device : devices) {
				Push push = new Push();
				push.setPushName(PushKindConstants.PUSH_SOFTWARE_UPGRADE);
				push.setPushKind(PushKindConstants.PUSH_SOFTWARE_KIND);
				push.setAddTime(new Date());
				push.setPushClientId(device.getDeviceCode());
				push.setPushContent(buildPushContent(operationType, su));
				push.setPushVersion(su.getVersionName());
				if(NumberUtils.isDigits(su.getSize()))
					push.setSize(Long.valueOf(su.getSize()));
				getPushDao().save(push);
			}
		}
	}
	
	//添加小区对应的组织编号
	private void addGroupId(SoftwareUpgradeTarget sut, 
			List<String> groupIds){
		if(sut.getHousingDistrictInfo() == null)
			return;
		
		String groupId = sut.getHousingDistrictInfo().getGroupId();
		if(StringUtils.isNotBlank(groupId) 
				&& !groupIds.contains(groupId))
			groupIds.add(groupId);
	}
	
	//添加设备类型
	private void addDeviceType(SoftwareUpgradeTarget sut, 
			List<String> deviceTypes){
		if(sut.getDeviceType() == null)
			return;
		
		String deviceType = sut.getDeviceType().getDeviceType();
		if(StringUtils.isNotBlank(deviceType) 
				&& !deviceTypes.contains(deviceType))
			deviceTypes.add(deviceType);
	}
	
	//构建推送内容
	private String buildPushContent(String operationType, SoftwareUpgrade su){
		StringBuilder pushContent = new StringBuilder();
//		pushContent.append("operationType=");
//		pushContent.append(operationType);
//		pushContent.append(",softwareId=");
//		pushContent.append(su.getSoftwareId());
//		pushContent.append(",softwareCode=");
//		pushContent.append(su.getSoftwareCode());
//		pushContent.append(",version=");
//		pushContent.append(su.getVersion());
//		pushContent.append(",softwareName=");
//		pushContent.append(su.getSoftwareName());
//		pushContent.append(",versionName=");
//		pushContent.append(su.getVersionName());
//		pushContent.append(",publishedTime=");
//		long publishedTime = 0L;
//		if(su.getPublishedTime() != null)
//			publishedTime = su.getPublishedTime().getTime();
//		pushContent.append(publishedTime);		
//		if(su.getUpgradedTime() != null){
//			pushContent.append(",upgradedTime=");
//			pushContent.append(su.getUpgradedTime().getTime());
//		}			
		pushContent.append(sysParamService.getExternalFileServerUrl());
		pushContent.append(su.getSavePath());
		return pushContent.toString();	
	}
	
	private ILoginService getLoginService() {
		return (ILoginService)getBean(Constants.BEAN_NAME_LOGIN_SERVICE);
	}
}
