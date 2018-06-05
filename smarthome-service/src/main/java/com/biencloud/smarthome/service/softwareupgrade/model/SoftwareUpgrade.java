package com.biencloud.smarthome.service.softwareupgrade.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.biencloud.smarthome.service.base.model.BaseEntity;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 * 软件升级实体对象。
 * @author kouy
 * @since 1.0 2012-4-23
 */
@Entity
@Table(name="t_software_upgrade")
public class SoftwareUpgrade extends BaseEntity {
	
	private static final long serialVersionUID = 3560069048278053598L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="software_id")
	private String softwareId;//软件ID

	@Column(name="applyed_time", updatable=false)
	private Date applyedTime;//申请时间

	@Column(name="applyed_user", updatable=false)
	private String applyedUser;//申请用户的登录帐号

	@Column(name="approved_time", insertable=false, updatable=false)
	private Date approvedTime;//审核时间

	@Column(name="approved_user", updatable=false)
	private String approvedUser;//审核用户的登录帐号
	
	@Column(name="published_time", insertable=false, updatable=false)
	private Date publishedTime;//发布时间

	@Column(name="published_user", updatable=false)
	private String publishedUser;//发布用户的登录帐号

	@Column(name="save_path")
	private String savePath;//软件链接地址

	@Column(name="software_code", updatable=false)
	private String softwareCode;//软件代码

	@Column(name="software_desc")
	private String softwareDesc;//软件描述

	@Column(name="software_name")
	private String softwareName;//软件名称
 
	private String status;//状态，参见 SoftwareUpgradeStatus
	
	private String size;//软件大小

	@Column(name="version_name")
	private String versionName;//软件版本名称
	
	private int version;//软件版本

	@Column(name="upgraded_user", updatable=false)
	private String upgradedUser;//修改用户的登录帐号
	
	@Column(name="upgraded_time", insertable=false, updatable=false)
	private Date upgradedTime;//修改时间
	
	@OneToMany(cascade={CascadeType.DETACH,CascadeType.REFRESH,CascadeType.REMOVE,CascadeType.MERGE},fetch=FetchType.EAGER,mappedBy="softwareId",orphanRemoval=true)
	private List<SoftwareUpgradeTarget> suTargets;//软件升级目标列表
	
	
    public SoftwareUpgrade() {
    }

	public String getSoftwareId() {
		return this.softwareId;
	}

	public void setSoftwareId(String softwareId) {
		this.softwareId = softwareId;
	}

	public Date getApplyedTime() {
		return this.applyedTime;
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

	public Date getApprovedTime() {
		return this.approvedTime;
	}

	public void setApprovedTime(Date approvedTime) {
		this.approvedTime = approvedTime;
	}

	public String getApprovedUser() {
		return this.approvedUser;
	}

	public void setApprovedUser(String approvedUser) {
		this.approvedUser = approvedUser;
	}

	public Date getPublishedTime() {
		return this.publishedTime;
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

	public String getSavePath() {
		return this.savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getSoftwareCode() {
		return this.softwareCode;
	}

	public void setSoftwareCode(String softwareCode) {
		this.softwareCode = softwareCode;
	}

	public String getSoftwareDesc() {
		return this.softwareDesc;
	}

	public void setSoftwareDesc(String softwareDesc) {
		this.softwareDesc = softwareDesc;
	}

	public String getSoftwareName() {
		return this.softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	public String getStatus() {
		return this.status;
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
		return this.versionName;
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

	public List<SoftwareUpgradeTarget> getSuTargets() {
		return suTargets;
	}

	public void setSuTargets(List<SoftwareUpgradeTarget> suTargets) {
		this.suTargets = suTargets;
	}

	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-170775741, -1884786205)				
				.append(this.version).append(this.softwareCode)
				.toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SoftwareUpgrade)) {
			return false;
		}
		SoftwareUpgrade su = (SoftwareUpgrade) object;
		return new EqualsBuilder()
				.append(this.version, su.version)
				.append(this.softwareCode, su.softwareCode)
				.isEquals();
	}
}