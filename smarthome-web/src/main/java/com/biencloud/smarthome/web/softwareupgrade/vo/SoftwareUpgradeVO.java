package com.biencloud.smarthome.web.softwareupgrade.vo;

import java.util.Date;
import java.util.List;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import com.biencloud.smarthome.web.common.util.Constants;

/**
 * 软件升级值对象。
 * @author kouy
 * @since 1.0 2012-5-2
 * @see BaseVO
 */
@SuppressWarnings({ "serial", "unused" })
public class SoftwareUpgradeVO extends BaseVO {

	private String softwareId;//软件ID
	private Date applyedTime;//申请时间
	private String applyedUser;//申请用户的登录帐号
	private Date approvedTime;//审核时间
	private String approvedUser;//审核用户的登录帐号
	private Date publishedTime;//发布时间
	private String publishedUser;//发布用户的登录帐号
	private String savePath;//软件链接地址
	private String softwareCode;//软件代码
	private String softwareDesc;//软件描述
	private String softwareName;//软件名称
	private String status;//状态，参见 Constants.SU_APPLIED、Constants.SU_APPROVED、Constants.SU_REFUSED、Constants.SU_PUBLISHING、Constants.SU_PUBLISHED、Constants.SU_DISABLED
	private String size;//软件大小
	private String versionName;//软件版本名称
	private int version;//软件版本
	private String upgradedUser;//修改用户的登录帐号
	private Date upgradedTime;//修改时间
	
	private List<SoftwareUpgradeTargetVO> suTargets;//软件升级目标列表
	
	public String getSoftwareId() {
		return softwareId;
	}

	public void setSoftwareId(String softwareId) {
		this.softwareId = softwareId;
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

	public Date getApprovedTime() {
		return approvedTime;
	}

	public void setApprovedTime(Date approvedTime) {
		this.approvedTime = approvedTime;
	}

	public String getApprovedUser() {
		return approvedUser;
	}

	public void setApprovedUser(String approvedUser) {
		this.approvedUser = approvedUser;
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

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getSoftwareCode() {
		return softwareCode;
	}

	public void setSoftwareCode(String softwareCode) {
		this.softwareCode = softwareCode;
	}

	public String getSoftwareDesc() {
		return softwareDesc;
	}

	public void setSoftwareDesc(String softwareDesc) {
		this.softwareDesc = softwareDesc;
	}

	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getUpgradedUser() {
		return upgradedUser;
	}

	public void setUpgradedUser(String upgradedUser) {
		this.upgradedUser = upgradedUser;
	}

	public Date getUpgradedTime() {
		return upgradedTime;
	}

	public void setUpgradedTime(Date upgradedTime) {
		this.upgradedTime = upgradedTime;
	}

	public List<SoftwareUpgradeTargetVO> getSuTargets() {
		return suTargets;
	}

	public void setSuTargets(List<SoftwareUpgradeTargetVO> suTargets) {
		this.suTargets = suTargets;
	}	
}
