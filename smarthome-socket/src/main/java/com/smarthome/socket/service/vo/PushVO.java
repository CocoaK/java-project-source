package com.smarthome.socket.service.vo;


import java.util.Date;


/**
 * 
 * 类名称：PushVO 
 * 类描述：推送VO类 
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-9 下午3:40:11
 */
public class PushVO extends BaseVO {

	// Fields
    //id
	private Long id;
	//推送名称
	private String pushName;
	//推送类型
	private String pushKind;
	//推送内容
	private String pushContent;
	//推送版本
	private String pushVersion;
	//添加时间
	private Date addTime;
	//设备编号
	private String pushClientId;
	//文件大小
	private Long size;
	//描述
	private String pushDescription;
	
	
	public PushVO() {
		super();
	}

    

	public PushVO(Long id, String pushName, String pushKind, String pushContent, String pushVersion, Date addTime, String pushClientId,Long size,String pushDescription) {
		super();
		this.id = id;
		this.pushName = pushName;
		this.pushKind = pushKind;
		this.pushContent = pushContent;
		this.pushVersion = pushVersion;
		this.addTime = addTime;
		this.pushClientId = pushClientId;
		this.size=size;
		this.pushDescription=pushDescription;
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



	public String getPushKind() {
		return pushKind;
	}



	public void setPushKind(String pushKind) {
		this.pushKind = pushKind;
	}



	public String getPushContent() {
		return pushContent;
	}



	public void setPushContent(String pushContent) {
		this.pushContent = pushContent;
	}



	public String getPushVersion() {
		return pushVersion;
	}



	public void setPushVersion(String pushVersion) {
		this.pushVersion = pushVersion;
	}



	public Date getAddTime() {
		return addTime;
	}



	public void setAddTime(Date addTime) {
		this.addTime = addTime;
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