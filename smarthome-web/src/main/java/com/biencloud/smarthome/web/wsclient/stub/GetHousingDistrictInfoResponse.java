
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getHousingDistrictInfoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getHousingDistrictInfoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.cxfservice.smarthome.biencloud.com/}housingDistrictInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getHousingDistrictInfoResponse", propOrder = {
    "_return"
})
public class GetHousingDistrictInfoResponse {

    @XmlElement(name = "return")
    protected HousingDistrictInfo _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link HousingDistrictInfo }
     *     
     */
    public HousingDistrictInfo getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link HousingDistrictInfo }
     *     
     */
    public void setReturn(HousingDistrictInfo value) {
        this._return = value;
    }

}
