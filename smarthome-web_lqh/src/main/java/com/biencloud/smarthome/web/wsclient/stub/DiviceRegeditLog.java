
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for diviceRegeditLog complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="diviceRegeditLog">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="addEndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="addStartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="addTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="buildingName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cellName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="districtName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="diveceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventAciton" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hourseNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="macAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="siteNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "diviceRegeditLog", propOrder = {
    "addEndTime",
    "addStartTime",
    "addTime",
    "buildingName",
    "cellName",
    "deviceNo",
    "districtName",
    "diveceType",
    "eventAciton",
    "hourseNo",
    "id",
    "location",
    "macAddr",
    "name",
    "regionName",
    "siteNo",
    "userIp"
})
public class DiviceRegeditLog
    extends BaseEntity
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar addEndTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar addStartTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar addTime;
    protected String buildingName;
    protected String cellName;
    protected String deviceNo;
    protected String districtName;
    protected String diveceType;
    protected String eventAciton;
    protected String hourseNo;
    protected Long id;
    protected String location;
    protected String macAddr;
    protected String name;
    protected String regionName;
    protected String siteNo;
    protected String userIp;

    /**
     * Gets the value of the addEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAddEndTime() {
        return addEndTime;
    }

    /**
     * Sets the value of the addEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAddEndTime(XMLGregorianCalendar value) {
        this.addEndTime = value;
    }

    /**
     * Gets the value of the addStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAddStartTime() {
        return addStartTime;
    }

    /**
     * Sets the value of the addStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAddStartTime(XMLGregorianCalendar value) {
        this.addStartTime = value;
    }

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
     * Gets the value of the buildingName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * Sets the value of the buildingName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildingName(String value) {
        this.buildingName = value;
    }

    /**
     * Gets the value of the cellName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCellName() {
        return cellName;
    }

    /**
     * Sets the value of the cellName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCellName(String value) {
        this.cellName = value;
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
     * Gets the value of the districtName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * Sets the value of the districtName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrictName(String value) {
        this.districtName = value;
    }

    /**
     * Gets the value of the diveceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiveceType() {
        return diveceType;
    }

    /**
     * Sets the value of the diveceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiveceType(String value) {
        this.diveceType = value;
    }

    /**
     * Gets the value of the eventAciton property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventAciton() {
        return eventAciton;
    }

    /**
     * Sets the value of the eventAciton property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventAciton(String value) {
        this.eventAciton = value;
    }

    /**
     * Gets the value of the hourseNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHourseNo() {
        return hourseNo;
    }

    /**
     * Sets the value of the hourseNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHourseNo(String value) {
        this.hourseNo = value;
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
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the macAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMacAddr() {
        return macAddr;
    }

    /**
     * Sets the value of the macAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMacAddr(String value) {
        this.macAddr = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the regionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * Sets the value of the regionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegionName(String value) {
        this.regionName = value;
    }

    /**
     * Gets the value of the siteNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiteNo() {
        return siteNo;
    }

    /**
     * Sets the value of the siteNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiteNo(String value) {
        this.siteNo = value;
    }

    /**
     * Gets the value of the userIp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserIp() {
        return userIp;
    }

    /**
     * Sets the value of the userIp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserIp(String value) {
        this.userIp = value;
    }

}
