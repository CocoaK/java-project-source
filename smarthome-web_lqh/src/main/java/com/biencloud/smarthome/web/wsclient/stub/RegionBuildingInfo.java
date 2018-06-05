
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
 * <p>Java class for regionBuildingInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="regionBuildingInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="constructionArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coordinate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coverArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="createUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="THmBuildingCellInfos" type="{http://service.cxfservice.smarthome.biencloud.com/}buildingCellInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="THmHousingDistrictRegionInfo" type="{http://service.cxfservice.smarthome.biencloud.com/}housingDistrictRegionInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "regionBuildingInfo", propOrder = {
    "code",
    "constructionArea",
    "coordinate",
    "coverArea",
    "createTime",
    "createUserId",
    "id",
    "name",
    "tHmBuildingCellInfos",
    "tHmHousingDistrictRegionInfo"
})
public class RegionBuildingInfo
    extends BaseEntity
{

    protected String code;
    protected String constructionArea;
    protected String coordinate;
    protected String coverArea;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTime;
    protected String createUserId;
    protected String id;
    protected String name;
    @XmlElement(name = "THmBuildingCellInfos", nillable = true)
    protected List<BuildingCellInfo> tHmBuildingCellInfos;
    @XmlElement(name = "THmHousingDistrictRegionInfo")
    protected HousingDistrictRegionInfo tHmHousingDistrictRegionInfo;

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
     * Gets the value of the constructionArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConstructionArea() {
        return constructionArea;
    }

    /**
     * Sets the value of the constructionArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConstructionArea(String value) {
        this.constructionArea = value;
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
     * Gets the value of the coverArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoverArea() {
        return coverArea;
    }

    /**
     * Sets the value of the coverArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoverArea(String value) {
        this.coverArea = value;
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
     * Gets the value of the tHmBuildingCellInfos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tHmBuildingCellInfos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTHmBuildingCellInfos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BuildingCellInfo }
     * 
     * 
     */
    public List<BuildingCellInfo> getTHmBuildingCellInfos() {
        if (tHmBuildingCellInfos == null) {
            tHmBuildingCellInfos = new ArrayList<BuildingCellInfo>();
        }
        return this.tHmBuildingCellInfos;
    }

    /**
     * Gets the value of the tHmHousingDistrictRegionInfo property.
     * 
     * @return
     *     possible object is
     *     {@link HousingDistrictRegionInfo }
     *     
     */
    public HousingDistrictRegionInfo getTHmHousingDistrictRegionInfo() {
        return tHmHousingDistrictRegionInfo;
    }

    /**
     * Sets the value of the tHmHousingDistrictRegionInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link HousingDistrictRegionInfo }
     *     
     */
    public void setTHmHousingDistrictRegionInfo(HousingDistrictRegionInfo value) {
        this.tHmHousingDistrictRegionInfo = value;
    }

}
