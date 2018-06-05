package com.biencloud.smarthome.service.ad.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.ad.dao.IAdTargetDao;
import com.biencloud.smarthome.service.ad.enums.AdStatus;
import com.biencloud.smarthome.service.ad.model.AdTarget;
import com.biencloud.smarthome.service.ad.model.Advertisement;
import com.biencloud.smarthome.service.ad.service.IAdService;
import com.biencloud.smarthome.service.ad.service.IAdTargetService;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.constants.PushKindConstants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.push.dao.IPushDao;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.sysparam.service.ISysParamService;

/**
 * 广告投放管理领域服务实现类。
 * @author kouy
 * @since 1.0 2012-5-29
 * @see BaseService
 * @see IAdService
 */
public class AdServiceImpl extends BaseService<Advertisement, String> implements IAdService {

	private ISysParamService sysParamService;
	private IAdTargetService adTargetService;	
	private IAdTargetDao adTargetDao;
	private IPushDao pushDao;
	
	public ISysParamService getSysParamService() {
		return sysParamService;
	}
	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}
	
	public IAdTargetService getAdTargetService() {
		return adTargetService;
	}
	public void setAdTargetService(IAdTargetService adTargetService) {
		this.adTargetService = adTargetService;
	}
		
	public IAdTargetDao getAdTargetDao() {
		return adTargetDao;
	}
	public void setAdTargetDao(IAdTargetDao adTargetDao) {
		this.adTargetDao = adTargetDao;
	}

	public IPushDao getPushDao() {
		return pushDao;
	}
	public void setPushDao(IPushDao pushDao) {
		this.pushDao = pushDao;
	}
	
	
	@Override
	/**
	 * adName：如果为空不作为查询条件，否则作为模糊查询条件
	 * adType.typeCode：如果为空不作为查询条件
	 * adLocation.adSys.sysCode：如果为空不作为查询条件
	 * adLocation.locationCode：如果为空不作为查询条件
	 * status：如果为空不作为查询条件
	 * adBeginTime：如果为Null不作为查询条件
	 * adEndTime：如果为Null不作为查询条件
	 */
	public Paging<Advertisement> queryAdsForPaging(Advertisement ad,
			int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();
		
		Map<String, Object> params = createParams(jpql, ad);
		jpql.append(" ORDER BY ad.applyedTime desc");
		jpql.insert(0, "SELECT " + REPLACE_CHARS +" FROM Advertisement ad");
		
		String queryString = jpql.toString().replace(
				REPLACE_CHARS, "ad");
		String queryStringCount = jpql.toString().replace(
				REPLACE_CHARS, "COUNT(ad)");
		
		Paging<Advertisement> paging = queryByNamedParamsForPaging(
				pageNum, pageSize, queryString, queryStringCount, params);
		
		List<Advertisement> list = paging.getResults();
		if(CollectionUtils.isNotEmpty(list)){
			for (Advertisement advertisement : list) 
				setProperties(advertisement);
		}
		
		return paging;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(Advertisement ad) {
		ad.setStatus(AdStatus.APPLIED.getValue());
		getDao().save(ad);
		
		List<AdTarget> list = ad.getAdTargets();
		if(CollectionUtils.isEmpty(list))
			return;
		
		for (AdTarget at : list) {
			at.setAdId(ad.getAdId());
			getDao().saveObject(at);
		}
	}
		
	@Override
	public Advertisement get(String entityId) {
		Advertisement ad = getDao().get(entityId);
		setProperties(ad);
		return ad;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	@SuppressWarnings("unchecked")
	public void update(Advertisement ad) {
		List<AdTarget> srcList = getAdTargetService().queryAdTargets(ad.getAdId());
		if(srcList == null)
			srcList = new ArrayList<AdTarget>();
		
		List<AdTarget> currList = ad.getAdTargets();
		if(currList == null)
			currList = new ArrayList<AdTarget>();
		
		//过滤出删除的广告投放目标列表
		List<AdTarget> removeList = (List<AdTarget>)CollectionUtils.subtract(srcList, currList);
		getAdTargetDao().removeAll(removeList);
		
		//过滤出新增的广告投放目标列表
		List<AdTarget> addList = (List<AdTarget>)CollectionUtils.subtract(currList, srcList);
		for (AdTarget at : addList)
			at.setAdId(ad.getAdId());
		
		getAdTargetDao().saveAll(addList);
				
		getDao().update(ad);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void publishAd(String adId, String publishedUser) {
		publish(adId, AdStatus.PUBLISHED.getValue(), 
				new Date(), publishedUser);	
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void publishTimingAd(String adId, Date publishedTime,
			String publishedUser) {
		publish(adId, AdStatus.PUBLISHING.getValue(), 
				publishedTime, publishedUser);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void handlePublishingAds() {
		StringBuilder jpql = new StringBuilder("SELECT ad.adId FROM Advertisement ad " +
				"WHERE ad.status = ?1 AND ad.publishedTime <= ?2");
		Object[] values = {AdStatus.PUBLISHING.getValue(), new Date()};
		List<String> ids = findIds(jpql.toString(), values);
		if(CollectionUtils.isEmpty(ids)){
			logger.info("************没有等待发布的广告************");
			return;
		}
		
		for(String id : ids)
			updateAdStatus(id, AdStatus.PUBLISHING.getValue(), 
					AdStatus.PUBLISHED.getValue());
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void handlePublishedAds() {
		StringBuilder jpql = new StringBuilder("SELECT ad.adId FROM Advertisement ad " +
				"WHERE ad.status = ?1 AND ad.adBeginTime <= ?2");
		Object[] values = {AdStatus.PUBLISHED.getValue(), new Date()};
		List<String> ids = findIds(jpql.toString(), values);
		if(CollectionUtils.isEmpty(ids)){
			logger.info("************没有已发布的广告等待投放************");
			return;
		}
		
		for(String id : ids){
			updateAdStatus(id, AdStatus.PUBLISHED.getValue(), 
					AdStatus.RUNNING.getValue());
			
			savePushData(id);
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void stopAd(String adId) {
		updateAdStatus(adId, AdStatus.RUNNING.getValue(), 
				AdStatus.STOPPED.getValue());
		
		savePushData(Constants.PUSH_OP_TYPE_STOP, getDao().get(adId));
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void resumeAd(String adId) {
		updateAdStatus(adId, AdStatus.STOPPED.getValue(), 
				AdStatus.RUNNING.getValue());
		
		savePushData(Constants.PUSH_OP_TYPE_RESUME, getDao().get(adId));
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeByIds(String... entityIds) {
		for (String adId : entityIds)
			savePushData(Constants.PUSH_OP_TYPE_REMOVE, getDao().get(adId));
		
		getDao().removeByIds(entityIds);		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeAdTargetDevices(List<String> deviceIds) {
		if(CollectionUtils.isEmpty(deviceIds))
			return;
		
		removeByParams("DELETE FROM AdTarget ad WHERE ad.device.deviceId IN ( ?1 )", deviceIds);
	}
	
	
	// 创建属性变量名和属性值映射。
	private Map<String, Object> createParams(
			StringBuilder jpql, Advertisement ad) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (ad == null)
			return params;

		if(!isEmpty(ad.getAdType()) && !isEmpty(ad.getAdType().getTypeCode()))
			appendCondition(jpql, "ad.adType.typeCode = :adTypeCode", "adTypeCode",
					ad.getAdType().getTypeCode(), params);
		
		if(!isEmpty(ad.getAdLocation()) && !isEmpty(ad.getAdLocation().getAdSys()) 
				&& !isEmpty(ad.getAdLocation().getAdSys().getSysCode()))
			appendCondition(jpql, "ad.adLocation.adSys.sysCode = :sysCode", "sysCode",
					ad.getAdLocation().getAdSys().getSysCode(), params);
		
		if(StringUtils.isNotBlank(ad.getAdName()))
			appendCondition(jpql, "ad.adName LIKE :adName", "adName", 
					"%" + ad.getAdName() + "%", params);
		
		appendCondition(jpql, "ad.status = :status", "status",
				ad.getStatus(), params);

		if(!isEmpty(ad.getAdLocation()) && !isEmpty(ad.getAdLocation().getLocationCode()))
			appendCondition(jpql, "ad.adLocation.locationCode = :locationCode", "locationCode",
					ad.getAdLocation().getLocationCode(), params);
		
		appendCondition(jpql, "ad.adBeginTime <= :adBeginTime", "adBeginTime",
				ad.getAdBeginTime(), params);
		
		appendCondition(jpql, "ad.adEndTime >= :adEndTime", "adEndTime",
				ad.getAdEndTime(), params);
		
		return params;
	}
	
	//重新设置广告的部分属性值
	private void setProperties(Advertisement ad){
		if(ad == null)
			return;
		String fileServerUrl = getSysParamService().getExternalFileServerUrl();
		String adPicUrl = ad.getAdPicUrl();
		if(StringUtils.isNotBlank(adPicUrl) 
				&& !StringUtils.contains(adPicUrl, fileServerUrl))
			ad.setAdPicUrl(fileServerUrl + adPicUrl);
		String adDetailPicUrl = ad.getAdDetailPicUrl();
		if(StringUtils.isNotBlank(adDetailPicUrl) 
				&& !StringUtils.contains(adDetailPicUrl, fileServerUrl))
			ad.setAdDetailPicUrl(fileServerUrl + adDetailPicUrl);		
	}
	
	//支持即时和定时发布广告。
	private void publish(String adId, String status, Date publishedTime,
			String publishedUser) {
		StringBuilder jpql = new StringBuilder(
				"UPDATE Advertisement ad SET ad.status = ?1, ad.publishedUser= ?2, "
						+ "ad.publishedTime= ?3 WHERE ad.adId = ?4 AND ad.status = ?5");
		Object[] values = { status, publishedUser, publishedTime, adId,
				AdStatus.APPLIED.getValue() };
		update(jpql.toString(), values);
	}
	
	//更新软件状态。
	private void updateAdStatus(String adId, String currStatus, String targetStatus) {
		StringBuilder jpql = new StringBuilder(
				"UPDATE Advertisement ad SET ad.status = ?1 WHERE ad.adId = ?2 AND ad.status = ?3");
		Object[] values = { targetStatus, adId, currStatus };
		update(jpql.toString(), values);
	}
	
	//根据广告编号保存推送的广告信息
	private void savePushData(String adId) {
		Advertisement ad = getDao().get(adId);
		savePushData(Constants.PUSH_OP_TYPE_ADD, ad);
	}
	
	//保存推送数据
	private void savePushData(String operationType, Advertisement ad){
		if(CollectionUtils.isEmpty(ad.getAdTargets()))
			return;
		
		for (AdTarget at : ad.getAdTargets()) {
			Push push = new Push();
			push.setPushName("Advertisement");
			push.setPushKind(PushKindConstants.PUSH_AD_KIND);
			push.setAddTime(new Date());
			push.setPushClientId(at.getDevice().getDeviceCode());
			push.setPushContent(buildPushContent(operationType, ad));
			getPushDao().save(push);
		}
	}
	
	//构建推送内容
	private String buildPushContent(String operationType, Advertisement ad){
		StringBuilder pushContent = new StringBuilder();
		pushContent.append("operationType=");
		pushContent.append(operationType);
		pushContent.append(",adId=");
		pushContent.append(ad.getAdId());
		pushContent.append(",adName=");
		pushContent.append(ad.getAdName());
		pushContent.append(",adType=");
		pushContent.append(ad.getAdType().getTypeCode());
		pushContent.append(",locationCode=");
		pushContent.append(ad.getAdLocation().getLocationCode());
		pushContent.append(",locationCoordinate=");
		pushContent.append(StringUtils.replace(ad.getAdLocation().getLocationCoordinate(), ",", "|"));
		pushContent.append(",publishedTime=");
		long publishedTime = 0L;
		if(ad.getPublishedTime() != null)
			publishedTime = ad.getPublishedTime().getTime();
		pushContent.append(publishedTime);
		pushContent.append(",adBeginTime=");
		long adBeginTime = 0L;
		if(ad.getAdBeginTime() != null)
			adBeginTime = ad.getAdBeginTime().getTime();
		pushContent.append(adBeginTime);
		pushContent.append(",adEndTime=");
		long adEndTime = 0L;
		if(ad.getAdEndTime() != null)
			adEndTime = ad.getAdEndTime().getTime();
		pushContent.append(adEndTime);
		pushContent.append(",downloadPicPath=");
		pushContent.append(ad.getAdPicUrl());
		pushContent.append(",downloadDetailPicPath=");
		pushContent.append(ad.getAdDetailPicUrl());
		return pushContent.toString();
	}
}
