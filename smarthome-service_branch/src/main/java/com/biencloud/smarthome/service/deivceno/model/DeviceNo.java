package com.biencloud.smarthome.service.deivceno.model;
// default package

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.device.model.Device;


/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：DeviceNo 
 * 类描述： 设备编号实体类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午11:55:56
 */
@Entity
@Table(name="t_device_no")
public class DeviceNo extends BaseEntity {
	
	private static final long serialVersionUID = -78256645258551886L;


    // Fields    
     //id
     private Long id;
     //设备对象，目前没有使用该字段
     private Device device;
     
     //设备编号
     private String deviceNo;
     //完整房号地址
     private String remark;
     //小区id
     private String areaId;


	/** default constructor */
    public DeviceNo() {
    }

	/** minimal constructor */
    public DeviceNo(Long id) {
        this.id = id;
    }
    
    @Transient
    public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
    
    /** full constructor */
    public DeviceNo(Long id, Device device, String deviceNo, String remark) {
        this.id = id;
        this.device = device;
        this.deviceNo = deviceNo;
        this.remark = remark;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    @OneToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="deviceId")
    public Device getDevice() {
        return this.device;
    }
    
    public void setDevice(Device device) {
        this.device = device;
    }
    
    @Column(name="deviceNo", length=20)

    public String getDeviceNo() {
        return this.deviceNo;
    }
    
    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
    
    @Column(name="remark", length=200)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   








}