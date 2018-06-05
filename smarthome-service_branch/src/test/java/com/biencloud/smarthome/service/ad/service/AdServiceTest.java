package com.biencloud.smarthome.service.ad.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.ad.enums.AdStatus;
import com.biencloud.smarthome.service.ad.model.AdLocation;
import com.biencloud.smarthome.service.ad.model.AdSys;
import com.biencloud.smarthome.service.ad.model.AdTarget;
import com.biencloud.smarthome.service.ad.model.AdType;
import com.biencloud.smarthome.service.ad.model.Advertisement;
import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.device.model.Device;

/**
 * 广告投放管理服务测试类。
 * @author Matt Weng
 * @since 1.0 2012-5-29
 */
public class AdServiceTest extends BaseTest {

	private final Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Autowired
	private IAdService adService;
	
	@Test
	public void queryAdsForPaging(){
		Paging<Advertisement> paging = adService.queryAdsForPaging(
				null, 1, 10);
		logger.info("---------------------------返回无查询条件的广告列表分页信息：{}", paging);
		Advertisement ad = new Advertisement();
		ad.setAdName("阿斯顿");
		ad.setStatus(AdStatus.APPLIED.getValue());
		AdType adType = new AdType();
		adType.setTypeCode("01");
		ad.setAdType(adType);
		AdLocation al = new AdLocation();
		AdSys as = new AdSys();
		as.setSysCode("02");
		al.setAdSys(as);
		ad.setAdLocation(al);
		paging = adService.queryAdsForPaging(ad, 1, 10);
		logger.info("---------------------------返回查询条件的广告列表分页信息：{}", paging);
	}
	
	@Test
	public void getAdDetail(){
		Advertisement ad = adService.get("7");
		logger.info("---------------------------返回广告详细信息：{}", ad);
	}
	
	@Test	
	public void addAd(){
		Advertisement ad = createAd();
		adService.save(ad);
		logger.info("---------------------------新增广告信息：{}", ad);
	}
	
	@Test
	public void updateAd(){
		Advertisement ad = adService.get("4");
		if(ad != null){
			ad.setAdDesc("阿斯顿马丁DBS跑车......");			
			List<AdTarget> adTargets = ad.getAdTargets();
			if(adTargets == null)
				adTargets = new ArrayList<AdTarget>();
			if(CollectionUtils.isNotEmpty(adTargets))
				adTargets.remove(0);
			
			AdTarget at1 = new AdTarget();
			Device d1 = new Device();
			d1.setDeviceId("3");
			at1.setDevice(d1);
			
			AdTarget at2 = new AdTarget();
			Device d2 = new Device();
			d2.setDeviceId("4");
			at2.setDevice(d2);
			
			adTargets.add(at1);
			adTargets.add(at2);
			ad.setAdTargets(adTargets);
			adService.update(ad);
			logger.info("---------------------------更新广告信息：{}", ad);
		}		
	}
	
	@Test
	public void removeAd(){
		String adId = "8";
		if(adService.get(adId) != null)
			adService.removeByIds(adId);
		logger.info("---------------------------删除广告编号为{}的广告信息", adId);
	}
	
	@Test
	public void publishAd(){
		String adId = "2";
		Advertisement ad = adService.get(adId);
		if(ad != null){
			if(AdStatus.APPLIED.getValue().equals(ad.getStatus()))
				adService.publishAd(adId, "test");
			logger.info("----------------------即时发布编号为{}的广告", adId);
		}
	}
	
	@Test
	public void publishTimingAd(){
		String adId = "2";
		Advertisement ad = adService.get(adId);
		if(ad != null){
			if(AdStatus.APPLIED.getValue().equals(ad.getStatus())){
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MINUTE, 5);
				adService.publishTimingAd(adId, cal.getTime(), "test");
			}
			logger.info("----------------------定时发布编号为{}的广告", adId);
		}
	}
	
	@Test
	public void handlePublishingAds(){
		Advertisement ad = createAd();
		adService.save(ad);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 5);
		adService.publishTimingAd(ad.getAdId(), cal.getTime(), "test");
		adService.handlePublishingAds();
		logger.info("----------------------结束处理等待发布的广告----------------------");
	}
	
	@Test
	public void handlePublishedAds(){
//		Advertisement ad = createAd();
//		adService.save(ad);
//		adService.publishAd(ad.getAdId(), "test");
		adService.handlePublishedAds();
		logger.info("----------------------结束处理并投放已发布的广告----------------------");
	}
	
	@Test
	public void stopAd(){
		String adId = "8";
		Advertisement ad = adService.get(adId);
		if(ad != null){
			if(AdStatus.RUNNING.getValue().equals(ad.getStatus())){
				adService.stopAd(adId);
			}
			logger.info("----------------------停止编号为{}的已投放广告", adId);
		}
	}
	
	@Test
	public void resumeAd(){
		String adId = "8";
		Advertisement ad = adService.get(adId);
		if(ad != null){
			if(AdStatus.STOPPED.getValue().equals(ad.getStatus())){
				adService.resumeAd(adId);
			}
			logger.info("----------------------恢复编号为{}的已停止广告", adId);
		}
	}
	
	@Test
	public void removeAdTargetDevices(){
		List<String> deviceIds = new ArrayList<String>();
		deviceIds.add("1");
		deviceIds.add("18");
		adService.removeAdTargetDevices(deviceIds);
		logger.info("----------------------删除设备编号列表为{}关联的广告投放目标", deviceIds);
	}
	
	
	private Advertisement createAd() {
		Advertisement ad = new Advertisement();
		ad.setAdName("阿斯顿马丁");
		ad.setAdDesc("阿斯顿马丁DBS跑车。");
		AdType adType = new AdType();
		adType.setTypeCode("01");
		ad.setAdType(adType);
		AdLocation adLocation = new AdLocation();
		adLocation.setLocationCode("APP00001");
		AdSys as = new AdSys();
		as.setSysCode("01");
		adLocation.setAdSys(as);
		ad.setAdLocation(adLocation);
		ad.setAdPicUrl("http://127.0.0.1:9080/smarthomeweb/images/ad.png");
		ad.setAdDetailPicUrl("http://127.0.0.1:9080/smarthomeweb/images/ad.png");
		ad.setApplyedTime(new Date());
		ad.setApplyedUser("test");
		ad.setStatus(AdStatus.APPLIED.getValue());
		Calendar cal = Calendar.getInstance();
		ad.setAdBeginTime(cal.getTime());
		cal.add(Calendar.YEAR, 1);
		ad.setAdEndTime(cal.getTime());
		List<AdTarget> adTargets = new ArrayList<AdTarget>();
		AdTarget at1 = new AdTarget();
		Device d1 = new Device();
		d1.setDeviceId("18");
		at1.setDevice(d1);
		adTargets.add(at1);
		ad.setAdTargets(adTargets);
		return ad;
	}
}
