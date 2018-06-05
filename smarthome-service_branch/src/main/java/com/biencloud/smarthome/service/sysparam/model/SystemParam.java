package com.biencloud.smarthome.service.sysparam.model;

import javax.persistence.*;

import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 系统参数实体对象。
 * @author kouy
 * @since 1.0 2012-4-9
 */
@Entity
@Table(name="t_system_param")
public class SystemParam extends BaseEntity {
	
	private static final long serialVersionUID = 3660171770401759876L;

	
	@Id
	@Column(name="param_code")
	private String paramCode;//参数代码

	@Column(name="param_name")
	private String paramName;//参数名称
	
	@Column(name="param_desc")
	private String paramDesc;//参数描述

	@Column(name="param_value")
	private String paramValue;//参数值

	@Column(name="updated_time", insertable=false)
	private Date updatedTime;//修改时间

	@Column(name="updated_user", insertable=false)
	private String updatedUser;//修改用户的登录帐号


	public String getParamCode() {
		return this.paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	public String getParamDesc() {
		return this.paramDesc;
	}
	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	public String getParamValue() {
		return this.paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public Date getUpdatedTime() {
		return this.updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedUser() {
		return this.updatedUser;
	}
	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SystemParam)) {
			return false;
		}
		SystemParam sp = (SystemParam) object;
		return new EqualsBuilder()
				.append(this.paramCode, sp.paramCode).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(724609027, -1162285637)
				.append(this.paramCode).toHashCode();
	}		
}