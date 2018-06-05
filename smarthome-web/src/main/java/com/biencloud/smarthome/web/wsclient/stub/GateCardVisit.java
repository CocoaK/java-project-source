
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for gateCardVisit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="gateCardVisit">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="beginTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="cardNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceAlias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="districtId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="gateCardId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ownerIdCard" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ownerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="picPath1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="picPath2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="picPath3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="visitId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="visitTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gateCardVisit", propOrder = {
    "beginTime",
    "cardNo",
    "deviceAlias",
    "deviceCode",
    "deviceId",
    "districtId",
    "endTime",
    "gateCardId",
    "ownerIdCard",
    "ownerName",
    "picPath1",
    "picPath2",
    "picPath3",
    "visitId",
    "visitTime"
})
public class GateCardVisit
    extends BaseEntity
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar beginTime;
    protected String cardNo;
    protected String deviceAlias;
    protected String deviceCode;
    protected String deviceId;
    protected String districtId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endTime;
    protected String gateCardId;
    protected String ownerIdCard;
    protected String ownerName;
    protected String picPath1;
    protected String picPath2;
    protected String picPath3;
    protected String visitId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar visitTime;

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
     * Gets the value of the cardNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * Sets the value of the cardNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardNo(String value) {
        this.cardNo = value;
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
     * Gets the value of the gateCardId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGateCardId() {
        return gateCardId;
    }

    /**
     * Sets the value of the gateCardId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGateCardId(String value) {
        this.gateCardId = value;
    }

    /**
     * Gets the value of the ownerIdCard property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerIdCard() {
        return ownerIdCard;
    }

    /**
     * Sets the value of the ownerIdCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerIdCard(String value) {
        this.ownerIdCard = value;
    }

    /**
     * Gets the value of the ownerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Sets the value of the ownerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerName(String value) {
        this.ownerName = value;
    }

    /**
     * Gets the value of the picPath1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPicPath1() {
        return picPath1;
    }

    /**
     * Sets the value of the picPath1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPicPath1(String value) {
        this.picPath1 = value;
    }

    /**
     * Gets the value of the picPath2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPicPath2() {
        return picPath2;
    }

    /**
     * Sets the value of the picPath2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPicPath2(String value) {
        this.picPath2 = value;
    }

    /**
     * Gets the value of the picPath3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPicPath3() {
        return picPath3;
    }

    /**
     * Sets the value of the picPath3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPicPath3(String value) {
        this.picPath3 = value;
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

}
