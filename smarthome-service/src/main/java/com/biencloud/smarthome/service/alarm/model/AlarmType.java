package com.biencloud.smarthome.service.alarm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * 
 * 类名称：AlarmType 
 * 类描述： 报警类型实体对象
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-23 上午11:04:07
 */
@Entity
@Table(name="t_alarm_type")
public class AlarmType extends BaseEntity{

	private static final long serialVersionUID = -6438374956144978488L;
	@Id
	@Column(name="alarm_type")
	private String alarmType;//报警类型
	
	@Column(name="alarm_name")
	private String alarmName;//报警名称
	
	@Column(name="alarm_desc")
	private String alarmDesc;//报警描述

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public String getAlarmName() {
		return alarmName;
	}

	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}

	public String getAlarmDesc() {
		return alarmDesc;
	}

	public void setAlarmDesc(String alarmDesc) {
		this.alarmDesc = alarmDesc;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.alarmType)
				.toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AlarmType)) {
			return false;
		}
		AlarmType at = (AlarmType) object;
		return new EqualsBuilder()
				.append(this.alarmType, at.alarmType)
				.isEquals();
	}
}
