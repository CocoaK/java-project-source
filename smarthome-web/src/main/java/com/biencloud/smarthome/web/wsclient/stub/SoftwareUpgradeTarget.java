
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for softwareUpgradeTarget complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="softwareUpgradeTarget">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="deviceType" type="{http://service.cxfservice.smarthome.biencloud.com/}deviceType" minOccurs="0"/>
 *         &lt;element name="housingDistrictInfo" type="{http://service.cxfservice.smarthome.biencloud.com/}housingDistrictInfo" minOccurs="0"/>
 *         &lt;element name="softwareId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="suTargetId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "softwareUpgradeTarget", propOrder = {
    "deviceType",
    "housingDistrictInfo",
    "softwareId",
    "suTargetId"
})
public class SoftwareUpgradeTarget
    extends BaseEntity
{

    protected DeviceType deviceType;
    protected HousingDistrictInfo housingDistrictInfo;
    protected String softwareId;
    protected String suTargetId;

    /**
     * Gets the value of the deviceType property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceType }
     *     
     */
    public DeviceType getDeviceType() {
        return deviceType;
    }

    /**
     * Sets the value of the deviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceType }
     *     
     */
    public void setDeviceType(DeviceType value) {
        this.deviceType = value;
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
     * Gets the value of the softwareId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoftwareId() {
        return softwareId;
    }

    /**
     * Sets the value of the softwareId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoftwareId(String value) {
        this.softwareId = value;
    }

    /**
     * Gets the value of the suTargetId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuTargetId() {
        return suTargetId;
    }

    /**
     * Sets the value of the suTargetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuTargetId(String value) {
        this.suTargetId = value;
    }

}
