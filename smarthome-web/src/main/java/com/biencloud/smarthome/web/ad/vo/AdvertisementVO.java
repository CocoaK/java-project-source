package com.biencloud.smarthome.web.ad.vo;

import java.util.Date;
import java.util.List;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 广告值对象。
 * @author kouy
 * @since 1.0 2012-5-30
 * @see BaseVO
 */
public class AdvertisementVO extends BaseVO {

	private static final long serialVersionUID = -1921626344509391726L;
	
	private String adId;//广告ID
	private Date adBeginTime;//广告投放起始时间
	private String adDesc;//广告描述
	private Date adEndTime;//广告投放结束时间
	private String adName;//广告名称
	private AdTypeVO adType;//广告类型
	private Date applyedTime;//广告申请时间
	private String applyedUser;//广告申请用户的登录帐号
	private String adDetailPicUrl;//广告图片详情链接地址
	private AdLocationVO adLocation;//广告投放位置
	private Date publishedTime;//广告发布时间
	private String publishedUser;//广告发布用户的登录帐号
	private String adPicUrl;//广告图片链接地址
	private String adLinkUrl;//广告链接地址
	private String adSize;//广告图片大小
	private String status;//广告状态

	private List<AdTargetVO> adTargets;//广告投放目标列表

	
	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public Date getAdBeginTime() {
		return adBeginTime;
	}

	public void setAdBeginTime(Date adBeginTime) {
		this.adBeginTime = adBeginTime;
	}

	public String getAdDesc() {
		return adDesc;
	}

	public void setAdDesc(String adDesc) {
		this.adDesc = adDesc;
	}

	public Date getAdEndTime() {
		return adEndTime;
	}

	public void setAdEndTime(Date adEndTime) {
		this.adEndTime = adEndTime;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public AdTypeVO getAdType() {
		return adType;
	}

	public void setAdType(AdTypeVO adType) {
		this.adType = adType;
	}

	public Date getApplyedTime() {
		return applyedTime;
	}

	public void setApplyedTime(Date applyedTime) {
		this.applyedTime = applyedTime;
	}

	public String getApplyedUser() {
		return applyedUser;
	}

	public void setApplyedUser(String applyedUser) {
		this.applyedUser = applyedUser;
	}

	public String getAdDetailPicUrl() {
		return adDetailPicUrl;
	}

	public void setAdDetailPicUrl(String adDetailPicUrl) {
		this.adDetailPicUrl = adDetailPicUrl;
	}

	public AdLocationVO getAdLocation() {
		return adLocation;
	}

	public void setAdLocation(AdLocationVO adLocation) {
		this.adLocation = adLocation;
	}

	public Date getPublishedTime() {
		return publishedTime;
	}

	public void setPublishedTime(Date publishedTime) {
		this.publishedTime = publishedTime;
	}

	public String getPublishedUser() {
		return publishedUser;
	}

	public void setPublishedUser(String publishedUser) {
		this.publishedUser = publishedUser;
	}

	public String getAdPicUrl() {
		return adPicUrl;
	}

	public void setAdPicUrl(String adPicUrl) {
		this.adPicUrl = adPicUrl;
	}

	public String getAdLinkUrl() {
		return adLinkUrl;
	}

	public void setAdLinkUrl(String adLinkUrl) {
		this.adLinkUrl = adLinkUrl;
	}

	public String getAdSize() {
		return adSize;
	}

	public void setAdSize(String adSize) {
		this.adSize = adSize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<AdTargetVO> getAdTargets() {
		return adTargets;
	}

	public void setAdTargets(List<AdTargetVO> adTargets) {
		this.adTargets = adTargets;
	}
}
