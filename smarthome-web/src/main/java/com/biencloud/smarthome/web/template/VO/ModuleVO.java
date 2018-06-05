package com.biencloud.smarthome.web.template.VO;

import com.biencloud.smarthome.web.base.vo.BaseVO;

@SuppressWarnings("serial")
public class ModuleVO extends BaseVO{
	private String moduleId;		//组件ID
	private String type;			//组件类型：按钮，图片等
	private String topModuleId;		//上部组件ID
	private String leftModuleId;	//左边组件ID
	private String imageUrl;		//资源地址
	private String linkUrl;		//链接地址
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTopModuleId() {
		return topModuleId;
	}
	public void setTopModuleId(String topModuleId) {
		this.topModuleId = topModuleId;
	}
	public String getLeftModuleId() {
		return leftModuleId;
	}
	public void setLeftModuleId(String leftModuleId) {
		this.leftModuleId = leftModuleId;
	}

	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

}
