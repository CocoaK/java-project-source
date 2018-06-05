package com.biencloud.smarthome.web.page.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 电商页面组件
 * @author Cocoa
 */
@SuppressWarnings("serial")
public class Component extends BaseVO{
	private String id;					//元件编号
	
	private String name;			//元件名称
	
	private String type;				//元件类型1：link,2:action
	
	private String groups;				//组1：title，2：content	
	
	private String pageId;				//页面编号
	
	private String url;				//链接地址
					
	private String image;			//图片地址
	
	private String belowOfId;			//位于哪个元素下面
	
	private String rightOfId;			//位于哪个元素右边

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBelowOfId() {
		return belowOfId;
	}

	public void setBelowOfId(String belowOfId) {
		this.belowOfId = belowOfId;
	}

	public String getRightOfId() {
		return rightOfId;
	}

	public void setRightOfId(String rightOfId) {
		this.rightOfId = rightOfId;
	}

}
