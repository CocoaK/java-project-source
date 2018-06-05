
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
 * <p>Java class for infoSend complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="infoSend">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="areaId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="infoReceivers" type="{http://service.cxfservice.smarthome.biencloud.com/}infoReceiver" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reply" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sendEndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="sendMode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="sendOb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sendStartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="sendTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="sendUserId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="sendUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="timimgTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="timimgTimeEndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="timimgTimeStartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="updateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "infoSend", propOrder = {
    "areaId",
    "content",
    "createTime",
    "id",
    "infoReceivers",
    "remark",
    "reply",
    "sendEndTime",
    "sendMode",
    "sendOb",
    "sendStartTime",
    "sendTime",
    "sendUserId",
    "sendUserName",
    "status",
    "timimgTime",
    "timimgTimeEndTime",
    "timimgTimeStartTime",
    "title",
    "type",
    "updateTime"
})
public class InfoSend
    extends BaseEntity
{

    protected Long areaId;
    protected String content;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTime;
    protected Long id;
    @XmlElement(nillable = true)
    protected List<InfoReceiver> infoReceivers;
    protected String remark;
    protected String reply;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sendEndTime;
    protected Integer sendMode;
    protected String sendOb;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sendStartTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sendTime;
    protected Long sendUserId;
    protected String sendUserName;
    protected Integer status;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timimgTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timimgTimeEndTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timimgTimeStartTime;
    protected String title;
    protected Integer type;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTime;

    /**
     * Gets the value of the areaId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAreaId() {
        return areaId;
    }

    /**
     * Sets the value of the areaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAreaId(Long value) {
        this.areaId = value;
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
     * Gets the value of the infoReceivers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the infoReceivers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInfoReceivers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InfoReceiver }
     * 
     * 
     */
    public List<InfoReceiver> getInfoReceivers() {
        if (infoReceivers == null) {
            infoReceivers = new ArrayList<InfoReceiver>();
        }
        return this.infoReceivers;
    }
    
    public void setInfoReceivers(List<InfoReceiver> value) {
        this.infoReceivers = value;
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
     * Gets the value of the reply property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReply() {
        return reply;
    }

    /**
     * Sets the value of the reply property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReply(String value) {
        this.reply = value;
    }

    /**
     * Gets the value of the sendEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSendEndTime() {
        return sendEndTime;
    }

    /**
     * Sets the value of the sendEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSendEndTime(XMLGregorianCalendar value) {
        this.sendEndTime = value;
    }

    /**
     * Gets the value of the sendMode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSendMode() {
        return sendMode;
    }

    /**
     * Sets the value of the sendMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSendMode(Integer value) {
        this.sendMode = value;
    }

    /**
     * Gets the value of the sendOb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendOb() {
        return sendOb;
    }

    /**
     * Sets the value of the sendOb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendOb(String value) {
        this.sendOb = value;
    }

    /**
     * Gets the value of the sendStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSendStartTime() {
        return sendStartTime;
    }

    /**
     * Sets the value of the sendStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSendStartTime(XMLGregorianCalendar value) {
        this.sendStartTime = value;
    }

    /**
     * Gets the value of the sendTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSendTime() {
        return sendTime;
    }

    /**
     * Sets the value of the sendTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSendTime(XMLGregorianCalendar value) {
        this.sendTime = value;
    }

    /**
     * Gets the value of the sendUserId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSendUserId() {
        return sendUserId;
    }

    /**
     * Sets the value of the sendUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSendUserId(Long value) {
        this.sendUserId = value;
    }

    /**
     * Gets the value of the sendUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendUserName() {
        return sendUserName;
    }

    /**
     * Sets the value of the sendUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendUserName(String value) {
        this.sendUserName = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

    /**
     * Gets the value of the timimgTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimimgTime() {
        return timimgTime;
    }

    /**
     * Sets the value of the timimgTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimimgTime(XMLGregorianCalendar value) {
        this.timimgTime = value;
    }

    /**
     * Gets the value of the timimgTimeEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimimgTimeEndTime() {
        return timimgTimeEndTime;
    }

    /**
     * Sets the value of the timimgTimeEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimimgTimeEndTime(XMLGregorianCalendar value) {
        this.timimgTimeEndTime = value;
    }

    /**
     * Gets the value of the timimgTimeStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimimgTimeStartTime() {
        return timimgTimeStartTime;
    }

    /**
     * Sets the value of the timimgTimeStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimimgTimeStartTime(XMLGregorianCalendar value) {
        this.timimgTimeStartTime = value;
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

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setType(Integer value) {
        this.type = value;
    }

    /**
     * Gets the value of the updateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateTime() {
        return updateTime;
    }

    /**
     * Sets the value of the updateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateTime(XMLGregorianCalendar value) {
        this.updateTime = value;
    }

}
