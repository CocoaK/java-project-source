package com.smarthome.socket.service.business.adapater;

import com.smarthome.socket.service.business.adapater.json.RspJson;

/**
 * 
 * 类名称：PushRsp 类描述：推送实体类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-7-23 上午11:05:47
 */
public class PushRsp extends RspJson {
	// 推送名称
	private String pushName;
	// 推送内容
	private String pushContent;
	// 推送版本
	private String pushVersion;
	// 推送设备编号
	private String pushClientId;
	// 文件大小
	private String size;
	// 描述
	private String description;

	public PushRsp() {
		super();
	}

	public PushRsp(String pushName, String pushContent, String pushVersion, String pushClientId, String size, String description) {
		super();
		this.pushName = pushName;
		this.pushContent = pushContent;
		this.pushVersion = pushVersion;
		this.pushClientId = pushClientId;
		this.size = size;
		this.description = description;
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

	public String getPushVersion() {
		return pushVersion;
	}

	public void setPushVersion(String pushVersion) {
		this.pushVersion = pushVersion;
	}

	public String getPushClientId() {
		return pushClientId;
	}

	public void setPushClientId(String pushClientId) {
		this.pushClientId = pushClientId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
