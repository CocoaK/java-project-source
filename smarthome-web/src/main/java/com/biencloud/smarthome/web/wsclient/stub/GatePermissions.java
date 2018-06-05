
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for gatePermissions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="gatePermissions">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="beginTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="device" type="{http://service.cxfservice.smarthome.biencloud.com/}device" minOccurs="0"/>
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="gateCardId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gatePermissionsId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gatePermissions", propOrder = {
    "beginTime",
    "device",
    "endTime",
    "gateCardId",
    "gatePermissionsId"
})
public class GatePermissions
    extends BaseEntity
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar beginTime;
    protected Device device;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endTime;
    protected String gateCardId;
    protected String gatePermissionsId;

    /**
     * Gets the value of the beginTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBeginTime() {
        return beginTime;
    }

    /**
     * Sets the value of the beginTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBeginTime(XMLGregorianCalendar value) {
        this.beginTime = value;
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

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndTime(XMLGregorianCalendar value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the gateCardId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGateCardId() {
        return gateCardId;
    }

    /**
     * Sets the value of the gateCardId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGateCardId(String value) {
        this.gateCardId = value;
    }

    /**
     * Gets the value of the gatePermissionsId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGatePermissionsId() {
        return gatePermissionsId;
    }

    /**
     * Sets the value of the gatePermissionsId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGatePermissionsId(String value) {
        this.gatePermissionsId = value;
    }

}
