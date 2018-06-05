
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for room complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="room">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="plan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sizeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "room", propOrder = {
    "id",
    "name",
    "orderIndex",
    "plan",
    "sizeId"
})
public class Room
    extends BaseEntity
{

    protected String id;
    protected String name;
    protected int orderIndex;
    protected String plan;
    protected String sizeId;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the orderIndex property.
     * 
     */
    public int getOrderIndex() {
        return orderIndex;
    }

    /**
     * Sets the value of the orderIndex property.
     * 
     */
    public void setOrderIndex(int value) {
        this.orderIndex = value;
    }

    /**
     * Gets the value of the plan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlan() {
        return plan;
    }

    /**
     * Sets the value of the plan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlan(String value) {
        this.plan = value;
    }

    /**
     * Gets the value of the sizeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSizeId() {
        return sizeId;
    }

    /**
     * Sets the value of the sizeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSizeId(String value) {
        this.sizeId = value;
    }

}
