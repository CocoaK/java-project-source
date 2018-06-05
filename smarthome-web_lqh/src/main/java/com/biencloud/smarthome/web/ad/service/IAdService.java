package com.biencloud.smarthome.web.ad.service;

import java.util.Date;

import com.biencloud.smarthome.web.ad.vo.AdvertisementVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;

/**
 * 广告投放管理调用后台服务接口。
 * @author kouy
 * @since 1.0 2012-5-30
 */
public interface IAdService {

	/**
	 * 查询广告列表，分页。
	 * @param adVO 广告对象
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public PagingVO<AdvertisementVO> queryAdsForPaging(AdvertisementVO adVO, 
			int pageNum, int pageSize);
	
	/**
	 * 获取广告详细信息。
	 * @param adId 广告编号
	 * @return
	 */
	public AdvertisementVO getAdDetail(String adId);
	
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
	public void publishTimingAd(String adId, 
			Date publishedTime, String publishedUser);
	
	/**
	 * 新增广告。
	 * @param adVO 广告对象
	 */
	public void addAd(AdvertisementVO adVO);
	
	/**
	 * 更新广告。
	 * @param adVO 广告对象
	 */
	public void updateAd(AdvertisementVO adVO);
	
	/**
	 * 删除广告。
	 * @param adId 广告编号
	 */
	public void removeAd(String adId);
	
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
}
