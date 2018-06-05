package com.biencloud.smarthome.web.log.vo;

import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 类名称：OperationLogVO 
 * 类描述：用户操作日志实体类。 
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-3 上午11:39:01
 */
public class OperationLogVO extends BaseVO {

	private static final long serialVersionUID = -4099136261513453328L;
	//日志编号
	private String logId;
	//访问客户端ip
	private String ip;
	//菜单代码
	private String menuCode;
	//操作结果
	private int operateResult;
	//操作时间
	private Date operateTime;
	//操作用户
	private String operateUser;
	//操作代码
	private String operationCode;
	//备注
	private String remark;
	//开始时间
	private Date beginTime;
	//结束时间
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
		if (!(object instanceof OperationLogVO)) {
			return false;
		}
		OperationLogVO ol = (OperationLogVO) object;
		return new EqualsBuilder()
				.append(this.logId, ol.logId).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(1186244755, 1853778761)
				.append(this.logId).toHashCode();
	}
}