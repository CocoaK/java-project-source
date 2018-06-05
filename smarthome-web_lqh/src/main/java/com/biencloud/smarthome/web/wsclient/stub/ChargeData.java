
package com.biencloud.smarthome.web.wsclient.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for chargeData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="chargeData">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="cellHouseholdInfo" type="{http://service.cxfservice.smarthome.biencloud.com/}cellHouseholdInfo" minOccurs="0"/>
 *         &lt;element name="chargeTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargeTypeResults" type="{http://service.cxfservice.smarthome.biencloud.com/}chargeTypeResult" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="createEndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="createStartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="housingDistrictInfo" type="{http://service.cxfservice.smarthome.biencloud.com/}housingDistrictInfo" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="isproductDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monetaryUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ownerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paUser" type="{http://service.cxfservice.smarthome.biencloud.com/}paUser" minOccurs="0"/>
 *         &lt;element name="regionBuildingInfo" type="{http://service.cxfservice.smarthome.biencloud.com/}regionBuildingInfo" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalMoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chargeData", propOrder = {
    "cellHouseholdInfo",
    "chargeTime",
    "chargeTypeResults",
    "createEndTime",
    "createStartTime",
    "createTime",
    "housingDistrictInfo",
    "id",
    "isproductDetail",
    "monetaryUnit",
    "ownerName",
    "paUser",
    "regionBuildingInfo",
    "remark",
    "totalMoney"
})
public class ChargeData
    extends BaseEntity
{

    protected CellHouseholdInfo cellHouseholdInfo;
    protected String chargeTime;
    @XmlElement(nillable = true)
    protected List<ChargeTypeResult> chargeTypeResults;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createEndTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createStartTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTime;
    protected HousingDistrictInfo housingDistrictInfo;
    protected Long id;
    protected String isproductDetail;
    protected String monetaryUnit;
    protected String ownerName;
    protected PaUser paUser;
    protected RegionBuildingInfo regionBuildingInfo;
    protected String remark;
    protected String totalMoney;

    /**
     * Gets the value of the cellHouseholdInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CellHouseholdInfo }
     *     
     */
    public CellHouseholdInfo getCellHouseholdInfo() {
        return cellHouseholdInfo;
    }

    /**
     * Sets the value of the cellHouseholdInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CellHouseholdInfo }
     *     
     */
    public void setCellHouseholdInfo(CellHouseholdInfo value) {
        this.cellHouseholdInfo = value;
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
     * Gets the value of the createEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateEndTime() {
        return createEndTime;
    }

    /**
     * Sets the value of the createEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateEndTime(XMLGregorianCalendar value) {
        this.createEndTime = value;
    }

    /**
     * Gets the value of the createStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateStartTime() {
        return createStartTime;
    }

    /**
     * Sets the value of the createStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateStartTime(XMLGregorianCalendar value) {
        this.createStartTime = value;
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
     * Gets the value of the isproductDetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsproductDetail() {
        return isproductDetail;
    }

    /**
     * Sets the value of the isproductDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsproductDetail(String value) {
        this.isproductDetail = value;
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
     * Gets the value of the ownerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Sets the value of the ownerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerName(String value) {
        this.ownerName = value;
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
     * Gets the value of the regionBuildingInfo property.
     * 
     * @return
     *     possible object is
     *     {@link RegionBuildingInfo }
     *     
     */
    public RegionBuildingInfo getRegionBuildingInfo() {
        return regionBuildingInfo;
    }

    /**
     * Sets the value of the regionBuildingInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegionBuildingInfo }
     *     
     */
    public void setRegionBuildingInfo(RegionBuildingInfo value) {
        this.regionBuildingInfo = value;
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
     * Gets the value of the totalMoney property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalMoney() {
        return totalMoney;
    }

    /**
     * Sets the value of the totalMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalMoney(String value) {
        this.totalMoney = value;
    }

}
