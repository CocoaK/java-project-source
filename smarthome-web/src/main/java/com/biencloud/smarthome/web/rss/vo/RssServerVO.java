package com.biencloud.smarthome.web.rss.vo;

import java.util.Date;
import com.biencloud.smarthome.web.base.vo.BaseVO;
import com.biencloud.smarthome.web.login.vo.LoginVO;

@SuppressWarnings("serial")
public class RssServerVO extends BaseVO{

	private Long rssId;			//编号
	private String rssServerCode; //rss服务编码
	private String rssName;		//名称
	private String status;		//状态
	private String createdUser;	//创建人
	private LoginVO userLogin;	//创建人姓名
	private Date createdTime;	//创建时间
	private String serverUrl;	//服务器地址
	
	public Long getRssId() {
		return rssId;
	}
	public void setRssId(Long rssId) {
		this.rssId = rssId;
	}
	public String getRssName() {
		return rssName;
	}
	public void setRssName(String rssName) {
		this.rssName = rssName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public LoginVO getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(LoginVO userLogin) {
		this.userLogin = userLogin;
	}
	public String getRssServerCode() {
		return rssServerCode;
	}
	public void setRssServerCode(String rssServerCode) {
		this.rssServerCode = rssServerCode;
	}
}
