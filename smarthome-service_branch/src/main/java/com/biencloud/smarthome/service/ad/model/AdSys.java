package com.biencloud.smarthome.service.ad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 广告投放目标系统实体类。
 * @author kouy
 * @since 1.0 2012-5-29
 * @see BaseEntity
 */
@Entity
@Table(name="t_ad_sys")
public class AdSys extends BaseEntity {

	private static final long serialVersionUID = 7609866919822337712L;

	@Id
	@Column(name="sys_code")
	private String sysCode;//广告投放目标系统代码

	@Column(name="sys_name")
	private String sysName;//广告投放目标系统名称
	
	@Column(name="sys_desc")
	private String sysDesc;//广告投放目标系统描述

	
	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getSysDesc() {
		return sysDesc;
	}

	public void setSysDesc(String sysDesc) {
		this.sysDesc = sysDesc;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-1526566363, 735784419)
				.append(this.sysCode).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AdSys)) {
			return false;
		}
		AdSys as = (AdSys) object;
		return new EqualsBuilder()
				.append(this.sysCode, as.sysCode).isEquals();
	}
}
