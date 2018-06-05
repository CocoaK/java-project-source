
package com.biencloud.smarthome.web.wsclient.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for advertisement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="advertisement">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="adBeginTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="adDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adDetailPicUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adEndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="adId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adLinkUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adLocation" type="{http://service.cxfservice.smarthome.biencloud.com/}adLocation" minOccurs="0"/>
 *         &lt;element name="adName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adPicUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adSize" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adTargets" type="{http://service.cxfservice.smarthome.biencloud.com/}adTarget" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="adType" type="{http://service.cxfservice.smarthome.biencloud.com/}adType" minOccurs="0"/>
 *         &lt;element name="applyedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="applyedUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="publishedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="publishedUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "advertisement", propOrder = {
    "adBeginTime",
    "adDesc",
    "adDetailPicUrl",
    "adEndTime",
    "adId",
    "adLinkUrl",
    "adLocation",
    "adName",
    "adPicUrl",
    "adSize",
    "adTargets",
    "adType",
    "applyedTime",
    "applyedUser",
    "publishedTime",
    "publishedUser",
    "status"
})
public class Advertisement
    extends BaseEntity
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar adBeginTime;
    protected String adDesc;
    protected String adDetailPicUrl;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar adEndTime;
    protected String adId;
    protected String adLinkUrl;
    protected AdLocation adLocation;
    protected String adName;
    protected String adPicUrl;
    protected String adSize;
    @XmlElement(nillable = true)
    protected List<AdTarget> adTargets;
    protected AdType adType;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar applyedTime;
    protected String applyedUser;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar publishedTime;
    protected String publishedUser;
    protected String status;

    /**
     * Gets the value of the adBeginTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAdBeginTime() {
        return adBeginTime;
    }

    /**
     * Sets the value of the adBeginTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAdBeginTime(XMLGregorianCalendar value) {
        this.adBeginTime = value;
    }

    /**
     * Gets the value of the adDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdDesc() {
        return adDesc;
    }

    /**
     * Sets the value of the adDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdDesc(String value) {
        this.adDesc = value;
    }

    /**
     * Gets the value of the adDetailPicUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdDetailPicUrl() {
        return adDetailPicUrl;
    }

    /**
     * Sets the value of the adDetailPicUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdDetailPicUrl(String value) {
        this.adDetailPicUrl = value;
    }

    /**
     * Gets the value of the adEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAdEndTime() {
        return adEndTime;
    }

    /**
     * Sets the value of the adEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAdEndTime(XMLGregorianCalendar value) {
        this.adEndTime = value;
    }

    /**
     * Gets the value of the adId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdId() {
        return adId;
    }

    /**
     * Sets the value of the adId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdId(String value) {
        this.adId = value;
    }

    /**
     * Gets the value of the adLinkUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdLinkUrl() {
        return adLinkUrl;
    }

    /**
     * Sets the value of the adLinkUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdLinkUrl(String value) {
        this.adLinkUrl = value;
    }

    /**
     * Gets the value of the adLocation property.
     * 
     * @return
     *     possible object is
     *     {@link AdLocation }
     *     
     */
    public AdLocation getAdLocation() {
        return adLocation;
    }

    /**
     * Sets the value of the adLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdLocation }
     *     
     */
    public void setAdLocation(AdLocation value) {
        this.adLocation = value;
    }

    /**
     * Gets the value of the adName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdName() {
        return adName;
    }

    /**
     * Sets the value of the adName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdName(String value) {
        this.adName = value;
    }

    /**
     * Gets the value of the adPicUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdPicUrl() {
        return adPicUrl;
    }

    /**
     * Sets the value of the adPicUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdPicUrl(String value) {
        this.adPicUrl = value;
    }

    /**
     * Gets the value of the adSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdSize() {
        return adSize;
    }

    /**
     * Sets the value of the adSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdSize(String value) {
        this.adSize = value;
    }

    /**
     * Gets the value of the adTargets property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the adTargets property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdTargets().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdTarget }
     * 
     * 
     */
    public List<AdTarget> getAdTargets() {
        if (adTargets == null) {
            adTargets = new ArrayList<AdTarget>();
        }
        return this.adTargets;
    }

    /**
     * Gets the value of the adType property.
     * 
     * @return
     *     possible object is
     *     {@link AdType }
     *     
     */
    public AdType getAdType() {
        return adType;
    }

    /**
     * Sets the value of the adType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdType }
     *     
     */
    public void setAdType(AdType value) {
        this.adType = value;
    }

    /**
     * Gets the value of the applyedTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getApplyedTime() {
        return applyedTime;
    }

    /**
     * Sets the value of the applyedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setApplyedTime(XMLGregorianCalendar value) {
        this.applyedTime = value;
    }

    /**
     * Gets the value of the applyedUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplyedUser() {
        return applyedUser;
    }

    /**
     * Sets the value of the applyedUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplyedUser(String value) {
        this.applyedUser = value;
    }

    /**
     * Gets the value of the publishedTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPublishedTime() {
        return publishedTime;
    }

    /**
     * Sets the value of the publishedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPublishedTime(XMLGregorianCalendar value) {
        this.publishedTime = value;
    }

    /**
     * Gets the value of the publishedUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublishedUser() {
        return publishedUser;
    }

    /**
     * Sets the value of the publishedUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublishedUser(String value) {
        this.publishedUser = value;
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
