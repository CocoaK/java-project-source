
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for chargeStatitics complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="chargeStatitics">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="acTotalCustomer" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="acTotalMoney" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="buildingName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargetTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerPercent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="feeTotalMoney" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="monetaryUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="moneyPercent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalCustomer" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="totalMoney" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="typeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chargeStatitics", propOrder = {
    "acTotalCustomer",
    "acTotalMoney",
    "buildingName",
    "chargetTime",
    "customerPercent",
    "feeTotalMoney",
    "monetaryUnit",
    "moneyPercent",
    "totalCustomer",
    "totalMoney",
    "typeName"
})
public class ChargeStatitics {

    protected Integer acTotalCustomer;
    protected Double acTotalMoney;
    protected String buildingName;
    protected String chargetTime;
    protected String customerPercent;
    protected Double feeTotalMoney;
    protected String monetaryUnit;
    protected String moneyPercent;
    protected Integer totalCustomer;
    protected Double totalMoney;
    protected String typeName;

    /**
     * Gets the value of the acTotalCustomer property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAcTotalCustomer() {
        return acTotalCustomer;
    }

    /**
     * Sets the value of the acTotalCustomer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAcTotalCustomer(Integer value) {
        this.acTotalCustomer = value;
    }

    /**
     * Gets the value of the acTotalMoney property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAcTotalMoney() {
        return acTotalMoney;
    }

    /**
     * Sets the value of the acTotalMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAcTotalMoney(Double value) {
        this.acTotalMoney = value;
    }

    /**
     * Gets the value of the buildingName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * Sets the value of the buildingName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildingName(String value) {
        this.buildingName = value;
    }

    /**
     * Gets the value of the chargetTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargetTime() {
        return chargetTime;
    }

    /**
     * Sets the value of the chargetTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargetTime(String value) {
        this.chargetTime = value;
    }

    /**
     * Gets the value of the customerPercent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerPercent() {
        return customerPercent;
    }

    /**
     * Sets the value of the customerPercent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerPercent(String value) {
        this.customerPercent = value;
    }

    /**
     * Gets the value of the feeTotalMoney property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getFeeTotalMoney() {
        return feeTotalMoney;
    }

    /**
     * Sets the value of the feeTotalMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setFeeTotalMoney(Double value) {
        this.feeTotalMoney = value;
    }

    /**
     * Gets the value of the monetaryUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonetaryUnit() {
        return monetaryUnit;
    }

    /**
     * Sets the value of the monetaryUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonetaryUnit(String value) {
        this.monetaryUnit = value;
    }

    /**
     * Gets the value of the moneyPercent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoneyPercent() {
        return moneyPercent;
    }

    /**
     * Sets the value of the moneyPercent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoneyPercent(String value) {
        this.moneyPercent = value;
    }

    /**
     * Gets the value of the totalCustomer property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalCustomer() {
        return totalCustomer;
    }

    /**
     * Sets the value of the totalCustomer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalCustomer(Integer value) {
        this.totalCustomer = value;
    }

    /**
     * Gets the value of the totalMoney property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalMoney() {
        return totalMoney;
    }

    /**
     * Sets the value of the totalMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalMoney(Double value) {
        this.totalMoney = value;
    }

    /**
     * Gets the value of the typeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Sets the value of the typeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeName(String value) {
        this.typeName = value;
    }

}
