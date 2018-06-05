
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
 * <p>Java class for housingDistrictRegionInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="housingDistrictRegionInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coordinate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="createUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="housingDistrictInfo" type="{http://service.cxfservice.smarthome.biencloud.com/}housingDistrictInfo" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="THmRegionBuildingInfos" type="{http://service.cxfservice.smarthome.biencloud.com/}regionBuildingInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "housingDistrictRegionInfo", propOrder = {
    "code",
    "coordinate",
    "createTime",
    "createUserId",
    "housingDistrictInfo",
    "id",
    "name",
    "tHmRegionBuildingInfos"
})
public class HousingDistrictRegionInfo
    extends BaseEntity
{

    protected String code;
    protected String coordinate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTime;
    protected String createUserId;
    protected HousingDistrictInfo housingDistrictInfo;
    protected String id;
    protected String name;
    @XmlElement(name = "THmRegionBuildingInfos", nillable = true)
    protected List<RegionBuildingInfo> tHmRegionBuildingInfos;

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
     * Gets the value of the tHmRegionBuildingInfos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tHmRegionBuildingInfos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTHmRegionBuildingInfos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RegionBuildingInfo }
     * 
     * 
     */
    public List<RegionBuildingInfo> getTHmRegionBuildingInfos() {
        if (tHmRegionBuildingInfos == null) {
            tHmRegionBuildingInfos = new ArrayList<RegionBuildingInfo>();
        }
        return this.tHmRegionBuildingInfos;
    }

}
