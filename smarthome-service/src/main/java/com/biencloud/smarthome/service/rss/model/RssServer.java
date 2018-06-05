package com.biencloud.smarthome.service.rss.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.login.model.Login;

/**
 * 类名称：RssServer 
 * 类描述： Rss服务器实体
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-6 上午10:50:05
 */
@Entity
@Table(name="t_rss_server")
public class RssServer extends BaseEntity{

	private static final long serialVersionUID = 5656010840469831286L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rss_id")
	private Long rssId;			//编号
	@Column(name="rss_code")
	private String rssServerCode;//rss服务编码
	@Column(name="rss_name")
	private String rssName;		//名称
	@Column(name="status")
	private String status;		//状态
	@Column(name="created_user")
	private String createdUser;	//创建人userId
	@Transient
	private Login userLogin;	//创建人登录信息
	@Column(name="created_time")
	private Date createdTime;	//创建时间
	@Column(name="server_url")
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
	public Login getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(Login userLogin) {
		this.userLogin = userLogin;
	}
	public String getRssServerCode() {
		return rssServerCode;
	}
	public void setRssServerCode(String rssServerCode) {
		this.rssServerCode = rssServerCode;
	}

}
