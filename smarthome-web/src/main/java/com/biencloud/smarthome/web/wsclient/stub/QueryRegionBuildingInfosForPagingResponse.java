
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryRegionBuildingInfosForPagingResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryRegionBuildingInfosForPagingResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.cxfservice.smarthome.biencloud.com/}paging" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryRegionBuildingInfosForPagingResponse", propOrder = {
    "_return"
})
public class QueryRegionBuildingInfosForPagingResponse {

    @XmlElement(name = "return")
    protected Paging _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link Paging }
     *     
     */
    public Paging getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link Paging }
     *     
     */
    public void setReturn(Paging value) {
        this._return = value;
    }

}
