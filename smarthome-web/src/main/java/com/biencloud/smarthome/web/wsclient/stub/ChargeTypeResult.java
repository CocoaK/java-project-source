
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for chargeTypeResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="chargeTypeResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="actualTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="calMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="calUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargeDataId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="chargeDetailId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="chargeTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="playMoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="standard" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chargeTypeResult", propOrder = {
    "actualTotal",
    "calMode",
    "calUnit",
    "chargeDataId",
    "chargeDetailId",
    "chargeTypeName",
    "endTotal",
    "id",
    "playMoney",
    "remark",
    "standard",
    "startTotal"
})
public class ChargeTypeResult
    extends BaseEntity
{

    protected String actualTotal;
    protected String calMode;
    protected String calUnit;
    protected Long chargeDataId;
    protected Long chargeDetailId;
    protected String chargeTypeName;
    protected String endTotal;
    protected Long id;
    protected String playMoney;
    protected String remark;
    protected String standard;
    protected String startTotal;

    /**
     * Gets the value of the actualTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActualTotal() {
        return actualTotal;
    }

    /**
     * Sets the value of the actualTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActualTotal(String value) {
        this.actualTotal = value;
    }

    /**
     * Gets the value of the calMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalMode() {
        return calMode;
    }

    /**
     * Sets the value of the calMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalMode(String value) {
        this.calMode = value;
    }

    /**
     * Gets the value of the calUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalUnit() {
        return calUnit;
    }

    /**
     * Sets the value of the calUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalUnit(String value) {
        this.calUnit = value;
    }

    /**
     * Gets the value of the chargeDataId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getChargeDataId() {
        return chargeDataId;
    }

    /**
     * Sets the value of the chargeDataId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setChargeDataId(Long value) {
        this.chargeDataId = value;
    }

    /**
     * Gets the value of the chargeDetailId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getChargeDetailId() {
        return chargeDetailId;
    }

    /**
     * Sets the value of the chargeDetailId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setChargeDetailId(Long value) {
        this.chargeDetailId = value;
    }

    /**
     * Gets the value of the chargeTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeTypeName() {
        return chargeTypeName;
    }

    /**
     * Sets the value of the chargeTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeTypeName(String value) {
        this.chargeTypeName = value;
    }

    /**
     * Gets the value of the endTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndTotal() {
        return endTotal;
    }

    /**
     * Sets the value of the endTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndTotal(String value) {
        this.endTotal = value;
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
     * Gets the value of the playMoney property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlayMoney() {
        return playMoney;
    }

    /**
     * Sets the value of the playMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlayMoney(String value) {
        this.playMoney = value;
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

    /**
     * Gets the value of the startTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartTotal() {
        return startTotal;
    }

    /**
     * Sets the value of the startTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartTotal(String value) {
        this.startTotal = value;
    }

}
