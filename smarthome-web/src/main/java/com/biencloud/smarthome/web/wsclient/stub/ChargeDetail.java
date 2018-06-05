
package com.biencloud.smarthome.web.wsclient.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for chargeDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="chargeDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="chargeData" type="{http://service.cxfservice.smarthome.biencloud.com/}chargeData" minOccurs="0"/>
 *         &lt;element name="chargeEndTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargeStartTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargeStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargeTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargeTypeResults" type="{http://service.cxfservice.smarthome.biencloud.com/}chargeTypeResult" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="infoId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="printSn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chargeDetail", propOrder = {
    "chargeData",
    "chargeEndTime",
    "chargeStartTime",
    "chargeStatus",
    "chargeTime",
    "chargeTypeResults",
    "id",
    "infoId",
    "printSn",
    "remark"
})
public class ChargeDetail
    extends BaseEntity
{

    protected ChargeData chargeData;
    protected String chargeEndTime;
    protected String chargeStartTime;
    protected String chargeStatus;
    protected String chargeTime;
    @XmlElement(nillable = true)
    protected List<ChargeTypeResult> chargeTypeResults;
    protected Long id;
    protected Long infoId;
    protected String printSn;
    protected String remark;

    /**
     * Gets the value of the chargeData property.
     * 
     * @return
     *     possible object is
     *     {@link ChargeData }
     *     
     */
    public ChargeData getChargeData() {
        return chargeData;
    }

    /**
     * Sets the value of the chargeData property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeData }
     *     
     */
    public void setChargeData(ChargeData value) {
        this.chargeData = value;
    }

    /**
     * Gets the value of the chargeEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeEndTime() {
        return chargeEndTime;
    }

    /**
     * Sets the value of the chargeEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeEndTime(String value) {
        this.chargeEndTime = value;
    }

    /**
     * Gets the value of the chargeStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeStartTime() {
        return chargeStartTime;
    }

    /**
     * Sets the value of the chargeStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeStartTime(String value) {
        this.chargeStartTime = value;
    }

    /**
     * Gets the value of the chargeStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeStatus() {
        return chargeStatus;
    }

    /**
     * Sets the value of the chargeStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeStatus(String value) {
        this.chargeStatus = value;
    }

    /**
     * Gets the value of the chargeTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeTime() {
        return chargeTime;
    }

    /**
     * Sets the value of the chargeTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeTime(String value) {
        this.chargeTime = value;
    }

    /**
     * Gets the value of the chargeTypeResults property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chargeTypeResults property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChargeTypeResults().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChargeTypeResult }
     * 
     * 
     */
    public List<ChargeTypeResult> getChargeTypeResults() {
        if (chargeTypeResults == null) {
            chargeTypeResults = new ArrayList<ChargeTypeResult>();
        }
        return this.chargeTypeResults;
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
     * Gets the value of the infoId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInfoId() {
        return infoId;
    }

    /**
     * Sets the value of the infoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInfoId(Long value) {
        this.infoId = value;
    }

    /**
     * Gets the value of the printSn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrintSn() {
        return printSn;
    }

    /**
     * Sets the value of the printSn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrintSn(String value) {
        this.printSn = value;
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

}
