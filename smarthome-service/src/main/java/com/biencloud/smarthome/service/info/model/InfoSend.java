package com.biencloud.smarthome.service.info.model;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：InfoSend 
 * 类描述：信息发布实体类 
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午2:04:04
 */
@Entity
@Table(name = "tb_information_send")
public class InfoSend extends BaseEntity {
	
	private static final long serialVersionUID = -7825658441886L;
	
	/** 标识未审核 */
	public static final Integer STATUSNOAUDIT=1;
	/** 标识审核通过 */
	public static final Integer STATUSNOAUDIT_THROUGH=2;
	/** 标识审核不通过 */
	public static final Integer STATUSNOAUDIT_NOTHROUGH=3;
	/** 标识未发布状态 */
	public static final Integer STATUSNOSEND=4;
	/** 标识已发布 */
	public static final Integer STATUSYESSEND=5;
	/** 马上发布 */
	public static final Integer SENDMODE_GOING=1;
	/** 定时发布 */
	public static final Integer SENDMODE_TIMIMG=2;

	// Fields

	private Long id;
	private String title;//标题
	private String content;//内容
	private Date sendTime;//发送时间
	private Date sendStartTime;//发送开始时间
	private Date sendEndTime;//发送结束时间
	private Integer sendMode;//发送模式
	private String sendOb;//发布对象
	private Date timimgTime;//定时发送时间
	private Date timimgTimeStartTime;//定时发送开始时间
	private Date timimgTimeEndTime;//定时发送结束时间
	private Integer status;//状态
	private Long sendUserId;//发送用户ID
	private String sendUserName;//发送用户名
	private Long areaId;//所属小区ID
	private Integer type;//信息类型
	private String remark;//描述
	private Set<InfoReceiver> infoReceivers = new HashSet<InfoReceiver>(0);//信息接收列表
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	private String reply;//回复内容

	// Constructors

	/** default constructor */
	public InfoSend() {
	}
	
	
	@Transient
	public Date getSendStartTime() {
		return sendStartTime;
	}



	public void setSendStartTime(Date sendStartTime) {
		this.sendStartTime = sendStartTime;
	}


	@Transient
	public Date getSendEndTime() {
		return sendEndTime;
	}



	public void setSendEndTime(Date sendEndTime) {
		this.sendEndTime = sendEndTime;
	}


	@Transient
	public Date getTimimgTimeStartTime() {
		return timimgTimeStartTime;
	}



	public void setTimimgTimeStartTime(Date timimgTimeStartTime) {
		this.timimgTimeStartTime = timimgTimeStartTime;
	}


	@Transient
	public Date getTimimgTimeEndTime() {
		return timimgTimeEndTime;
	}



	public void setTimimgTimeEndTime(Date timimgTimeEndTime) {
		this.timimgTimeEndTime = timimgTimeEndTime;
	}



	/** minimal constructor */
	public InfoSend(String title, String content, Integer status) {
		this.title = title;
		this.content = content;
		this.status = status;
	}

	/** full constructor */
	public InfoSend(String title, String content, Date sendTime, Integer sendMode, String sendOb, Date timimgTime,
			Integer status, Long sendUserId, String sendUserName, Long areaId, Integer type, String remark, Set<InfoReceiver> infoReceivers) {
		this.title = title;
		this.content = content;
		this.sendTime = sendTime;
		this.sendMode = sendMode;
		this.sendOb = sendOb;
		this.timimgTime = timimgTime;
		this.status = status;
		this.sendUserId = sendUserId;
		this.sendUserName = sendUserName;
		this.areaId = areaId;
		this.type = type;
		this.remark = remark;
		this.infoReceivers = infoReceivers;
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

	@Column(name = "title", nullable = false, length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "send_time", length = 19)
	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "send_mode")
	public Integer getSendMode() {
		return this.sendMode;
	}

	public void setSendMode(Integer sendMode) {
		this.sendMode = sendMode;
	}

	@Column(name = "sendOb", length = 30)
	public String getSendOb() {
		return this.sendOb;
	}

	public void setSendOb(String sendOb) {
		this.sendOb = sendOb;
	}

	@Column(name = "timimg_time", length = 19)
	public Date getTimimgTime() {
		return this.timimgTime;
	}

	public void setTimimgTime(Date timimgTime) {
		this.timimgTime = timimgTime;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "send_user_id")
	public Long getSendUserId() {
		return this.sendUserId;
	}

	public void setSendUserId(Long sendUserId) {
		this.sendUserId = sendUserId;
	}

	@Column(name = "send_user_name", length = 10)
	public String getSendUserName() {
		return this.sendUserName;
	}

	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}

	@Column(name = "areaId")
	public Long getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="info_id")
	public Set<InfoReceiver> getInfoReceivers() {
		return this.infoReceivers;
	}

	public void setInfoReceivers(Set<InfoReceiver> infoReceivers) {
		this.infoReceivers = infoReceivers;
	}
	
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time")
	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "reply")
	public String getReply() {
		return reply;
	}


	public void setReply(String reply) {
		this.reply = reply;
	}
	
	

}