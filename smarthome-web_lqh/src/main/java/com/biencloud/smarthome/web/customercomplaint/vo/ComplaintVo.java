package com.biencloud.smarthome.web.customercomplaint.vo;

import java.util.Date;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import com.biencloud.smarthome.web.login.vo.LoginVO;

/**
 * 投诉信息VO
 * 
 * @author jsun  
 * @since 1.0 2012-5-30
 */
public class ComplaintVo extends BaseVO {
	private static final long serialVersionUID = -3629589312225136651L;

	private String id;
	private String content;//内容
	private String location;//位置
	private String suggestion;//处理意见
	private String title;//标题
	private String type;//投诉类型

	private Date complaintDate;//投诉日期
	private String complaintLoginName;//投诉人登录名
	private LoginVO complaintLogin;//登录实体对象

	private Date processingDate;//处理日期
	private String processingLoginName;//处理人登录名
	private LoginVO processingLogin;//处理人登录实体对象
	private String districtId;//所属小区

	private String processingStatus;//处理状态
	private String deviceNo;//设备编号
	private String houseId;;//房间ID
	/**
	 * 投诉处理状态的文字描述
	 */
	private String processingStatusText;

	private Integer appId;// APP那边保存的投诉ID
	
	
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
	private String onlyValue;

	public String getOnlyValue() {
		return onlyValue;
	}
	public void setOnlyValue(String onlyValue) {
		this.onlyValue = onlyValue;
	}
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getComplaintDate() {
		return complaintDate;
	}
	public void setComplaintDate(Date complaintDate) {
		this.complaintDate = complaintDate;
	}
	public String getComplaintLoginName() {
		return complaintLoginName;
	}
	public void setComplaintLoginName(String complaintLoginName) {
		this.complaintLoginName = complaintLoginName;
	}
	public LoginVO getComplaintLogin() {
		return complaintLogin;
	}
	public void setComplaintLogin(LoginVO complaintLogin) {
		this.complaintLogin = complaintLogin;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getProcessingDate() {
		return processingDate;
	}
	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}
	public String getProcessingLoginName() {
		return processingLoginName;
	}
	public void setProcessingLoginName(String processingLoginName) {
		this.processingLoginName = processingLoginName;
	}
	public LoginVO getProcessingLogin() {
		return processingLogin;
	}
	public void setProcessingLogin(LoginVO processingLogin) {
		this.processingLogin = processingLogin;
	}
	public String getProcessingStatus() {
		return processingStatus;
	}
	public void setProcessingStatus(String processingStatus) {
		this.processingStatus = processingStatus;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 投诉处理状态的文字描述
	 * 
	 * @return 经过国际化处理的文字描述
	 */
	public String getProcessingStatusText() {
		return processingStatusText;
	}
	public void setProcessingStatusText(String processingStatusText) {
		this.processingStatusText = processingStatusText;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	
}
