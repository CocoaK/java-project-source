
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for scene complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="scene">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="deviceNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="isUse" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="mode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roomNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sceneId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sceneKind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sceneName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "scene", propOrder = {
    "addTime",
    "deviceNo",
    "id",
    "isUse",
    "mode",
    "roomNo",
    "sceneId",
    "sceneKind",
    "sceneName"
})
public class Scene {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar addTime;
    protected String deviceNo;
    protected Long id;
    protected Integer isUse;
    protected String mode;
    protected String roomNo;
    protected String sceneId;
    protected String sceneKind;
    protected String sceneName;

    /**
     * Gets the value of the addTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAddTime() {
        return addTime;
    }

    /**
     * Sets the value of the addTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAddTime(XMLGregorianCalendar value) {
        this.addTime = value;
    }

    /**
     * Gets the value of the deviceNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceNo() {
        return deviceNo;
    }

    /**
     * Sets the value of the deviceNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceNo(String value) {
        this.deviceNo = value;
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
     * Gets the value of the isUse property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIsUse() {
        return isUse;
    }

    /**
     * Sets the value of the isUse property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIsUse(Integer value) {
        this.isUse = value;
    }

    /**
     * Gets the value of the mode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMode() {
        return mode;
    }

    /**
     * Sets the value of the mode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMode(String value) {
        this.mode = value;
    }

    /**
     * Gets the value of the roomNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomNo() {
        return roomNo;
    }

    /**
     * Sets the value of the roomNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomNo(String value) {
        this.roomNo = value;
    }

    /**
     * Gets the value of the sceneId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSceneId() {
        return sceneId;
    }

    /**
     * Sets the value of the sceneId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSceneId(String value) {
        this.sceneId = value;
    }

    /**
     * Gets the value of the sceneKind property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSceneKind() {
        return sceneKind;
    }

    /**
     * Sets the value of the sceneKind property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSceneKind(String value) {
        this.sceneKind = value;
    }

    /**
     * Gets the value of the sceneName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSceneName() {
        return sceneName;
    }

    /**
     * Sets the value of the sceneName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSceneName(String value) {
        this.sceneName = value;
    }

}
