
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for alarmType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="alarmType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="alarmDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="alarmName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="alarmType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "alarmType", propOrder = {
    "alarmDesc",
    "alarmName",
    "alarmType"
})
public class AlarmType
    extends BaseEntity
{

    protected String alarmDesc;
    protected String alarmName;
    protected String alarmType;

    /**
     * Gets the value of the alarmDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlarmDesc() {
        return alarmDesc;
    }

    /**
     * Sets the value of the alarmDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlarmDesc(String value) {
        this.alarmDesc = value;
    }

    /**
     * Gets the value of the alarmName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlarmName() {
        return alarmName;
    }

    /**
     * Sets the value of the alarmName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlarmName(String value) {
        this.alarmName = value;
    }

    /**
     * Gets the value of the alarmType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlarmType() {
        return alarmType;
    }

    /**
     * Sets the value of the alarmType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlarmType(String value) {
        this.alarmType = value;
    }

}
