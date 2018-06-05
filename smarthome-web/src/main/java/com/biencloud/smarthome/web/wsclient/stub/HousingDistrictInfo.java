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
 * <p>
 * Java class for housingDistrictInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="housingDistrictInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="constructionArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="createUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="developer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="district" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="floorPlan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="housingDistrictRegionInfos" type="{http://service.cxfservice.smarthome.biencloud.com/}housingDistrictRegionInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="position" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="propertyCompanyAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="propertyCompanyInfo" type="{http://service.cxfservice.smarthome.biencloud.com/}propertyCompanyInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "housingDistrictInfo", propOrder = { "code",
		"constructionArea", "createTime", "createUserId", "developer",
		"district", "floorPlan", "groupId", "housingDistrictRegionInfos", "id",
		"name", "position", "propertyCompanyAddress", "propertyCompanyInfo" })
public class HousingDistrictInfo extends BaseEntity {

	protected String code;
	protected String constructionArea;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar createTime;
	protected String createUserId;
	protected String developer;
	protected String district;
	protected String floorPlan;
	protected String groupId;
	@XmlElement(nillable = true)
	protected List<HousingDistrictRegionInfo> housingDistrictRegionInfos;
	protected String id;
	protected String name;
	protected String position;
	protected String propertyCompanyAddress;
	protected PropertyCompanyInfo propertyCompanyInfo;

	/**
	 * Gets the value of the code property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the value of the code property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCode(String value) {
		this.code = value;
	}

	/**
	 * Gets the value of the constructionArea property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConstructionArea() {
		return constructionArea;
	}

	/**
	 * Sets the value of the constructionArea property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConstructionArea(String value) {
		this.constructionArea = value;
	}

	/**
	 * Gets the value of the createTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getCreateTime() {
		return createTime;
	}

	/**
	 * Sets the value of the createTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setCreateTime(XMLGregorianCalendar value) {
		this.createTime = value;
	}

	/**
	 * Gets the value of the createUserId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * Sets the value of the createUserId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreateUserId(String value) {
		this.createUserId = value;
	}

	/**
	 * Gets the value of the developer property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDeveloper() {
		return developer;
	}

	/**
	 * Sets the value of the developer property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDeveloper(String value) {
		this.developer = value;
	}

	/**
	 * Gets the value of the district property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * Sets the value of the district property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDistrict(String value) {
		this.district = value;
	}

	/**
	 * Gets the value of the floorPlan property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFloorPlan() {
		return floorPlan;
	}

	/**
	 * Sets the value of the floorPlan property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFloorPlan(String value) {
		this.floorPlan = value;
	}

	/**
	 * Gets the value of the groupId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * Sets the value of the groupId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGroupId(String value) {
		this.groupId = value;
	}

	/**
	 * Gets the value of the housingDistrictRegionInfos property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the housingDistrictRegionInfos property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getHousingDistrictRegionInfos().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link HousingDistrictRegionInfo }
	 * 
	 * 
	 */
	public List<HousingDistrictRegionInfo> getHousingDistrictRegionInfos() {
		if (housingDistrictRegionInfos == null) {
			housingDistrictRegionInfos = new ArrayList<HousingDistrictRegionInfo>();
		}
		return this.housingDistrictRegionInfos;
	}

	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setId(String value) {
		this.id = value;
	}

	/**
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Gets the value of the position property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Sets the value of the position property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPosition(String value) {
		this.position = value;
	}

	/**
	 * Gets the value of the propertyCompanyAddress property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPropertyCompanyAddress() {
		return propertyCompanyAddress;
	}

	/**
	 * Sets the value of the propertyCompanyAddress property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPropertyCompanyAddress(String value) {
		this.propertyCompanyAddress = value;
	}

	/**
	 * Gets the value of the propertyCompanyInfo property.
	 * 
	 * @return possible object is {@link PropertyCompanyInfo }
	 * 
	 */
	public PropertyCompanyInfo getPropertyCompanyInfo() {
		return propertyCompanyInfo;
	}

	/**
	 * Sets the value of the propertyCompanyInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link PropertyCompanyInfo }
	 * 
	 */
	public void setPropertyCompanyInfo(PropertyCompanyInfo value) {
		this.propertyCompanyInfo = value;
	}

}
