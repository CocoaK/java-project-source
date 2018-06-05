package com.biencloud.smarthome.service.ad.model;

import javax.persistence.*;

import com.biencloud.smarthome.service.base.model.BaseEntity;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 * 广告投放实体类。
 * @author kouy
 * @since 1.0 2012-5-29
 * @see BaseEntity
 */
@Entity
@Table(name="t_advertisement")
public class Advertisement extends BaseEntity {

	private static final long serialVersionUID = 8490399003397167467L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ad_id")
	private String adId;//广告ID

	@Column(name="ad_begin_time")
	private Date adBeginTime;//广告投放起始时间

	@Column(name="ad_desc")
	private String adDesc;//广告描述

	@Column(name="ad_end_time")
	private Date adEndTime;//广告投放结束时间

	@Column(name="ad_name")
	private String adName;//广告名称

	@OneToOne
	@JoinColumn(name="type_code")
	private AdType adType;//广告类型
	
	@Column(name="applyed_time", updatable=false)
	private Date applyedTime;//广告申请时间

	@Column(name="applyed_user", updatable=false)
	private String applyedUser;//广告申请用户的登录帐号

	@Column(name="ad_detail_pic_url")
	private String adDetailPicUrl;//广告图片详情链接地址

	@OneToOne
	@JoinColumn(name="location_code")
	private AdLocation adLocation;//广告投放位置

	@Column(name="published_time", insertable=false)
	private Date publishedTime;//广告发布时间

	@Column(name="published_user", insertable=false)
	private String publishedUser;//广告发布用户的登录帐号

	@Column(name="ad_pic_url")
	private String adPicUrl;//广告图片链接地址
	
	@Column(name="ad_link_url")
	private String adLinkUrl;//广告链接地址
	
	@Column(name="ad_size")
	private String adSize;//广告图片大小
	
	@Column(name="status", updatable=false)
	private String status;//广告状态，参见 AdStatus

	@OneToMany(cascade={CascadeType.DETACH,CascadeType.REFRESH,CascadeType.REMOVE,CascadeType.MERGE},fetch=FetchType.EAGER,mappedBy="adId")
	private List<AdTarget> adTargets;//广告投放目标列表

    public Advertisement() {
    }

	public String getAdId() {
		return this.adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public Date getAdBeginTime() {
		return this.adBeginTime;
	}

	public void setAdBeginTime(Date adBeginTime) {
		this.adBeginTime = adBeginTime;
	}

	public String getAdDesc() {
		return this.adDesc;
	}

	public void setAdDesc(String adDesc) {
		this.adDesc = adDesc;
	}

	public Date getAdEndTime() {
		return this.adEndTime;
	}

	public void setAdEndTime(Date adEndTime) {
		this.adEndTime = adEndTime;
	}

	public String getAdName() {
		return this.adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public Date getApplyedTime() {
		return this.applyedTime;
	}

	public AdType getAdType() {
		return adType;
	}

	public void setAdType(AdType adType) {
		this.adType = adType;
	}

	public void setApplyedTime(Date applyedTime) {
		this.applyedTime = applyedTime;
	}

	public String getApplyedUser() {
		return this.applyedUser;
	}

	public void setApplyedUser(String applyedUser) {
		this.applyedUser = applyedUser;
	}

	public String getAdPicUrl() {
		return adPicUrl;
	}

	public void setAdPicUrl(String adPicUrl) {
		this.adPicUrl = adPicUrl;
	}
	
	public Date getPublishedTime() {
		return this.publishedTime;
	}

	public AdLocation getAdLocation() {
		return adLocation;
	}

	public void setAdLocation(AdLocation adLocation) {
		this.adLocation = adLocation;
	}

	public void setPublishedTime(Date publishedTime) {
		this.publishedTime = publishedTime;
	}

	public String getPublishedUser() {
		return this.publishedUser;
	}

	public void setPublishedUser(String publishedUser) {
		this.publishedUser = publishedUser;
	}

	public String getAdDetailPicUrl() {
		return adDetailPicUrl;
	}

	public void setAdDetailPicUrl(String adDetailPicUrl) {
		this.adDetailPicUrl = adDetailPicUrl;
	}	
	
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAdSize() {
		return adSize;
	}

	public void setAdSize(String adSize) {
		this.adSize = adSize;
	}

	public List<AdTarget> getAdTargets() {
		return this.adTargets;
	}

	public void setAdTargets(List<AdTarget> adTargets) {
		this.adTargets = adTargets;
	}
	
	public String getAdLinkUrl() {
		return adLinkUrl;
	}

	public void setAdLinkUrl(String adLinkUrl) {
		this.adLinkUrl = adLinkUrl;
	}

	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-440730253, -147709611)
				.append(this.adId).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Advertisement)) {
			return false;
		}
		Advertisement ad = (Advertisement) object;
		return new EqualsBuilder()				
				.append(this.adId, ad.adId)
				.isEquals();
	}	
}