
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryHdfsTaskByIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryHdfsTaskByIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.cxfservice.smarthome.biencloud.com/}hdfsTask" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryHdfsTaskByIdResponse", propOrder = {
    "_return"
})
public class QueryHdfsTaskByIdResponse {

    @XmlElement(name = "return")
    protected HdfsTask _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link HdfsTask }
     *     
     */
    public HdfsTask getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link HdfsTask }
     *     
     */
    public void setReturn(HdfsTask value) {
        this._return = value;
    }

}
