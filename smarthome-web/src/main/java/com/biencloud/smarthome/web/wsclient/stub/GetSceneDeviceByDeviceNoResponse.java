
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSceneDeviceByDeviceNoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSceneDeviceByDeviceNoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.cxfservice.smarthome.biencloud.com/}MapConvertor" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSceneDeviceByDeviceNoResponse", propOrder = {
    "_return"
})
public class GetSceneDeviceByDeviceNoResponse {

    @XmlElement(name = "return")
    protected MapConvertor _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link MapConvertor }
     *     
     */
    public MapConvertor getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link MapConvertor }
     *     
     */
    public void setReturn(MapConvertor value) {
        this._return = value;
    }

}
