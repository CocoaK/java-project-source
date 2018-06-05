
package com.biencloud.smarthome.web.info.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：InfoReceiverVO 
 * 类描述：信息接收VO类 
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午2:05:55
 */
public class InfoReceiverVO extends BaseVO {
	
	private static final long serialVersionUID = -4179809771340076652L;
	/** 标识信息接收为不可读的 */
	public static final Integer STATUSNOREAD=0;
	/** 标识未读状态 */
	public static final Integer STATUSNO=1;
	/** 标识已读状态 */
	public static final Integer STATUSYES=2;
	/** 个人信息 */
	public static final Integer INFO_TYPE_PERSON=0;
	/** 小区信息 */
	public static final Integer INFO_TYPE_COMMUNITY=1;
	/** 系统信息 */
	public static final Integer INFO_TYPE_SYSTEM=2;

    private Long id;
	private Long houseId;//最早是用户ID，现在已改为房间
	private String receviceUserName;//接收用户名
	private Long areaId;//接收用户ID或者接收小区ID
	private Integer receiverType;//接收类型
	private Integer status;//状态
	private String remark;//描述
	private InfoSendVO infoSend;//信息发布实体对象

    /**
     * Gets the value of the areaId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAreaId() {
        return areaId;
    }

    /**
     * Sets the value of the areaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAreaId(Long value) {
        this.areaId = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }


    
    public InfoSendVO getInfoSend() {
		return infoSend;
	}

	public void setInfoSend(InfoSendVO infoSend) {
		this.infoSend = infoSend;
	}

	/**
     * Gets the value of the receiverType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getReceiverType() {
        return receiverType;
    }

    /**
     * Sets the value of the receiverType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setReceiverType(Integer value) {
        this.receiverType = value;
    }

    /**
     * Gets the value of the receviceUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceviceUserName() {
        return receviceUserName;
    }

    /**
     * Sets the value of the receviceUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceviceUserName(String value) {
        this.receviceUserName = value;
    }

    /**
     * Gets the value of the remark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the value of the remark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}


}
