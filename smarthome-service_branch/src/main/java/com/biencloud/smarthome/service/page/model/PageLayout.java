package com.biencloud.smarthome.service.page.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * 电商页面
 * @author Cocoa
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_page")
public class PageLayout extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private String id;					//元件编号
	
	@Column(name="name")
	private String name;			//元件名称
	
	@Column(name="update_id")
	private String updateId;		//更新编号
	
	@Column(name="page_desc")
	private String pageDesc;		//页面描述
	
	@Transient
	private List<String> districts;		//所属小区
	
	@Transient
	private String districtId;			//查询参数：所属小区
	
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

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	
}
