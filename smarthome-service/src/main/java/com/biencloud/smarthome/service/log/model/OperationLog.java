package com.biencloud.smarthome.service.log.model;

import java.util.Date;

import javax.persistence.*;

import com.biencloud.smarthome.service.base.model.BaseEntity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


/**
 * 用户操作日志实体类。
 * @author kouy
 * @since 1.0 2012-4-11
 */
@Entity
@Table(name="t_operation_log")
public class OperationLog extends BaseEntity {

	private static final long serialVersionUID = 2482066205981569973L;
	//日志编号
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="log_id")
	private String logId;
	//ip
	@Column(name="ip")
	private String ip;
	//菜单代码
	@Column(name="menu_code")
	private String menuCode;
	//操作结果
	@Column(name="operate_result")
	private int operateResult;
	//操作时间
	@Column(name="operate_time")
	private Date operateTime;
	//操作用户
	@Column(name="operate_user")
	private String operateUser;
	//操作代码
	@Column(name="operation_code")
	private String operationCode;
	//备注
	@Column(name="remark")
	private String remark;
	//开始时间
	@Transient
	private Date beginTime;
	//结束时间
	@Transient
	private Date endTime;

	public String getLogId() {
		return this.logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getIp() {
		return this.ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMenuCode() {
		return this.menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public int getOperateResult() {
		return operateResult;
	}
	public void setOperateResult(int operateResult) {
		this.operateResult = operateResult;
	}
	public Date getOperateTime() {
		return this.operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateUser() {
		return this.operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}

	public String getOperationCode() {
		return this.operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof OperationLog)) {
			return false;
		}
		OperationLog ol = (OperationLog) object;
		return new EqualsBuilder()
				.append(this.logId, ol.logId).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(1186244755, 1853778761)
				.append(this.logId).toHashCode();
	}
}