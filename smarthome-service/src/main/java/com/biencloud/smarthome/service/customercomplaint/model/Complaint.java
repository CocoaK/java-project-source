package com.biencloud.smarthome.service.customercomplaint.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.login.model.Login;

/**
 * 客服投诉信息 实体类
 * 
 * @author jsun  
 * @since 1.0 2012-5-30
 */
@Entity
@Table(name="t_cc_complaint")
public class Complaint extends BaseEntity {
	private static final long serialVersionUID = 9054906490567891803L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	private String title;//标题
	private String content;//内容
	private String location;//位置
	/**
	 * 投诉类型
	 * 0: 系统投诉(物业公司 投诉 系统xx问题), 1: 住户投诉(住户 投诉 物业公司xx问题)
	 */
	private String type;
	private String suggestion;//处理意见

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="COMPLAINT_DATE")
	private Date complaintDate;//投诉日期
	@Column(name="COMPLAINT_LOGIN_NAME")
	private String complaintLoginName;//投诉人登录名
	@Transient
	private Login complaintLogin;//登录实体对象

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PROCESSING_DATE")
	private Date processingDate;//处理日期
	@Column(name="PROCESSING_LOGIN_NAME")
	private String processingLoginName;//处理人登录名
	@Transient
	private Login processingLogin;//处理人登录实体对象
	@Column(name="PROCESSING_STATUS")
	private String processingStatus;//处理状态
	@Column(name="district_id")
	private String districtId;//所属小区
	@Column(name="device_no")
	private String deviceNo;//设备编号
	@Column(name="house_id")
	private String houseId;//房间ID 

	/**
	 * APP那边保存的投诉ID
	 */
	@Column(name="APP_ID")
	private Integer appId;
	
	/**
	 * 唯一，不为空
	 */
	@Column(name="ONLY_VALUE")
	private String onlyValue;

	public String getOnlyValue() {
		return onlyValue;
	}
	public void setOnlyValue(String onlyValue) {
		this.onlyValue = onlyValue;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 投诉时间
	 * 
	 * @return
	 */
	public Date getComplaintDate() {
		return this.complaintDate;
	}
	public void setComplaintDate(Date complaintDate) {
		this.complaintDate = complaintDate;
	}
	
	/**
	 * 投诉人登录名
	 * 
	 * @return
	 * @see Login#getLoginName()
	 */
	public String getComplaintLoginName() {
		return this.complaintLoginName;
	}
	public void setComplaintLoginName(String complaintLoginName) {
		this.complaintLoginName = complaintLoginName;
	}

	/**
	 * 投诉内容
	 * 
	 * @return
	 */
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 投诉(人)地址
	 * 
	 * @return
	 */
	public String getLocation() {
		return this.location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * 处理投诉的时间
	 * 
	 * @return
	 */
	public Date getProcessingDate() {
		return this.processingDate;
	}
	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}
	
	/**
	 * 处理人登录名
	 * 
	 * @return
	 * @see Login#getLoginName()
	 */
	public String getProcessingLoginName() {
		return this.processingLoginName;
	}
	public void setProcessingLoginName(String processingLoginName) {
		this.processingLoginName = processingLoginName;
	}

	/**
	 * 处理状态
	 * 0: 待处理, 1: 已处理, 2: 未提交(投诉信息先保存起来, 以供下次继续修改, 然后提交后, 状态才变为待处理)
	 * 
	 * @return
	 */
	public String getProcessingStatus() {
		return this.processingStatus;
	}
	public void setProcessingStatus(String processingStatus) {
		this.processingStatus = processingStatus;
	}
	
	/**
	 * 处理人针对投诉给出的意见
	 * 
	 * @return
	 */
	public String getSuggestion() {
		return this.suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	/**
	 * 投诉信息的标题
	 * 
	 * @return
	 */
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 投诉类型
	 * 0: 系统投诉(物业公司 投诉 系统xx问题), 1: 住户投诉(住户 投诉 物业公司xx问题)
	 * 
	 * @return
	 */
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 投诉人登陆信息, 数据库中只保存了LoginName, 需要从登陆模块中取出登陆信息, 这里是需要用户名
	 * 
	 * @return
	 */
	public Login getComplaintLogin() {
		return complaintLogin;
	}
	public void setComplaintLogin(Login complaintLogin) {
		this.complaintLogin = complaintLogin;
	}

	/**
	 * 处理人登陆信息, 数据库中只保存了LoginName, 需要从登陆模块中取出登陆信息, 这里是需要用户名
	 * 
	 * @return
	 */
	public Login getProcessingLogin() {
		return processingLogin;
	}
	public void setProcessingLogin(Login processingLogin) {
		this.processingLogin = processingLogin;
	}

	/**
	 * APP那边保存的投诉ID
	 * 
	 * @return
	 */
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	
	
}