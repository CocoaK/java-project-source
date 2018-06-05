package com.biencloud.smarthome.web.page.vo;

import java.util.Date;
import com.biencloud.smarthome.web.base.vo.BaseVO;

@SuppressWarnings("serial")
public class PageContentVO extends BaseVO{
	private String updateId;
	private String content;
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
