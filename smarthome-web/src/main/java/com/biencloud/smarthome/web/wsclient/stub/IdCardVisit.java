
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for idCardVisit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="idCardVisit">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="beginTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="deviceAlias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="districtId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idCard" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="visitId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="visitTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="visitorAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="visitorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "idCardVisit", propOrder = {
    "beginTime",
    "deviceAlias",
    "deviceCode",
    "deviceId",
    "districtId",
    "endTime",
    "gender",
    "idCard",
    "reason",
    "visitId",
    "visitTime",
    "visitorAddress",
    "visitorName"
})
public class IdCardVisit
    extends BaseEntity
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar beginTime;
    protected String deviceAlias;
    protected String deviceCode;
    protected String deviceId;
    protected String districtId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endTime;
    protected String gender;
    protected String idCard;
    protected String reason;
    protected String visitId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar visitTime;
    protected String visitorAddress;
    protected String visitorName;

    /**
     * Gets the value of the beginTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBeginTime() {
        return beginTime;
    }

    /**
     * Sets the value of the beginTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBeginTime(XMLGregorianCalendar value) {
        this.beginTime = value;
    }

    /**
     * Gets the value of the deviceAlias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceAlias() {
        return deviceAlias;
    }

    /**
     * Sets the value of the deviceAlias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceAlias(String value) {
        this.deviceAlias = value;
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
     * Gets the value of the deviceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * Sets the value of the deviceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceId(String value) {
        this.deviceId = value;
    }

    /**
     * Gets the value of the districtId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrictId() {
        return districtId;
    }

    /**
     * Sets the value of the districtId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrictId(String value) {
        this.districtId = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndTime(XMLGregorianCalendar value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Gets the value of the idCard property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * Sets the value of the idCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCard(String value) {
        this.idCard = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * Gets the value of the visitId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisitId() {
        return visitId;
    }

    /**
     * Sets the value of the visitId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisitId(String value) {
        this.visitId = value;
    }

    /**
     * Gets the value of the visitTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVisitTime() {
        return visitTime;
    }

    /**
     * Sets the value of the visitTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVisitTime(XMLGregorianCalendar value) {
        this.visitTime = value;
    }

    /**
     * Gets the value of the visitorAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisitorAddress() {
        return visitorAddress;
    }

    /**
     * Sets the value of the visitorAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisitorAddress(String value) {
        this.visitorAddress = value;
    }

    /**
     * Gets the value of the visitorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisitorName() {
        return visitorName;
    }

    /**
     * Sets the value of the visitorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisitorName(String value) {
        this.visitorName = value;
    }

}
