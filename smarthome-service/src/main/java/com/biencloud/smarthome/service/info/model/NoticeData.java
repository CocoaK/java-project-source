package com.biencloud.smarthome.service.info.model;

import java.util.Date;

import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * 小区公告数据类
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class NoticeData extends BaseEntity{
	
	private Long infoId;			//信息编号
	private String title;			//标题
	private String content;			//内容
	private Date publishTime;		//发布时间
	private String publishBy;		//发布者
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public String getPublishBy() {
		return publishBy;
	}
	public void setPublishBy(String publishBy) {
		this.publishBy = publishBy;
	}
	public Long getInfoId() {
		return infoId;
	}
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

}
