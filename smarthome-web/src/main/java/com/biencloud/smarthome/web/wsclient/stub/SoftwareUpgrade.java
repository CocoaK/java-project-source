
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
 * <p>Java class for softwareUpgrade complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="softwareUpgrade">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="applyedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="applyedUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="approvedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="approvedUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="publishedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="publishedUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="savePath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="softwareCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="softwareDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="softwareId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="softwareName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="suTargets" type="{http://service.cxfservice.smarthome.biencloud.com/}softwareUpgradeTarget" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="upgradedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="upgradedUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="versionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "softwareUpgrade", propOrder = {
    "applyedTime",
    "applyedUser",
    "approvedTime",
    "approvedUser",
    "publishedTime",
    "publishedUser",
    "savePath",
    "size",
    "softwareCode",
    "softwareDesc",
    "softwareId",
    "softwareName",
    "status",
    "suTargets",
    "upgradedTime",
    "upgradedUser",
    "version",
    "versionName"
})
public class SoftwareUpgrade
    extends BaseEntity
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar applyedTime;
    protected String applyedUser;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar approvedTime;
    protected String approvedUser;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar publishedTime;
    protected String publishedUser;
    protected String savePath;
    protected String size;
    protected String softwareCode;
    protected String softwareDesc;
    protected String softwareId;
    protected String softwareName;
    protected String status;
    @XmlElement(nillable = true)
    protected List<SoftwareUpgradeTarget> suTargets;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar upgradedTime;
    protected String upgradedUser;
    protected int version;
    protected String versionName;

    /**
     * Gets the value of the applyedTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getApplyedTime() {
        return applyedTime;
    }

    /**
     * Sets the value of the applyedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setApplyedTime(XMLGregorianCalendar value) {
        this.applyedTime = value;
    }

    /**
     * Gets the value of the applyedUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplyedUser() {
        return applyedUser;
    }

    /**
     * Sets the value of the applyedUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplyedUser(String value) {
        this.applyedUser = value;
    }

    /**
     * Gets the value of the approvedTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getApprovedTime() {
        return approvedTime;
    }

    /**
     * Sets the value of the approvedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setApprovedTime(XMLGregorianCalendar value) {
        this.approvedTime = value;
    }

    /**
     * Gets the value of the approvedUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApprovedUser() {
        return approvedUser;
    }

    /**
     * Sets the value of the approvedUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApprovedUser(String value) {
        this.approvedUser = value;
    }

    /**
     * Gets the value of the publishedTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPublishedTime() {
        return publishedTime;
    }

    /**
     * Sets the value of the publishedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPublishedTime(XMLGregorianCalendar value) {
        this.publishedTime = value;
    }

    /**
     * Gets the value of the publishedUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublishedUser() {
        return publishedUser;
    }

    /**
     * Sets the value of the publishedUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublishedUser(String value) {
        this.publishedUser = value;
    }

    /**
     * Gets the value of the savePath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSavePath() {
        return savePath;
    }

    /**
     * Sets the value of the savePath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSavePath(String value) {
        this.savePath = value;
    }

    /**
     * Gets the value of the size property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSize(String value) {
        this.size = value;
    }

    /**
     * Gets the value of the softwareCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoftwareCode() {
        return softwareCode;
    }

    /**
     * Sets the value of the softwareCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoftwareCode(String value) {
        this.softwareCode = value;
    }

    /**
     * Gets the value of the softwareDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoftwareDesc() {
        return softwareDesc;
    }

    /**
     * Sets the value of the softwareDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoftwareDesc(String value) {
        this.softwareDesc = value;
    }

    /**
     * Gets the value of the softwareId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoftwareId() {
        return softwareId;
    }

    /**
     * Sets the value of the softwareId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoftwareId(String value) {
        this.softwareId = value;
    }

    /**
     * Gets the value of the softwareName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoftwareName() {
        return softwareName;
    }

    /**
     * Sets the value of the softwareName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoftwareName(String value) {
        this.softwareName = value;
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
     * Gets the value of the suTargets property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the suTargets property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuTargets().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SoftwareUpgradeTarget }
     * 
     * 
     */
    public List<SoftwareUpgradeTarget> getSuTargets() {
        if (suTargets == null) {
            suTargets = new ArrayList<SoftwareUpgradeTarget>();
        }
        return this.suTargets;
    }

    /**
     * Gets the value of the upgradedTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpgradedTime() {
        return upgradedTime;
    }

    /**
     * Sets the value of the upgradedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpgradedTime(XMLGregorianCalendar value) {
        this.upgradedTime = value;
    }

    /**
     * Gets the value of the upgradedUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpgradedUser() {
        return upgradedUser;
    }

    /**
     * Sets the value of the upgradedUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpgradedUser(String value) {
        this.upgradedUser = value;
    }

    /**
     * Gets the value of the version property.
     * 
     */
    public int getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     */
    public void setVersion(int value) {
        this.version = value;
    }

    /**
     * Gets the value of the versionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * Sets the value of the versionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersionName(String value) {
        this.versionName = value;
    }

}
