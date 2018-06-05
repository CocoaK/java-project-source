package com.biencloud.smarthome.service.device.model;

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

/**
 * 设备留言实体对象。
 * @author Cocoa
 * @since 1.0 2012-5-16
 */
@Entity
@Table(name="t_call_record")
public class CallRecord extends BaseEntity{

	private static final long serialVersionUID = -4511271957812428734L;
	
	//编号
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private String id;
	//通话编号
	@Column(name="call_id")
	private String callId;
	//设备实体
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="device_id")
	private Device device;
	//设备编号
	@Column(name="device_code")
	private String deviceCode;
	//通话类型
	@Column(name="call_type")
	private String callType;
	//通话人
	@Column(name="caller")
	private String caller;
	//通话时间
	@Column(name="call_time")
	private Date callTime;
	//通话时长
	@Column(name="talk_time")
	private long talkTime;
	//视频本地路径
	@Column(name="video_local_path")
	private String videoLocalPath;
	//视频网络路径
	@Column(name="video_net_path")
	private String videoNetPath;
	//音频网络路径
	@Column(name="audio_net_path")
	private String audioNetPath;
	//音频本地路径
	@Column(name="audio_local_path")
	private String audioLocalPath;
	//图片网络路径
	@Column(name="picture_net_path")
	private String pictureNetPath;
	//图片本地路径
	@Column(name="picture_local_path")
	private String pictureLocalPath;
	
	@Transient
	private Date beginTime;
	@Transient
	private Date endTime;
	public String getCallId() {
		return callId;
	}
	public void setCallId(String callId) {
		this.callId = callId;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
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
	public String getAudioNetPath() {
		return audioNetPath;
	}
	public void setAudioNetPath(String audioNetPath) {
		this.audioNetPath = audioNetPath;
	}
	public String getAudioLocalPath() {
		return audioLocalPath;
	}
	public void setAudioLocalPath(String audioLocalPath) {
		this.audioLocalPath = audioLocalPath;
	}
	public String getPictureNetPath() {
		return pictureNetPath;
	}
	public void setPictureNetPath(String pictureNetPath) {
		this.pictureNetPath = pictureNetPath;
	}
	public String getPictureLocalPath() {
		return pictureLocalPath;
	}
	public void setPictureLocalPath(String pictureLocalPath) {
		this.pictureLocalPath = pictureLocalPath;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
