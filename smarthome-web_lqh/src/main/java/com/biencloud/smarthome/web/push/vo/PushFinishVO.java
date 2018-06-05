package com.biencloud.smarthome.web.push.vo;

import java.util.Date;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 
 * 类名称：PushFinishVO 类描述： 推送数据完成VO类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-11 下午3:09:59
 */
public class PushFinishVO extends BaseVO {
	//id,唯一性标识
	private Long id;
	//推送名称
	private String pushName;
	//推送内容
	private String pushContent;
	//推送类型
	private String pushKind;
	//添加时间
	private Date addTime;
	//推送完成时间
	private Date pushFinishTime;
	//推送终端设备编号
	private String pushClientId;
	//如果推送是文件，则该字段表示文件大小
	private Long size;
	//推送描述
	private String pushDescription;
	
	
	public PushFinishVO() {
		super();
	}
	public PushFinishVO(Long id, String pushName, String pushContent, String pushKind, Date addTime, Date pushFinishTime,
			String pushClientId, Long size, String pushDescription) {
		super();
		this.id = id;
		this.pushName = pushName;
		this.pushContent = pushContent;
		this.pushKind = pushKind;
		this.addTime = addTime;
		this.pushFinishTime = pushFinishTime;
		this.pushClientId = pushClientId;
		this.size = size;
		this.pushDescription = pushDescription;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPushName() {
		return pushName;
	}
	public void setPushName(String pushName) {
		this.pushName = pushName;
	}
	public String getPushContent() {
		return pushContent;
	}
	public void setPushContent(String pushContent) {
		this.pushContent = pushContent;
	}
	public String getPushKind() {
		return pushKind;
	}
	public void setPushKind(String pushKind) {
		this.pushKind = pushKind;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getPushFinishTime() {
		return pushFinishTime;
	}
	public void setPushFinishTime(Date pushFinishTime) {
		this.pushFinishTime = pushFinishTime;
	}
	public String getPushClientId() {
		return pushClientId;
	}
	public void setPushClientId(String pushClientId) {
		this.pushClientId = pushClientId;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public String getPushDescription() {
		return pushDescription;
	}
	public void setPushDescription(String pushDescription) {
		this.pushDescription = pushDescription;
	}

	

}
