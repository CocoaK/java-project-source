
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
 * <p>Java class for cellHouseholdInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cellHouseholdInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargeTypes" type="{http://service.cxfservice.smarthome.biencloud.com/}chargeType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="checkInDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coordinate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="createUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="housingStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="owner" type="{http://service.cxfservice.smarthome.biencloud.com/}ownerUser" minOccurs="0"/>
 *         &lt;element name="THmBuildingCellInfo" type="{http://service.cxfservice.smarthome.biencloud.com/}buildingCellInfo" minOccurs="0"/>
 *         &lt;element name="THmCellSizeInfo" type="{http://service.cxfservice.smarthome.biencloud.com/}cellSizeInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cellHouseholdInfo", propOrder = {
    "area",
    "chargeTypes",
    "checkInDate",
    "code",
    "coordinate",
    "createTime",
    "createUserId",
    "housingStatus",
    "id",
    "name",
    "owner",
    "tHmBuildingCellInfo",
    "tHmCellSizeInfo"
})
public class CellHouseholdInfo
    extends BaseEntity
{

    protected String area;
    @XmlElement(nillable = true)
    protected List<ChargeType> chargeTypes;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar checkInDate;
    protected String code;
    protected String coordinate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTime;
    protected String createUserId;
    protected String housingStatus;
    protected String id;
    protected String name;
    protected OwnerUser owner;
    @XmlElement(name = "THmBuildingCellInfo")
    protected BuildingCellInfo tHmBuildingCellInfo;
    @XmlElement(name = "THmCellSizeInfo")
    protected CellSizeInfo tHmCellSizeInfo;

    /**
     * Gets the value of the area property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArea(String value) {
        this.area = value;
    }

    /**
     * Gets the value of the chargeTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chargeTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChargeTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChargeType }
     * 
     * 
     */
    public List<ChargeType> getChargeTypes() {
        if (chargeTypes == null) {
            chargeTypes = new ArrayList<ChargeType>();
        }
        return this.chargeTypes;
    }

    /**
     * Gets the value of the checkInDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCheckInDate() {
        return checkInDate;
    }

    /**
     * Sets the value of the checkInDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCheckInDate(XMLGregorianCalendar value) {
        this.checkInDate = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the coordinate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoordinate() {
        return coordinate;
    }

    /**
     * Sets the value of the coordinate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoordinate(String value) {
        this.coordinate = value;
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
     * Gets the value of the createUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * Sets the value of the createUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateUserId(String value) {
        this.createUserId = value;
    }

    /**
     * Gets the value of the housingStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHousingStatus() {
        return housingStatus;
    }

    /**
     * Sets the value of the housingStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHousingStatus(String value) {
        this.housingStatus = value;
    }

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
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link OwnerUser }
     *     
     */
    public OwnerUser getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link OwnerUser }
     *     
     */
    public void setOwner(OwnerUser value) {
        this.owner = value;
    }

    /**
     * Gets the value of the tHmBuildingCellInfo property.
     * 
     * @return
     *     possible object is
     *     {@link BuildingCellInfo }
     *     
     */
    public BuildingCellInfo getTHmBuildingCellInfo() {
        return tHmBuildingCellInfo;
    }

    /**
     * Sets the value of the tHmBuildingCellInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BuildingCellInfo }
     *     
     */
    public void setTHmBuildingCellInfo(BuildingCellInfo value) {
        this.tHmBuildingCellInfo = value;
    }

    /**
     * Gets the value of the tHmCellSizeInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CellSizeInfo }
     *     
     */
    public CellSizeInfo getTHmCellSizeInfo() {
        return tHmCellSizeInfo;
    }

    /**
     * Sets the value of the tHmCellSizeInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CellSizeInfo }
     *     
     */
    public void setTHmCellSizeInfo(CellSizeInfo value) {
        this.tHmCellSizeInfo = value;
    }

}
