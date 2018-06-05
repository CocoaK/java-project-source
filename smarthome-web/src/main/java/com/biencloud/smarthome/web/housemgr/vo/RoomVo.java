package com.biencloud.smarthome.web.housemgr.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 房间VO
 * 
 * @author jsun
 * @since 1.0 2012-6-20
 */
public class RoomVo extends BaseVO {
	private static final long serialVersionUID = -6670716819364041215L;

	private String id;//房间ID
	private int orderIndex;//房间排序的索引值
	private String name;//房间名称
	private String plan;//房间平面图
	private String sizeId;//户型ID

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public int getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	
	public String getSizeId() {
		return sizeId;
	}
	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}
}
