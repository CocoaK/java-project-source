
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for infoReceiver complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="infoReceiver">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="areaId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="houseId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="infoSendId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="receiverType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="receviceUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "infoReceiver", propOrder = {
    "areaId",
    "houseId",
    "id",
    "infoSendId",
    "receiverType",
    "receviceUserName",
    "remark",
    "status"
})
public class InfoReceiver
    extends BaseEntity
{

    protected Long areaId;
    protected Long houseId;
    protected Long id;
    protected Long infoSendId;
    protected Integer receiverType;
    protected String receviceUserName;
    protected String remark;
    protected Integer status;

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
     * Gets the value of the houseId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHouseId() {
        return houseId;
    }

    /**
     * Sets the value of the houseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHouseId(Long value) {
        this.houseId = value;
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

    /**
     * Gets the value of the infoSendId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInfoSendId() {
        return infoSendId;
    }

    /**
     * Sets the value of the infoSendId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInfoSendId(Long value) {
        this.infoSendId = value;
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

}
