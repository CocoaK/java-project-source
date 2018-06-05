package com.biencloud.smarthome.web.wsclient.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for page complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="page">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="districtId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="districts" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pageDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updateId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "page", propOrder = { "districtId", "districts", "id", "name",
		"pageDesc", "updateId" })
public class Page extends BaseEntity {

	protected String districtId;
	@XmlElement(nillable = true)
	protected List<String> districts;
	protected String id;
	protected String name;
	protected String pageDesc;
	protected String updateId;

	/**
	 * Gets the value of the districtId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDistrictId() {
		return districtId;
	}

	/**
	 * Sets the value of the districtId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDistrictId(String value) {
		this.districtId = value;
	}

	/**
	 * Gets the value of the districts property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the districts property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getDistricts().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 * 
	 * 
	 */
	public List<String> getDistricts() {
		if (districts == null) {
			districts = new ArrayList<String>();
		}
		return this.districts;
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
	 * Gets the value of the pageDesc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPageDesc() {
		return pageDesc;
	}

	/**
	 * Sets the value of the pageDesc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPageDesc(String value) {
		this.pageDesc = value;
	}

	/**
	 * Gets the value of the updateId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * Sets the value of the updateId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUpdateId(String value) {
		this.updateId = value;
	}

}
