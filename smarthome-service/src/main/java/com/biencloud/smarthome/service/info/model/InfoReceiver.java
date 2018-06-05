package com.biencloud.smarthome.service.info.model;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：InfoReceiver 
 * 类描述： 信息接收实体类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午2:03:37
 */
@Entity
@Table(name = "tb_infomation_receiver")
public class InfoReceiver extends BaseEntity {
	private static final long serialVersionUID = -78214244851886L;
	/** 标识信息接收为不可读的  */
	public static final Integer STATUSNOREAD=0;
	/** 标识未读状态 */
	public static final Integer STATUSNO=1;
	/** 标识已读状态 */
	public static final Integer STATUSYES=2;
	/** 个人信息 */
	public static final Integer INFO_TYPE_PERSON=0;
	/** 小区信息 */
	public static final Integer INFO_TYPE_COMMUNITY=1;
	/** 系统信息 */
	public static final Integer INFO_TYPE_SYSTEM=2;

	// Fields

	private Long id;
	private Long infoSendId;//信息发布ID
	private Long houseId;//最早是用户ID，现在已改为房间
	private String receviceUserName;//接收用户名
	private Long areaId;//小区ID
	private Integer receiverType;//接收类型
	private Integer status;//状态
	private String remark;//描述

	// Constructors

	/** default constructor */
	public InfoReceiver() {
	}

	/** full constructor */
	public InfoReceiver(InfoSend infoSend, Long userId, String receviceUserName, Long areaId, Integer receiverType, Integer status,
			String remark) {
		this.receviceUserName = receviceUserName;
		this.areaId = areaId;
		this.receiverType = receiverType;
		this.status = status;
		this.remark = remark;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "info_id")
	public InfoSend getInfoSend() {
		return this.infoSend;
	}

	public void setInfoSend(InfoSend infoSend) {
		this.infoSend = infoSend;
	}*/
	
	
	@Column(name = "info_id")
	public Long getInfoSendId() {
		return infoSendId;
	}

	public void setInfoSendId(Long infoSendId) {
		this.infoSendId = infoSendId;
	}

	@Column(name = "house_id")
	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	@Column(name = "recevice_user_name", length = 10)
	public String getReceviceUserName() {
		return this.receviceUserName;
	}

	public void setReceviceUserName(String receviceUserName) {
		this.receviceUserName = receviceUserName;
	}

	@Column(name = "area_id")
	public Long getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	@Column(name = "receiver_type")
	public Integer getReceiverType() {
		return this.receiverType;
	}

	public void setReceiverType(Integer receiverType) {
		this.receiverType = receiverType;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public int hashCode() {
		if(this.id==null){
			return new HashCodeBuilder(-1959941635, 1952620985).append(this.houseId).toHashCode();
		}else
		return new HashCodeBuilder(-1959941635, 1952620985).append(this.id).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof InfoReceiver)) {
			return false;
		}
		InfoReceiver infoReceiver = (InfoReceiver) object;
		if(this.id==null){
			return new EqualsBuilder().append(this.houseId, infoReceiver.houseId).isEquals();
		}else
		return new EqualsBuilder().append(this.id, infoReceiver.id).isEquals();
	}
}