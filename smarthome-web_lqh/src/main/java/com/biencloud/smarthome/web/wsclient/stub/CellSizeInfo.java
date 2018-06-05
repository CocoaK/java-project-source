
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
 * <p>Java class for cellSizeInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cellSizeInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="createUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hall" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="plan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="room" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rooms" type="{http://service.cxfservice.smarthome.biencloud.com/}room" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="THmBuildingCellInfo" type="{http://service.cxfservice.smarthome.biencloud.com/}buildingCellInfo" minOccurs="0"/>
 *         &lt;element name="THmCellHouseholdInfos" type="{http://service.cxfservice.smarthome.biencloud.com/}cellHouseholdInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cellSizeInfo", propOrder = {
    "createTime",
    "createUserId",
    "customFlag",
    "hall",
    "id",
    "plan",
    "room",
    "rooms",
    "tHmBuildingCellInfo",
    "tHmCellHouseholdInfos"
})
public class CellSizeInfo
    extends BaseEntity
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTime;
    protected String createUserId;
    protected String customFlag;
    protected String hall;
    protected String id;
    protected String plan;
    protected String room;
    @XmlElement(nillable = true)
    protected List<Room> rooms;
    @XmlElement(name = "THmBuildingCellInfo")
    protected BuildingCellInfo tHmBuildingCellInfo;
    @XmlElement(name = "THmCellHouseholdInfos", nillable = true)
    protected List<CellHouseholdInfo> tHmCellHouseholdInfos;

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
     * Gets the value of the customFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFlag() {
        return customFlag;
    }

    /**
     * Sets the value of the customFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFlag(String value) {
        this.customFlag = value;
    }

    /**
     * Gets the value of the hall property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHall() {
        return hall;
    }

    /**
     * Sets the value of the hall property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHall(String value) {
        this.hall = value;
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
     * Gets the value of the plan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlan() {
        return plan;
    }

    /**
     * Sets the value of the plan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlan(String value) {
        this.plan = value;
    }

    /**
     * Gets the value of the room property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoom() {
        return room;
    }

    /**
     * Sets the value of the room property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoom(String value) {
        this.room = value;
    }

    /**
     * Gets the value of the rooms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rooms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRooms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Room }
     * 
     * 
     */
    public List<Room> getRooms() {
        if (rooms == null) {
            rooms = new ArrayList<Room>();
        }
        return this.rooms;
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
     * Gets the value of the tHmCellHouseholdInfos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tHmCellHouseholdInfos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTHmCellHouseholdInfos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CellHouseholdInfo }
     * 
     * 
     */
    public List<CellHouseholdInfo> getTHmCellHouseholdInfos() {
        if (tHmCellHouseholdInfos == null) {
            tHmCellHouseholdInfos = new ArrayList<CellHouseholdInfo>();
        }
        return this.tHmCellHouseholdInfos;
    }

}
