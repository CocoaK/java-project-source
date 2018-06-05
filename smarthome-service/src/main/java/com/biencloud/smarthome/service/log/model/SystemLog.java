package com.biencloud.smarthome.service.log.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import org.apache.commons.lang3.builder.HashCodeBuilder;


/**
 * 系统日志实体对象。
 * @author kouy
 * @since 1.0 2012-4-9
 */
@Entity
@Table(name="t_system_log")
public class SystemLog extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 5890635761830389495L;

	//编号
	@Id
	@Column(name="log_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String logId;
	//错误位置
	@Column(name="error_location")
	private String errorLocation;
	//错误信息
	@Column(name="error_msg")
	private String errorMsg;
	//菜单代码
	@Column(name="menu_code")
	private String menuCode;
	//操作时间
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="operate_time")
	private Date operateTime;
    //操作用户
	@Column(name="operate_user")
	private String operateUser;
	//操作代码
	@Column(name="operation_code")
	private String operationCode;	

	public String getLogId() {
		return this.logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getErrorLocation() {
		return this.errorLocation;
	}
	public void setErrorLocation(String errorLocation) {
		this.errorLocation = errorLocation;
	}

	public String getErrorMsg() {
		return this.errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getMenuCode() {
		return this.menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
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
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SystemLog)) {
			return false;
		}
		SystemLog sl = (SystemLog) object;
		return new EqualsBuilder()
				.append(this.logId, sl.logId).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(1074356323, 1593502165)
				.append(this.logId).toHashCode();
	}

}