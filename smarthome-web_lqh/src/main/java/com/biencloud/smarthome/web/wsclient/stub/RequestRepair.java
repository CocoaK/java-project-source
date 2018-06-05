
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for requestRepair complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="requestRepair">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="cellHouseholdInfo" type="{http://service.cxfservice.smarthome.biencloud.com/}cellHouseholdInfo" minOccurs="0"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="districtId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="isexcuteNoSubmit" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ownerUser" type="{http://service.cxfservice.smarthome.biencloud.com/}ownerUser" minOccurs="0"/>
 *         &lt;element name="paUser" type="{http://service.cxfservice.smarthome.biencloud.com/}paUser" minOccurs="0"/>
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="repairEndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="repairOpinion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="repairStartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="repairTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="requestEndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="requestStartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="requestTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestRepair", propOrder = {
    "cellHouseholdInfo",
    "content",
    "districtId",
    "id",
    "isexcuteNoSubmit",
    "ownerUser",
    "paUser",
    "phone",
    "remark",
    "repairEndTime",
    "repairOpinion",
    "repairStartTime",
    "repairTime",
    "requestEndTime",
    "requestStartTime",
    "requestTime",
    "status",
    "title"
})
public class RequestRepair
    extends BaseEntity
{

    protected CellHouseholdInfo cellHouseholdInfo;
    protected String content;
    protected Long districtId;
    protected Long id;
    protected boolean isexcuteNoSubmit;
    protected OwnerUser ownerUser;
    protected PaUser paUser;
    protected String phone;
    protected String remark;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar repairEndTime;
    protected String repairOpinion;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar repairStartTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar repairTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar requestEndTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar requestStartTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar requestTime;
    protected String status;
    protected String title;

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
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the districtId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDistrictId() {
        return districtId;
    }

    /**
     * Sets the value of the districtId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDistrictId(Long value) {
        this.districtId = value;
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
     * Gets the value of the isexcuteNoSubmit property.
     * 
     */
    public boolean isIsexcuteNoSubmit() {
        return isexcuteNoSubmit;
    }

    /**
     * Sets the value of the isexcuteNoSubmit property.
     * 
     */
    public void setIsexcuteNoSubmit(boolean value) {
        this.isexcuteNoSubmit = value;
    }

    /**
     * Gets the value of the ownerUser property.
     * 
     * @return
     *     possible object is
     *     {@link OwnerUser }
     *     
     */
    public OwnerUser getOwnerUser() {
        return ownerUser;
    }

    /**
     * Sets the value of the ownerUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link OwnerUser }
     *     
     */
    public void setOwnerUser(OwnerUser value) {
        this.ownerUser = value;
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
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
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
     * Gets the value of the repairEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRepairEndTime() {
        return repairEndTime;
    }

    /**
     * Sets the value of the repairEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRepairEndTime(XMLGregorianCalendar value) {
        this.repairEndTime = value;
    }

    /**
     * Gets the value of the repairOpinion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepairOpinion() {
        return repairOpinion;
    }

    /**
     * Sets the value of the repairOpinion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepairOpinion(String value) {
        this.repairOpinion = value;
    }

    /**
     * Gets the value of the repairStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRepairStartTime() {
        return repairStartTime;
    }

    /**
     * Sets the value of the repairStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRepairStartTime(XMLGregorianCalendar value) {
        this.repairStartTime = value;
    }

    /**
     * Gets the value of the repairTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRepairTime() {
        return repairTime;
    }

    /**
     * Sets the value of the repairTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRepairTime(XMLGregorianCalendar value) {
        this.repairTime = value;
    }

    /**
     * Gets the value of the requestEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRequestEndTime() {
        return requestEndTime;
    }

    /**
     * Sets the value of the requestEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequestEndTime(XMLGregorianCalendar value) {
        this.requestEndTime = value;
    }

    /**
     * Gets the value of the requestStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRequestStartTime() {
        return requestStartTime;
    }

    /**
     * Sets the value of the requestStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequestStartTime(XMLGregorianCalendar value) {
        this.requestStartTime = value;
    }

    /**
     * Gets the value of the requestTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRequestTime() {
        return requestTime;
    }

    /**
     * Sets the value of the requestTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequestTime(XMLGregorianCalendar value) {
        this.requestTime = value;
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
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

}
