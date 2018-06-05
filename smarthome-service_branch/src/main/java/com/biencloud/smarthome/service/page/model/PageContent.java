package com.biencloud.smarthome.service.page.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.biencloud.smarthome.service.base.model.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_page_content")
public class PageContent extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="update_id")
	private String updateId;
	
	@Column(name="content")
	private String content;
	
	@Column(name="create_time")
	private Date createTime;
	


	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
