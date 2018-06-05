
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryChargeMonetaryUnitForPaging complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryChargeMonetaryUnitForPaging">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://service.cxfservice.smarthome.biencloud.com/}chargeMonetaryUnit" minOccurs="0"/>
 *         &lt;element name="arg1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="arg2" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryChargeMonetaryUnitForPaging", propOrder = {
    "arg0",
    "arg1",
    "arg2"
})
public class QueryChargeMonetaryUnitForPaging {

    protected ChargeMonetaryUnit arg0;
    protected int arg1;
    protected int arg2;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link ChargeMonetaryUnit }
     *     
     */
    public ChargeMonetaryUnit getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeMonetaryUnit }
     *     
     */
    public void setArg0(ChargeMonetaryUnit value) {
        this.arg0 = value;
    }

    /**
     * Gets the value of the arg1 property.
     * 
     */
    public int getArg1() {
        return arg1;
    }

    /**
     * Sets the value of the arg1 property.
     * 
     */
    public void setArg1(int value) {
        this.arg1 = value;
    }

    /**
     * Gets the value of the arg2 property.
     * 
     */
    public int getArg2() {
        return arg2;
    }

    /**
     * Sets the value of the arg2 property.
     * 
     */
    public void setArg2(int value) {
        this.arg2 = value;
    }

}
