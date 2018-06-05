
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for chargeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="chargeType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="chargeCalMode" type="{http://service.cxfservice.smarthome.biencloud.com/}chargeCalMode" minOccurs="0"/>
 *         &lt;element name="chargeCalUnit" type="{http://service.cxfservice.smarthome.biencloud.com/}chargeCalUnit" minOccurs="0"/>
 *         &lt;element name="chargeMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargeMonetaryUnit" type="{http://service.cxfservice.smarthome.biencloud.com/}chargeMonetaryUnit" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="housingDistrictInfo" type="{http://service.cxfservice.smarthome.biencloud.com/}housingDistrictInfo" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paUser" type="{http://service.cxfservice.smarthome.biencloud.com/}paUser" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="standard" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chargeType", propOrder = {
    "chargeCalMode",
    "chargeCalUnit",
    "chargeMode",
    "chargeMonetaryUnit",
    "createTime",
    "housingDistrictInfo",
    "id",
    "name",
    "paUser",
    "remark",
    "standard"
})
public class ChargeType
    extends BaseEntity
{

    protected ChargeCalMode chargeCalMode;
    protected ChargeCalUnit chargeCalUnit;
    protected String chargeMode;
    protected ChargeMonetaryUnit chargeMonetaryUnit;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTime;
    protected HousingDistrictInfo housingDistrictInfo;
    protected Long id;
    protected String name;
    protected PaUser paUser;
    protected String remark;
    protected String standard;

    /**
     * Gets the value of the chargeCalMode property.
     * 
     * @return
     *     possible object is
     *     {@link ChargeCalMode }
     *     
     */
    public ChargeCalMode getChargeCalMode() {
        return chargeCalMode;
    }

    /**
     * Sets the value of the chargeCalMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeCalMode }
     *     
     */
    public void setChargeCalMode(ChargeCalMode value) {
        this.chargeCalMode = value;
    }

    /**
     * Gets the value of the chargeCalUnit property.
     * 
     * @return
     *     possible object is
     *     {@link ChargeCalUnit }
     *     
     */
    public ChargeCalUnit getChargeCalUnit() {
        return chargeCalUnit;
    }

    /**
     * Sets the value of the chargeCalUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeCalUnit }
     *     
     */
    public void setChargeCalUnit(ChargeCalUnit value) {
        this.chargeCalUnit = value;
    }

    /**
     * Gets the value of the chargeMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeMode() {
        return chargeMode;
    }

    /**
     * Sets the value of the chargeMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeMode(String value) {
        this.chargeMode = value;
    }

    /**
     * Gets the value of the chargeMonetaryUnit property.
     * 
     * @return
     *     possible object is
     *     {@link ChargeMonetaryUnit }
     *     
     */
    public ChargeMonetaryUnit getChargeMonetaryUnit() {
        return chargeMonetaryUnit;
    }

    /**
     * Sets the value of the chargeMonetaryUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeMonetaryUnit }
     *     
     */
    public void setChargeMonetaryUnit(ChargeMonetaryUnit value) {
        this.chargeMonetaryUnit = value;
    }

    /**
     * Gets the value of the createTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateTime() {
        return createTime;
    }

    /**
     * Sets the value of the createTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateTime(XMLGregorianCalendar value) {
        this.createTime = value;
    }

    /**
     * Gets the value of the housingDistrictInfo property.
     * 
     * @return
     *     possible object is
     *     {@link HousingDistrictInfo }
     *     
     */
    public HousingDistrictInfo getHousingDistrictInfo() {
        return housingDistrictInfo;
    }

    /**
     * Sets the value of the housingDistrictInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link HousingDistrictInfo }
     *     
     */
    public void setHousingDistrictInfo(HousingDistrictInfo value) {
        this.housingDistrictInfo = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
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
     * Gets the value of the paUser property.
     * 
     * @return
     *     possible object is
     *     {@link PaUser }
     *     
     */
    public PaUser getPaUser() {
        return paUser;
    }

    /**
     * Sets the value of the paUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaUser }
     *     
     */
    public void setPaUser(PaUser value) {
        this.paUser = value;
    }

    /**
     * Gets the value of the remark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the value of the remark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * Gets the value of the standard property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStandard() {
        return standard;
    }

    /**
     * Sets the value of the standard property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStandard(String value) {
        this.standard = value;
    }

}
