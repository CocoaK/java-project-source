
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for clientLog complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="clientLog">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="areaName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="areaNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceMac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="launch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "clientLog", propOrder = {
    "addTime",
    "areaName",
    "areaNo",
    "dataContent",
    "dataType",
    "deviceMac",
    "deviceName",
    "deviceNo",
    "id",
    "ip",
    "launch"
})
public class ClientLog {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar addTime;
    protected String areaName;
    protected String areaNo;
    protected String dataContent;
    protected String dataType;
    protected String deviceMac;
    protected String deviceName;
    protected String deviceNo;
    protected Long id;
    protected String ip;
    protected String launch;

    /**
     * Gets the value of the addTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAddTime() {
        return addTime;
    }

    /**
     * Sets the value of the addTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAddTime(XMLGregorianCalendar value) {
        this.addTime = value;
    }

    /**
     * Gets the value of the areaName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * Sets the value of the areaName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaName(String value) {
        this.areaName = value;
    }

    /**
     * Gets the value of the areaNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaNo() {
        return areaNo;
    }

    /**
     * Sets the value of the areaNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaNo(String value) {
        this.areaNo = value;
    }

    /**
     * Gets the value of the dataContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataContent() {
        return dataContent;
    }

    /**
     * Sets the value of the dataContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataContent(String value) {
        this.dataContent = value;
    }

    /**
     * Gets the value of the dataType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Sets the value of the dataType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataType(String value) {
        this.dataType = value;
    }

    /**
     * Gets the value of the deviceMac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceMac() {
        return deviceMac;
    }

    /**
     * Sets the value of the deviceMac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceMac(String value) {
        this.deviceMac = value;
    }

    /**
     * Gets the value of the deviceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * Sets the value of the deviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceName(String value) {
        this.deviceName = value;
    }

    /**
     * Gets the value of the deviceNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceNo() {
        return deviceNo;
    }

    /**
     * Sets the value of the deviceNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceNo(String value) {
        this.deviceNo = value;
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
     * Gets the value of the ip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets the value of the ip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIp(String value) {
        this.ip = value;
    }

    /**
     * Gets the value of the launch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLaunch() {
        return launch;
    }

    /**
     * Sets the value of the launch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLaunch(String value) {
        this.launch = value;
    }

}
