package com.biencloud.smarthome.web.log.vo;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.biencloud.smarthome.web.base.vo.BaseVO;

import java.util.Date;

/**
 * 类名称：SystemLogVO 
 * 类描述： 系统日志实体对象。
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-3 上午11:43:45
 */

public class SystemLogVO extends BaseVO{
	
	private static final long serialVersionUID = 1457241064762590571L;
	//日志编号
	private String logId;
	//错误位置
	private String errorLocation;
	//错误信息
	private String errorMsg;
	//菜单代码
	private String menuCode;
	//操作时间
	private Date operateTime;
	//操作用户
	private String operateUser;
	//操作代码
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
		if (!(object instanceof SystemLogVO)) {
			return false;
		}
		SystemLogVO sl = (SystemLogVO) object;
		return new EqualsBuilder()
				.append(this.logId, sl.logId).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(1074356323, 1593502165)
				.append(this.logId).toHashCode();
	}

}