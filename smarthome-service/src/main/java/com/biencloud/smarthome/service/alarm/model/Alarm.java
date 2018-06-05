package com.biencloud.smarthome.service.alarm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.user.model.OwnerUser;
import com.biencloud.smarthome.service.user.model.PaUser;

/**
 * 
 * 类名称：Alarm 
 * 类描述： 报警信息实体对象
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-23 上午10:55:57
 */
@Entity
@Table(name="t_alarm_info")
public class Alarm extends BaseEntity{

	private static final long serialVersionUID = -2400540507285464707L;
	
	/**
	 * 报警状态-用户已取消
	 */
	public static  final Integer HANLDER_STATUS_CANCEL=0;
	/**
	 * 报警状态-未处理
	 */
	public static  final Integer HANLDER_STATUS_NO=1;
	/**
	 * 报警状态-已处理
	 */
	public static  final Integer HANLDER_STATUS_YES=2;


	private String alarmId;
	//保存时不保存业主id
	private OwnerUser ownerUser;//报警业主
	private PaUser paUser;//物管用户
	private AlarmType alarmType;//报警类型
	private String deviceCode;//设备编码
	private String status;//报警状态
	private Date createdTime;//创建时间
	private String content;//报警内容
	private String houseNo;//报警房间ID
	private Date hanlderTime;//处理时间
	private HousingDistrictInfo housingDistrictInfo;//所属小区
	private Date alarmStartTime;//报警开始时间（搜索用）
	private Date alarmEndTime;//报警结束时间（搜索用）
	private Date handlerStartTime;//处理开始时间（搜索用）
	private Date handlerEndTime;//处理结束时间（搜索用）
	private boolean cancelAndNoHanlder;//是否取消和未处理（搜索用）
	
	

	// Constructors
	@Transient
	public Date getAlarmStartTime() {
		return alarmStartTime;
	}

	public void setAlarmStartTime(Date alarmStartTime) {
		this.alarmStartTime = alarmStartTime;
	}
	
	@Transient
	public boolean isCancelAndNoHanlder() {
		return cancelAndNoHanlder;
	}

	public void setCancelAndNoHanlder(boolean cancelAndNoHanlder) {
		this.cancelAndNoHanlder = cancelAndNoHanlder;
	}

	@Transient
	public Date getAlarmEndTime() { 
		return alarmEndTime;
	}

	public void setAlarmEndTime(Date alarmEndTime) {
		this.alarmEndTime = alarmEndTime;
	}

	@Transient
	public Date getHandlerStartTime() {
		return handlerStartTime;
	}

	public void setHandlerStartTime(Date handlerStartTime) {
		this.handlerStartTime = handlerStartTime;
	}

	@Transient
	public Date getHandlerEndTime() {
		return handlerEndTime;
	}

	public void setHandlerEndTime(Date handlerEndTime) {
		this.handlerEndTime = handlerEndTime;
	}

	/** default constructor */
	public Alarm() {
	}

	/** minimal constructor */
	public Alarm(AlarmType alarmType, String deviceCode, String status, Date createdTime) {
		this.alarmType = alarmType;
		this.deviceCode = deviceCode;
		this.status = status;
		this.createdTime = createdTime;
	}

	/** full constructor */
	public Alarm(OwnerUser ownerUser, PaUser paUser, AlarmType alarmType, String deviceCode, String status, Date createdTime,
			String content, String houseNo, Date hanlderTime) {
		this.ownerUser = ownerUser;
		this.paUser = paUser;
		this.alarmType = alarmType;
		this.deviceCode = deviceCode;
		this.status = status;
		this.createdTime = createdTime;
		this.content = content;
		this.houseNo = houseNo;
		this.hanlderTime = hanlderTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="alarm_id")
	public String getAlarmId() {
		return this.alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_id")
	public OwnerUser getownerUser() {
		return this.ownerUser;
	}

	public void setownerUser(OwnerUser ownerUser) {
		this.ownerUser = ownerUser;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pauser_id")
	public PaUser getpaUser() {
		return this.paUser;
	}

	public void setpaUser(PaUser paUser) {
		this.paUser = paUser;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "alarm_type", nullable = false)
	public AlarmType getAlarmType() {
		return this.alarmType;
	}

	public void setAlarmType(AlarmType alarmType) {
		this.alarmType = alarmType;
	}

	@Column(name = "device_code", nullable = false, length = 20)
	public String getDeviceCode() {
		return this.deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	@Column(name = "status", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "created_time", nullable = false, length = 19)
	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "content", length = 1000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "house_no", length = 10)
	public String getHouseNo() {
		return this.houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "comId", nullable = true)
	public HousingDistrictInfo getHousingDistrictInfo() {
		return housingDistrictInfo;
	}

	public void setHousingDistrictInfo(HousingDistrictInfo housingDistrictInfo) {
		this.housingDistrictInfo = housingDistrictInfo;
	}

	@Column(name = "hanlder_time", length = 19)
	public Date getHanlderTime() {
		return this.hanlderTime;
	}

	public void setHanlderTime(Date hanlderTime) {
		this.hanlderTime = hanlderTime;
	}
}
