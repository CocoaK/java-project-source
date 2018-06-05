
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for complaint complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="complaint">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="appId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="complaintDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="complaintLogin" type="{http://service.cxfservice.smarthome.biencloud.com/}login" minOccurs="0"/>
 *         &lt;element name="complaintLoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="districtId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="houseId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="onlyValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="processingDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="processingLogin" type="{http://service.cxfservice.smarthome.biencloud.com/}login" minOccurs="0"/>
 *         &lt;element name="processingLoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="processingStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="suggestion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "complaint", propOrder = {
    "appId",
    "complaintDate",
    "complaintLogin",
    "complaintLoginName",
    "content",
    "deviceNo",
    "districtId",
    "houseId",
    "id",
    "location",
    "onlyValue",
    "processingDate",
    "processingLogin",
    "processingLoginName",
    "processingStatus",
    "suggestion",
    "title",
    "type"
})
public class Complaint
    extends BaseEntity
{

    protected Integer appId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar complaintDate;
    protected Login complaintLogin;
    protected String complaintLoginName;
    protected String content;
    protected String deviceNo;
    protected String districtId;
    protected String houseId;
    protected String id;
    protected String location;
    protected String onlyValue;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar processingDate;
    protected Login processingLogin;
    protected String processingLoginName;
    protected String processingStatus;
    protected String suggestion;
    protected String title;
    protected String type;

    /**
     * Gets the value of the appId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * Sets the value of the appId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAppId(Integer value) {
        this.appId = value;
    }

    /**
     * Gets the value of the complaintDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getComplaintDate() {
        return complaintDate;
    }

    /**
     * Sets the value of the complaintDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setComplaintDate(XMLGregorianCalendar value) {
        this.complaintDate = value;
    }

    /**
     * Gets the value of the complaintLogin property.
     * 
     * @return
     *     possible object is
     *     {@link Login }
     *     
     */
    public Login getComplaintLogin() {
        return complaintLogin;
    }

    /**
     * Sets the value of the complaintLogin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Login }
     *     
     */
    public void setComplaintLogin(Login value) {
        this.complaintLogin = value;
    }

    /**
     * Gets the value of the complaintLoginName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComplaintLoginName() {
        return complaintLoginName;
    }

    /**
     * Sets the value of the complaintLoginName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplaintLoginName(String value) {
        this.complaintLoginName = value;
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
     * Gets the value of the houseId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseId() {
        return houseId;
    }

    /**
     * Sets the value of the houseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseId(String value) {
        this.houseId = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
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
     * Gets the value of the onlyValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnlyValue() {
        return onlyValue;
    }

    /**
     * Sets the value of the onlyValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnlyValue(String value) {
        this.onlyValue = value;
    }

    /**
     * Gets the value of the processingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProcessingDate() {
        return processingDate;
    }

    /**
     * Sets the value of the processingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProcessingDate(XMLGregorianCalendar value) {
        this.processingDate = value;
    }

    /**
     * Gets the value of the processingLogin property.
     * 
     * @return
     *     possible object is
     *     {@link Login }
     *     
     */
    public Login getProcessingLogin() {
        return processingLogin;
    }

    /**
     * Sets the value of the processingLogin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Login }
     *     
     */
    public void setProcessingLogin(Login value) {
        this.processingLogin = value;
    }

    /**
     * Gets the value of the processingLoginName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessingLoginName() {
        return processingLoginName;
    }

    /**
     * Sets the value of the processingLoginName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessingLoginName(String value) {
        this.processingLoginName = value;
    }

    /**
     * Gets the value of the processingStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessingStatus() {
        return processingStatus;
    }

    /**
     * Sets the value of the processingStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessingStatus(String value) {
        this.processingStatus = value;
    }

    /**
     * Gets the value of the suggestion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuggestion() {
        return suggestion;
    }

    /**
     * Sets the value of the suggestion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuggestion(String value) {
        this.suggestion = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
