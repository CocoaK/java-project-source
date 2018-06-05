package com.biencloud.smarthome.service.rss.vo;

import java.util.Date;

import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * 
 * 类名称：RssVO 
 * 类描述：RSS类 
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-18 下午3:54:51
 */
public class RssVO extends BaseEntity{
	//标题
	private String title;
	//访问路径地址
	private String url;
	//描述
	private String description;
	//发布时间
	private Date publishTime;
	//发布者
	private String publisher;
	
    
	public RssVO() {
		super();
	}

	public RssVO(String title, String url, String description, Date publishTime, String publisher) {
		super();
		this.title = title;
		this.url = url;
		this.description = description;
		this.publishTime = publishTime;
		this.publisher = publisher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

}
