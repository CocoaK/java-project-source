
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for callRecord complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="callRecord">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="audioLocalPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="audioNetPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="beginTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="callId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="callTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="callType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="caller" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="device" type="{http://service.cxfservice.smarthome.biencloud.com/}device" minOccurs="0"/>
 *         &lt;element name="deviceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pictureLocalPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pictureNetPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="talkTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="videoLocalPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="videoNetPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "callRecord", propOrder = {
    "audioLocalPath",
    "audioNetPath",
    "beginTime",
    "callId",
    "callTime",
    "callType",
    "caller",
    "device",
    "deviceCode",
    "endTime",
    "id",
    "pictureLocalPath",
    "pictureNetPath",
    "talkTime",
    "videoLocalPath",
    "videoNetPath"
})
public class CallRecord
    extends BaseEntity
{

    protected String audioLocalPath;
    protected String audioNetPath;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar beginTime;
    protected String callId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar callTime;
    protected String callType;
    protected String caller;
    protected Device device;
    protected String deviceCode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endTime;
    protected String id;
    protected String pictureLocalPath;
    protected String pictureNetPath;
    protected long talkTime;
    protected String videoLocalPath;
    protected String videoNetPath;

    /**
     * Gets the value of the audioLocalPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAudioLocalPath() {
        return audioLocalPath;
    }

    /**
     * Sets the value of the audioLocalPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAudioLocalPath(String value) {
        this.audioLocalPath = value;
    }

    /**
     * Gets the value of the audioNetPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAudioNetPath() {
        return audioNetPath;
    }

    /**
     * Sets the value of the audioNetPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAudioNetPath(String value) {
        this.audioNetPath = value;
    }

    /**
     * Gets the value of the beginTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBeginTime() {
        return beginTime;
    }

    /**
     * Sets the value of the beginTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBeginTime(XMLGregorianCalendar value) {
        this.beginTime = value;
    }

    /**
     * Gets the value of the callId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallId() {
        return callId;
    }

    /**
     * Sets the value of the callId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallId(String value) {
        this.callId = value;
    }

    /**
     * Gets the value of the callTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCallTime() {
        return callTime;
    }

    /**
     * Sets the value of the callTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCallTime(XMLGregorianCalendar value) {
        this.callTime = value;
    }

    /**
     * Gets the value of the callType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallType() {
        return callType;
    }

    /**
     * Sets the value of the callType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallType(String value) {
        this.callType = value;
    }

    /**
     * Gets the value of the caller property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaller() {
        return caller;
    }

    /**
     * Sets the value of the caller property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaller(String value) {
        this.caller = value;
    }

    /**
     * Gets the value of the device property.
     * 
     * @return
     *     possible object is
     *     {@link Device }
     *     
     */
    public Device getDevice() {
        return device;
    }

    /**
     * Sets the value of the device property.
     * 
     * @param value
     *     allowed object is
     *     {@link Device }
     *     
     */
    public void setDevice(Device value) {
        this.device = value;
    }

    /**
     * Gets the value of the deviceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceCode() {
        return deviceCode;
    }

    /**
     * Sets the value of the deviceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceCode(String value) {
        this.deviceCode = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndTime(XMLGregorianCalendar value) {
        this.endTime = value;
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
     * Gets the value of the pictureLocalPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPictureLocalPath() {
        return pictureLocalPath;
    }

    /**
     * Sets the value of the pictureLocalPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPictureLocalPath(String value) {
        this.pictureLocalPath = value;
    }

    /**
     * Gets the value of the pictureNetPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPictureNetPath() {
        return pictureNetPath;
    }

    /**
     * Sets the value of the pictureNetPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPictureNetPath(String value) {
        this.pictureNetPath = value;
    }

    /**
     * Gets the value of the talkTime property.
     * 
     */
    public long getTalkTime() {
        return talkTime;
    }

    /**
     * Sets the value of the talkTime property.
     * 
     */
    public void setTalkTime(long value) {
        this.talkTime = value;
    }

    /**
     * Gets the value of the videoLocalPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideoLocalPath() {
        return videoLocalPath;
    }

    /**
     * Sets the value of the videoLocalPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideoLocalPath(String value) {
        this.videoLocalPath = value;
    }

    /**
     * Gets the value of the videoNetPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideoNetPath() {
        return videoNetPath;
    }

    /**
     * Sets the value of the videoNetPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideoNetPath(String value) {
        this.videoNetPath = value;
    }

}
