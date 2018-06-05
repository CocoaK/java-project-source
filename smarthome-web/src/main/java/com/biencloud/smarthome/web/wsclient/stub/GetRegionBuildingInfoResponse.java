
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getRegionBuildingInfoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getRegionBuildingInfoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.cxfservice.smarthome.biencloud.com/}regionBuildingInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRegionBuildingInfoResponse", propOrder = {
    "_return"
})
public class GetRegionBuildingInfoResponse {

    @XmlElement(name = "return")
    protected RegionBuildingInfo _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link RegionBuildingInfo }
     *     
     */
    public RegionBuildingInfo getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegionBuildingInfo }
     *     
     */
    public void setReturn(RegionBuildingInfo value) {
        this._return = value;
    }

}
