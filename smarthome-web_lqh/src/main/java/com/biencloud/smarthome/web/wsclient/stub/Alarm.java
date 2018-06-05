
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for alarm complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="alarm">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="alarmEndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="alarmId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="alarmStartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="alarmType" type="{http://service.cxfservice.smarthome.biencloud.com/}alarmType" minOccurs="0"/>
 *         &lt;element name="cancelAndNoHanlder" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="deviceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="handlerEndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="handlerStartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="hanlderTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="houseNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="housingDistrictInfo" type="{http://service.cxfservice.smarthome.biencloud.com/}housingDistrictInfo" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ownerUser" type="{http://service.cxfservice.smarthome.biencloud.com/}ownerUser" minOccurs="0"/>
 *         &lt;element name="paUser" type="{http://service.cxfservice.smarthome.biencloud.com/}paUser" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "alarm", propOrder = {
    "alarmEndTime",
    "alarmId",
    "alarmStartTime",
    "alarmType",
    "cancelAndNoHanlder",
    "content",
    "createdTime",
    "deviceCode",
    "handlerEndTime",
    "handlerStartTime",
    "hanlderTime",
    "houseNo",
    "housingDistrictInfo",
    "status",
    "ownerUser",
    "paUser"
})
public class Alarm
    extends BaseEntity
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar alarmEndTime;
    protected String alarmId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar alarmStartTime;
    protected AlarmType alarmType;
    protected boolean cancelAndNoHanlder;
    protected String content;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String deviceCode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar handlerEndTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar handlerStartTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar hanlderTime;
    protected String houseNo;
    protected HousingDistrictInfo housingDistrictInfo;
    protected String status;
    protected OwnerUser ownerUser;
    protected PaUser paUser;

    /**
     * Gets the value of the alarmEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAlarmEndTime() {
        return alarmEndTime;
    }

    /**
     * Sets the value of the alarmEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAlarmEndTime(XMLGregorianCalendar value) {
        this.alarmEndTime = value;
    }

    /**
     * Gets the value of the alarmId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlarmId() {
        return alarmId;
    }

    /**
     * Sets the value of the alarmId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlarmId(String value) {
        this.alarmId = value;
    }

    /**
     * Gets the value of the alarmStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAlarmStartTime() {
        return alarmStartTime;
    }

    /**
     * Sets the value of the alarmStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAlarmStartTime(XMLGregorianCalendar value) {
        this.alarmStartTime = value;
    }

    /**
     * Gets the value of the alarmType property.
     * 
     * @return
     *     possible object is
     *     {@link AlarmType }
     *     
     */
    public AlarmType getAlarmType() {
        return alarmType;
    }

    /**
     * Sets the value of the alarmType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlarmType }
     *     
     */
    public void setAlarmType(AlarmType value) {
        this.alarmType = value;
    }

    /**
     * Gets the value of the cancelAndNoHanlder property.
     * 
     */
    public boolean isCancelAndNoHanlder() {
        return cancelAndNoHanlder;
    }

    /**
     * Sets the value of the cancelAndNoHanlder property.
     * 
     */
    public void setCancelAndNoHanlder(boolean value) {
        this.cancelAndNoHanlder = value;
    }

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the createdTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedTime() {
        return createdTime;
    }

    /**
     * Sets the value of the createdTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedTime(XMLGregorianCalendar value) {
        this.createdTime = value;
    }

    /**
     * Gets the value of the deviceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceCode() {
        return deviceCode;
    }

    /**
     * Sets the value of the deviceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceCode(String value) {
        this.deviceCode = value;
    }

    /**
     * Gets the value of the handlerEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getHandlerEndTime() {
        return handlerEndTime;
    }

    /**
     * Sets the value of the handlerEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setHandlerEndTime(XMLGregorianCalendar value) {
        this.handlerEndTime = value;
    }

    /**
     * Gets the value of the handlerStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getHandlerStartTime() {
        return handlerStartTime;
    }

    /**
     * Sets the value of the handlerStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setHandlerStartTime(XMLGregorianCalendar value) {
        this.handlerStartTime = value;
    }

    /**
     * Gets the value of the hanlderTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getHanlderTime() {
        return hanlderTime;
    }

    /**
     * Sets the value of the hanlderTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setHanlderTime(XMLGregorianCalendar value) {
        this.hanlderTime = value;
    }

    /**
     * Gets the value of the houseNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseNo() {
        return houseNo;
    }

    /**
     * Sets the value of the houseNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseNo(String value) {
        this.houseNo = value;
    }

    /**
     * Gets the value of the housingDistrictInfo property.
     * 
     * @return
     *     possible object is
     *     {@link HousingDistrictInfo }
     *     
     */
    public HousingDistrictInfo getHousingDistrictInfo() {
        return housingDistrictInfo;
    }

    /**
     * Sets the value of the housingDistrictInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link HousingDistrictInfo }
     *     
     */
    public void setHousingDistrictInfo(HousingDistrictInfo value) {
        this.housingDistrictInfo = value;
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

    /**
     * Gets the value of the ownerUser property.
     * 
     * @return
     *     possible object is
     *     {@link OwnerUser }
     *     
     */
    public OwnerUser getOwnerUser() {
        return ownerUser;
    }

    /**
     * Sets the value of the ownerUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link OwnerUser }
     *     
     */
    public void setOwnerUser(OwnerUser value) {
        this.ownerUser = value;
    }

    /**
     * Gets the value of the paUser property.
     * 
     * @return
     *     possible object is
     *     {@link PaUser }
     *     
     */
    public PaUser getPaUser() {
        return paUser;
    }

    /**
     * Sets the value of the paUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaUser }
     *     
     */
    public void setPaUser(PaUser value) {
        this.paUser = value;
    }

}
