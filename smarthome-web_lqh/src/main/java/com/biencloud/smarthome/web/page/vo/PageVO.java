package com.biencloud.smarthome.web.page.vo;

import java.util.List;
import javax.persistence.Entity;
import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 电商页面
 * @author Cocoa
 */
@SuppressWarnings("serial")
public class PageVO extends BaseVO{
	
	private String id;					//页面编号
	
	private String name;			//页面名称
	
	private String updateId;			//页面名称
	
	private String pageDesc;		//页面描述
	
	private List<String> districts;		//所属小区

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

	public String getPageDesc() {
		return pageDesc;
	}

	public void setPageDesc(String pageDesc) {
		this.pageDesc = pageDesc;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public List<String> getDistricts() {
		return districts;
	}

	public void setDistricts(List<String> districts) {
		this.districts = districts;
	}
}
