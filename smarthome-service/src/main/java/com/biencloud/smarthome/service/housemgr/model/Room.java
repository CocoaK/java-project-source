package com.biencloud.smarthome.service.housemgr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * 房间信息 实体类
 * 
 * @author jsun
 * @since 1.0 2012-6-20
 */
@Entity
@Table(name="t_hm_room")
public class Room extends BaseEntity {
	private static final long serialVersionUID = -4539003015741083013L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	/**
	 * 房间ID
	 */
	private String id;

	/**
	 * 房间排序的索引值
	 */
	@Column(name="ORDER_INDEX")
	private int orderIndex;
	
	/**
	 * 房间名称
	 */
	private String name;

	/**
	 * 房间平面图
	 */
	private String plan;
	
	/**
	 * 户型ID
	 */
	@Column(name="SIZE_ID")
	private String sizeId;

	
	public String getId() {
		return this.id;
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
	
	/**
	 * 房间平面图
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 房间平面图
	 * 
	 * @return 相对URL地址
	 */
	public String getPlan() {
		return this.plan;
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