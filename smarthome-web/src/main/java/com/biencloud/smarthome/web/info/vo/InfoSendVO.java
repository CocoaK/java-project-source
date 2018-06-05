
package com.biencloud.smarthome.web.info.vo;

import java.util.Date;
import java.util.List;

import com.biencloud.smarthome.web.base.vo.BaseVO;
/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：InfoSendVO 
 * 类描述：信息发布VO类 
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午2:05:36
 */
public class InfoSendVO extends BaseVO {
	
	private static final long serialVersionUID = -4908040635074482646L;
	/**  标识未审核 */
	public static final Integer STATUSNOAUDIT=1;
	/** 标识审核通过  */
	public static final Integer STATUSNOAUDIT_THROUGH=2;
	/**  标识审核不通过 */
	public static final Integer STATUSNOAUDIT_NOTHROUGH=3;
	/** 标识未发布状态  */
	public static final Integer STATUSNOSEND=4;
	/**  标识已发布 */
	public static final Integer STATUSYESSEND=5;
	/**  马上发布 */
	public static final Integer SENDMODE_GOING=1;
	/**  定时发布 */
	public static final Integer SENDMODE_TIMIMG=2;

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
	private List<InfoReceiverVO> infoReceivers;//信息接收列表
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	private String reply;//回复内容
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<InfoReceiverVO> getInfoReceivers() {
		return infoReceivers;
	}
	public void setInfoReceivers(List<InfoReceiverVO> infoReceivers) {
		this.infoReceivers = infoReceivers;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getSendMode() {
		return sendMode;
	}
	public void setSendMode(Integer sendMode) {
		this.sendMode = sendMode;
	}
	public String getSendOb() {
		return sendOb;
	}
	public void setSendOb(String sendOb) {
		this.sendOb = sendOb;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Long getSendUserId() {
		return sendUserId;
	}
	public void setSendUserId(Long sendUserId) {
		this.sendUserId = sendUserId;
	}
	public String getSendUserName() {
		return sendUserName;
	}
	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getTimimgTime() {
		return timimgTime;
	}
	public void setTimimgTime(Date timimgTime) {
		this.timimgTime = timimgTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getSendStartTime() {
		return sendStartTime;
	}
	public void setSendStartTime(Date sendStartTime) {
		this.sendStartTime = sendStartTime;
	}
	public Date getSendEndTime() {
		return sendEndTime;
	}
	public void setSendEndTime(Date sendEndTime) {
		this.sendEndTime = sendEndTime;
	}
	public Date getTimimgTimeStartTime() {
		return timimgTimeStartTime;
	}
	public void setTimimgTimeStartTime(Date timimgTimeStartTime) {
		this.timimgTimeStartTime = timimgTimeStartTime;
	}
	public Date getTimimgTimeEndTime() {
		return timimgTimeEndTime;
	}
	public void setTimimgTimeEndTime(Date timimgTimeEndTime) {
		this.timimgTimeEndTime = timimgTimeEndTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
    
	
	
    


}
