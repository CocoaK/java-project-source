package com.biencloud.smarthome.web.ad.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import com.biencloud.smarthome.web.ad.service.IAdService;
import com.biencloud.smarthome.web.ad.vo.AdLocationVO;
import com.biencloud.smarthome.web.ad.vo.AdSysVO;
import com.biencloud.smarthome.web.ad.vo.AdTargetVO;
import com.biencloud.smarthome.web.ad.vo.AdTypeVO;
import com.biencloud.smarthome.web.ad.vo.AdvertisementVO;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.devicetype.vo.DeviceTypeVO;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.wsclient.stub.AdLocation;
import com.biencloud.smarthome.web.wsclient.stub.AdSys;
import com.biencloud.smarthome.web.wsclient.stub.AdTarget;
import com.biencloud.smarthome.web.wsclient.stub.AdType;
import com.biencloud.smarthome.web.wsclient.stub.Advertisement;
import com.biencloud.smarthome.web.wsclient.stub.Device;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 广告投放管理调用后台服务实现类。
 * @author kouy
 * @since 1.0 2012-5-30
 */
public class AdServiceImpl extends BaseService<AdvertisementVO> implements IAdService {

	private static final String AD_TARGETS = "adTargets";
	private static final String APPLYED_TIME = "applyedTime";
	private static final String PUBLISHED_TIME = "publishedTime";
	private static final String BEGIN_TIME = "adBeginTime";
	private static final String END_TIME = "adEndTime";
	private static final String AD_TYPE = "adType";
	private static final String AD_SYS = "adSys";
	private static final String AD_LOCATION = "adLocation";
	private static final String DEVICE = "device";
	private static final String RESULTS = "results";
	
	
	@Override
	public PagingVO<AdvertisementVO> queryAdsForPaging(AdvertisementVO adVO,
			int pageNum, int pageSize) {
		Advertisement ad = new Advertisement();
		copyProperties(adVO, ad, new String[]{AD_TARGETS, AD_TYPE, AD_LOCATION}, false, 
				APPLYED_TIME, PUBLISHED_TIME, BEGIN_TIME, END_TIME);	
		
		if(adVO != null){
			copyAdType(adVO, ad, false);
			copyAdLocation(adVO, ad, false);
		}
		
		Paging paging = getSmartHomeService().queryAdsForPaging(
				ad, pageNum, pageSize);
		
		PagingVO<AdvertisementVO> pagingVO = new PagingVO<AdvertisementVO>();
		copyProperties(paging, pagingVO, new String[]{RESULTS}, true);
		
		List<Object> list = paging.getResults();
		if(list != null && list.size() > 0){
			List<AdvertisementVO> results = new ArrayList<AdvertisementVO>();
			for (int index = 0, size = list.size(); index < size; index++) {
				results.add(convertToVO((Advertisement)list.get(index)));
			}
			pagingVO.setResults(results);
		}
		
		return pagingVO;
		
	}
	
	@Override
	public void publishAd(String adId, String publishedUser) {
		getSmartHomeService().publishAd(adId, publishedUser);		
	}

	@Override
	public void publishTimingAd(String adId, Date publishedTime,
			String publishedUser) {
		getSmartHomeService().publishTimingAd(adId, 
				convertToXMLGregorianCalendar(publishedTime), publishedUser);
	}

	@Override
	public void addAd(AdvertisementVO adVO) {
		getSmartHomeService().addAd(convertToEntity(adVO));
	}

	@Override
	public void updateAd(AdvertisementVO adVO) {
		getSmartHomeService().updateAd(convertToEntity(adVO));
	}

	@Override
	public void removeAd(String adId) {
		getSmartHomeService().removeAd(adId);
	}

	@Override
	public void stopAd(String adId) {
		getSmartHomeService().stopAd(adId);
	}

	@Override
	public void resumeAd(String adId) {
		getSmartHomeService().resumeAd(adId);
	}

	@Override
	public AdvertisementVO getAdDetail(String adId) {
		Advertisement ad = getSmartHomeService().getAdDetail(adId);
		return convertToVO(ad);
	}
	
	
	//广告投放位置值对象和实体对象相互拷贝
	private void copyAdLocation(AdvertisementVO adVO, Advertisement ad,
			boolean convertToVO) {
		if (convertToVO) {
			if (ad.getAdLocation() != null) {
				AdLocationVO alVO = new AdLocationVO();
				copyProperties(ad.getAdLocation(), alVO,
						new String[] { AD_SYS }, true);
				if (ad.getAdLocation().getAdSys() != null) {
					AdSysVO asVO = new AdSysVO();
					copyProperties(ad.getAdLocation().getAdSys(), asVO, null,
							true);
					alVO.setAdSys(asVO);
				}
				adVO.setAdLocation(alVO);
			}
			return;
		}

		if (adVO.getAdLocation() != null) {
			AdLocation al = new AdLocation();
			copyProperties(adVO.getAdLocation(), al, new String[] { AD_SYS },
					false);
			if (adVO.getAdLocation().getAdSys() != null) {
				AdSys as = new AdSys();
				copyProperties(adVO.getAdLocation().getAdSys(), as, null, false);
				al.setAdSys(as);
			}
			ad.setAdLocation(al);
		}
	}

	// 广告类型值对象和实体对象相互拷贝
	private void copyAdType(AdvertisementVO adVO, Advertisement ad,
			boolean convertToVO) {
		if (convertToVO) {
			if (ad.getAdType() != null) {
				AdTypeVO atVO = new AdTypeVO();
				copyProperties(ad.getAdType(), atVO, null, true);
				adVO.setAdType(atVO);
			}
			return;
		}

		if (adVO.getAdType() != null) {
			AdType at = new AdType();
			copyProperties(adVO.getAdType(), at, null, false);
			ad.setAdType(at);
		}
	}

	// 转换为广告值对象
	private AdvertisementVO convertToVO(Advertisement ad,
			String... ignoreProperties) {
		AdvertisementVO adVO = new AdvertisementVO();
		copyObject(ad, adVO, true, ignoreProperties);
		return adVO;
	}

	// 转换为广告实体对象
	private Advertisement convertToEntity(AdvertisementVO adVO,
			String... ignoreProperties) {
		Advertisement ad = new Advertisement();
		copyObject(ad, adVO, false, ignoreProperties);
		return ad;
	}

	// 广告值对象和实体对象相互拷贝
	private void copyObject(Advertisement ad, AdvertisementVO adVO,
			boolean convertToVO, String[] ignoreProperties) {
		String[] filterProperties = { AD_TARGETS, AD_TYPE, AD_LOCATION };
		for (String p : ignoreProperties) {
			if (!ArrayUtils.contains(filterProperties, p))
				filterProperties = (String[]) ArrayUtils.add(filterProperties,
						p);
		}

		if (convertToVO) {
			copyProperties(ad, adVO, filterProperties, convertToVO,
					APPLYED_TIME, PUBLISHED_TIME, BEGIN_TIME, END_TIME);
		} else {
			copyProperties(adVO, ad, filterProperties, convertToVO,
					APPLYED_TIME, PUBLISHED_TIME, BEGIN_TIME, END_TIME);
		}

		if (!ArrayUtils.contains(ignoreProperties, AD_TARGETS)) {
			if (convertToVO) {
				adVO.setAdTargets(convertToVOList(ad.getAdTargets()));
			} else {
				copyToEntityList(adVO, ad);
			}
		}

		if (!ArrayUtils.contains(ignoreProperties, AD_TYPE))
			copyAdType(adVO, ad, convertToVO);

		if (!ArrayUtils.contains(ignoreProperties, AD_LOCATION))
			copyAdLocation(adVO, ad, convertToVO);
	}

	// 转换为广告投放目标值对象列表
	private List<AdTargetVO> convertToVOList(List<AdTarget> adTargets) {
		List<AdTargetVO> list = new ArrayList<AdTargetVO>();
		if (adTargets != null && adTargets.size() > 0) {
			AdTarget at = null;
			for (int index = 0, size = adTargets.size(); index < size; index++) {
				AdTargetVO atVO = new AdTargetVO();
				at = adTargets.get(index);
				copyProperties(at, atVO, new String[] { DEVICE },
						true);
				if (at.getDevice() != null) {
					DeviceVO device = new DeviceVO();
					device.setDeviceId(at.getDevice().getDeviceId());
					device.setDeviceAlias(at.getDevice().getDeviceAlias());
					if(at.getDevice().getDeviceType() != null){
						DeviceTypeVO dt = new DeviceTypeVO();
						dt.setDeviceType(at.getDevice().getDeviceType().getDeviceType());
						device.setDeviceType(dt);
					}
					if(at.getDevice().getHousingDistrictInfo() != null){
						HousingDistrictInfoVo hdi = new HousingDistrictInfoVo();
						hdi.setId(at.getDevice().getHousingDistrictInfo().getId());
						hdi.setGroupId(at.getDevice().getHousingDistrictInfo().getGroupId());
						hdi.setName(at.getDevice().getHousingDistrictInfo().getName());
						device.setHousingDistrictInfo(hdi);
					}
					atVO.setDevice(device);				
				}
				
				list.add(atVO);
			}
		}
		return list;
	}

	// 转换为广告投放目标实体对象列表
	private void copyToEntityList(AdvertisementVO adVO, Advertisement ad) {
		List<AdTarget> list = ad.getAdTargets();
		List<AdTargetVO> voList = adVO.getAdTargets();
		if (voList != null && voList.size() > 0) {
			AdTargetVO atVO = null;
			for (int index = 0, size = voList.size(); index < size; index++) {
				AdTarget at = new AdTarget();
				atVO = voList.get(index);
				copyProperties(atVO, at, new String[] { DEVICE },
						false);
				if (atVO.getDevice() != null) {
					Device device = new Device();
					device.setDeviceId(atVO.getDevice().getDeviceId());
					at.setDevice(device);
				}
				list.add(at);
			}
		}
	}
}
