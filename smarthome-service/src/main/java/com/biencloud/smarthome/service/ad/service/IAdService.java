package com.biencloud.smarthome.service.ad.service;

import java.util.Date;
import java.util.List;

import com.biencloud.smarthome.service.ad.model.Advertisement;
import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;

/**
 * 广告投放管理领域服务接口。
 * @author kouy
 * @since 1.0 2012-5-29
 * @see IService
 * @throws RuntimeException 当方法执行发生异常时
 */
public interface IAdService extends IService<Advertisement, String> {

	/**
	 * 查询广告列表，分页。
	 * @param ad 广告对象
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public Paging<Advertisement> queryAdsForPaging(Advertisement ad, int pageNum, int pageSize);
	
	/**
	 * 即时发布广告。
	 * @param adId 广告编号
	 * @param publishedUser 发布人员登录名
	 */
	public void publishAd(String adId, String publishedUser);
	
	/**
	 * 定时发布广告。
	 * @param adId 广告编号
	 * @param publishedTime 发布时间
	 * @param publishedUser 发布人员登录名
	 */
	public void publishTimingAd(String adId, Date publishedTime, String publishedUser);
	
	/**
	 * 处理等待发布的广告。
	 */
	public void handlePublishingAds();
	
	/**
	 * 处理并投放已发布的广告。
	 */
	public void handlePublishedAds();
	
	/**
	 * 停止已投放的广告。
	 * @param adId 广告编号
	 */
	public void stopAd(String adId);
	
	/**
	 * 恢复已停止的广告。
	 * @param adId 广告编号
	 */
	public void resumeAd(String adId);
	
	/**
	 * 删除指定设备编号列表关联的广告投放目标。
	 * @param deviceIds 设备编号列表
	 */
	public void removeAdTargetDevices(List<String> deviceIds);
}
