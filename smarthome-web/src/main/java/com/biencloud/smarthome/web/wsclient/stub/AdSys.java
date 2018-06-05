
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for adSys complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="adSys">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="sysCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sysDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sysName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adSys", propOrder = {
    "sysCode",
    "sysDesc",
    "sysName"
})
public class AdSys
    extends BaseEntity
{

    protected String sysCode;
    protected String sysDesc;
    protected String sysName;

    /**
     * Gets the value of the sysCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysCode() {
        return sysCode;
    }

    /**
     * Sets the value of the sysCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysCode(String value) {
        this.sysCode = value;
    }

    /**
     * Gets the value of the sysDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysDesc() {
        return sysDesc;
    }

    /**
     * Sets the value of the sysDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysDesc(String value) {
        this.sysDesc = value;
    }

    /**
     * Gets the value of the sysName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysName() {
        return sysName;
    }

    /**
     * Sets the value of the sysName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysName(String value) {
        this.sysName = value;
    }

}
