
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for hdfsTask complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="hdfsTask">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="basepath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="hdfsPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="kind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taskAddTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="taskFinishTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="taskStatus" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="uploadPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "hdfsTask", propOrder = {
    "basepath",
    "fileFormat",
    "fileName",
    "fileSize",
    "hdfsPath",
    "id",
    "kind",
    "taskAddTime",
    "taskFinishTime",
    "taskStatus",
    "uploadPath",
    "uploaderKind"
})
public class HdfsTask {

    protected String basepath;
    protected String fileFormat;
    protected String fileName;
    protected Long fileSize;
    protected String hdfsPath;
    protected Long id;
    protected String kind;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar taskAddTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar taskFinishTime;
    protected Integer taskStatus;
    protected String uploadPath;
    protected String uploaderKind;

    /**
     * Gets the value of the basepath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBasepath() {
        return basepath;
    }

    /**
     * Sets the value of the basepath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBasepath(String value) {
        this.basepath = value;
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
     * Gets the value of the taskAddTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTaskAddTime() {
        return taskAddTime;
    }

    /**
     * Sets the value of the taskAddTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTaskAddTime(XMLGregorianCalendar value) {
        this.taskAddTime = value;
    }

    /**
     * Gets the value of the taskFinishTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTaskFinishTime() {
        return taskFinishTime;
    }

    /**
     * Sets the value of the taskFinishTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTaskFinishTime(XMLGregorianCalendar value) {
        this.taskFinishTime = value;
    }

    /**
     * Gets the value of the taskStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTaskStatus() {
        return taskStatus;
    }

    /**
     * Sets the value of the taskStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTaskStatus(Integer value) {
        this.taskStatus = value;
    }

    /**
     * Gets the value of the uploadPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUploadPath() {
        return uploadPath;
    }

    /**
     * Sets the value of the uploadPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUploadPath(String value) {
        this.uploadPath = value;
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
