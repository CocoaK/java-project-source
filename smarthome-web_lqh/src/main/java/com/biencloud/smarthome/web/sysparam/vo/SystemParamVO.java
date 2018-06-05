package com.biencloud.smarthome.web.sysparam.vo;

import java.util.Date;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 系统参数值对象。
 * @author kouy
 * @since 1.0 2012-4-9
 * @see BaseVO
 */
public class SystemParamVO extends BaseVO {

	private static final long serialVersionUID = 796264294348830283L;
	
	private String paramCode;//参数代码
	private String paramName;//参数名称
	private String paramValue;//ֵ参数值
	private String paramDesc;//参数描述
	private Date updatedTime;//修改时间
	private String updatedUser;//修改用户的登录帐号
	
	
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	public String getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}
	
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	
	public String getParamDesc() {
		return paramDesc;
	}
	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}
	
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(556458687, 1439524577)
				.append(this.paramCode).toHashCode();
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SystemParamVO)) {
			return false;
		}
		SystemParamVO spv = (SystemParamVO) object;
		return new EqualsBuilder()
				.append(this.paramCode, spv.paramCode).isEquals();
	}	
}
