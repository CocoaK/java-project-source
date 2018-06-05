
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for infoReceiverDevice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="infoReceiverDevice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="areaIds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="builldingIds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cellIds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="districtIds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="infoSend" type="{http://service.cxfservice.smarthome.biencloud.com/}infoSend" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "infoReceiverDevice", propOrder = {
    "areaIds",
    "builldingIds",
    "cellIds",
    "deviceTypeId",
    "districtIds",
    "id",
    "infoSend",
    "status"
})
public class InfoReceiverDevice {

    protected String areaIds;
    protected String builldingIds;
    protected String cellIds;
    protected String deviceTypeId;
    protected String districtIds;
    protected Long id;
    protected InfoSend infoSend;
    protected String status;

    /**
     * Gets the value of the areaIds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaIds() {
        return areaIds;
    }

    /**
     * Sets the value of the areaIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaIds(String value) {
        this.areaIds = value;
    }

    /**
     * Gets the value of the builldingIds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuilldingIds() {
        return builldingIds;
    }

    /**
     * Sets the value of the builldingIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuilldingIds(String value) {
        this.builldingIds = value;
    }

    /**
     * Gets the value of the cellIds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCellIds() {
        return cellIds;
    }

    /**
     * Sets the value of the cellIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCellIds(String value) {
        this.cellIds = value;
    }

    /**
     * Gets the value of the deviceTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    /**
     * Sets the value of the deviceTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceTypeId(String value) {
        this.deviceTypeId = value;
    }

    /**
     * Gets the value of the districtIds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrictIds() {
        return districtIds;
    }

    /**
     * Sets the value of the districtIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrictIds(String value) {
        this.districtIds = value;
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
     * Gets the value of the infoSend property.
     * 
     * @return
     *     possible object is
     *     {@link InfoSend }
     *     
     */
    public InfoSend getInfoSend() {
        return infoSend;
    }

    /**
     * Sets the value of the infoSend property.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoSend }
     *     
     */
    public void setInfoSend(InfoSend value) {
        this.infoSend = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
