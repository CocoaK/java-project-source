package com.biencloud.smarthome.service.device.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * 
 * 类名称：DevicePassword 
 * 类描述： 设备开锁密码实体类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-8-3 下午3:11:46
 */

@Entity
@Table(name="t_device_password")
public class DevicePassword extends BaseEntity{

	private static final long serialVersionUID = 3280449775354985815L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;	//编号
	
	@Column(name="password")
	private String password;	//密码
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="device_id")
	private Device device;	//设备编号
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="target_device_id")
	private Device targetDevice;	//目标设备编号


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Device getTargetDevice() {
		return targetDevice;
	}

	public void setTargetDevice(Device targetDevice) {
		this.targetDevice = targetDevice;
	}

}
