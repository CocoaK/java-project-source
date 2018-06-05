
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
 * <p>Java class for gateCard complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="gateCard">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="cardNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="createdUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="districtId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gateCardId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gatePermissions" type="{http://service.cxfservice.smarthome.biencloud.com/}gatePermissions" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ownerIdCard" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ownerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updatedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="updatedUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gateCard", propOrder = {
    "cardNo",
    "createdTime",
    "createdUser",
    "districtId",
    "gateCardId",
    "gatePermissions",
    "ownerIdCard",
    "ownerName",
    "houseId",
    "status",
    "updatedTime",
    "updatedUser",
    "roomNo",
    "roomName"
})
public class GateCard
    extends BaseEntity
{

    protected String cardNo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String createdUser;
    protected String districtId;
    protected String gateCardId;
    @XmlElement(nillable = true)
    protected List<GatePermissions> gatePermissions;
    protected String ownerIdCard;
    protected String ownerName;
    protected String houseId;
    protected String status;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedTime;
    protected String updatedUser;
    protected String roomNo;
    protected String roomName;

    /**
     * Gets the value of the cardNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * Sets the value of the cardNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardNo(String value) {
        this.cardNo = value;
    }

    /**
     * Gets the value of the createdTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedTime() {
        return createdTime;
    }

    /**
     * Sets the value of the createdTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedTime(XMLGregorianCalendar value) {
        this.createdTime = value;
    }

    /**
     * Gets the value of the createdUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * Sets the value of the createdUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedUser(String value) {
        this.createdUser = value;
    }

    /**
     * Gets the value of the districtId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrictId() {
        return districtId;
    }

    /**
     * Sets the value of the districtId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrictId(String value) {
        this.districtId = value;
    }

    /**
     * Gets the value of the gateCardId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGateCardId() {
        return gateCardId;
    }

    /**
     * Sets the value of the gateCardId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGateCardId(String value) {
        this.gateCardId = value;
    }

    /**
     * Gets the value of the gatePermissions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gatePermissions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGatePermissions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GatePermissions }
     * 
     * 
     */
    public List<GatePermissions> getGatePermissions() {
        if (gatePermissions == null) {
            gatePermissions = new ArrayList<GatePermissions>();
        }
        return this.gatePermissions;
    }

    /**
     * Gets the value of the ownerIdCard property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerIdCard() {
        return ownerIdCard;
    }

    /**
     * Sets the value of the ownerIdCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerIdCard(String value) {
        this.ownerIdCard = value;
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
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the updatedTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdatedTime() {
        return updatedTime;
    }

    /**
     * Sets the value of the updatedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdatedTime(XMLGregorianCalendar value) {
        this.updatedTime = value;
    }

    /**
     * Gets the value of the updatedUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdatedUser() {
        return updatedUser;
    }

    /**
     * Sets the value of the updatedUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdatedUser(String value) {
        this.updatedUser = value;
    }

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String value) {
		this.houseId = value;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String value) {
		this.roomNo = value;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String value) {
		this.roomName = value;
	}

}
