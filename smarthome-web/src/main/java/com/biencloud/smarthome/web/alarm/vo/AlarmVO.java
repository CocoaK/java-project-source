package com.biencloud.smarthome.web.alarm.vo;

import java.util.Date;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.user.vo.OwnerUserVO;
import com.biencloud.smarthome.web.user.vo.PaUserVO;
/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：AlarmVO 
 * 类描述： 报警VO类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午11:46:46
 */
public class AlarmVO extends BaseVO{

	private static final long serialVersionUID = 4985401168645041501L;
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
	private AlarmTypeVO alarmType;//报警类型
	private String content;//报警内容
	private Date createdTime;//创建时间
	private String deviceCode;//设备编码
	private Date hanlderTime;//处理时间
	private String houseNo;//报警房间ID
	private String status;//报警状态
	private OwnerUserVO ownerUser;;//报警业主
	private PaUserVO paUser;//物管用户
	private HousingDistrictInfoVo housingDistrictInfo;//所属小区
	private Date alarmStartTime;//报警开始时间（搜索用）
	private Date alarmEndTime;//报警结束时间（搜索用）
	private Date handlerStartTime;//处理开始时间（搜索用）
	private Date handlerEndTime;//处理结束时间（搜索用）
	private DeviceVO device;//设备对象
	
	
	
	public DeviceVO getDevice() {
		return device;
	}
	public void setDevice(DeviceVO device) {
		this.device = device;
	}
	public Date getAlarmStartTime() {
		return alarmStartTime;
	}
	public void setAlarmStartTime(Date alarmStartTime) {
		this.alarmStartTime = alarmStartTime;
	}
	public Date getAlarmEndTime() {
		return alarmEndTime;
	}
	public void setAlarmEndTime(Date alarmEndTime) {
		this.alarmEndTime = alarmEndTime;
	}
	public Date getHandlerStartTime() {
		return handlerStartTime;
	}
	public void setHandlerStartTime(Date handlerStartTime) {
		this.handlerStartTime = handlerStartTime;
	}
	public Date getHandlerEndTime() {
		return handlerEndTime;
	}
	public void setHandlerEndTime(Date handlerEndTime) {
		this.handlerEndTime = handlerEndTime;
	}
	public String getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}
	public AlarmTypeVO getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(AlarmTypeVO alarmType) {
		this.alarmType = alarmType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public Date getHanlderTime() {
		return hanlderTime;
	}
	public void setHanlderTime(Date hanlderTime) {
		this.hanlderTime = hanlderTime;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public PaUserVO getPaUser() {
		return paUser;
	}
	public void setPaUser(PaUserVO paUser) {
		this.paUser = paUser;
	}
	public OwnerUserVO getOwnerUser() {
		return ownerUser;
	}
	public void setOwnerUser(OwnerUserVO ownerUser) {
		this.ownerUser = ownerUser;
	}
	public HousingDistrictInfoVo getHousingDistrictInfo() {
		return housingDistrictInfo;
	}
	public void setHousingDistrictInfo(HousingDistrictInfoVo housingDistrictInfo) {
		this.housingDistrictInfo = housingDistrictInfo;
	}
	
	
	

	
}
