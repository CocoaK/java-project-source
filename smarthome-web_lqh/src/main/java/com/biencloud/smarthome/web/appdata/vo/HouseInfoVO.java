package com.biencloud.smarthome.web.appdata.vo;

import com.biencloud.smarthome.web.appdata.json.Json;

/**
 * 
 * 类名称：CompanyInfo 类描述：房产信息类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-14 上午8:53:58
 */
public class HouseInfoVO extends Json{

	private String roomNo; // 房号
	private String unit; // 单元
	private String houseStructure; // 房间结构
	private String structureChartPath; // 结构图
	private float buildAcreage; // 建筑面积
    
	
	public HouseInfoVO() {
		super();
	}

	public HouseInfoVO(String roomNo, String unit, String houseStructure, String structureChartPath, float buildAcreage) {
		super();
		this.roomNo = roomNo;
		this.unit = unit;
		this.houseStructure = houseStructure;
		this.structureChartPath = structureChartPath;
		this.buildAcreage = buildAcreage;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getHouseStructure() {
		return houseStructure;
	}

	public void setHouseStructure(String houseStructure) {
		this.houseStructure = houseStructure;
	}

	public String getStructureChartPath() {
		return structureChartPath;
	}

	public void setStructureChartPath(String structureChartPath) {
		this.structureChartPath = structureChartPath;
	}

	public float getBuildAcreage() {
		return buildAcreage;
	}

	public void setBuildAcreage(float buildAcreage) {
		this.buildAcreage = buildAcreage;
	}

}
