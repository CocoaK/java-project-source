package com.smarthome.socket.service.vo;

import java.util.Date;

public class AlarmInfo {
	
@SuppressWarnings("unused")
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


    private Long alarmId;			//报警信息编号

    private String deviceCode;		//设备编号

    private String alarmType;		//报警类型

    private String status;			//报警状态

    private Date createdTime;	//创建时间

    private String content;		//报警内容

    private String houseNo;		//报警房号

    private Long ownerId;		//用户

    private Long pauserId;

    private Date hanlderTime;	//处理时间

    private Long comid;			//所属小区
    
    private String alarmName;	//报警名称
    
    private String userId;		//用户编号，查询用
    
    private String time;	//时间类型，d为本日，w,为本周，m为本月

    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType == null ? null : alarmType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo == null ? null : houseNo.trim();
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getPauserId() {
        return pauserId;
    }

    public void setPauserId(Long pauserId) {
        this.pauserId = pauserId;
    }

    public Date getHanlderTime() {
        return hanlderTime;
    }

    public void setHanlderTime(Date hanlderTime) {
        this.hanlderTime = hanlderTime;
    }

    public Long getComid() {
        return comid;
    }

    public void setComid(Long comid) {
        this.comid = comid;
    }

	public String getAlarmName() {
		return alarmName;
	}

	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}