
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for adLocation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="adLocation">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="adSys" type="{http://service.cxfservice.smarthome.biencloud.com/}adSys" minOccurs="0"/>
 *         &lt;element name="locationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locationCoordinate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adLocation", propOrder = {
    "adSys",
    "locationCode",
    "locationCoordinate",
    "locationName"
})
public class AdLocation
    extends BaseEntity
{

    protected AdSys adSys;
    protected String locationCode;
    protected String locationCoordinate;
    protected String locationName;

    /**
     * Gets the value of the adSys property.
     * 
     * @return
     *     possible object is
     *     {@link AdSys }
     *     
     */
    public AdSys getAdSys() {
        return adSys;
    }

    /**
     * Sets the value of the adSys property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdSys }
     *     
     */
    public void setAdSys(AdSys value) {
        this.adSys = value;
    }

    /**
     * Gets the value of the locationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationCode() {
        return locationCode;
    }

    /**
     * Sets the value of the locationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationCode(String value) {
        this.locationCode = value;
    }

    /**
     * Gets the value of the locationCoordinate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationCoordinate() {
        return locationCoordinate;
    }

    /**
     * Sets the value of the locationCoordinate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationCoordinate(String value) {
        this.locationCoordinate = value;
    }

    /**
     * Gets the value of the locationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * Sets the value of the locationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationName(String value) {
        this.locationName = value;
    }

}
