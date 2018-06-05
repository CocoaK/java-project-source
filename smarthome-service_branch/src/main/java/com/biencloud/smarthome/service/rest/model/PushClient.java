package com.biencloud.smarthome.service.rest.model;

public class PushClient {
	
	private String title;
	private String content1;
	private String content2;
	private String content3;
	private String content4;
	private Long deviceId;
	private String excludeToken;
	//设防状态
	private String status;
	
	public PushClient(){}
	
	public PushClient(String title,String content1,String content2,String content3,String content4,Long deviceId,String excludeToken){
		this.title = title;
		this.content1 = content1;
		this.content2 = content2;
		this.content3 = content3;
		this.content4 = content4;
		this.deviceId = deviceId;
		this.excludeToken = excludeToken;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent1() {
		return content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public String getContent2() {
		return content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public String getContent3() {
		return content3;
	}

	public void setContent3(String content3) {
		this.content3 = content3;
	}

	public String getContent4() {
		return content4;
	}

	public void setContent4(String content4) {
		this.content4 = content4;
	}

	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExcludeToken() {
		return excludeToken;
	}

	public void setExcludeToken(String excludeToken) {
		this.excludeToken = excludeToken;
	}

}
