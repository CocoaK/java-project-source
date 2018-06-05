
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for localHdfs complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="localHdfs">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fileFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="hdfsPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="kind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="localPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transferWay" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uploaderKind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "localHdfs", propOrder = {
    "addTime",
    "fileFormat",
    "fileName",
    "fileSize",
    "hdfsPath",
    "id",
    "kind",
    "localPath",
    "transferWay",
    "uploaderKind"
})
public class LocalHdfs {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar addTime;
    protected String fileFormat;
    protected String fileName;
    protected Long fileSize;
    protected String hdfsPath;
    protected Long id;
    protected String kind;
    protected String localPath;
    protected String transferWay;
    protected String uploaderKind;

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
     * Gets the value of the fileFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileFormat() {
        return fileFormat;
    }

    /**
     * Sets the value of the fileFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileFormat(String value) {
        this.fileFormat = value;
    }

    /**
     * Gets the value of the fileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the value of the fileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    /**
     * Gets the value of the fileSize property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * Sets the value of the fileSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFileSize(Long value) {
        this.fileSize = value;
    }

    /**
     * Gets the value of the hdfsPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHdfsPath() {
        return hdfsPath;
    }

    /**
     * Sets the value of the hdfsPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHdfsPath(String value) {
        this.hdfsPath = value;
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
     * Gets the value of the kind property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKind() {
        return kind;
    }

    /**
     * Sets the value of the kind property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKind(String value) {
        this.kind = value;
    }

    /**
     * Gets the value of the localPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalPath() {
        return localPath;
    }

    /**
     * Sets the value of the localPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalPath(String value) {
        this.localPath = value;
    }

    /**
     * Gets the value of the transferWay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransferWay() {
        return transferWay;
    }

    /**
     * Sets the value of the transferWay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransferWay(String value) {
        this.transferWay = value;
    }

    /**
     * Gets the value of the uploaderKind property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUploaderKind() {
        return uploaderKind;
    }

    /**
     * Sets the value of the uploaderKind property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUploaderKind(String value) {
        this.uploaderKind = value;
    }

}
