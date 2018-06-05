package com.biencloud.smarthome.web.device.vo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.biencloud.smarthome.web.base.vo.BaseVO;
/**
 * 类名称：CallRecordVO 
 * 类描述： 通话记录实体VO
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-11-27 上午10:24:04
 */
public class CallRecordVO extends BaseVO{

	private static final long serialVersionUID = -5081879568863213546L;
	//编号
	private String id;
	//通话ID
	private String callId;
	//设备VO
	private DeviceVO device;
	//设备编号
	private String deviceCode;
	//通话类型
	private String callType;
	//通话人
	private String caller;
	//通话时间
	private Date callTime;
	//通话时长
	private long talkTime;
	//视频本地地址(app)
	private String videoLocalPath;
	//视频网络地址
	private String videoNetPath;
	//音频本地地址
	private String audioLocalPath;
	//音频网络地址
	private String audioNetPath;
	//抓拍图片本地地址
	private String pictureLocalPath;
	//抓拍图片网络地址
	private String pictureNetPath;
	//开始时间
	private Date beginTime;
	//结束时间
	private Date endTime;
	
	public String getCallId() {
		return callId;
	}
	public void setCallId(String callId) {
		this.callId = callId;
	}
	public DeviceVO getDevice() {
		return device;
	}
	public void setDevice(DeviceVO device) {
		this.device = device;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getCallType() {
		return callType;
	}
	public void setCallType(String callType) {
		this.callType = callType;
	}
	public String getCaller() {
		return caller;
	}
	public void setCaller(String caller) {
		this.caller = caller;
	}
	@JSON(format="yyyy-MM-dd hh:mm:ss")
	public Date getCallTime() {
		return callTime;
	}
	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}
	public long getTalkTime() {
		return talkTime;
	}
	public void setTalkTime(long talkTime) {
		this.talkTime = talkTime;
	}
	public String getVideoLocalPath() {
		return videoLocalPath;
	}
	public void setVideoLocalPath(String videoLocalPath) {
		this.videoLocalPath = videoLocalPath;
	}
	public String getVideoNetPath() {
		return videoNetPath;
	}
	public void setVideoNetPath(String videoNetPath) {
		this.videoNetPath = videoNetPath;
	}
	public String getAudioLocalPath() {
		return audioLocalPath;
	}
	public void setAudioLocalPath(String audioLocalPath) {
		this.audioLocalPath = audioLocalPath;
	}
	public String getAudioNetPath() {
		return audioNetPath;
	}
	public void setAudioNetPath(String audioNetPath) {
		this.audioNetPath = audioNetPath;
	}
	public String getPictureLocalPath() {
		return pictureLocalPath;
	}
	public void setPictureLocalPath(String pictureLocalPath) {
		this.pictureLocalPath = pictureLocalPath;
	}
	public String getPictureNetPath() {
		return pictureNetPath;
	}
	public void setPictureNetPath(String pictureNetPath) {
		this.pictureNetPath = pictureNetPath;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
