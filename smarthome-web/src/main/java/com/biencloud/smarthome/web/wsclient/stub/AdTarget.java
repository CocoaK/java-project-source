
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for adTarget complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="adTarget">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="adId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adTargetId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="device" type="{http://service.cxfservice.smarthome.biencloud.com/}device" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adTarget", propOrder = {
    "adId",
    "adTargetId",
    "device"
})
public class AdTarget
    extends BaseEntity
{

    protected String adId;
    protected String adTargetId;
    protected Device device;

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
     * Gets the value of the adTargetId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdTargetId() {
        return adTargetId;
    }

    /**
     * Sets the value of the adTargetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdTargetId(String value) {
        this.adTargetId = value;
    }

    /**
     * Gets the value of the device property.
     * 
     * @return
     *     possible object is
     *     {@link Device }
     *     
     */
    public Device getDevice() {
        return device;
    }

    /**
     * Sets the value of the device property.
     * 
     * @param value
     *     allowed object is
     *     {@link Device }
     *     
     */
    public void setDevice(Device value) {
        this.device = value;
    }

}
